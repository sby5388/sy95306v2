package com.by5388.sy95306v2.module.shenyang;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.by5388.sy95306v2.App;
import com.by5388.sy95306v2.MyListener;
import com.by5388.sy95306v2.R;
import com.by5388.sy95306v2.common.Tools;
import com.by5388.sy95306v2.database.DataBaseApiImpl;
import com.by5388.sy95306v2.database.IShenYangDbApi;
import com.by5388.sy95306v2.dialog.ITrainType;
import com.by5388.sy95306v2.dialog.TrainFilterDialog2;
import com.by5388.sy95306v2.dialog.TrainType;
import com.by5388.sy95306v2.main.MainFragment;
import com.by5388.sy95306v2.module.shenyang.bean.Station;
import com.by5388.sy95306v2.module.shenyang.list.TrainNumberListActivity2;
import com.by5388.sy95306v2.module.shenyang.select.SelectStationActivity;

import java.util.Calendar;
import java.util.Objects;

/**
 * @author by5388  on 2018/7/28.
 */

public class Station2StationFragment extends BaseShenYangFragment {

    public static final String TAG = "FirstFragment";
    public static final String DATA_BUNDLE = "bundle";

    public static final String DATA_STATION_CODE = "code";
    /**
     * 请求成功
     */
    public static final int SUCCESS_CODE = 100;
    public static final String DIALOG_TITLE = "列车车型";
    private static final int START_STATION_CODE = 1;
    private static final int END_STATION_CODE = 2;
    private static final int REQUEST_CODE_FILTER = 2000;
    private static final String FIRST_CITY = "北京";
    private static final String SECOND_CITY = "上海";
    private Calendar calendar;
    private MyListener dateListener;
    private TextView startCity, endCity;
    private Button buttonSelectType, buttonSelectDate;
    private Station fromStation, toStation;
    private IShenYangDbApi mService;
    private int selectedDate = 20180606;
    private boolean mRegistered = false;

    private BroadcastReceiver mBroadcastReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            if (mService == null) {
                return;
            }
            fromStation = mService.selectStationByName(FIRST_CITY);
            toStation = mService.selectStationByName(SECOND_CITY);
            if (startCity == null || endCity == null) {
                return;
            }
            startCity.setText(fromStation.getName());
            endCity.setText(toStation.getName());
        }
    };
    private IntentFilter mIntentFilter = new IntentFilter(MainFragment.ACTION_REFRESH_STATION_LIST);

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
        mService = DataBaseApiImpl.getInstance();
        calendar = Calendar.getInstance();
        selectedDate = getData(calendar);
        dateListener = new MyListener(this);
        fromStation = mService.selectStationByName(FIRST_CITY);
        toStation = mService.selectStationByName(SECOND_CITY);
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
        if (Activity.RESULT_OK != resultCode) {
            return;
        }
        switch (requestCode) {
            case END_STATION_CODE:
            case START_STATION_CODE:
                updateStation(requestCode, data);
                break;
            case REQUEST_CODE_FILTER:
                final String title = TrainFilterDialog2.getTitle(data);
                if (!TextUtils.isEmpty(title)) {
                    buttonSelectType.setText(title);
                }
                break;
            default:
                super.onActivityResult(requestCode, resultCode, data);
        }
    }

    /**
     * 选择车站成功，刷新显示
     *
     * @param requestCode 开始车站或者结束车站
     * @param data        返回数据
     */
    private void updateStation(int requestCode, Intent data) {
        Bundle bundle = data.getBundleExtra(DATA_BUNDLE);
        if (null == bundle) {
            return;
        }
        String nameUpper = bundle.getString(DATA_STATION_CODE);
        if (TextUtils.isEmpty(nameUpper)) {
            return;
        }
        Station station = mService.selectStationByNameUpper(nameUpper);
        if (START_STATION_CODE == requestCode) {
            fromStation = station;
            startCity.setText(fromStation.getName());
        } else {
            toStation = station;
            endCity.setText(toStation.getName());
        }
    }


    @Override
    public void onStart() {
        super.onStart();
        if (mRegistered) {
            return;
        }
        final Context context = getContext();
        if (context == null) {
            return;
        }
        context.registerReceiver(mBroadcastReceiver, mIntentFilter);
        mRegistered = true;
    }

    @Override
    public void onStop() {
        super.onStop();
        if (!mRegistered) {
            return;
        }
        final Context context = getContext();
        if (context == null) {
            return;
        }
        context.unregisterReceiver(mBroadcastReceiver);
        mRegistered = false;
    }


    private void swapStations() {
        Station tempStation = fromStation;
        fromStation = toStation;
        toStation = tempStation;
        startCity.setText(fromStation.getName());
        endCity.setText(toStation.getName());
    }

    private void search() {
        // TODO: 2020/3/17 检查网络
        final boolean networkEnable = App.getInstance().networkEnable();
        if (!networkEnable) {
            Tools.openSetting(getContext(), startCity);
            return;
        }
        startActivity(TrainNumberListActivity2.newIntent(getContext(), selectedDate, fromStation, toStation));
    }


    private void showDialog() {
        // TODO: 2020/3/19 调试新的类型筛选
        final TrainFilterDialog2 trainFilterDialog2 = TrainFilterDialog2.newInstance();
        trainFilterDialog2.setTargetFragment(this, REQUEST_CODE_FILTER);
        trainFilterDialog2.show(Objects.requireNonNull(getFragmentManager()), TrainFilterDialog2.class.getName());
    }

    @Override
    protected void loadData() {

    }

    @Override
    public void onResume() {
        super.onResume();
        final ITrainType trainType = TrainType.getInstance();
        buttonSelectType.setText(trainType.getTitle());
    }
}
