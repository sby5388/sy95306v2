package com.by5388.sy95306v2.module.tiezong.zzcx;

import android.os.Bundle;
import com.google.android.material.textfield.TextInputEditText;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.by5388.sy95306v2.MyListener;
import com.by5388.sy95306v2.R;
import com.by5388.sy95306v2.bean.IRemainingTicket;
import com.by5388.sy95306v2.module.tiezong.BaseTzFragment;
import com.by5388.sy95306v2.module.tiezong.remainticket.temp.RemainTicketAdapter;
import com.by5388.sy95306v2.module.tiezong.zzcx.persenter.ITzZzCxPresenter;
import com.by5388.sy95306v2.module.tiezong.zzcx.persenter.TzZzCxPresenter;
import com.by5388.sy95306v2.module.tiezong.zzcx.view.ITzZzCxView;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Objects;

/**
 * FIXME: 2018/8/25  gson  解析泛型 参考
 * https://www.cnblogs.com/qq78292959/p/3781808.html
 * 中转查询：余票查询
 *
 * @author by5388  on 2018/8/17.
 */
public class ZzCxFragment extends BaseTzFragment implements ITzZzCxView {

    private static final String TAG = "ZzCxFragment";
    private TextInputEditText fromStation, toStation;
    private Button buttonDate;
    private RemainTicketAdapter adapter;
    private final static List<IRemainingTicket> EMPTY_LIST = new ArrayList<>();
    private MyListener dateListener;
    private Calendar calendar;
    private ITzZzCxPresenter presenter;

    public static ZzCxFragment newInstance() {
        ZzCxFragment fragment = new ZzCxFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected void initData() {
        presenter = new TzZzCxPresenter(this);
        dateListener = new MyListener(this);
        calendar = Calendar.getInstance();
        adapter = new RemainTicketAdapter(Objects.requireNonNull(getContext()));
    }

    @Override
    protected void loadData() {
    }

    @Override
    protected int getLayoutID() {
        return R.layout.fragment_tz_zzcx;
    }

    @Override
    protected void initView(View view) {
        ListView listView = view.findViewById(R.id.listView_train_code);
        listView.setAdapter(adapter);
        fromStation = view.findViewById(R.id.textView_from_station);
        toStation = view.findViewById(R.id.textView_to_station);
        Button buttonSearch = view.findViewById(R.id.button_query);

        buttonDate = view.findViewById(R.id.button_query_date);
        buttonSearch.setOnClickListener(v -> searchTrainNumber());
        buttonDate.setOnClickListener(v -> selectDate(dateListener, calendar));
        buttonDate.setText(getData(calendar));
        view.findViewById(R.id.imageView).setOnClickListener(v -> switchStations());
    }

    private void switchStations() {
        String to = toStation.getText().toString().trim();
        toStation.setText(fromStation.getText().toString().trim());
        fromStation.setText(to);
    }

    private void searchTrainNumber() {
        String date = buttonDate.getText().toString().trim();
        String fromStation = this.fromStation.getText().toString().trim();
        String toStation = this.toStation.getText().toString().trim();
        presenter.getTrainList(date, fromStation, toStation);
        adapter.setTickets(EMPTY_LIST);
    }

    @Override
    public void updateView(int year, int month, int dayOfMonth) {
        calendar = Calendar.getInstance();
        calendar.set(year, month, dayOfMonth);
        buttonDate.setText(getData(calendar));
    }

    @Override
    public void startQuery() {
    }

    @Override
    public void finishQuery() {
    }

    @Override
    public void showError(String tip) {
        Toast.makeText(getContext(), tip, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void updateList(List<IRemainingTicket> dataBeans) {
        if (null == dataBeans || dataBeans.isEmpty()) {
            return;
        }
        adapter.setTickets(dataBeans);
    }

    @Override
    public void onDestroy() {
        presenter.unSubscribe();
        super.onDestroy();
    }
}
