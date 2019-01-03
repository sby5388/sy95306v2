package com.by5388.sy95306v2.chengdu.screen;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.by5388.sy95306v2.R;
import com.by5388.sy95306v2.chengdu.bean.screen.ScreenStation;
import com.by5388.sy95306v2.chengdu.BaseCdFragment;
import com.by5388.sy95306v2.chengdu.screen.model.ICdScreenModel;
import com.by5388.sy95306v2.chengdu.screen.persenter.CdScreenPresenter;
import com.by5388.sy95306v2.chengdu.screen.persenter.ICdScreenPresenter;
import com.by5388.sy95306v2.chengdu.screen.view.ICdScreenView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/**
 * 成都车站大屏幕
 *
 * @author by5388  on 2018/8/18.
 */
public class CdScreenFragment extends BaseCdFragment implements ICdScreenView {
    private static final String TAG = "CdScreenFragment";
    private ICdScreenPresenter presenter;
    private CdScreenAdapter adapter;
    private Button buttonSearch;
    private ToggleButton toggleButton;
    private Spinner spinner;
    private ListView listView;
    /**
     * 时间（发时：到时），位置（检票口：出站口）
     */
    private TextView showTime, showPlace;
    private List<ScreenStation> stations;
    private ArrayAdapter<ScreenStation> arrayAdapter;
    private Calendar calendar;

    /**
     * 出发
     */
    private static final int TYPE_LEAVE = ICdScreenModel.TYPE_LEAVE;
    /**
     * 到达
     */
    private static final int TYPE_ARRIVE = ICdScreenModel.TYPE_ARRIVE;

    public static CdScreenFragment newInstance() {
        CdScreenFragment fragment = new CdScreenFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected void initData() {
        presenter = new CdScreenPresenter(this);
        adapter = new CdScreenAdapter(getContext(), new ArrayList<>());
        stations = new ArrayList<>();
        arrayAdapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_list_item_1, stations);
        calendar = Calendar.getInstance();

    }

    @Override
    protected void loadData() {
        listView.setAdapter(adapter);
        spinner.setAdapter(arrayAdapter);
        toggleButton.setOnCheckedChangeListener((buttonView, isChecked) -> setTextView(isChecked));
        buttonSearch.setOnClickListener(v -> query());
        Log.d(TAG, "loadData: isAdded: " + isAdded());
        //TODO  重复
//        if (isAdded()) {
//            presenter.getScreenStation();
//        }
    }

    private void setTextView(boolean isChecked) {
        if (isChecked) {
            showTime.setText(R.string.show_time_arrive);
            showPlace.setText(R.string.show_place_arrive);
        } else {
            showTime.setText(R.string.show_time_leave);
            showPlace.setText(R.string.show_place_leave);
        }
        query();
    }


    private void query() {
        if (null == stations || stations.isEmpty()) {
            Toast.makeText(getContext(), "加载中，请稍候", Toast.LENGTH_SHORT).show();
            presenter.getScreenStation();
            return;
        }
        ScreenStation screenStation = arrayAdapter.getItem(spinner.getSelectedItemPosition());
        if (null == screenStation) {
            Toast.makeText(getContext(), "加载中，请稍候", Toast.LENGTH_SHORT).show();
            presenter.getScreenStation();
            return;
        }
        int type = toggleButton.isChecked() ? TYPE_ARRIVE : TYPE_LEAVE;
        String date = getData(calendar);
        presenter.getScreenItems(screenStation.getZMLM(), date, type);

    }

    @Override
    protected int getLayoutID() {
        return R.layout.fragment_cd_screen;
    }

    @Override
    protected void initView(View view) {
        buttonSearch = view.findViewById(R.id.button_search);
        toggleButton = view.findViewById(R.id.toggle_button_screen);
        spinner = view.findViewById(R.id.spinner_cd_station);
        listView = view.findViewById(R.id.listView_cd_screen);
        showTime = view.findViewById(R.id.showTime);
        showPlace = view.findViewById(R.id.showPlace);
    }


    @Override
    public void startQuery() {
        buttonSearch.setEnabled(false);
        toggleButton.setEnabled(false);
    }

    @Override
    public void finishQuery() {
        buttonSearch.setEnabled(true);
        toggleButton.setEnabled(true);

    }

    @Override
    public void showError(String tip) {
        Toast.makeText(getContext(), tip, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void updateCdScreenData(List<CdScreenItem> screenItems) {
        adapter.setScreenItems(screenItems);
    }

    @Override
    public void updateScreenStation(List<ScreenStation> screenStations) {
        stations = screenStations;
        arrayAdapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_list_item_1, stations);
        spinner.setAdapter(arrayAdapter);
        Toast.makeText(getContext(), "加载完成", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void updateView(int year, int month, int dayOfMonth) {

    }

    /**
     * 日期选择
     *
     * @param calendar 日期
     * @return 格式转化后的日期
     */
    private String getData(Calendar calendar) {
        Locale locale = Locale.getDefault();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd", locale);
        Date date = calendar.getTime();
        return sdf.format(date);
    }
}
