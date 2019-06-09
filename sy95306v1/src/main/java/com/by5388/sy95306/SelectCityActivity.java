package com.by5388.sy95306;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.GridView;

import com.by5388.sy95306.adapter.StationAdapter;
import com.by5388.sy95306.bean.Station;
import com.by5388.sy95306.common.Tools;
import com.by5388.sy95306.database.DataBaseApi;
import com.by5388.sy95306.database.DataBaseTempApiImpl;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

import static com.by5388.sy95306.fragment.ListFragment.DATA_BUNDLE;
import static com.by5388.sy95306.fragment.ListFragment.DATA_STATION_CODE;
import static com.by5388.sy95306.fragment.ListFragment.SUCCESS_CODE;

/**
 * 车站选择列表
 *
 * @author by5388
 * @date 20180530
 */
public class SelectCityActivity extends AppCompatActivity {
    @SuppressWarnings("unused")
    private final static String TAG = "SelectCityActivity";
    private StationAdapter adapter;
    private List<Station> defaultStations;
    private  DataBaseApi dataBaseService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selct_city);
        ActionBar actionBar = getSupportActionBar();
        if (null != actionBar) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
        initData();
        initView();
        loadData();
    }

    private void loadData() {

    }

//    public static Intent newIntent(Context context,int requestCode){
//        Intent intent = new Intent(context, SelectCityActivity.class);
//        Bundle bundle = new Bundle();
//        bundle.putInt(DATA_STATION_CODE, requestCode);
//        intent.putExtra()
//
//    }


    private void back(Station station) {
        Intent intent = new Intent();
        Bundle bundle = new Bundle();
        bundle.putString(DATA_STATION_CODE, station.getNameUpper());
        intent.putExtra(DATA_BUNDLE, bundle);
        setResult(SUCCESS_CODE, intent);
        finish();
    }


    private void initView() {
        GridView gridView = findViewById(R.id.gridView_city);
        gridView.setAdapter(adapter);
        gridView.setOnItemClickListener((parent, view, position, id) -> back(adapter.getItem(position)));
        EditText textViewInput = findViewById(R.id.edit_text_select_city_name);
        textViewInput.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                // 更改下方的显示数据
                refreshStationList(s);
            }
        });
    }

    /**
     * 刷新车站列表
     *
     * @param charSequence 输入
     */
    private void refreshStationList(CharSequence charSequence) {
        String string = charSequence.toString().trim();
        if (TextUtils.isEmpty(string)||Tools.regNumber(string)) {
            return;
        }
        Disposable disposable = Observable.just(dataBaseService.selectStationList(string))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(adapter::setStations);
    }


    private void initData() {
        dataBaseService = DataBaseTempApiImpl.getInstance();
        adapter = new StationAdapter(this, new ArrayList<>());
        if (null == defaultStations) {
            defaultStations = new ArrayList<>();
            Disposable disposable = Observable.fromArray(Tools.STATION_NAME_UPPER)
                    .map(dataBaseService::selectStationByNameUpper)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(defaultStations::add,
                            throwable -> Log.e(TAG, "accept: ", throwable),
                            () -> adapter.setStations(defaultStations));
        }
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                break;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
