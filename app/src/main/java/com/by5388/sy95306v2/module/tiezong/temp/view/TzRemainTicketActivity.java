package com.by5388.sy95306v2.module.tiezong.temp.view;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.Toast;

import com.by5388.sy95306v2.App;
import com.by5388.sy95306v2.MyListener;
import com.by5388.sy95306v2.R;
import com.by5388.sy95306v2.base.BaseActivity;
import com.by5388.sy95306v2.common.Tools;
import com.by5388.sy95306v2.module.tiezong.bean.yp.success.TzDataBean;
import com.by5388.sy95306v2.module.tiezong.temp.persenter.DetailRemainTicketPresenter;
import com.by5388.sy95306v2.module.tiezong.temp.persenter.IDetailRemainTicketPresenter;
import com.google.android.material.textfield.TextInputEditText;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

/**
 * 铁总余票查询多功能界面
 *
 * @author by5388  on 2018/8/26.
 */
public class TzRemainTicketActivity extends BaseActivity implements IDetailRemainTicketView, MyListener.UpdateDate {

    private final List<TzDataBean> dataBeans = new ArrayList<>();
    private DetailRemainTicketAdapter adapter;
    private IDetailRemainTicketPresenter presenter;
    private TextInputEditText fromStation, toStation, trainCode;
    private Button buttonDate;
    private MyListener dateListener;
    private Calendar calendar;

    public static Intent toTzRemainTicketActivity(@NonNull Context context, String trainCode, String date, String fromStation, String toStation, String randCode) {
        Intent intent = new Intent(context, TzRemainTicketActivity.class);
        Bundle bundle = new Bundle();
        bundle.putString(IDetailRemainTicketView.KEY_TRAIN_CODE, trainCode);
        bundle.putString(IDetailRemainTicketView.KEY_DATE, date);
        bundle.putString(IDetailRemainTicketView.KEY_FROM, fromStation);
        bundle.putString(IDetailRemainTicketView.KEY_TO, toStation);
        bundle.putString(IDetailRemainTicketView.KEY_RAND_CODE, randCode);
        intent.putExtra(IDetailRemainTicketView.KEY_BUNDLE, bundle);
        return intent;
    }

    @Override
    protected int getLayoutViewID() {
        return R.layout.activity_tz_combination;
    }

    @Override
    protected void initData() {
        presenter = new DetailRemainTicketPresenter(this);
        adapter = new DetailRemainTicketAdapter(this, new ArrayList<>());
        dateListener = new MyListener(this);
        calendar = Calendar.getInstance();
    }

    @Override
    protected void initView() {
        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        recyclerView.setAdapter(adapter);

        fromStation = findViewById(R.id.textView_from_station);
        toStation = findViewById(R.id.textView_to_station);
        trainCode = findViewById(R.id.textView_train_code);
        Button buttonSearch = findViewById(R.id.button_query);

        buttonDate = findViewById(R.id.button_query_date);
        buttonSearch.setOnClickListener(v -> searchTrainNumber());
        buttonDate.setOnClickListener(v -> selectDate(dateListener, calendar));
        findViewById(R.id.imageView).setOnClickListener(v -> switchStations());

    }

    private void switchStations() {
        final String to = toStation.getText().toString().trim();
        toStation.setText(fromStation.getText().toString().trim());
        fromStation.setText(to);
    }

    private void selectDate(MyListener dateListener, Calendar calendarData) {
        final long maxMinute = 5184000000L;
        //  这里要记录已经选择的日期
        DatePickerDialog datePickerDialog = new DatePickerDialog(this, dateListener,
                calendarData.get(Calendar.YEAR), calendarData.get(Calendar.MONTH), calendarData.get(Calendar.DATE));
        Calendar calendar = Calendar.getInstance();
        DatePicker picker = datePickerDialog.getDatePicker();
        // 设置最大日期
        picker.setMaxDate(calendar.getTimeInMillis() + maxMinute);
        // 设置最小日期
        picker.setMinDate(calendar.getTimeInMillis());
        datePickerDialog.show();
    }

    private void searchTrainNumber() {
        // TODO: 2020/3/17 检查网络
        final boolean networkEnable = App.getInstance().networkEnable();
        if (!networkEnable) {
            Tools.openSetting(this, buttonDate);
            return;
        }
        adapter.setBeans(new ArrayList<>());
        dataBeans.clear();
        final String date = buttonDate.getText().toString().trim();
        final String fromStation = this.fromStation.getText().toString().trim();
        final String toStation = this.toStation.getText().toString().trim();
        final String trainCode = this.trainCode.getText().toString().trim();

        boolean isNotEmptyFromStation = !TextUtils.isEmpty(fromStation);
        boolean isNotEmptyToStation = !TextUtils.isEmpty(toStation);
        boolean isNotEmptyTrainCode = !TextUtils.isEmpty(trainCode);
        //组合0：三个都不为空
        if (isNotEmptyFromStation && isNotEmptyToStation && isNotEmptyTrainCode) {
            presenter.getOnlyOneTrainList(date, fromStation, toStation, trainCode);
            return;
        }
        //组合1：出发+目的：列出所有的车次信息
        if (isNotEmptyFromStation && isNotEmptyToStation) {
            presenter.getTrainListByEmptyTrainCode(date, fromStation, toStation);
            return;
        }
        //组合2：没有出发站
        if (isNotEmptyToStation && isNotEmptyTrainCode) {
            presenter.getTrainListByEmptyFromStation(date, toStation, trainCode);
            return;
        }//组合3：没有目的站
        if (isNotEmptyFromStation && isNotEmptyTrainCode) {
            presenter.getTrainListByEmptyToStation(date, fromStation, trainCode);
            return;
        }
        showError("请至少填写2个信息");
    }

    @Override
    protected void loadData() {
        buttonDate.setText(getData(calendar));


    }

    private void loadBundleData(Bundle bundle) {
        String date = bundle.getString(IDetailRemainTicketView.KEY_DATE);
        String fromStation = bundle.getString(IDetailRemainTicketView.KEY_FROM);
        String toStation = bundle.getString(IDetailRemainTicketView.KEY_TO);
        String randCode = bundle.getString(IDetailRemainTicketView.KEY_RAND_CODE);
        String trainCode = bundle.getString(IDetailRemainTicketView.KEY_TRAIN_CODE);

        boolean isNotEmptyFromStation = !TextUtils.isEmpty(fromStation);
        boolean isNotEmptyToStation = !TextUtils.isEmpty(toStation);
        boolean isNotEmptyTrainCode = !TextUtils.isEmpty(trainCode);
        //组合0：三个都不为空
        if (isNotEmptyFromStation && isNotEmptyToStation && isNotEmptyTrainCode) {
            presenter.getOnlyOneTrainList(date, fromStation, toStation, trainCode);
            Log.d(IDetailRemainTicketView.TAG, "searchTrainNumber: 0");
            return;
        }
        //组合1：出发+目的：列出所有的车次信息
        if (isNotEmptyFromStation && isNotEmptyToStation) {
            presenter.getTrainListByEmptyTrainCode(date, fromStation, toStation);
            Log.d(IDetailRemainTicketView.TAG, "searchTrainNumber: 1");
            return;
        }
        //组合2：没有出发站
        if (isNotEmptyToStation && isNotEmptyTrainCode) {
            presenter.getTrainListByEmptyFromStation(date, toStation, trainCode);
            Log.d(IDetailRemainTicketView.TAG, "searchTrainNumber: 2");
            return;
        }//组合3：没有目的站
        if (isNotEmptyFromStation && isNotEmptyTrainCode) {
            presenter.getTrainListByEmptyToStation(date, fromStation, trainCode);
            Log.d(IDetailRemainTicketView.TAG, "searchTrainNumber: 3");
            return;
        }
        showError("请至少填写2个信息");
    }

    @Override
    public void updateList(List<TzDataBean> dataBeans) {
        adapter.setBeans(dataBeans);
    }

    @Override
    public void startQuery() {
    }

    @Override
    public void finishQuery() {
    }

    @Override
    public void showError(String tip) {
        Toast.makeText(this, tip, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void addRemainingTicket(TzDataBean bean) {
        Log.d(IDetailRemainTicketView.TAG, "addIRemainingTicket: " + bean.getStation_train_code());
        dataBeans.add(bean);
        adapter.setBeans(dataBeans);
    }

    @Override
    public void updateView(int year, int month, int dayOfMonth) {
        calendar = Calendar.getInstance();
        calendar.set(year, month, dayOfMonth);
        buttonDate.setText(getData(calendar));
    }

    /**
     * 日期选择
     *
     * @param calendar 日期
     * @return 格式转化后的日期
     */
    private String getData(Calendar calendar) {
        Locale locale = Locale.getDefault();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", locale);
        Date date = calendar.getTime();
        return sdf.format(date);
    }
}
