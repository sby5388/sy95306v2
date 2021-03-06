package com.by5388.sy95306v2.module.tiezong.detail;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ListView;

import com.by5388.sy95306v2.R;
import com.by5388.sy95306v2.module.tiezong.api.pass.code.GetPassCodeImpl;
import com.by5388.sy95306v2.module.tiezong.api.pass.code.IGetPassCodeService;
import com.by5388.sy95306v2.module.tiezong.bean.number.NumberDataBean;

import java.util.List;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * @author by5388  on 2019/1/9.
 */
public class TzDetailActivity extends AppCompatActivity {
    public static final String TAG = "TzDetailActivity";
    private static final String BUNDLE_NAME = "bundleName";
    private static final String TRAIN_NO = "trainNo";
    private static final String FROM_STATION_CODE = "fromStationCode";
    private static final String TO_STATION_CODE = "toStationCode";
    private static final String DATE = "date";

    public static Intent newIntent(Context context, String trainCode, String fromStationCode, String toStationCode, String date) {
        Intent intent = new Intent(context, TzDetailActivity.class);
        Bundle bundle = new Bundle();
        bundle.putString(TRAIN_NO, trainCode);
        bundle.putString(FROM_STATION_CODE, fromStationCode);
        bundle.putString(TO_STATION_CODE, toStationCode);
        bundle.putString(DATE, date);
        intent.putExtra(BUNDLE_NAME, bundle);
        return intent;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.acticity_temp);
        initView();
        loadData();
    }

    private void initView() {
        ListView mListView = findViewById(R.id.listView_tz_detail);
    }

    private void loadData() {
        Bundle bundle = getIntent().getBundleExtra(BUNDLE_NAME);
        if (bundle == null) {
            return;
        }
        String trainCode = bundle.getString(TRAIN_NO);
        String fromStationCode = bundle.getString(FROM_STATION_CODE);
        String toStationCode = bundle.getString(TO_STATION_CODE);
        String date = bundle.getString(DATE);


        IGetPassCodeService service = new GetPassCodeImpl();

        service.getListNumberDataBean(trainCode, fromStationCode, toStationCode, date)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<List<NumberDataBean>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(List<NumberDataBean> mNumberDataBeans) {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });


    }
}
