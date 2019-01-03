package com.by5388.sy95306v2.shenyang.list;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.by5388.sy95306v2.R;
import com.by5388.sy95306v2.base.BaseActivity;
import com.by5388.sy95306v2.shenyang.detail.TrainDetailActivity;
import com.by5388.sy95306v2.shenyang.list.presenter.ITrainListPresenter;
import com.by5388.sy95306v2.shenyang.list.presenter.TrainListPresenter;
import com.by5388.sy95306v2.shenyang.list.view.ITrainListView;
import com.by5388.sy95306v2.shenyang.adapter.TrainListAdapter;
import com.by5388.sy95306v2.shenyang.bean.Station;
import com.by5388.sy95306v2.shenyang.bean.TrainNumber;
import com.by5388.sy95306v2.dialog.TrainFilterDialog;
import com.by5388.sy95306v2.dialog.TrainFilterDialog.UpdateFilterDataCallBack;
import com.by5388.sy95306v2.shenyang.net.api.SyService;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindBitmap;
import butterknife.BindView;
import butterknife.BindViews;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @author by5388  on 2018/7/29.
 */

public class TrainNumberListActivity extends BaseActivity implements ITrainListView, UpdateFilterDataCallBack {
    private TrainFilterDialog dialog;

    @BindViews({R.id.image_view_1, R.id.image_view_2, R.id.image_view_3})
    List<ImageView> imageViews;
    @BindView(R.id.textView_show_time)
    TextView textViewDate;
    @BindViews({R.id.lly_menu_start_time, R.id.lly_menu_spend_time, R.id.lly_menu_end_time, R.id.lly_menu_filter})
    List<ConstraintLayout> constraintLayouts;
    @BindView(R.id.textView_train_count)

    TextView textViewCount;
    @BindView(R.id.recycler_View_train_list)

    RecyclerView recyclerView;
    @BindBitmap(R.drawable.ic_up)


    Bitmap bitmapUp;
    @BindBitmap(R.drawable.ic_down)

    Bitmap bitmapDown;
    @BindView(R.id.textView_show_city)
    TextView stationNames;
    @BindView(R.id.swipeRefreshLayout)
    SwipeRefreshLayout swipeRefreshLayout;
    /**
     * 默认起始车站：沈阳北站
     */
    private String fromStationCode = "STB";
    /**
     * 默认终点车站：北京站
     */
    private String toStationCode = "BJP";
    /**
     * 默认选择日期：待优化：应当为当日
     */
    private int selectedDate;
    private TrainListAdapter adapter;
    private ITrainListPresenter presenter;
    /**
     * 从上往下，默认的状态
     */
    private boolean isUp = true;
    /**
     * 默认起始
     */
    private int defaultPosition = 0;

    /**
     * 进入 TrainNumberListActivity
     *
     * @param context      上下文环境
     * @param selectedDate 日期
     * @param fromStation  出发站
     * @param toStation    目的站
     * @return intent
     */
    public static Intent newIntent(Context context, int selectedDate, Station fromStation, Station toStation) {
        Intent intent = new Intent(context, TrainNumberListActivity.class);
        Bundle bundle = new Bundle();
        bundle.putInt(SyService.TRAIN_DATE, selectedDate);
        bundle.putString(SyService.FROM_STATION, fromStation.getNameUpper());
        bundle.putString(SyService.TO_STATION, toStation.getNameUpper());
        intent.putExtra(DATA_BUNDLE, bundle);
        return intent;
    }

    @Override
    protected int getLayoutViewID() {
        return R.layout.activity_train_list_order;
    }

    @Override
    protected void initData() {
        Intent intent = getIntent();
        Bundle bundle = intent.getBundleExtra(DATA_BUNDLE);
        if (null != bundle) {
            selectedDate = bundle.getInt(SyService.TRAIN_DATE);
            fromStationCode = bundle.getString(SyService.FROM_STATION);
            toStationCode = bundle.getString(SyService.TO_STATION);
        }
        presenter = new TrainListPresenter(this);

        TrainOnClickListener listener = (view, position) -> {
            TrainNumber number = adapter.getItem(position);
            if (null == number) {
                return;
            }
            startActivity(TrainDetailActivity.newIntent(this, selectedDate, number.getSTCODE()));
        };

        adapter = new TrainListAdapter(new ArrayList<>(), this, listener);
    }

    @OnClick({R.id.lly_menu_start_time, R.id.lly_menu_spend_time, R.id.lly_menu_end_time, R.id.lly_menu_filter})
    public void sortTrainNumber(ConstraintLayout view) {
        int position = constraintLayouts.indexOf(view);
        final int showFilter = 3;
        if (showFilter == position) {
            showFilterDialog();
            return;
        }
        sortTrainNumber(position);
    }

    /**
     * 跟换图片 后台处理排序
     *
     * @param position 位置
     */
    private void sortTrainNumber(int position) {
        ImageView view = imageViews.get(position);
        if (defaultPosition == position) {
            if (isUp) {
                view.setImageBitmap(bitmapDown);
            } else {
                view.setImageBitmap(bitmapUp);
            }
            isUp = !isUp;
        } else {
            view.setImageBitmap(bitmapUp);
            isUp = true;
            defaultPosition = position;
        }
        for (ImageView imageView : imageViews) {
            if (!view.equals(imageView)) {
                imageView.setImageResource(0);
            }
        }
        presenter.sortTrainList(defaultPosition, isUp);
    }

    /**
     * 筛选对话框
     */
    private void showFilterDialog() {
        if (null == dialog) {
            dialog = new TrainFilterDialog(this, this);
        }
        if (dialog.isShowing()) {
            dialog.dismiss();
        }
        dialog.refresh().show();
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
    }

    @Override
    protected void loadData() {
        textViewDate.setText(String.valueOf(selectedDate));
        stationNames.setText(presenter.getStationNames(fromStationCode, toStationCode));
        recyclerView.setAdapter(adapter);
        presenter.getTrainList(selectedDate, fromStationCode, toStationCode);
    }

    @Override
    public void updateTrainList(List<TrainNumber> numbers) {
        adapter.setTrainNumbers(numbers);
        String show = "共" + numbers.size() + "列";
        textViewCount.setText(show);
    }

    @Override
    public void showErrorMessage(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
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
    protected void onDestroy() {
        if (null != presenter) {
            presenter.unSubscribe();
            presenter = null;
        }
        super.onDestroy();
    }

    @Override
    protected boolean isShowActionBar() {
        return true;
    }

    @Override
    public void updateFilterData() {
        presenter.sortTrainList(defaultPosition, isUp);
    }
}
