package com.by5388.sy95306v2.module.shenyang.select;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.Toast;

import com.by5388.sy95306v2.R;
import com.by5388.sy95306v2.base.BaseActivity;
import com.by5388.sy95306v2.module.shenyang.Station2StationFragment;
import com.by5388.sy95306v2.module.shenyang.adapter.StationAdapter;
import com.by5388.sy95306v2.module.shenyang.bean.Station;
import com.by5388.sy95306v2.module.shenyang.select.presenter.IStationPresenter;
import com.by5388.sy95306v2.module.shenyang.select.presenter.StationPresenter;
import com.by5388.sy95306v2.module.shenyang.select.view.IStationView;

import java.util.ArrayList;
import java.util.List;

/**
 * 选择车站
 *
 * @author by5388
 * @date 20180729
 */
public class SelectStationActivity extends BaseActivity implements IStationView,
        AdapterView.OnItemClickListener, TextWatcher {

    private GridView mGridView;
    EditText stationName;
    private IStationPresenter presenter;
    private StationAdapter adapter;

    @Override
    protected void initView() {
        mGridView = findViewById(R.id.gridView_city);
        mGridView.setOnItemClickListener(this);
        stationName = findViewById(R.id.edit_text_select_city_name);
        stationName.addTextChangedListener(this);

    }

    @Override
    protected void initData() {
        presenter = new StationPresenter(this);
        adapter = new StationAdapter(this, new ArrayList<>());
    }

    @Override
    protected void loadData() {
        mGridView.setAdapter(adapter);
        presenter.getDefaultStation();
    }


    @Override
    protected int getLayoutViewID() {
        return R.layout.activity_select_station;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Station station = adapter.getItem(position);
        setStation(station);
    }

    private void setStation(Station station) {
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

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
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

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void afterTextChanged(Editable s) {

    }
}
