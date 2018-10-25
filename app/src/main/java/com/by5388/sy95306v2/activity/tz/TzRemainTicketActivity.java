package com.by5388.sy95306v2.activity.tz;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.TextInputEditText;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.Toast;

import com.by5388.sy95306v2.R;
import com.by5388.sy95306v2.activity.BaseActivity;
import com.by5388.sy95306v2.activity.tz.persenter.DetailRemainTicketPresenter;
import com.by5388.sy95306v2.activity.tz.persenter.IDetailRemainTicketPresenter;
import com.by5388.sy95306v2.activity.tz.view.IDetailRemainTicketView;
import com.by5388.sy95306v2.bean.IRemainingTicket;
import com.by5388.sy95306v2.bean.tz.yp.success.TzDataBean;
import com.by5388.sy95306v2.fragment.MyListener;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/**
 * 铁总余票查询多功能界面
 *
 * @author by5388  on 2018/8/26.
 */
public class TzRemainTicketActivity extends BaseActivity implements IDetailRemainTicketView, MyListener.UpdateDate {

    private RecyclerView recyclerView;
    private DetailRemainTicketAdapter adapter;

    IDetailRemainTicketPresenter presenter;
    private List<TzDataBean> dataBeans = new ArrayList<>();
    private TextInputEditText fromStation, toStation, passCode, trainCode;
    /**
     * 图片验证码
     */
    private ImageView imageViewPassCode;
    private Button buttonSearch, buttonDate, buttonCheck;
    private final static List<IRemainingTicket> EMPTY_LIST = new ArrayList<>();
    private MyListener dateListener;
    private Calendar calendar;

    @Override
    protected boolean isShowActionBar() {
        return true;
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
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        recyclerView.setAdapter(adapter);

        fromStation = findViewById(R.id.textView_from_station);
        toStation = findViewById(R.id.textView_to_station);
        passCode = findViewById(R.id.textView_pass_code);
        trainCode = findViewById(R.id.textView_train_code);
        buttonCheck = findViewById(R.id.button_check_pass_code);
        buttonCheck.setOnClickListener(v -> checkPassCode());
        buttonSearch = findViewById(R.id.button_query);
        imageViewPassCode = findViewById(R.id.imageView_pass_code);
        imageViewPassCode.setOnClickListener(v -> refreshPassCode());

        buttonDate = findViewById(R.id.button_query_date);
        buttonSearch.setOnClickListener(v -> searchTrainNumber());
        buttonDate.setOnClickListener(v -> selectDate(dateListener, calendar));
        findViewById(R.id.imageView).setOnClickListener(v -> switchStations());

    }

    private void switchStations() {
        String to = toStation.getText().toString().trim();
        toStation.setText(fromStation.getText().toString().trim());
        fromStation.setText(to);
    }

    private void selectDate(MyListener dateListener, Calendar calendarData) {
        /**
         * 往后延长60天
         */
        final long MAX_TIME = 5184000000L;
        //  这里要记录已经选择的日期
        DatePickerDialog datePickerDialog = new DatePickerDialog(this, dateListener,
                calendarData.get(Calendar.YEAR), calendarData.get(Calendar.MONTH), calendarData.get(Calendar.DATE));
        Calendar calendar = Calendar.getInstance();
        DatePicker picker = datePickerDialog.getDatePicker();
        // 设置最大日期
        picker.setMaxDate(calendar.getTimeInMillis() + MAX_TIME);
        // 设置最小日期
        picker.setMinDate(calendar.getTimeInMillis());
        datePickerDialog.show();
    }

    private void searchTrainNumber() {
        adapter.setBeans(new ArrayList<>());
        dataBeans.clear();
        String date = buttonDate.getText().toString().trim();
        String fromStation = this.fromStation.getText().toString().trim();
        String toStation = this.toStation.getText().toString().trim();
        String randCode = passCode.getText().toString().trim();
        String trainCode = this.trainCode.getText().toString().trim();

        boolean isNotEmptyFromStation = !TextUtils.isEmpty(fromStation);
        boolean isNotEmptyToStation = !TextUtils.isEmpty(toStation);
        boolean isNotEmptyTrainCode = !TextUtils.isEmpty(trainCode);
        //组合0：三个都不为空
        if (isNotEmptyFromStation && isNotEmptyToStation && isNotEmptyTrainCode) {
            presenter.getOnlyOneTrainList(date, fromStation, toStation, randCode, trainCode);
            Log.d(TAG, "searchTrainNumber: 0");
            return;
        }
        //组合1：出发+目的：列出所有的车次信息
        if (isNotEmptyFromStation && isNotEmptyToStation) {
            presenter.getTrainListByEmptyTrainCode(date, fromStation, toStation, randCode);
            Log.d(TAG, "searchTrainNumber: 1");
            return;
        }
        //组合2：没有出发站
        if (isNotEmptyToStation && isNotEmptyTrainCode) {
            presenter.getTrainListByEmptyFromStation(date, toStation, randCode, trainCode);
            Log.d(TAG, "searchTrainNumber: 2");
            return;
        }//组合3：没有目的站
        if (isNotEmptyFromStation && isNotEmptyTrainCode) {
            presenter.getTrainListByEmptyToStation(date, fromStation, randCode, trainCode);
            Log.d(TAG, "searchTrainNumber: 3");
            return;
        }
        showError("请至少填写2个信息");
    }

    private void refreshPassCode() {
        imageViewPassCode.setEnabled(false);
        presenter.refreshPassCodeBitmap();
    }

    private void checkPassCode() {
        final int codeLength = 4;
        String code = passCode.getText().toString().trim();
        Log.d(TAG, "checkPassCode: " + code);
        if (codeLength != code.length()) {
            passCode.setError("格式不对");
            return;
        }
        buttonCheck.setEnabled(false);
        presenter.checkPassCode(code);
    }

    @Override
    protected void loadData() {
        buttonDate.setText(getData(calendar));


    }

    private void loadBundleData(Bundle bundle) {
        String date = bundle.getString(KEY_DATE);
        String fromStation = bundle.getString(KEY_FROM);
        String toStation = bundle.getString(KEY_TO);
        String randCode = bundle.getString(KEY_RAND_CODE);
        String trainCode = bundle.getString(KEY_TRAIN_CODE);

        boolean isNotEmptyFromStation = !TextUtils.isEmpty(fromStation);
        boolean isNotEmptyToStation = !TextUtils.isEmpty(toStation);
        boolean isNotEmptyTrainCode = !TextUtils.isEmpty(trainCode);
        //组合0：三个都不为空
        if (isNotEmptyFromStation && isNotEmptyToStation && isNotEmptyTrainCode) {
            presenter.getOnlyOneTrainList(date, fromStation, toStation, randCode, trainCode);
            Log.d(TAG, "searchTrainNumber: 0");
            return;
        }
        //组合1：出发+目的：列出所有的车次信息
        if (isNotEmptyFromStation && isNotEmptyToStation) {
            presenter.getTrainListByEmptyTrainCode(date, fromStation, toStation, randCode);
            Log.d(TAG, "searchTrainNumber: 1");
            return;
        }
        //组合2：没有出发站
        if (isNotEmptyToStation && isNotEmptyTrainCode) {
            presenter.getTrainListByEmptyFromStation(date, toStation, randCode, trainCode);
            Log.d(TAG, "searchTrainNumber: 2");
            return;
        }//组合3：没有目的站
        if (isNotEmptyFromStation && isNotEmptyTrainCode) {
            presenter.getTrainListByEmptyToStation(date, fromStation, randCode, trainCode);
            Log.d(TAG, "searchTrainNumber: 3");
            return;
        }
        showError("请至少填写2个信息");
    }


    @Override
    public void showFilterDialog() {

    }

    @Override
    public void updateList(List<TzDataBean> dataBeans) {
        adapter.setBeans(dataBeans);
    }

    @Override
    public void updateCheckCodeBitmap(Bitmap bitmap) {
        if (null == bitmap) {
            return;
        }
        imageViewPassCode.setImageBitmap(bitmap);
        imageViewPassCode.setEnabled(true);
    }

    @Override
    public void checkPassCode(boolean isChecked) {
        String show = "输入不正确";
        if (isChecked) {
            show = "输入正确";
            buttonSearch.setEnabled(true);
        }
        Toast.makeText(this, show, Toast.LENGTH_SHORT).show();
        buttonCheck.setEnabled(true);
        imageViewPassCode.setEnabled(true);
    }

    @Override
    public void clearPassCode() {
        passCode.setText("");
    }

    @Override
    public void startQuery() {
        imageViewPassCode.setEnabled(false);
        buttonCheck.setEnabled(false);
    }

    @Override
    public void finishQuery() {
        imageViewPassCode.setEnabled(true);
        buttonCheck.setEnabled(true);
    }

    @Override
    public void showError(String tip) {
        Toast.makeText(this, tip, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void addRemainingTicket(TzDataBean bean) {
        Log.d(TAG, "addIRemainingTicket: " + bean.getStation_train_code());
        dataBeans.add(bean);
        adapter.setBeans(dataBeans);
    }

    public static Intent toTzRemainTicketActivity(@NonNull Context context, String trainCode, String date, String fromStation, String toStation, String randCode) {
        Intent intent = new Intent(context, TzRemainTicketActivity.class);
        Bundle bundle = new Bundle();
        bundle.putString(KEY_TRAIN_CODE, trainCode);
        bundle.putString(KEY_DATE, date);
        bundle.putString(KEY_FROM, fromStation);
        bundle.putString(KEY_TO, toStation);
        bundle.putString(KEY_RAND_CODE, randCode);
        intent.putExtra(KEY_BUNDLE, bundle);
        return intent;
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
    protected String getData(Calendar calendar) {
        Locale locale = Locale.getDefault();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", locale);
        Date date = calendar.getTime();
        return sdf.format(date);
    }
}
