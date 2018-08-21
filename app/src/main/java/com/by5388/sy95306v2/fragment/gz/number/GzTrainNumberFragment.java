package com.by5388.sy95306v2.fragment.gz.number;

import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.by5388.sy95306v2.R;
import com.by5388.sy95306v2.bean.guangzhou.station.TrainsBean;
import com.by5388.sy95306v2.dialog.ITrainDetailView;
import com.by5388.sy95306v2.dialog.TrainDetailDialog;
import com.by5388.sy95306v2.fragment.MyListener;
import com.by5388.sy95306v2.fragment.gz.BaseGzFragment;
import com.by5388.sy95306v2.fragment.gz.number.perserter.ITrainNumberPresenter;
import com.by5388.sy95306v2.fragment.gz.number.perserter.TrainNumberPresenter;
import com.by5388.sy95306v2.fragment.gz.number.view.ITrainNumberView;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * @author by5388  on 2018/8/1.
 */

public class GzTrainNumberFragment extends BaseGzFragment implements ITrainNumberView {
    public static final String TAG = "GetTrainByTrain";
    private Button buttonSearch, buttonDate;
    private ListView listView;
    private TextInputEditText trainCode;
    private TempAdapter adapter;
    private ITrainNumberPresenter presenter;
    private ITrainDetailView detailDialog;
    private MyListener dateListener;
    private Calendar calendar;


    public static GzTrainNumberFragment newInstance() {
        GzTrainNumberFragment fragment = new GzTrainNumberFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected void initData() {
        dateListener = new MyListener(this);
        calendar = Calendar.getInstance();
        presenter = new TrainNumberPresenter(this);
        adapter = new TempAdapter(getContext(), new ArrayList<>());
    }

    @Override
    protected void loadData() {
        buttonDate.setText(getData(calendar));
        listView.setAdapter(adapter);
        listView.setOnItemClickListener((parent, view, position, id) -> showDetailDialog(position));
    }

    private void showDetailDialog(int position) {
        TrainsBean trainsBean = adapter.getItem(position);
        if (trainsBean == null) {
            return;
        }
        if (detailDialog == null) {
            detailDialog = new TrainDetailDialog(getContext());
        }
        if (detailDialog.isShowing()) {
            detailDialog.dismiss();
        }
        detailDialog.setData(trainsBean).show();
    }

    @Override
    protected int getLayoutID() {
        return R.layout.fragment_guang_zhou_train_number;
    }

    @Override
    protected void initView(View view) {
        trainCode = view.findViewById(R.id.textView_train_code);
        buttonDate = view.findViewById(R.id.button_query_date);
        buttonDate.setOnClickListener(v -> selectDate(dateListener, calendar));
        listView = view.findViewById(R.id.listView_train_code);
        buttonSearch = view.findViewById(R.id.button_search);
        buttonSearch.setOnClickListener(v -> testSearch());
    }

    private void testSearch() {
        final String trainNo = trainCode.getText().toString().trim();
        if (TextUtils.isEmpty(trainNo)) {
            //设置错误提示内容
            trainCode.setError("请输入车次");
            trainCode.requestFocus();
            return;
        }
        final String date = buttonDate.getText().toString().trim().replace("/", "-");
        presenter.search(trainNo, date);
    }

    @Override
    public void startQuery() {
        buttonSearch.setEnabled(false);
    }

    @Override
    public void finishQuery() {
        buttonSearch.setEnabled(true);
    }

    @Override
    public void updateDate(List<TrainsBean> trainsBeans) {
        adapter.setTrainsBeans(trainsBeans);
    }

    @Override
    public void showError(String tip) {
        Toast.makeText(getContext(), tip, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        presenter.unSubscribe();
    }

    @Override
    public void updateView(int year, int month, int dayOfMonth) {
        calendar = Calendar.getInstance();
        calendar.set(year, month, dayOfMonth);
        buttonDate.setText(String.valueOf(getData(calendar)));
    }
}
