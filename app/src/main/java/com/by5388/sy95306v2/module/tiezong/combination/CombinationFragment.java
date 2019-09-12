package com.by5388.sy95306v2.module.tiezong.combination;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.by5388.sy95306v2.MyListener;
import com.by5388.sy95306v2.R;
import com.by5388.sy95306v2.bean.IRemainingTicket;
import com.by5388.sy95306v2.module.tiezong.BaseTzFragment;
import com.by5388.sy95306v2.module.tiezong.combination.persenter.CombinationPresenter;
import com.by5388.sy95306v2.module.tiezong.combination.persenter.ICombinationPresenter;
import com.by5388.sy95306v2.module.tiezong.combination.view.ICombinationView;
import com.by5388.sy95306v2.module.tiezong.detail.TzDetailActivity;
import com.by5388.sy95306v2.module.tiezong.remainticket.temp.RemainTicketAdapter;
import com.by5388.sy95306v2.module.tiezong.temp.view.TzRemainTicketActivity;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Objects;

/**
 * @author by5388  on 2018/8/22.
 */
public class CombinationFragment extends BaseTzFragment implements ICombinationView {
    private static final String TAG = "CombinationFragment";
    private List<IRemainingTicket> currentTickets;
    private TextInputEditText fromStation, toStation, trainCode;
    private Button buttonDate;
    private ListView listView;
    private RemainTicketAdapter adapter;
    private final static List<IRemainingTicket> EMPTY_LIST = new ArrayList<>();
    private MyListener dateListener;
    private Calendar calendar;
    private ICombinationPresenter presenter;
    private String textDate = null;

    public static CombinationFragment newInstance() {
        CombinationFragment fragment = new CombinationFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected void initData() {
        presenter = new CombinationPresenter(this);
        dateListener = new MyListener(this);
        calendar = Calendar.getInstance();
        adapter = new RemainTicketAdapter(Objects.requireNonNull(getContext()));
        currentTickets = new ArrayList<>();
    }

    @Override
    protected void loadData() {
        buttonDate.setText(getData(calendar));
        listView.setAdapter(adapter);
        listView.setOnItemClickListener((parent, view, position, id) -> {
            // TODO: 2019/1/9
            if (TextUtils.isEmpty(textDate)) {
                return;
            }
            IRemainingTicket iRemainingTicket = adapter.getItem(position);
            Context context = getContext();
            String trainCode = iRemainingTicket.getCode();
            String fromStationCode = iRemainingTicket.getFromStation();
            String toStationCode = iRemainingTicket.getToStation();
            String date = textDate;
            startActivity(TzDetailActivity.newIntent(context, trainCode, fromStationCode, toStationCode, date));
        });
    }


    @Override
    protected int getLayoutID() {
        return R.layout.fragment_tz_combination;
    }

    @Override
    protected void initView(View view) {
        listView = view.findViewById(R.id.listView_train_code);
        fromStation = view.findViewById(R.id.textView_from_station);
        toStation = view.findViewById(R.id.textView_to_station);
        trainCode = view.findViewById(R.id.textView_train_code);
        Button buttonSearch = view.findViewById(R.id.button_query);

        buttonDate = view.findViewById(R.id.button_query_date);
        buttonSearch.setOnClickListener(v -> searchTrainNumber());
        buttonSearch.setOnLongClickListener(v -> {
            startActivity(new Intent(getContext(), TzRemainTicketActivity.class));
            return true;
        });
        buttonDate.setOnClickListener(v -> selectDate(dateListener, calendar));
        view.findViewById(R.id.imageView).setOnClickListener(v -> switchStations());
    }

    private void switchStations() {
        // TODO: 2019/6/14 需要增加交换文本的动画
        String to = toStation.getText().toString().trim();
        toStation.setText(fromStation.getText().toString().trim());
        fromStation.setText(to);
    }

    private void searchTrainNumber() {
        adapter.setTickets(EMPTY_LIST);
        currentTickets.clear();
        String date = buttonDate.getText().toString().trim();
        String fromStation = this.fromStation.getText().toString().trim();
        String toStation = this.toStation.getText().toString().trim();
        String trainCode = this.trainCode.getText().toString().trim();

        boolean isNotEmptyFromStation = !TextUtils.isEmpty(fromStation);
        boolean isNotEmptyToStation = !TextUtils.isEmpty(toStation);
        boolean isNotEmptyTrainCode = !TextUtils.isEmpty(trainCode);
        //组合0：三个都不为空
        if (isNotEmptyFromStation && isNotEmptyToStation && isNotEmptyTrainCode) {
            textDate = date;
            presenter.getOnlyOneTrainList(date, fromStation, toStation, trainCode);
            Log.d(TAG, "searchTrainNumber: 0");
            return;
        }
        //组合1：出发+目的：列出所有的车次信息
        if (isNotEmptyFromStation && isNotEmptyToStation) {
            textDate = date;
            presenter.getTrainListByEmptyTrainCode(date, fromStation, toStation);
            Log.d(TAG, "searchTrainNumber: 1");
            return;
        }
        //组合2：没有出发站
        if (isNotEmptyToStation && isNotEmptyTrainCode) {
            textDate = date;
            presenter.getTrainListByEmptyFromStation(date, toStation, trainCode);
            Log.d(TAG, "searchTrainNumber: 2");
            return;
        }//组合3：没有目的站
        if (isNotEmptyFromStation && isNotEmptyTrainCode) {
            textDate = date;
            presenter.getTrainListByEmptyToStation(date, fromStation, trainCode);
            Log.d(TAG, "searchTrainNumber: 3");
            return;
        }
        showError("请至少填写2个信息");

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

    @Override
    public void addIRemainingTicket(IRemainingTicket ticket) {
        currentTickets.add(ticket);
        adapter.setTickets(currentTickets);

    }
}
