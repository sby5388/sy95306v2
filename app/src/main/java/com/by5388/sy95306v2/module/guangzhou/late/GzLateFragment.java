package com.by5388.sy95306v2.module.guangzhou.late;

import android.os.Bundle;
import com.google.android.material.textfield.TextInputEditText;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.by5388.sy95306v2.R;
import com.by5388.sy95306v2.module.guangzhou.bean.late.GzLateStationInfoBean;
import com.by5388.sy95306v2.MyListener;
import com.by5388.sy95306v2.module.guangzhou.BaseGzFragment;
import com.by5388.sy95306v2.module.guangzhou.late.persenter.GzLatePresenter;
import com.by5388.sy95306v2.module.guangzhou.late.persenter.IGzLatePresenter;
import com.by5388.sy95306v2.module.guangzhou.late.view.IGzLateView;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * @author by5388  on 2018/8/21.
 */
public class GzLateFragment extends BaseGzFragment implements IGzLateView {
    private static final String TAG = "GzLateFragment";
    private GzLateAdapter adapter;
    private IGzLatePresenter presenter;
    private ListView listView;
    private Button buttonSearch, buttonDate;
    private ProgressBar progressBar;
    private TextInputEditText trainCode;
    private TextView trainCodeShow, trainName;
    private MyListener dateListener;
    private Calendar calendar;

    public static GzLateFragment newInstance() {
        GzLateFragment fragment = new GzLateFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected void initData() {
        presenter = new GzLatePresenter(this);
        adapter = new GzLateAdapter(getContext(), new ArrayList<>());
        calendar = Calendar.getInstance();
        dateListener = new MyListener(this);
    }

    @Override
    protected void loadData() {
        buttonDate.setText(getData(calendar));
        listView.setAdapter(adapter);
    }

    @Override
    protected int getLayoutID() {
        return R.layout.fragment_gz_late;
    }

    @Override
    protected void initView(View view) {
        listView = view.findViewById(R.id.listView);
        buttonSearch = view.findViewById(R.id.button_search);
        trainCodeShow = view.findViewById(R.id.trainCode);
        trainName = view.findViewById(R.id.trainName);
        buttonSearch.setOnClickListener(v -> testSearch());
        buttonDate = view.findViewById(R.id.button_query_date);
        buttonDate.setOnClickListener(v -> selectDate(dateListener, calendar));
        progressBar = view.findViewById(R.id.progressBar);
        trainCode = view.findViewById(R.id.textView_train_code);
    }

    private void testSearch() {
        final String trainNo = trainCode.getText().toString().trim();
        if (TextUtils.isEmpty(trainNo)) {
            //设置错误提示内容
            trainCode.setError("请输入车次");
            trainCode.requestFocus();
            return;
        }
        presenter.getLateDetail(trainNo);
    }

    @Override
    public void updateView(int year, int month, int dayOfMonth) {

    }

    @Override
    public void startQuery() {
        buttonSearch.setEnabled(false);
        progressBar.setVisibility(View.VISIBLE);
        listView.setVisibility(View.GONE);
    }

    @Override
    public void finishQuery() {
        buttonSearch.setEnabled(true);
        progressBar.setVisibility(View.GONE);
        listView.setVisibility(View.VISIBLE);
    }

    @Override
    public void showError(String tip) {
        Toast.makeText(getContext(), tip, Toast.LENGTH_SHORT).show();
        buttonSearch.setEnabled(true);

    }

    @Override
    public void setTitle(String trainCode, String trainName) {
        this.trainCodeShow.setText(trainCode);
        this.trainName.setText(trainName);
    }

    @Override
    public void updateData(List<GzLateStationInfoBean> beans) {
        if (null == beans || beans.isEmpty()) {
            Log.e(TAG, "updateData: empty");
            return;
        }
        adapter.setBeans(beans);
    }

    @Override
    public void showDialog(List<String> trainDetail) {
        // FIXME: 2018/8/22
    }

    @Override
    public void getLateDetail() {
        // FIXME: 2018/8/22
    }

    @Override
    public void onDestroy() {
        if (null != presenter) {
            presenter.unSubscribe();
            presenter = null;
        }
        super.onDestroy();
    }
}
