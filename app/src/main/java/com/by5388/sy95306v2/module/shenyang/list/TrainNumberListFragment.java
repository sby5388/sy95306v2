package com.by5388.sy95306v2.module.shenyang.list;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.by5388.sy95306v2.App;
import com.by5388.sy95306v2.R;
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

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

/**
 * @author Administrator  on 2020/3/19.
 */
public class TrainNumberListFragment extends Fragment
        implements ITrainListView {
    public static final String TAG = TrainNumberListFragment.class.getSimpleName();
    private static final int SORT_BY_START_TIME = 0;
    private static final int SORT_BY_SPEND_TIME = 1;
    private static final int SORT_BY_ARRIVE_TIME = 2;

    private static final int REQUEST_CODE_FILTER = 2000;
    private TextView mTextViewShowDate;
    private RecyclerView mRecyclerView;
    private Drawable mDrawableUp;
    private Drawable mDrawableDown;
    private TextView mStationNames;
    private SwipeRefreshLayout mSwipeRefreshLayout;
    private TextView mTextViewCount;
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
    private int mSelectedDate;
    private TrainListAdapter adapter;
    private ITrainListPresenter presenter;
    /**
     * 从上往下，默认的状态
     */
    private boolean mBooleanUp = true;
    /**
     * 默认起始
     */
    private int mCurrentPosition = 0;
    /**
     * TODO 20191126 加载完成,避免空数据时 点击闪退
     */
    private boolean mLoaded = false;

    private TextView mTextView1;
    private TextView mTextView2;
    private TextView mTextView3;
    private List<TextView> mTextViews = new ArrayList<>(3);

    public static TrainNumberListFragment newInstance(int selectedDate, Station fromStation, Station toStation) {
        final TrainNumberListFragment fragment = new TrainNumberListFragment();
        final Bundle args = new Bundle();
        args.putInt(SyService.TRAIN_DATE, selectedDate);
        args.putString(SyService.FROM_STATION, fromStation.getNameUpper());
        args.putString(SyService.TO_STATION, toStation.getNameUpper());
        fragment.setArguments(args);
        return fragment;
    }

    /**
     * @param bundle
     * @return
     * @see #newInstance(int, Station, Station)
     */
    public static TrainNumberListFragment newInstance(Bundle bundle) {
        final TrainNumberListFragment fragment = new TrainNumberListFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
        final Context context = Objects.requireNonNull(getContext());
        mDrawableUp = ContextCompat.getDrawable(context, R.drawable.ic_up);
        mDrawableDown = ContextCompat.getDrawable(context, R.drawable.ic_down);
        {
            // TODO: 2020/3/19  textview.setCompoundDrawables不显示图片
            //  https://blog.csdn.net/u010313877/article/details/38728053
            mDrawableDown.setBounds(0, 0, mDrawableDown.getIntrinsicWidth(), mDrawableDown.getMinimumHeight());
            mDrawableUp.setBounds(0, 0, mDrawableUp.getIntrinsicWidth(), mDrawableUp.getMinimumHeight());
        }
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.menu_train_list, menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.menu_filter) {
            if (mLoaded) {
                showFilterDialog();
            }
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_train_list_order, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mTextViewShowDate = view.findViewById(R.id.textView_show_time);
        mRecyclerView = view.findViewById(R.id.recycler_View_train_list);
        mStationNames = view.findViewById(R.id.textView_show_city);
        mSwipeRefreshLayout = view.findViewById(R.id.swipeRefreshLayout);
        mTextView1 = view.findViewById(R.id.textView_menu_1);
        mTextView2 = view.findViewById(R.id.textView_menu_2);
        mTextView3 = view.findViewById(R.id.textView_menu_3);
        mTextViews.add(mTextView1);
        mTextViews.add(mTextView2);
        mTextViews.add(mTextView3);
        mTextView1.setOnClickListener(v -> sortTrainNumber(0));
        mTextView2.setOnClickListener(v -> sortTrainNumber(1));
        mTextView3.setOnClickListener(v -> sortTrainNumber(2));


        mSwipeRefreshLayout.setColorSchemeResources(R.color.colorPrimary,
                R.color.colorPrimaryDark, R.color.colorAccent,
                R.color.colorAccent);
        mSwipeRefreshLayout.setOnRefreshListener(this::loadData);
        final Context context = Objects.requireNonNull(getContext());
        mRecyclerView.setLayoutManager(new LinearLayoutManager(context));
        mRecyclerView.addItemDecoration(new DividerItemDecoration(context, DividerItemDecoration.VERTICAL));
        mTextViewCount = view.findViewById(R.id.textView_train_count);

        initData();
        loadData();
    }


    protected void initData() {
        Bundle arguments = getArguments();
        if (arguments == null) {
            final FragmentActivity activity = getActivity();
            if (activity != null) {
                activity.finish();
            }
            return;
        }
        mSelectedDate = arguments.getInt(SyService.TRAIN_DATE);
        fromStationCode = arguments.getString(SyService.FROM_STATION);
        toStationCode = arguments.getString(SyService.TO_STATION);
        presenter = new TrainListPresenter(this);
        TrainOnClickListener listener = (view, position) -> {
            TrainNumber number = adapter.getItem(position);
            if (null == number) {
                return;
            }
            startActivity(TrainDetailActivity.newIntent(getContext(), mSelectedDate, number.getSTCODE()));
        };
        adapter = new TrainListAdapter(new ArrayList<>(), getContext(), listener);
    }


    private void loadData() {
        mTextViewShowDate.setText(String.valueOf(mSelectedDate));
        mStationNames.setText(presenter.getStationNames(fromStationCode, toStationCode));
        mRecyclerView.setAdapter(adapter);
        presenter.getTrainList(mSelectedDate, fromStationCode, toStationCode);
    }

    @Override
    public void updateTrainList(List<TrainNumber> numbers) {
        adapter.setTrainNumbers(numbers);
        String show = "共" + numbers.size() + "列";
        mTextViewCount.setText(show);
        mLoaded = true;
    }

    @Override
    public void showErrorMessage(String message) {
        App.getInstance().toast(message);
        Objects.requireNonNull(getActivity()).finish();
    }


    @Override
    public void onStartLoading() {
        mLoaded = false;
        mSwipeRefreshLayout.setRefreshing(true);
        mRecyclerView.setVisibility(View.GONE);
    }

    @Override
    public void onFinishLoading() {
        mSwipeRefreshLayout.setRefreshing(false);
        mRecyclerView.setVisibility(View.VISIBLE);
        mLoaded = true;
    }

    @Override
    public void onDestroy() {
        if (null != presenter) {
            presenter.unSubscribe();
            presenter = null;
        }
        super.onDestroy();
    }

    /**
     * 跟换图片 后台处理排序
     *
     * @param position 位置
     */
    private void sortTrainNumber(int position) {
        if (!mLoaded) {
            App.getInstance().toast("请稍候");
            return;
        }
        final TextView selectTextView = mTextViews.get(position);

        if (mCurrentPosition == position) {
            final boolean isUp = this.mBooleanUp;
            if (isUp) {
                selectTextView.setCompoundDrawables(null, null, mDrawableDown, null);
                mBooleanUp = false;
            } else {
                selectTextView.setCompoundDrawables(null, null, mDrawableUp, null);
                mBooleanUp = true;
            }
        } else {
            selectTextView.setCompoundDrawables(null, null, mDrawableUp, null);
            mBooleanUp = true;
            mCurrentPosition = position;
        }
        for (TextView textView : mTextViews) {
            if (!selectTextView.equals(textView)) {
                textView.setCompoundDrawables(null, null, null, null);
            }
        }
        presenter.sortTrainList(mCurrentPosition, mBooleanUp);
    }

    /**
     * 筛选对话框
     */
    private void showFilterDialog() {
        if (!mLoaded) {
            return;
        }
        final TrainFilterDialog2 trainFilterDialog2 = TrainFilterDialog2.newInstance();
        trainFilterDialog2.setTargetFragment(this, REQUEST_CODE_FILTER);
        assert getFragmentManager() != null;
        trainFilterDialog2.show(getFragmentManager(), TrainFilterDialog2.class.getName());
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode != Activity.RESULT_OK) {
            return;
        }
        if (requestCode == REQUEST_CODE_FILTER) {
            updateFilter();
        }
    }

    private void updateFilter() {
        presenter.sortTrainList(mCurrentPosition, mBooleanUp);
    }
}
