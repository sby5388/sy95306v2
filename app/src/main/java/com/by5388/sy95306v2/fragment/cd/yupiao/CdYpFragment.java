package com.by5388.sy95306v2.fragment.cd.yupiao;

import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.by5388.sy95306v2.R;
import com.by5388.sy95306v2.bean.IYp;
import com.by5388.sy95306v2.fragment.MyListener;
import com.by5388.sy95306v2.fragment.cd.BaseCdFragment;
import com.by5388.sy95306v2.fragment.cd.yupiao.persenter.CdYpPresenter;
import com.by5388.sy95306v2.fragment.cd.yupiao.persenter.ICdYpPresenter;
import com.by5388.sy95306v2.fragment.cd.yupiao.view.ICdYpView;
import com.by5388.sy95306v2.fragment.tz.yupiao.temp.YpAdapter;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/**
 * @author by5388  on 2018/8/18.
 */
public class CdYpFragment extends BaseCdFragment implements ICdYpView {
    private static final String TAG = "CdYpFragment";
    private TextInputEditText fromStation, toStation;
    private Button buttonSearch, buttonDate;
    private ListView listView;
    private YpAdapter adapter;
    private ICdYpPresenter presenter;
    private final static List<IYp> EMPTY_LIST = new ArrayList<>();
    private MyListener dateListener;
    private Calendar calendar;


    public static CdYpFragment newInstance() {
        CdYpFragment fragment = new CdYpFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected void initData() {
        presenter = new CdYpPresenter(this);
        adapter = new YpAdapter(getContext(), EMPTY_LIST);
        dateListener = new MyListener(this);
        calendar = Calendar.getInstance();
    }

    @Override
    protected void loadData() {
        listView.setAdapter(adapter);
        buttonDate.setText(getData(calendar));
    }

    @Override
    protected int getLayoutID() {
        return R.layout.fragment_yu_piao;
    }

    @Override
    protected void initView(View view) {
        listView = view.findViewById(R.id.listView_train_code);
        fromStation = view.findViewById(R.id.textView_from_station);
        toStation = view.findViewById(R.id.textView_to_station);
        buttonSearch = view.findViewById(R.id.button_query);
        buttonDate = view.findViewById(R.id.button_query_date);
        buttonSearch.setOnClickListener(v -> searchTrainNumber());
        buttonDate.setOnClickListener(v -> selectDate(dateListener, calendar));
        view.findViewById(R.id.imageView).setOnClickListener(v -> switchStations());
    }

    private void switchStations() {
        String to = toStation.getText().toString().trim();
        toStation.setText(fromStation.getText().toString().trim());
        fromStation.setText(to);
    }

    private void searchTrainNumber() {
        final String fromStationName = fromStation.getText().toString().trim();
        final String toStationName = toStation.getText().toString().trim();
        final String date = buttonDate.getText().toString().trim().replace("/", "-");
        if (TextUtils.isEmpty(fromStationName)) {
            fromStation.setError("请输入车站");
            fromStation.requestFocus();
            return;
        }
        if (TextUtils.isEmpty(toStationName)) {
            toStation.setError("请输入车站");
            toStation.requestFocus();
            return;
        }
        adapter.setYuPiaoData(EMPTY_LIST);
        Log.d(TAG, "searchTrainNumber: " + fromStationName);
        Log.d(TAG, "searchTrainNumber: " + toStationName);
        Log.d(TAG, "searchTrainNumber: " + date);
        presenter.getYpMessage(fromStationName, toStationName, date);
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
    public void showError(String tip) {
        Toast.makeText(getContext(), tip, Toast.LENGTH_SHORT).show();
        buttonSearch.setEnabled(true);
    }

    @Override
    public void updateDate(List<IYp> yuPiaoData) {
        if (null != yuPiaoData) {
            adapter.setYuPiaoData(yuPiaoData);
        }
    }

    @Override
    public void updateView(int year, int month, int dayOfMonth) {
        calendar = Calendar.getInstance();
        calendar.set(year, month, dayOfMonth);
        buttonDate.setText(String.valueOf(getData(calendar)));
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
     * 日期选择
     *
     * @param calendar 日期
     * @return 格式转化后的日期
     */
    private String getData(Calendar calendar) {
        Locale locale = Locale.getDefault();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", locale);
        Date date = calendar.getTime();
        return sdf.format(date);
    }
}
