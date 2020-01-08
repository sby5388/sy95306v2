package com.by5388.sy95306.fragment;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.NonNull;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.by5388.sy95306.R;
import com.by5388.sy95306.SelectCityActivity;
import com.by5388.sy95306.bean.Station;
import com.by5388.sy95306.common.StaticData;
import com.by5388.sy95306.database.DataBaseApi;
import com.by5388.sy95306.database.DataBaseTempApiImpl;
import com.by5388.sy95306.list.TrainListActivity;

import java.util.Arrays;
import java.util.Calendar;
import java.util.HashSet;
import java.util.Set;

/**
 * 站到站查询
 *
 * @author by5388
 */
public class ListFragment extends BaseFragment {

    public static final String TAG = "ListFragment";
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


    private TextView startCity, endCity;
    private Button buttonSelectType, buttonSelectDate;
    private Station fromStation, toStation;
    private DataBaseApi service;
    private Calendar calendar;
    private MyListener dateListener;
    private int selectedDate = 20180606;
    public static final String DIALOG_TITLE = "列车车型";

    /**
     * 车型::TCCODE
     * 0:全部    99
     * 1:G、C    TCCODE:8 高速
     * 2:D       TCCODE:D 动车
     * 3:Z       TCCODE:1 直特
     * 4:T       TCCODE:2 特快
     * 5:K、Y    TCCODE:0 快速
     * 6:普客    TCCODE:3 普快
     */


    public ListFragment() {
    }

    public static ListFragment newInstance() {
        ListFragment fragment = new ListFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initData();
    }

    private void initData() {
        service = DataBaseTempApiImpl.getInstance();
        calendar = Calendar.getInstance();
        selectedDate = getData(calendar);
        dateListener = new MyListener();
        fromStation = service.selectStationByName(FIRST_CITY);
        toStation = service.selectStationByName(SECOND_CITY);
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
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case END_STATION_CODE:
            case START_STATION_CODE:
                if (SUCCESS_CODE == resultCode) {
                    refreshStation(requestCode, data);
                }
                break;
            default:
                break;
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


    @Override
    public void onResume() {
        super.onResume();
        boolean[] selected = StaticData.SELECTED;

        if (null != selected) {
            StringBuilder stringBuilder = new StringBuilder();
            isSelected(selected, stringBuilder);
            buttonSelectType.setText(stringBuilder.toString());
        }
    }

    private void isSelected(boolean[] selected, StringBuilder stringBuilder) {
        String[] name = StaticData.TYPES;
        if (selected[0]) {
            stringBuilder.append(name[0]);
            return;
        }
        for (int i = 1; i < selected.length; i++) {
            if (selected[i]) {
                stringBuilder.append(name[i])
                        .append(";");
            }
        }

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_first, container, false);
        initView(view);
        return view;
    }

    private void initView(View view) {
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

    /**
     * @param types         已勾选的车型
     * @param stringBuilder 按钮显示的字符串
     * @return 是否已选择车型
     */
    private static boolean isSelected(@NonNull Set<Integer> types, @NonNull StringBuilder stringBuilder) {
        String[] trainTypes = StaticData.TYPES;
        if (types.isEmpty()) {
            return false;
        }
        if (types.contains(0)) {
            stringBuilder.append(trainTypes[0]);
            return true;
        }
        for (int i = 1; i < 6; i++) {
            if (types.contains(i)) {
                stringBuilder.append(trainTypes[i]);
                stringBuilder.append(";");
            }
        }
        return true;
    }

    /**
     * 列车筛选
     */
    private void showDialog() {
        boolean[] selected = StaticData.SELECTED;
        boolean[] newSelected = Arrays.copyOf(selected, 6);
        String[] trainTypes = getResources().getStringArray(R.array.train_type);
        AlertDialog alertDialog = new AlertDialog.Builder(getContext())
                .setTitle(DIALOG_TITLE)
                .setMultiChoiceItems(trainTypes, newSelected, (dialog, which, isChecked) -> newSelected[which] = isChecked)
                .setNegativeButton(android.R.string.cancel, (DialogInterface dialog, int which) -> dialog.dismiss())
                .setPositiveButton(android.R.string.ok, (dialog, which) -> {
                    Set<Integer> types = new HashSet<>();
                    for (int i = 0; i < 6; i++) {
                        selected[i] = newSelected[i];
                        if (newSelected[i]) {
                            types.add(i);
                        }
                    }

                    StringBuilder stringBuilder = new StringBuilder();
                    boolean isSelected = isSelected(types, stringBuilder);
                    if (!isSelected) {
                        Toast.makeText(getContext(), "请选择列车类型", Toast.LENGTH_SHORT).show();
                        //TODO  要保持对话框不关闭
                        return;
                    }
                    buttonSelectType.setText(stringBuilder.toString());
                    dialog.dismiss();
                })
                .create();
        alertDialog.show();
    }

    private void search() {
        // 携带数据，大量的数据只携带少量的，2个NameUpper,日期
        startActivity(TrainListActivity.newIntent(getContext(),
                selectedDate, fromStation.getNameUpper(), toStation.getNameUpper()));
    }

    private void selectStation(int requestCode) {
        Intent intent = new Intent(getContext(), SelectCityActivity.class);
        Bundle bundle = new Bundle();
        bundle.putInt(DATA_STATION_CODE, requestCode);
        startActivityForResult(intent, requestCode);
    }

    /**
     * 应当交换2个名称
     */
    private void swapStations() {
        Station tempStation = fromStation;
        fromStation = toStation;
        toStation = tempStation;
        startCity.setText(fromStation.getName());
        endCity.setText(toStation.getName());
    }

    /**
     * 更新UI
     *
     * @param year       年
     * @param month      月
     * @param dayOfMonth 日
     */
    @Override
    public void updateView(int year, int month, int dayOfMonth) {
        calendar = Calendar.getInstance();
        calendar.set(year, month, dayOfMonth);
        selectedDate = getData(calendar);
        buttonSelectDate.setText(String.valueOf(selectedDate));
    }

}
