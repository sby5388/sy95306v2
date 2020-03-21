package com.by5388.sy95306v2.module.shenyang.detail;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.by5388.sy95306v2.R;
import com.by5388.sy95306v2.base.BaseActivity;
import com.by5388.sy95306v2.module.shenyang.adapter.TrainDetailListAdapter;
import com.by5388.sy95306v2.module.shenyang.bean.TrainDetail;
import com.by5388.sy95306v2.module.shenyang.detail.presenter.DetailPresenter;
import com.by5388.sy95306v2.module.shenyang.detail.presenter.IDetailPresenter;
import com.by5388.sy95306v2.module.shenyang.detail.view.IDetailView;
import com.by5388.sy95306v2.module.shenyang.net.api.SyService;

import java.util.ArrayList;
import java.util.List;

import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author by5388  on 2018/7/29.
 */

public class TrainDetailActivity extends BaseActivity implements IDetailView {
    public static final String TAG = "TrainDetailActivity";
    @BindView(R.id.textView_train_code)
    TextView trainCode;
    @BindView(R.id.textView_train_stations)
    TextView trainStation;
    @BindView(R.id.textView_train_date)
    TextView trainDate;
    @BindView(R.id.recyclerView_train_detail)
    RecyclerView recyclerView;
    @BindView(R.id.swipeRefreshLayout)
    SwipeRefreshLayout swipeRefreshLayout;
    private TrainDetailListAdapter listAdapter;
    private IDetailPresenter presenter;
    /**
     * 日期
     */
    private int currentDate = 20180614;
    /**
     * 车次
     */
    private String code = "";


    /**
     * Intent
     *
     * @param context      上下文
     * @param selectedDate 日期
     * @param code         车次
     * @return intent
     */
    public static Intent newIntent(Context context, int selectedDate, String code) {
        Intent intent = new Intent(context, TrainDetailActivity.class);
        Bundle bundle = new Bundle();
        bundle.putInt(SyService.TRAIN_DATE, selectedDate);
        bundle.putString(SyService.TRAIN_CODE, code);
        intent.putExtra(DATA_BUNDLE, bundle);
        return intent;
    }


    @Override
    protected int getLayoutViewID() {
        return R.layout.activity_train_detail;
    }

    @Override
    protected void initData() {
        Intent intent = getIntent();
        Bundle bundle = intent.getBundleExtra(DATA_BUNDLE);
        if (null != bundle) {
            currentDate = bundle.getInt(SyService.TRAIN_DATE);
            code = bundle.getString(SyService.TRAIN_CODE);
        }
        presenter = new DetailPresenter(this);
        listAdapter = new TrainDetailListAdapter(this, new ArrayList<>());
    }

    @Override
    protected void initView() {
        ButterKnife.bind(this);
        swipeRefreshLayout.setColorSchemeResources(R.color.colorPrimary,
                R.color.colorPrimaryDark, R.color.colorAccent,
                R.color.colorAccent);
        swipeRefreshLayout.setOnRefreshListener(this::loadData);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        recyclerView.setAdapter(listAdapter);
    }

    @Override
    protected void loadData() {
        presenter.getDetailData(currentDate, code);
    }


    @Override
    public void showLoading() {
        recyclerView.setVisibility(View.GONE);
        swipeRefreshLayout.setRefreshing(true);
    }

    @Override
    public void finishLoading() {
        recyclerView.setVisibility(View.VISIBLE);
        swipeRefreshLayout.setRefreshing(false);
    }

    @Override
    public void setStations(List<TrainDetail> details) {
        listAdapter.setDetails(details);
    }

    @Override
    public void showErrorMessage(String message) {
        toast(message);
        finish();
    }

    @Override
    public void unSubscribe() {
        presenter.unSubscribe();
    }

    @Override
    protected void onDestroy() {
        unSubscribe();
        presenter = null;
        super.onDestroy();
    }

    @Override
    public void updateTopView(String trainName, String code) {
        trainStation.setText(trainName);
        trainCode.setText(code);
        trainDate.setText(String.valueOf(currentDate));
    }

}
