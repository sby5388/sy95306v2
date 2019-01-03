package com.by5388.sy95306v2.guangzhou.p2p;

import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.by5388.sy95306v2.R;
import com.by5388.sy95306v2.guangzhou.bean.station.DataBeanP2P;
import com.by5388.sy95306v2.guangzhou.bean.station.StationsBean;
import com.by5388.sy95306v2.guangzhou.bean.station.TrainsBean;
import com.by5388.sy95306v2.dialog.ITrainDetailView;
import com.by5388.sy95306v2.dialog.TrainDetailDialog;
import com.by5388.sy95306v2.MyListener;
import com.by5388.sy95306v2.guangzhou.BaseGzFragment;
import com.by5388.sy95306v2.guangzhou.p2p.presenter.GzP2pPresenter;
import com.by5388.sy95306v2.guangzhou.p2p.presenter.IGzP2pPresenter;
import com.by5388.sy95306v2.guangzhou.p2p.view.IGzP2pView;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * 广铁 车站<-->车站 查询：简洁版、流畅
 *
 * @author by5388  on 2018/8/1.
 */

public class GzP2pFragment extends BaseGzFragment implements IGzP2pView {
    private static final String TAG = "GetTrainByStation";
    private TextInputEditText fromStation, toStation;
    private Button buttonSearch, buttonDate;
    private ListView listView;
    private TempStationAdapter adapter;
    private ITrainDetailView detailDialog;
    private List<TrainsBean> trainsBeans;
    private IGzP2pPresenter presenter;

    private final static List<StationsBean> EMPTY_LIST = new ArrayList<>();
    private MyListener dateListener;
    private Calendar calendar;

    public static GzP2pFragment newInstance() {
        GzP2pFragment fragment = new GzP2pFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected void initData() {
        presenter = new GzP2pPresenter(this);
        adapter = new TempStationAdapter(getContext(), EMPTY_LIST);
        dateListener = new MyListener(this);
        calendar = Calendar.getInstance();
    }

    @Override
    protected void loadData() {
        listView.setAdapter(adapter);
        listView.setOnItemClickListener((parent, view, position, id) -> showDetailDialog(position));
        buttonDate.setText(getData(calendar));
    }

    private void showDetailDialog(int position) {
        StationsBean stationsBean = adapter.getItem(position);
        for (TrainsBean trainsBean : trainsBeans) {
            if (stationsBean.getCc().equals(trainsBean.getTrainNo())) {
                showDetailDialog(trainsBean);
                break;
            }
        }
    }

    private void showDetailDialog(TrainsBean trainsBean) {
        if (null == detailDialog) {
            detailDialog = new TrainDetailDialog(getContext());
        }
        if (detailDialog.isShowing()) {
            detailDialog.dismiss();
        }
        detailDialog.setData(trainsBean).show();

    }

    @Override
    protected int getLayoutID() {
        return R.layout.fragment_guang_zhou_p2p;
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
    }

    /**
     * 查找
     */
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
        adapter.setStationsBeans(EMPTY_LIST);
        presenter.search(fromStationName, toStationName, date);
    }

    @Override
    public void updateView(int year, int month, int dayOfMonth) {
        calendar = Calendar.getInstance();
        calendar.set(year, month, dayOfMonth);
        buttonDate.setText(String.valueOf(getData(calendar)));
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
    }

    @Override
    public void updateDate(DataBeanP2P dataBean) {
        if (dataBean == null) {
            Log.e(TAG, "updateDate: 没有数据" );
            Toast.makeText(getContext(), "没有数据", Toast.LENGTH_SHORT).show();
            return;
        }
        trainsBeans = dataBean.getTrains();
        List<StationsBean> stations = dataBean.getStations();
        if (stations == null) {
            Log.e(TAG, "updateDate: " );
            Toast.makeText(getContext(), "发生错误", Toast.LENGTH_SHORT).show();
            return;
        }
        adapter.setStationsBeans(stations);
    }
}
