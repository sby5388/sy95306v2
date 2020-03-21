package com.by5388.sy95306v2.module.shenyang.list;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.by5388.sy95306v2.R;
import com.by5388.sy95306v2.base.BaseActivity;
import com.by5388.sy95306v2.dialog.TrainFilterDialog;
import com.by5388.sy95306v2.dialog.TrainFilterDialog.UpdateFilterDataCallBack;
import com.by5388.sy95306v2.dialog.TrainFilterDialog2;
import com.by5388.sy95306v2.module.shenyang.adapter.TrainListAdapter;
import com.by5388.sy95306v2.module.shenyang.bean.Station;
import com.by5388.sy95306v2.module.shenyang.bean.TrainNumber;
import com.by5388.sy95306v2.module.shenyang.detail.TrainDetailActivity;
import com.by5388.sy95306v2.module.shenyang.list.presenter.ITrainListPresenter;
import com.by5388.sy95306v2.module.shenyang.list.presenter.TrainListPresenter;
import com.by5388.sy95306v2.module.shenyang.list.view.ITrainListView;
import com.by5388.sy95306v2.module.shenyang.net.api.SyService;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import butterknife.BindBitmap;
import butterknife.BindView;
import butterknife.BindViews;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @author by5388  on 2018/7/29.
 */

public class TrainNumberListActivity extends BaseActivity implements ITrainListView, UpdateFilterDataCallBack {
    private static final int REQUEST_CODE_FILTER = 2000;
    @BindViews({R.id.image_view_1, R.id.image_view_2, R.id.image_view_3})
    List<ImageView> imageViews;
    @BindView(R.id.textView_show_time)
    TextView textViewDate;
    @BindViews({R.id.lly_menu_start_time, R.id.lly_menu_spend_time, R.id.lly_menu_end_time})
    List<ConstraintLayout> constraintLayouts;
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
    private TrainFilterDialog dialog;
    private SortType mDefaultSortType = SortType.StartTime;
    private SortType mSortType = mDefaultSortType;
    private TextView textViewCount;
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
     * TODO 20191126 加载完成,避免空数据时 点击闪退
     */
    private boolean mLoaded = false;

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
        if (bundle == null) {
            finish();
            return;
        }
        selectedDate = bundle.getInt(SyService.TRAIN_DATE);
        fromStationCode = bundle.getString(SyService.FROM_STATION);
        toStationCode = bundle.getString(SyService.TO_STATION);
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


    @Override
    protected void initView() {
        ButterKnife.bind(this);
        findViewById(R.id.lly_menu_filter).setOnClickListener(v -> showFilterDialog());
        if (false) {
            // TODO: 2019/11/26 未实现，继续使用原来的api
            findViewById(R.id.lly_menu_start_time).setOnClickListener(this::sortTrainNumber);
            findViewById(R.id.lly_menu_spend_time).setOnClickListener(this::sortTrainNumber);
            findViewById(R.id.lly_menu_end_time).setOnClickListener(this::sortTrainNumber);
        }

        swipeRefreshLayout.setColorSchemeResources(R.color.colorPrimary,
                R.color.colorPrimaryDark, R.color.colorAccent,
                R.color.colorAccent);
        swipeRefreshLayout.setOnRefreshListener(this::loadData);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        textViewCount = findViewById(R.id.textView_train_count);
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
        mLoaded = true;
    }

    @Override
    public void showErrorMessage(String message) {
        toast(message);
        finish();
    }


    @Override
    public void onStartLoading() {
        mLoaded = false;
        swipeRefreshLayout.setRefreshing(true);
        recyclerView.setVisibility(View.GONE);
    }

    @Override
    public void onFinishLoading() {
        swipeRefreshLayout.setRefreshing(false);
        recyclerView.setVisibility(View.VISIBLE);
        mLoaded = true;
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
    public void updateFilterData() {
        presenter.sortTrainList(defaultPosition, isUp);
    }

    @OnClick({R.id.lly_menu_start_time, R.id.lly_menu_spend_time, R.id.lly_menu_end_time})
    public void sortTrainNumber(ConstraintLayout view) {
        if (!mLoaded) {
            return;
        }
        int position = constraintLayouts.indexOf(view);
        sortTrainNumber(position);
    }

    private void sortTrainNumber(View view) {
        if (!mLoaded) {
            toast("请稍候");
            return;
        }
        final int id = view.getId();
        final SortType sortType = getSortType(id);
        // TODO: 2019/11/26

    }

    private SortType getSortType(int viewId) {
        switch (viewId) {
            case R.id.lly_menu_spend_time:
                return SortType.SpeedTime;
            case R.id.lly_menu_end_time:
                return SortType.ArriveTime;
            case R.id.lly_menu_start_time:
            default:
                return SortType.StartTime;
        }
    }


    /**
     * 跟换图片 后台处理排序
     *
     * @param position 位置
     */
    private void sortTrainNumber(int position) {
        if (!mLoaded) {
            toast("请稍候");
            return;
        }
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
        if (!mLoaded) {
            return;
        }
        // TODO: 2020/3/19 调试新的类型筛选
        if (true) {

            final TrainFilterDialog2 trainFilterDialog2 = TrainFilterDialog2.newInstance();
            //trainFilterDialog2.setTargetFragment(this, REQUEST_CODE_FILTER);
            trainFilterDialog2.show(Objects.requireNonNull(getSupportFragmentManager()), TrainFilterDialog2.class.getName());
            return;
        }


        if (null == dialog) {
            dialog = new TrainFilterDialog(this, this);
        }
        if (dialog.isShowing()) {
            dialog.dismiss();
        }
        dialog.refresh().show();
    }

}
