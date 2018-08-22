package com.by5388.sy95306v2.fragment.tz.remainticket;

import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.by5388.sy95306v2.R;
import com.by5388.sy95306v2.bean.IRemainingTicket;
import com.by5388.sy95306v2.fragment.MyListener;
import com.by5388.sy95306v2.fragment.tz.BaseTzFragment;
import com.by5388.sy95306v2.fragment.tz.remainticket.presenter.IYpPresenter;
import com.by5388.sy95306v2.fragment.tz.remainticket.presenter.YpPresenter;
import com.by5388.sy95306v2.fragment.tz.remainticket.temp.YpAdapter;
import com.by5388.sy95306v2.fragment.tz.remainticket.view.IYpView;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * @author by5388  on 2018/8/13.
 */
public class YuPiaoFragment extends BaseTzFragment implements IYpView {

    public static final String TAG = "YuPiaoFragment";
    private TextInputEditText fromStation, toStation;
    private Button buttonSearch, buttonDate;
    private ListView listView;
    private YpAdapter adapter;
    private IYpPresenter presenter;
    private final static List<IRemainingTicket> EMPTY_LIST = new ArrayList<>();
    private MyListener dateListener;
    private Calendar calendar;

    public static YuPiaoFragment newInstance() {
        YuPiaoFragment fragment = new YuPiaoFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected void initData() {
        presenter = new YpPresenter(this);
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
        presenter.getYpMessage(fromStationName, toStationName, "", date);
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
    public void updateDate(List<IRemainingTicket> yuPiaoData) {
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
}
