package com.by5388.sy95306v2.activity.selectStation;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.Toast;

import com.by5388.sy95306v2.R;
import com.by5388.sy95306v2.activity.BaseActivity;
import com.by5388.sy95306v2.activity.selectStation.presenter.IStationPresenter;
import com.by5388.sy95306v2.activity.selectStation.presenter.StationPresenter;
import com.by5388.sy95306v2.activity.selectStation.view.IStationView;
import com.by5388.sy95306v2.adapter.StationAdapter;
import com.by5388.sy95306v2.bean.Station;
import com.by5388.sy95306v2.fragment.shenyang.Station2StationFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnItemClick;
import butterknife.OnTextChanged;

/**
 * 选择车站
 *
 * @author by5388
 * @date 20180729
 */
public class SelectStationActivity extends BaseActivity implements IStationView {

    @BindView(R.id.gridView_city)
    GridView gridView;
    @BindView(R.id.edit_text_select_city_name)
    EditText stationName;
    private IStationPresenter presenter;
    private StationAdapter adapter;

    @Override
    protected void initView() {
        ButterKnife.bind(this);
    }

    @Override
    protected void initData() {
        presenter = new StationPresenter(this);
        adapter = new StationAdapter(this, new ArrayList<>());
    }

    @Override
    protected void loadData() {
        gridView.setAdapter(adapter);
        presenter.getDefaultStation();
    }

    @Override
    protected int getLayoutViewID() {
        return R.layout.activity_select_station;
    }

    @OnItemClick(R.id.gridView_city)
    void stationSelect(int position) {
        Station station = adapter.getItem(position);
        back(station);
    }

    private void back(Station station) {
        Intent intent = new Intent();
        Bundle bundle = new Bundle();
        bundle.putString(Station2StationFragment.DATA_STATION_CODE, station.getNameUpper());
        intent.putExtra(Station2StationFragment.DATA_BUNDLE, bundle);
        setResult(Station2StationFragment.SUCCESS_CODE, intent);
        finish();
    }


    @Override
    protected boolean isShowActionBar() {
        return true;
    }

    /**
     * TODO 监听
     *
     * @param s
     * @param start
     * @param before
     * @param count
     */
    @OnTextChanged(R.id.edit_text_select_city_name)
    void onTextChanged(CharSequence s, int start, int before, int count) {
        presenter.getStation(s.toString());
    }

    @Override
    public void setStations(List<Station> stations) {
        adapter.setStations(stations);
    }

    @Override
    public void showErrorMessage(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }


    @Override
    protected void onDestroy() {
        if (presenter != null) {
            presenter.unSubscribe();
            presenter = null;
        }
        super.onDestroy();
    }
}
