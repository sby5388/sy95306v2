package com.by5388.sy95306v2.module.shenyang;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.by5388.sy95306v2.R;
import com.by5388.sy95306v2.database.DataBaseApiImpl;
import com.by5388.sy95306v2.module.shenyang.list.TrainNumberListActivity;
import com.by5388.sy95306v2.module.shenyang.select.SelectStationActivity;
import com.by5388.sy95306v2.module.shenyang.bean.Station;
import com.by5388.sy95306v2.database.IShenYangDbApi;
import com.by5388.sy95306v2.dialog.TrainFilterDialog;
import com.by5388.sy95306v2.dialog.TrainFilterDialog.UpdateFilterDataCallBack;
import com.by5388.sy95306v2.dialog.bean.FilterBean;
import com.by5388.sy95306v2.dialog.bean.FilterData;
import com.by5388.sy95306v2.MyListener;

import java.util.Calendar;
import java.util.List;

import static com.by5388.sy95306v2.dialog.bean.FilterData.isAllSelect;

/**
 * @author by5388  on 2018/7/28.
 */

public class Station2StationFragment extends BaseShenYangFragment implements UpdateFilterDataCallBack {

    public static final String TAG = "FirstFragment";
    public static final String DATA_BUNDLE = "bundle";

    public static final String DATA_STATION_CODE = "code";
    private static final int START_STATION_CODE = 1;
    private static final int END_STATION_CODE = 2;

    private static final String FIRST_CITY = "北京";
    private static final String SECOND_CITY = "上海";

    /**
     * 请求成功
     */
    public static final int SUCCESS_CODE = 100;
    public static final String DIALOG_TITLE = "列车车型";
    private Calendar calendar;
    private MyListener dateListener;
    private TextView startCity, endCity;
    private Button buttonSelectType, buttonSelectDate;
    private Station fromStation, toStation;
    private IShenYangDbApi service;
    private int selectedDate = 20180606;
    private TrainFilterDialog dialog;

    public static Station2StationFragment newInstance() {
        Station2StationFragment fragment = new Station2StationFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void updateView(int year, int month, int dayOfMonth) {
        calendar = Calendar.getInstance();
        calendar.set(year, month, dayOfMonth);
        selectedDate = getData(calendar);
        buttonSelectDate.setText(String.valueOf(selectedDate));
    }

    @Override
    protected void initData() {
        service = DataBaseApiImpl.getInstance();
        calendar = Calendar.getInstance();
        selectedDate = getData(calendar);
        dateListener = new MyListener(this);
        fromStation = service.selectStationByName(FIRST_CITY);
        toStation = service.selectStationByName(SECOND_CITY);
    }

    @Override
    protected int getLayoutID() {
        return R.layout.fragment_station_to_station;
    }

    @Override
    protected void initView(View view) {
        startCity = view.findViewById(R.id.textView_input_start_city);
        endCity = view.findViewById(R.id.textView_input_end_city);
        startCity.setText(fromStation.getName());
        endCity.setText(toStation.getName());
        startCity.setOnClickListener(v -> selectStation(START_STATION_CODE));
        endCity.setOnClickListener(v -> selectStation(END_STATION_CODE));
        view.findViewById(R.id.imageView_exchange).setOnClickListener(v -> swapStations());
        buttonSelectType = view.findViewById(R.id.button_select_train_type);
        buttonSelectDate = view.findViewById(R.id.button_select_date);
        buttonSelectDate.setText(String.valueOf(selectedDate));
        view.findViewById(R.id.button_search).setOnClickListener(v -> search());
        buttonSelectType.setOnClickListener(v -> showDialog());
        buttonSelectDate.setOnClickListener(v -> selectDate(dateListener, calendar));
    }

    private void selectStation(int requestCode) {
        Intent intent = new Intent(getContext(), SelectStationActivity.class);
        Bundle bundle = new Bundle();
        bundle.putInt(DATA_STATION_CODE, requestCode);
        startActivityForResult(intent, requestCode);

    }

    /**
     * 选择车站后的回调事件
     *
     * @param requestCode 请求码
     * @param resultCode  结果码
     * @param data        数据
     */
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {

        switch (requestCode) {
            case END_STATION_CODE:
            case START_STATION_CODE:
                if (SUCCESS_CODE == resultCode) {
                    refreshStation(requestCode, data);
                }
                break;
            default:
                super.onActivityResult(requestCode, resultCode, data);;
        }
    }

    /**
     * 选择车站成功，刷新显示
     *
     * @param requestCode 开始车站或者结束车站
     * @param data        返回数据
     */
    private void refreshStation(int requestCode, Intent data) {
        Bundle bundle = data.getBundleExtra(DATA_BUNDLE);
        if (null == bundle) {
            return;
        }
        String nameUpper = bundle.getString(DATA_STATION_CODE);
        if (TextUtils.isEmpty(nameUpper)) {
            return;
        }
        Station station = service.selectStationByNameUpper(nameUpper);
        if (START_STATION_CODE == requestCode) {
            fromStation = station;
            startCity.setText(fromStation.getName());
        } else {
            toStation = station;
            endCity.setText(toStation.getName());
        }
    }


    private void swapStations() {
        Station tempStation = fromStation;
        fromStation = toStation;
        toStation = tempStation;
        startCity.setText(fromStation.getName());
        endCity.setText(toStation.getName());
    }

    private void search() {
        startActivity(TrainNumberListActivity.newIntent(getContext(), selectedDate, fromStation, toStation));
    }

    private void showDialog() {
        if (null == dialog) {
            dialog = new TrainFilterDialog(getContext(), this);
        }
        if (dialog.isShowing()) {
            dialog.dismiss();
        }
        dialog.refresh().show();
    }

    @Override
    protected void loadData() {

    }

    @Override
    public void updateFilterData() {
        if (isAllSelect()) {
            buttonSelectType.setText("全部");
            return;
        }
        //TODO
        List<FilterBean> beans = FilterData.getFilterItems();
        StringBuilder stringBuilder = new StringBuilder();
        for (FilterBean bean : beans) {
            if (bean.isSelected()) {
                stringBuilder.append(bean.getItemName());
                stringBuilder.append(";");
            }
        }
        buttonSelectType.setText(stringBuilder.toString());

    }
}
