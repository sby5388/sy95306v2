package com.by5388.sy95306.list;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.by5388.sy95306.BaseListActivity;
import com.by5388.sy95306.R;
import com.by5388.sy95306.adapter.TrainListAdapter;
import com.by5388.sy95306.bean.Station;
import com.by5388.sy95306.bean.TrainNumber;
import com.by5388.sy95306.common.ImageTool;
import com.by5388.sy95306.common.StaticData;
import com.by5388.sy95306.database.DataBaseApi;
import com.by5388.sy95306.database.DataBaseTempApiImpl;
import com.by5388.sy95306.detail.TrainDetailActivity;
import com.by5388.sy95306.list.presenter.ITrainListPresenter;
import com.by5388.sy95306.list.presenter.TrainListPresenter;
import com.by5388.sy95306.list.view.ITrainListView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import io.reactivex.Observable;

import static com.by5388.sy95306.fragment.ListFragment.DATA_BUNDLE;
import static com.by5388.sy95306.fragment.ListFragment.DIALOG_TITLE;
import static com.by5388.sy95306.net.TrainNumberService.FROM_STATION;
import static com.by5388.sy95306.net.TrainNumberService.TO_STATION;
import static com.by5388.sy95306.net.TrainNumberService.TRAIN_DATE;

/**
 * @author by5388
 * @date 20180607
 * 车次列表
 */
public class TrainListActivity extends BaseListActivity implements ITrainListView {
    private static final String TAG = "TrainListActivity";
    private TrainListAdapter mAdapter;
    private List<TrainNumber> trainNumbers;
    private List<ImageView> imageViews;
    private TextView textViewCount;
    SwipeRefreshLayout swipeRefreshLayout;
    /**
     * 数据未加载完成前，屏蔽点击事件（排序、过滤）
     */
    private boolean isEnable = false;
    private ITrainListPresenter mPresenter;


    /**
     * 排序方式
     * 0：出发时刻
     * 1：消耗时间
     * 2：到达时刻
     */
    private int currentPosition = 0;
    /**
     * 排序向上 :0
     * 排序向下 :1
     */
    private int currentStatus = 0;


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

    public static Intent newIntent(Context mContext, int mSelectedDate, String mFromStationCode, String mToStationCode) {
        Intent intent = new Intent(mContext, TrainListActivity.class);
        Bundle bundle = new Bundle();
        bundle.putInt(TRAIN_DATE, mSelectedDate);
        bundle.putString(FROM_STATION, mFromStationCode);
        bundle.putString(TO_STATION, mToStationCode);
        intent.putExtra(DATA_BUNDLE, bundle);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_train_list);
        ActionBar actionBar = getSupportActionBar();
        if (null != actionBar) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        initData();
        initView();
        loadData();

    }

    /**
     * 加载数据
     */
    private void loadData() {
        mPresenter.getTrainList(selectedDate, fromStationCode, toStationCode);
    }


    /**
     * 初始化数据：
     * 1.getIntent()中获取数据
     * 2.加载页面组件:声明
     * 3.开启线程 加载数据+进度条
     */
    private void initData() {
        Intent intent = getIntent();
        Bundle bundle = intent.getBundleExtra(DATA_BUNDLE);
        if (null != bundle) {
            selectedDate = bundle.getInt(TRAIN_DATE);
            fromStationCode = bundle.getString(FROM_STATION);
            toStationCode = bundle.getString(TO_STATION);
        }
        mPresenter = new TrainListPresenter(this);

        imageViews = new ArrayList<>();
        trainNumbers = new ArrayList<>();

        TrainOnClickListener listener = (view, position) -> {
            TrainNumber trainNumber = mAdapter.getItem(position);
            if (trainNumber == null) {
                return;
            }
            startActivity(TrainDetailActivity.newIntent(this, selectedDate, trainNumber.getSTCODE()));
        };
        mAdapter = new TrainListAdapter(this, trainNumbers, listener);
    }


    private void initView() {

        topView = findViewById(R.id.lly_top_show);
        secondView = findViewById(R.id.lly_second_menu);
        bottomView = findViewById(R.id.lly_bottom);
        swipeRefreshLayout = findViewById(R.id.swipeRefreshLayout);
        swipeRefreshLayout.setColorSchemeResources(R.color.colorPrimary,
                R.color.colorPrimaryDark, R.color.colorAccent,
                R.color.colorAccent);
        swipeRefreshLayout.setOnRefreshListener(this::loadData);
        initMenuView();
        TextView textViewDate = findViewById(R.id.textView_show_time);
        textViewDate.setText(String.valueOf(selectedDate));
        TextView stationNames = findViewById(R.id.textView_show_city);
        stationNames.setText(getStationNames(fromStationCode, toStationCode));
        textViewCount = findViewById(R.id.textView_train_count);
        findViewById(R.id.imageView_refresh).setOnClickListener(v -> loadData());
        findViewById(R.id.imageView_return).setOnClickListener(v -> finish());

        imageViews.add((ImageView) findViewById(R.id.image_view_1));
        imageViews.add((ImageView) findViewById(R.id.image_view_2));
        imageViews.add((ImageView) findViewById(R.id.image_view_3));
        recyclerView = findViewById(R.id.recycler_View_train_list);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        recyclerView.setAdapter(mAdapter);


    }


    private static List<Integer> selectedType(boolean[] selected) {
        List<Integer> trainTypes = new ArrayList<>();
        if (selected[0]) {
            trainTypes.add(0);
        }
        if (selected[1]) {
            trainTypes.add(1);
        }
        if (selected[2]) {
            trainTypes.add(2);
        }
        if (selected[3]) {
            trainTypes.add(3);
            trainTypes.add(4);
        }
        if (selected[4]) {
            trainTypes.add(5);
            trainTypes.add(6);
        }
        if (selected[5]) {
            trainTypes.add(0);
        }
        return trainTypes;
    }

    /**
     * 列车筛选
     */
    private void showDialog() {
        if (!isEnable) {
            Toast.makeText(this, "加载中，稍后重试", Toast.LENGTH_SHORT).show();
            return;
        }
        boolean[] selected = StaticData.SELECTED;
        boolean[] newSelected = Arrays.copyOf(selected, 6);
        String[] trainTypes = getResources().getStringArray(R.array.train_type);
        AlertDialog alertDialog = new AlertDialog.Builder(this)
                .setTitle(DIALOG_TITLE)
                .setMultiChoiceItems(trainTypes, newSelected, (dialog, which, isChecked) -> newSelected[which] = isChecked)
                .setNegativeButton(android.R.string.cancel, (DialogInterface dialog, int which) -> dialog.dismiss())
                .setPositiveButton(android.R.string.ok, (dialog, which) -> {
                    // TODO: 2018/10/27 数组复制
                    System.arraycopy(newSelected, 0, selected, 0, 6);
                    isEnable = false;
                    swipeRefreshLayout.setRefreshing(true);
                    recyclerView.setVisibility(View.GONE);
                    dialog.dismiss();
                })
                .create();
        alertDialog.show();
    }


    /**
     * 始末站名
     *
     * @param fromStationCode 开始站名
     * @param toStationCode   结束站名
     * @return 开始站名-结束站名
     */
    private String getStationNames(String fromStationCode, String toStationCode) {
        DataBaseApi dataBaseService = DataBaseTempApiImpl.getInstance();
        Station startStation = dataBaseService.selectStationByNameUpper(fromStationCode);
        Station endStation = dataBaseService.selectStationByNameUpper(toStationCode);
        if (null == startStation || null == endStation) {
            return "";
        }
        return startStation.getName() + "-" + endStation.getName();
    }

    /**
     * 初始化顶部相关的排序、过滤组件
     */
    private void initMenuView() {
        findViewById(R.id.lly_menu_start_time).setOnClickListener(v -> sortTrainNumberList(0));
        findViewById(R.id.lly_menu_spend_time).setOnClickListener(v -> sortTrainNumberList(1));
        findViewById(R.id.lly_menu_end_time).setOnClickListener(v -> sortTrainNumberList(2));
        findViewById(R.id.lly_menu_filter).setOnClickListener(v -> showDialog());
    }

    private void sort(List<TrainNumber> numbers) {
        Collections.sort(numbers, new TrainNumberSort(currentPosition, currentStatus));
    }

    /**
     * 排序
     */
    private class TrainNumberSort implements Comparator<TrainNumber> {
        final int status;
        final int position;

        TrainNumberSort(int position, int status) {
            this.status = status;
            this.position = position;
        }

        @Override
        public int compare(TrainNumber o1, TrainNumber o2) {
            try {
                if (position == 0) {
                    if (status == 0) {
                        return o1.getStartTime() - o2.getStartTime();
                    } else {
                        return o2.getStartTime() - o1.getStartTime();
                    }
                } else if (position == 1) {
                    if (status == 0) {
                        return o1.getSpendTime() - o2.getSpendTime();
                    } else {
                        return o2.getSpendTime() - o1.getSpendTime();
                    }
                } else if (position == 2) {
                    if (status == 0) {
                        return o1.getArriveTime() - o2.getArriveTime();
                    } else {
                        return o2.getArriveTime() - o1.getArriveTime();
                    }
                }
            } catch (NumberFormatException e) {
                return 0;
            }
            return 0;
        }

    }


    private void sortTrainNumberList(int position) {
        if (!isEnable) {
            Toast.makeText(this, "加载中，稍后重试", Toast.LENGTH_SHORT).show();
            return;
        }
        Log.d(TAG, "sortTrainNumberList: 排序");

        //上<--->下
        if (currentPosition == position) {
            currentStatus = (currentStatus == 0) ? 1 : 0;
        } else {
            currentPosition = position;
            currentStatus = 0;
        }
        ImageView imageViewSelected = imageViews.get(currentPosition);
        for (ImageView imageView : imageViews) {
            if (imageView != imageViewSelected) {
                imageView.setImageResource(0);
            }
        }
        if (currentStatus == 0) {
            imageViewSelected.setImageResource(R.drawable.ic_up);
        } else {
            imageViewSelected.setImageResource(R.drawable.ic_down);
        }
//        mPresenter.sortTrainList();
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

    @Override
    public void updateTrainList(List<TrainNumber> numbers) {
        if (numbers != null) {
            mAdapter.setTrainNumbers(numbers);
            trainNumbers = numbers;
            isEnable = true;
            String show = "共" + numbers.size() + "列";
            textViewCount.setText(show);
        }
    }

    @Override
    public void showErrorMessage(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
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
}
