package com.by5388.sy95306v2.fragment.tz.zzcx;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.by5388.sy95306v2.R;
import com.by5388.sy95306v2.bean.IRemainingTicket;
import com.by5388.sy95306v2.fragment.MyListener;
import com.by5388.sy95306v2.fragment.tz.BaseTzFragment;
import com.by5388.sy95306v2.fragment.tz.remainticket.temp.YpAdapter;
import com.by5388.sy95306v2.fragment.tz.zzcx.persenter.ITzZzCxPresenter;
import com.by5388.sy95306v2.fragment.tz.zzcx.persenter.TzZzCxPresenter;
import com.by5388.sy95306v2.fragment.tz.zzcx.view.ITzZzCxView;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * @author by5388  on 2018/8/17.
 */
public class ZzCxFragment extends BaseTzFragment implements ITzZzCxView {

    private static final String TAG = "GetTrainByStation";
    private TextInputEditText fromStation, toStation, passCode;
    /**
     * 图片验证码
     */
    private ImageView imageViewPassCode;
    private Button buttonSearch, buttonDate, buttonCheck;
    private ListView listView;
    private YpAdapter adapter;
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
        adapter = new YpAdapter(getContext(), EMPTY_LIST);
    }

    @Override
    protected void loadData() {
        buttonDate.setText(getData(calendar));
        listView.setAdapter(adapter);
    }

    @Override
    protected int getLayoutID() {
        return R.layout.fragment_tz_zzcx;
    }

    @Override
    protected void initView(View view) {
        listView = view.findViewById(R.id.listView_train_code);
        fromStation = view.findViewById(R.id.textView_from_station);
        toStation = view.findViewById(R.id.textView_to_station);
        passCode = view.findViewById(R.id.textView_pass_code);
        buttonCheck = view.findViewById(R.id.button_check_pass_code);
        buttonCheck.setOnClickListener(v -> checkPassCode());
        buttonSearch = view.findViewById(R.id.button_query);
        imageViewPassCode = view.findViewById(R.id.imageView_pass_code);
        imageViewPassCode.setOnClickListener(v -> refreshPassCode());

        buttonDate = view.findViewById(R.id.button_query_date);
        buttonSearch.setOnClickListener(v -> searchTrainNumber());
        buttonDate.setOnClickListener(v -> selectDate(dateListener, calendar));
        view.findViewById(R.id.imageView).setOnClickListener(v -> switchStations());
    }

    /**
     * 校验验证码
     */
    private void checkPassCode() {
        final int codeLength = 4;
        String code = passCode.getText().toString().trim();
        Log.d(TAG, "checkPassCode: " + code);
        if (codeLength != code.length()) {
            passCode.setError("格式不对");
            return;
        }
        buttonCheck.setEnabled(false);
        presenter.checkPassCode(code);
    }

    /**
     * 刷新验证码
     */
    private void refreshPassCode() {
        imageViewPassCode.setEnabled(false);
        presenter.refreshPassCodeBitmap();
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
        String randCode = passCode.getText().toString().trim();
        presenter.getTrainList(date, fromStation, toStation, randCode);
        adapter.setYuPiaoData(EMPTY_LIST);
    }

    @Override
    public void updateView(int year, int month, int dayOfMonth) {
        calendar = Calendar.getInstance();
        calendar.set(year, month, dayOfMonth);
        buttonDate.setText(getData(calendar));
    }

    @Override
    public void startQuery() {
        imageViewPassCode.setEnabled(false);
        //buttonSearch.setEnabled(false);
        buttonCheck.setEnabled(false);
    }

    @Override
    public void finishQuery() {
        imageViewPassCode.setEnabled(true);
        buttonCheck.setEnabled(true);
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
        adapter.setYuPiaoData(dataBeans);
    }

    @Override
    public void updateCheckCodeBitmap(Bitmap bitmap) {
        if (null == bitmap) {
            return;
        }
        imageViewPassCode.setImageBitmap(bitmap);
        imageViewPassCode.setEnabled(true);
    }

    @Override
    public void checkPassCode(boolean isChecked) {
        String show = "输入不正确";
        if (isChecked) {
            show = "输入正确";
            buttonSearch.setEnabled(true);
        }
        Toast.makeText(getContext(), show, Toast.LENGTH_SHORT).show();
        buttonCheck.setEnabled(true);
        imageViewPassCode.setEnabled(true);
    }

    @Override
    public void onDestroy() {
        presenter.unSubscribe();
        super.onDestroy();
    }
}
