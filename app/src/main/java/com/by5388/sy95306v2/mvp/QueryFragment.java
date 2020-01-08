package com.by5388.sy95306v2.mvp;

import android.app.DatePickerDialog;
import android.os.Bundle;
import com.google.android.material.textfield.TextInputEditText;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ListView;
import android.widget.Toast;

import com.by5388.sy95306v2.MyListener;
import com.by5388.sy95306v2.R;
import com.by5388.sy95306v2.bean.IRemainingTicket;
import com.by5388.sy95306v2.module.tiezong.remainticket.temp.RemainTicketAdapter;
import com.by5388.sy95306v2.t201906.TrainResult;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

public class QueryFragment extends CommonFragment<QueryPresenter, QueryPresenter.QueryUi>
        implements MyListener.UpdateDate, QueryPresenter.QueryUi {
    private TextInputEditText fromStation, toStation;
    private Button buttonSearch, buttonDate;
    private ListView mListView;
    private Calendar calendar;
    private MyListener dateListener;
    private RemainTicketAdapter mAdapter;
    private DatePickerDialog mDialog;

    @Override
    public QueryPresenter createPresenter() {
        return QueryPresenter.getInstance();
    }

    @Override
    public QueryPresenter.QueryUi getUI() {
        return this;
    }

    public QueryFragment() {
    }

    public static QueryFragment newInstance() {
        QueryFragment fragment = new QueryFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
        }
        calendar = Calendar.getInstance();
        mAdapter = new RemainTicketAdapter(getContext());
        dateListener = new MyListener(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_plus_one, container, false);

        mListView = view.findViewById(R.id.listView_train_code);
        mListView.setAdapter(mAdapter);
        fromStation = view.findViewById(R.id.textView_from_station);
        toStation = view.findViewById(R.id.textView_to_station);
        buttonSearch = view.findViewById(R.id.button_query);
        buttonSearch.setOnClickListener(v -> searchTrainNumber());
        buttonDate = view.findViewById(R.id.button_query_date);
        buttonDate.setOnClickListener(v -> selectDate(dateListener, calendar));
        initDateButton();
        return view;
    }


    private void initDateButton() {
        buttonDate.setText(getData(calendar));
    }

    /**
     * 日期选择
     *
     * @param calendar 日期
     * @return 格式转化后的日期
     */
    private String getData(Calendar calendar) {
        Locale locale = Locale.getDefault();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd", locale);
        Date date = calendar.getTime();
        return sdf.format(date);
    }

    @Override
    public void updateView(int year, int month, int dayOfMonth) {
        // TODO: 2019/9/16
        calendar.set(year, month, dayOfMonth);
        initDateButton();
    }

    private void selectDate(MyListener dateListener, Calendar calendarData) {
        if (mDialog == null) {
            //  这里要记录已经选择的日期
            mDialog = new DatePickerDialog(Objects.requireNonNull(getContext()), dateListener,
                    calendarData.get(Calendar.YEAR), calendarData.get(Calendar.MONTH), calendarData.get(Calendar.DATE));
        } else {
            mDialog.updateDate(calendarData.get(Calendar.YEAR), calendarData.get(Calendar.MONTH), calendarData.get(Calendar.DATE));
        }
        upgradeDateLimit();
        mDialog.show();
    }

    /**
     * 更新时间范围限制
     */
    private void upgradeDateLimit() {
        if (mDialog == null) {
            return;
        }
        final long MAX_TIME = TimeUnit.DAYS.toMillis(60);
        final DatePicker picker = mDialog.getDatePicker();
        // 设置最大日期
        picker.setMaxDate(Calendar.getInstance().getTimeInMillis() + MAX_TIME);
        // 设置最小日期
        picker.setMinDate(Calendar.getInstance().getTimeInMillis());
    }


    private void test() {
        final String date = "2019-10-11";
        final String from = "RVQ";
        final String to = "GZQ";
        final String type = "1";
        getPresenter().queryTicketPrices(date, from, to);
    }

    private void test2() {
        final String date = "2019-10-11";
        final String from = "饶平";
        final String to = "广州东";
        final String type = "1";
        getPresenter().queryTicketPrices(date, from, to);
    }

    private void query(final String date, final String from, final String to) {
        getPresenter().queryTicketPrices(date, from, to);
    }


    private void searchTrainNumber() {

        if (false) {
            // TODO: 2019/9/24 just for Test
            test2();
            return;
        }

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
        query(date, fromStationName, toStationName);
    }

    @Override
    public void onStartQuery() {
        buttonSearch.setEnabled(false);
    }

    @Override
    public void onEndQuery() {
        buttonSearch.setEnabled(true);
    }

    @Override
    public void onFailQuery(String s) {
        buttonSearch.setEnabled(true);
        Toast.makeText(getContext(), s, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onResult(List<TrainResult.DataBean> results) {
        if (results == null || results.isEmpty()) {
            return;
        }
        final List<IRemainingTicket> list = new ArrayList<>();
        // FIXME: 2019/9/24 需要更新UI
        for (TrainResult.DataBean result : results) {
            list.add(result.queryLeftNewDTO);
        }
        if (!list.isEmpty()) {
            mAdapter.setTickets(list);
            mAdapter.notifyDataSetChanged();
        }


    }
}
