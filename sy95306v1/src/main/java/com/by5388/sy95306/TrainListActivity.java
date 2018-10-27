package com.by5388.sy95306;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.by5388.sy95306.adapter.TrainListAdapter;
import com.by5388.sy95306.bean.Station;
import com.by5388.sy95306.bean.TrainNumber;
import com.by5388.sy95306.common.ImageTool;
import com.by5388.sy95306.common.StaticData;
import com.by5388.sy95306.database.DataBaseImpl;
import com.by5388.sy95306.database.DataBaseTempAction;
import com.by5388.sy95306.fragment.FirstFragment;
import com.by5388.sy95306.net.NetTools;
import com.by5388.sy95306.net.TrainNumberService;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableSource;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

import static com.by5388.sy95306.fragment.FirstFragment.DATA_BUNDLE;
import static com.by5388.sy95306.net.TrainNumberService.FROM_STATION;
import static com.by5388.sy95306.net.TrainNumberService.TO_STATION;
import static com.by5388.sy95306.net.TrainNumberService.TRAIN_CODE;
import static com.by5388.sy95306.net.TrainNumberService.TRAIN_DATE;

/**
 * @author by5388
 * @date 20180607
 * 车次列表
 */
public class TrainListActivity extends BaseListActivity {
    private static final String TAG = "TrainListActivity";
    private TrainListAdapter trainListAdapter;
    private List<TrainNumber> trainNumbers;
    private List<TrainNumber> allTrainNumbers;
    private List<ImageView> imageViews;
    private TextView textViewCount;
    private Consumer<List<TrainNumber>> consumer;
    private Consumer<Throwable> throwableConsumer;
    private ConstraintLayout progressBar;
    private static final String DIALOG_TITLE = FirstFragment.DIALOG_TITLE;
    /**
     * 数据未加载完成前，屏蔽点击事件（排序、过滤）
     */
    private boolean isEnable = false;


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
        recyclerView.setVisibility(View.GONE);
        progressBar.setVisibility(View.VISIBLE);
        Comparator<TrainNumber> comparator = (o1, o2) -> o1.getStartTime() - o2.getStartTime();
        TrainNumberService service = NetTools.getRetrofit().create(TrainNumberService.class);
        disposable = refreshDisposable(
                service.getTrainNumberByP2P(selectedDate, fromStationCode, toStationCode)
                        .doOnNext(numbers -> allTrainNumbers = numbers)
                        //应该封装成单独一个Observable
                        .doOnNext(numbers -> Collections.sort(numbers, comparator))
                        .flatMap((Function<List<TrainNumber>, ObservableSource<List<TrainNumber>>>) numbers -> Observable.just(trainFilter(numbers, StaticData.SELECTED)))
        );

    }


    private void updateTrainNumbers(List<TrainNumber> trainNumbers) {
        trainListAdapter.setTrainNumbers(trainNumbers);
        String show = "共" + trainNumbers.size() + "列";
        textViewCount.setText(show);
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

        imageViews = new ArrayList<>();
        trainNumbers = new ArrayList<>();

        TrainOnClickListener listener = (view, position) -> {
            TrainNumber trainNumber = trainNumbers.get(position);
            Intent intent1 = new Intent(TrainListActivity.this, TrainDetailActivity.class);
            Bundle bundle1 = new Bundle();
            bundle1.putInt(TRAIN_DATE, selectedDate);
            bundle1.putString(TRAIN_CODE, trainNumber.getSTCODE());
            intent1.putExtra(DATA_BUNDLE, bundle1);
            startActivity(intent1);
        };
        trainListAdapter = new TrainListAdapter(trainNumbers, this, listener);
        throwableConsumer = this::showException;
        consumer = (List<TrainNumber> numbers) -> {
            trainNumbers = numbers;
            updateTrainNumbers(numbers);
            progressBar.setVisibility(View.GONE);
            recyclerView.setVisibility(View.VISIBLE);
            isEnable = true;
        };

    }


    private void initView() {

        topView = findViewById(R.id.lly_top_show);
        secondView = findViewById(R.id.lly_second_menu);
        bottomView = findViewById(R.id.lly_bottom);
        initMenuView();
        TextView textViewDate = findViewById(R.id.textView_show_time);
        textViewDate.setText(String.valueOf(selectedDate));
        TextView stationNames = findViewById(R.id.textView_show_city);
        stationNames.setText(getStationNames(fromStationCode, toStationCode));
        textViewCount = findViewById(R.id.textView_train_count);
        findViewById(R.id.imageView_refresh).setOnClickListener(v -> loadData());
        findViewById(R.id.imageView_return).setOnClickListener(v -> finish());

        progressBar = findViewById(R.id.lly_progress_bar);
        imageViews.add((ImageView) findViewById(R.id.image_view_1));
        imageViews.add((ImageView) findViewById(R.id.image_view_2));
        imageViews.add((ImageView) findViewById(R.id.image_view_3));
        recyclerView = findViewById(R.id.recycler_View_train_list);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        recyclerView.setAdapter(trainListAdapter);

    }


    /**
     * @param allTrainNumbers 所有的车次（未过滤）
     * @param selected        多选框
     * @return 过滤后的车次
     */
    private static List<TrainNumber> trainFilter(List<TrainNumber> allTrainNumbers, boolean[] selected) {
        if (selected[0]) {
            return allTrainNumbers;
        } else {
            List<Integer> trainTypes = selectedType(selected);
            Map<Integer, String> trainType = StaticData.getTrainType();
            List<TrainNumber> numbers = new ArrayList<>();
            for (TrainNumber number : allTrainNumbers) {
                String type = number.getTCCODE();
                for (Integer key : trainType.keySet()) {
                    if (type.equals(trainType.get(key)) && trainTypes.contains(key)) {
                        numbers.add(number);
                    }
                }
            }
            return numbers;

        }

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
                    progressBar.setVisibility(View.VISIBLE);
                    recyclerView.setVisibility(View.GONE);
                    disposable = refreshDisposable(
                            Observable.just(trainFilter(allTrainNumbers, selected))
                                    .doOnNext(numbers -> Collections.sort(numbers, new TrainNumberSort(currentPosition, currentStatus)))
                    );
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
        DataBaseImpl dataBaseService = DataBaseTempAction.getInstance();
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

    /**
     * 对车次列表进行排序,待实现
     *
     * @param numbers 车次
     *                0:出发时间
     *                1:消耗时间
     *                2:到达时间
     */
    private void sortTrainNumberList(List<TrainNumber> numbers) {
        progressBar.setVisibility(View.VISIBLE);
        recyclerView.setVisibility(View.GONE);
        disposable = refreshDisposable(
                Observable.create(
                        (ObservableEmitter<List<TrainNumber>> e) -> {
                            sort(numbers);
                            e.onNext(numbers);
                            e.onComplete();
                        }));
    }

    private Disposable refreshDisposable(Observable<List<TrainNumber>> observable) {
        return observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(consumer, throwableConsumer);
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
        progressBar.setVisibility(View.VISIBLE);
        recyclerView.setVisibility(View.GONE);
        sortTrainNumberList(trainNumbers);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
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
}
