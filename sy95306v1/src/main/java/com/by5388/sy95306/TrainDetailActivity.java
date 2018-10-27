package com.by5388.sy95306;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.by5388.sy95306.adapter.TrainDetailListAdapter;
import com.by5388.sy95306.bean.TrainDetail;
import com.by5388.sy95306.common.ImageTool;
import com.by5388.sy95306.net.NetTools;
import com.by5388.sy95306.net.TrainNumberService;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;

import static com.by5388.sy95306.fragment.FirstFragment.DATA_BUNDLE;
import static com.by5388.sy95306.net.TrainNumberService.TRAIN_CODE;
import static com.by5388.sy95306.net.TrainNumberService.TRAIN_DATE;

/**
 * 车次的停站信息
 *
 * @author by5388
 * @date 20180614
 */
public class TrainDetailActivity extends BaseListActivity {
    private static final String TAG = "TrainDetailActivity";
    private TrainDetailListAdapter listAdapter;
    private TextView trainCode, trainStation, trainDate;
    private ConstraintLayout progressBar;


    /**
     * 日期
     */
    private int currentDate = 20180614;
    /**
     * 车次
     */
    private String code = "D7512";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_train_detail);
        ActionBar actionBar = getSupportActionBar();
        if (null != actionBar) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
        initData();
        initView();
        loadData();
    }


    private void initData() {
        Intent intent = getIntent();
        Bundle bundle = intent.getBundleExtra(DATA_BUNDLE);
        if (null != bundle) {
            currentDate = bundle.getInt(TRAIN_DATE);
            code = bundle.getString(TRAIN_CODE);
        }
        listAdapter = new TrainDetailListAdapter(this, new ArrayList<>());

    }

    private void initView() {
        topView = findViewById(R.id.lly_top);
        secondView = findViewById(R.id.lly_second);
        bottomView = findViewById(R.id.lly_bottom);

        trainCode = findViewById(R.id.textView_train_code);
        trainStation = findViewById(R.id.textView_train_stations);
        trainDate = findViewById(R.id.textView_train_date);
        recyclerView = findViewById(R.id.recyclerView_train_detail);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        recyclerView.setAdapter(listAdapter);
        findViewById(R.id.imageView_refresh).setOnClickListener(v -> loadData());
        findViewById(R.id.imageView_return).setOnClickListener(v -> finish());
        progressBar = findViewById(R.id.lly_progress_bar);
    }


    @Override
    protected Observable<Bitmap> createBitmap() {
        return Observable.create(e -> {
            List<Bitmap> bitmaps = new ArrayList<>(4);
            bitmaps.add(ImageTool.getBitmap(topView));
            bitmaps.add(ImageTool.getBitmap(secondView));
            bitmaps.add(ImageTool.shotRecyclerView(recyclerView));
            bitmaps.add(ImageTool.getBitmap(bottomView));
            e.onNext(ImageTool.longImage(bitmaps));
            e.onComplete();
        });
    }

    /**
     * 从服务器加载数据
     */
    private void loadData() {
        progressBar.setVisibility(View.VISIBLE);
        Retrofit retrofit = NetTools.getRetrofit();
        TrainNumberService trainNumberService = retrofit.create(TrainNumberService.class);
        disposable = trainNumberService.getTrainByTrainCode(currentDate, code)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::updateData, throwable -> showException(throwable));
               // .subscribe(trainDetails -> updateData(trainDetails), throwable -> showException(throwable));
    }


    @Override
    protected void showException(Throwable e) {
        Log.d(TAG, "onError: " + e.getMessage());
        //TODO 针对网络不可用、超时、数据错误等情况进行提示
        Log.d(TAG, "onError:'JSON格式转换错误' " + e.getLocalizedMessage());
        Toast.makeText(TrainDetailActivity.this, "未查到相关车次信息", Toast.LENGTH_SHORT).show();
        finish();
    }

    /**
     * 刷新数据、显示新的标题
     *
     * @param trainDetails 停站信息列表
     */
    private void updateData(@NonNull List<TrainDetail> trainDetails) {
        String code = getTrainCode(trainDetails);
        String trainStationName = getTrainName(trainDetails);
        listAdapter.setDetails(trainDetails);
        trainCode.setText(code);
        trainStation.setText(trainStationName);
        trainDate.setText(String.valueOf(currentDate));
        progressBar.setVisibility(View.GONE);
        recyclerView.setVisibility(View.VISIBLE);
        imageName = code + " " + trainStationName + " " + currentDate + "-";

    }

    private static String getTrainCode(@NonNull List<TrainDetail> trainDetails) {
        Set<String> codes = new HashSet<>();
        for (TrainDetail detail : trainDetails) {
            codes.add(detail.getSTCODE());
        }
        StringBuilder stringBuilder = new StringBuilder();
        for (String code : codes) {
            stringBuilder.append(code);
            stringBuilder.append("/");
        }
        String code = stringBuilder.toString();
        if (TextUtils.isEmpty(code)) {
            return code;
        }
        return code.substring(0, code.length() - 1);

    }

    private static String getTrainName(@NonNull List<TrainDetail> trainDetails) {
        return trainDetails.get(0).getSNAME() + "-" + trainDetails.get(trainDetails.size() - 1).getSNAME();
    }

}
