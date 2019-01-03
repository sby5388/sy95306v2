package com.by5388.sy95306v2.tiezong.combination;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.by5388.sy95306v2.R;
import com.by5388.sy95306v2.tiezong.TzRemainTicketActivity;
import com.by5388.sy95306v2.bean.IRemainingTicket;
import com.by5388.sy95306v2.MyListener;
import com.by5388.sy95306v2.tiezong.BaseTzFragment;
import com.by5388.sy95306v2.tiezong.combination.persenter.CombinationPresenter;
import com.by5388.sy95306v2.tiezong.combination.persenter.ICombinationPresenter;
import com.by5388.sy95306v2.tiezong.combination.view.ICombinationView;
import com.by5388.sy95306v2.tiezong.remainticket.temp.RemainTicketAdapter;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * @author by5388  on 2018/8/22.
 */
public class CombinationFragment extends BaseTzFragment implements ICombinationView {
    private static final String TAG = "CombinationFragment";
    private List<IRemainingTicket> currentTickets;
    private TextInputEditText fromStation, toStation, passCode, trainCode;
    /**
     * 图片验证码
     */
    private ImageView imageViewPassCode;
    private Button buttonSearch, buttonDate, buttonCheck;
    private ListView listView;
    private RemainTicketAdapter adapter;
    private final static List<IRemainingTicket> EMPTY_LIST = new ArrayList<>();
    private MyListener dateListener;
    private Calendar calendar;
    private ICombinationPresenter presenter;

    public static CombinationFragment newInstance() {
        CombinationFragment fragment = new CombinationFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected void initData() {
        presenter = new CombinationPresenter(this);
        dateListener = new MyListener(this);
        calendar = Calendar.getInstance();
        adapter = new RemainTicketAdapter(getContext(), EMPTY_LIST);
        currentTickets = new ArrayList<>();
    }

    @Override
    protected void loadData() {
        buttonDate.setText(getData(calendar));
        listView.setAdapter(adapter);
//        listView.setOnItemClickListener((parent, view, position, id) -> {
//            IRemainingTicket ticket = adapter.getItem(position);
//            Intent intent = TzRemainTicketActivity.toTzRemainTicketActivity(
//                    getContext(),
//                    ticket.getCode(),
//                    getData(calendar),
//                    ticket.getFromStation(),
//                    ticket.getToStation(),
//                    getRandCode()
//            );
//            newIntent(intent);
//        });
    }

    private String getRandCode() {
        return passCode.getText().toString().trim();
    }

    @Override
    protected int getLayoutID() {
        return R.layout.fragment_tz_combination;
    }

    @Override
    protected void initView(View view) {
        listView = view.findViewById(R.id.listView_train_code);
        fromStation = view.findViewById(R.id.textView_from_station);
        toStation = view.findViewById(R.id.textView_to_station);
        passCode = view.findViewById(R.id.textView_pass_code);
        trainCode = view.findViewById(R.id.textView_train_code);
        buttonCheck = view.findViewById(R.id.button_check_pass_code);
        buttonCheck.setOnClickListener(v -> checkPassCode());
        buttonSearch = view.findViewById(R.id.button_query);
        imageViewPassCode = view.findViewById(R.id.imageView_pass_code);
        imageViewPassCode.setOnClickListener(v -> refreshPassCode());

        buttonDate = view.findViewById(R.id.button_query_date);
        buttonSearch.setOnClickListener(v -> searchTrainNumber());
        buttonSearch.setOnLongClickListener(v -> {
            startActivity(new Intent(getContext(), TzRemainTicketActivity.class));
            return true;
        });
        buttonDate.setOnClickListener(v -> selectDate(dateListener, calendar));
        view.findViewById(R.id.imageView).setOnClickListener(v -> switchStations());
    }

    /**
     * 校验验证码
     */
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

    /**
     * 刷新验证码
     */
    private void refreshPassCode() {
        imageViewPassCode.setEnabled(false);
        presenter.refreshPassCodeBitmap();
    }

    private void switchStations() {
        String to = toStation.getText().toString().trim();
        toStation.setText(fromStation.getText().toString().trim());
        fromStation.setText(to);
    }

    private void searchTrainNumber() {
        adapter.setTickets(EMPTY_LIST);
        currentTickets.clear();
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

    @Override
    public void updateView(int year, int month, int dayOfMonth) {
        calendar = Calendar.getInstance();
        calendar.set(year, month, dayOfMonth);
        buttonDate.setText(getData(calendar));
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
        Toast.makeText(getContext(), tip, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void updateList(List<IRemainingTicket> dataBeans) {
        if (null == dataBeans || dataBeans.isEmpty()) {
            return;
        }
        adapter.setTickets(dataBeans);
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
        Toast.makeText(getContext(), show, Toast.LENGTH_SHORT).show();
        buttonCheck.setEnabled(true);
        imageViewPassCode.setEnabled(true);
    }

    @Override
    public void onDestroy() {
        presenter.unSubscribe();
        super.onDestroy();
    }

    @Override
    public void addIRemainingTicket(IRemainingTicket ticket) {
        currentTickets.add(ticket);
        adapter.setTickets(currentTickets);
    }

    @Override
    public void clearPassCode() {
        passCode.setText("");
    }
}
