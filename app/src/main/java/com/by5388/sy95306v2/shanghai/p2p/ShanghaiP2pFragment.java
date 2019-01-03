package com.by5388.sy95306v2.shanghai.p2p;

import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.by5388.sy95306v2.R;
import com.by5388.sy95306v2.shanghai.bean.ShanghaiTrainP2P;
import com.by5388.sy95306v2.MyListener;
import com.by5388.sy95306v2.shanghai.BaseShFragment;
import com.by5388.sy95306v2.shanghai.dialog.ShNumberDialog;
import com.by5388.sy95306v2.shanghai.dialog.view.IShNumberDialogView;
import com.by5388.sy95306v2.shanghai.p2p.presenter.IP2pPresenter;
import com.by5388.sy95306v2.shanghai.p2p.presenter.P2pPresenter;
import com.by5388.sy95306v2.shanghai.p2p.view.IP2pView;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * @author by5388  on 2018/8/10.
 */

public class ShanghaiP2pFragment extends BaseShFragment implements IP2pView {

    public static final String TAG = "ShanghaiP2pFragment";
    private TextInputEditText fromStation, toStation;
    private Button buttonSearch, buttonDate;
    private ListView listView;
    private P2pAdapter adapter;
    private IShNumberDialogView detailDialog;

    private final static List<ShanghaiTrainP2P> EMPTY_LIST = new ArrayList<>();
    private MyListener dateListener;
    private Calendar calendar;

    private IP2pPresenter presenter;

    public static ShanghaiP2pFragment newInstance() {
        ShanghaiP2pFragment fragment = new ShanghaiP2pFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public void updateList(List<ShanghaiTrainP2P> trainP2PS) {
        adapter.setTrainP2PS(trainP2PS);
    }

    @Override
    public void startQuery() {
        buttonSearch.setEnabled(false);
        adapter.setTrainP2PS(EMPTY_LIST);
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
    protected void initData() {
        adapter = new P2pAdapter(getContext(), new ArrayList<>());
        dateListener = new MyListener(this);
        calendar = Calendar.getInstance();
        presenter = new P2pPresenter(this);
    }

    @Override
    protected void loadData() {
        buttonDate.setText(getData(calendar));
        listView.setAdapter(adapter);
        listView.setOnItemClickListener((parent, view, position, id) -> {
            ShanghaiTrainP2P p2p = adapter.getItem(position);
            if (null == p2p) {
                return;
            }
            showDialog(p2p);
        });
    }

    private void showDialog(ShanghaiTrainP2P p2p) {
        final String date = buttonDate.getText().toString().trim().replace("/", "-");
        if (null == detailDialog) {
            detailDialog = new ShNumberDialog(getContext());
        }
        if (detailDialog.isShowing()) {
            detailDialog.dismiss();
        }
        detailDialog.show();

        detailDialog.search(p2p.getTrainCode(), date);
    }

    @Override
    protected int getLayoutID() {
        return R.layout.fragment_shang_hai_p2p;
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

    private void searchTrainNumber() {
        // TODO: 2018/8/10
        boolean isNext = checkStatus();
        if (!isNext) {
            return;
        }
        testGetData();
    }

    private void testGetData() {
        final String fromStationName = fromStation.getText().toString().trim();
        final String toStationName = toStation.getText().toString().trim();
        final String date = buttonDate.getText().toString().trim().replace("/", "-");
        presenter.search(fromStationName, toStationName, date);
    }

    private boolean checkStatus() {
        if (TextUtils.isEmpty(fromStation.getText().toString().trim())) {
            //设置错误提示内容
            // TODO: 2018/8/1
            fromStation.setError("请输入车站");
            fromStation.requestFocus();
            return false;
        }
        if (TextUtils.isEmpty(toStation.getText().toString().trim())) {
            //设置错误提示内容
            // TODO: 2018/8/1
            toStation.setError("请输入车站");
            toStation.requestFocus();
            return false;
        }
        return true;
    }

    @Override
    public void updateView(int year, int month, int dayOfMonth) {
        calendar = Calendar.getInstance();
        calendar.set(year, month, dayOfMonth);
        buttonDate.setText(String.valueOf(getData(calendar)));
    }
}
