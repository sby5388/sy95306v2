package com.by5388.sy95306.detail;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import androidx.appcompat.app.ActionBar;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.by5388.sy95306.BaseListActivity;
import com.by5388.sy95306.R;
import com.by5388.sy95306.adapter.TrainDetailAdapter;
import com.by5388.sy95306.bean.TrainDetail;
import com.by5388.sy95306.common.ImageTool;
import com.by5388.sy95306.detail.presenter.DetailPresenter;
import com.by5388.sy95306.detail.presenter.IDetailPresenter;
import com.by5388.sy95306.detail.view.IDetailView;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;

import static com.by5388.sy95306.fragment.ListFragment.DATA_BUNDLE;
import static com.by5388.sy95306.net.TrainNumberService.TRAIN_CODE;
import static com.by5388.sy95306.net.TrainNumberService.TRAIN_DATE;

/**
 * 车次的停站信息
 *
 * @author by5388
 * @date 20180614
 */
public class TrainDetailActivity extends BaseListActivity implements IDetailView {
    private static final String TAG = "TrainDetailActivity";
    private TrainDetailAdapter mAdapter;
    private TextView trainCode, trainStation, trainDate;
    SwipeRefreshLayout swipeRefreshLayout;
    private IDetailPresenter mPresenter;

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


    public static Intent newIntent(Context context, int date, String trainCode) {
        Intent intent = new Intent(context, TrainDetailActivity.class);
        Bundle bundle1 = new Bundle();
        bundle1.putInt(TRAIN_DATE, date);
        bundle1.putString(TRAIN_CODE, trainCode);
        intent.putExtra(DATA_BUNDLE, bundle1);
        return intent;
    }



    private void initData() {
        Intent intent = getIntent();
        Bundle bundle = intent.getBundleExtra(DATA_BUNDLE);
        if (null != bundle) {
            currentDate = bundle.getInt(TRAIN_DATE);
            code = bundle.getString(TRAIN_CODE);
        }
        mAdapter = new TrainDetailAdapter(this);
        mPresenter = new DetailPresenter(this);
    }

    private void initView() {
        swipeRefreshLayout = findViewById(R.id.swipeRefreshLayout);
        swipeRefreshLayout.setColorSchemeResources(R.color.colorPrimary,
                R.color.colorPrimaryDark, R.color.colorAccent,
                R.color.colorAccent);
        swipeRefreshLayout.setOnRefreshListener(this::loadData);
        topView = findViewById(R.id.lly_top);
        secondView = findViewById(R.id.lly_second);
        bottomView = findViewById(R.id.lly_bottom);

        trainCode = findViewById(R.id.textView_train_code);
        trainStation = findViewById(R.id.textView_train_stations);
        trainDate = findViewById(R.id.textView_train_date);
        recyclerView = findViewById(R.id.recyclerView_train_detail);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        recyclerView.setAdapter(mAdapter);
        findViewById(R.id.imageView_refresh).setOnClickListener(v -> loadData());
        findViewById(R.id.imageView_return).setOnClickListener(v -> finish());
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
        mPresenter.getDetailData(currentDate, code);
    }


    @Override
    protected void showException(Throwable e) {
        Log.d(TAG, "onError: " + e.getMessage());
        //TODO 针对网络不可用、超时、数据错误等情况进行提示
        Log.d(TAG, "onError:'JSON格式转换错误' " + e.getLocalizedMessage());
        Toast.makeText(TrainDetailActivity.this, "未查到相关车次信息", Toast.LENGTH_SHORT).show();
        finish();
    }

    @Override
    public void showLoading() {
        swipeRefreshLayout.setRefreshing(true);
        recyclerView.setVisibility(View.GONE);
    }

    @Override
    public void finishLoading() {
        swipeRefreshLayout.setRefreshing(false);
        recyclerView.setVisibility(View.VISIBLE);
    }

    @Override
    public void setStations(List<TrainDetail> trainDetails) {
        if (trainDetails != null) {
            mAdapter.setDetails(trainDetails);
        }
    }

    @Override
    public void showErrorMessage(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void unSubscribe() {
        if (mPresenter != null) {
            mPresenter.unSubscribe();
        }
    }

    @Override
    public void updateTopView(String trainName, String code) {
        trainCode.setText(code);
        trainStation.setText(trainName);
        trainDate.setText(String.valueOf(currentDate));
        imageName = code + " " + trainName + " " + currentDate + "-";
    }
}
