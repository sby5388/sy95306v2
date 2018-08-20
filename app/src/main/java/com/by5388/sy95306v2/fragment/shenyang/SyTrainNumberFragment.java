package com.by5388.sy95306v2.fragment.shenyang;

import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.by5388.sy95306v2.R;
import com.by5388.sy95306v2.activity.trainDetail.TrainDetailActivity;
import com.by5388.sy95306v2.fragment.MyListener;

import java.util.Calendar;

/**
 * @author by5388  on 2018/7/28.
 */

public final class SyTrainNumberFragment extends BaseShenYangFragment {
    public static final String TAG = "GetTrainByTrain";
    private Button buttonSearch, buttonDate;
    private ListView listView;
    private TextInputEditText trainCode;
    MyListener dateListener;
    Calendar calendar;
    private int selectedDate = 20180606;

    public static SyTrainNumberFragment newInstance() {
        SyTrainNumberFragment fragment = new SyTrainNumberFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected void initData() {
        dateListener = new MyListener(this);
        calendar = Calendar.getInstance();
        selectedDate = getData(calendar);
    }

    @Override
    protected void loadData() {
        buttonDate.setText(String.valueOf(getData(calendar)));
    }

    @Override
    protected int getLayoutID() {
        return R.layout.train_number_code_new;
    }

    @Override
    protected void initView(View view) {
        trainCode = view.findViewById(R.id.textView_train_code);
        buttonDate = view.findViewById(R.id.button_query_date);
        buttonDate.setOnClickListener(v -> selectDate(dateListener, calendar));
        listView = view.findViewById(R.id.listView_train_code);
        buttonSearch = view.findViewById(R.id.button_search);
        buttonSearch.setOnClickListener(v -> search());

    }


    @Override
    public void updateView(int year, int month, int dayOfMonth) {
        //TODO
        calendar = Calendar.getInstance();
        calendar.set(year, month, dayOfMonth);
        selectedDate = getData(calendar);
        buttonDate.setText(String.valueOf(selectedDate));
    }


    /**
     * 查询
     */
    private void search() {
        String code = trainCode.getText().toString();
        if (TextUtils.isEmpty(code)) {
            Toast.makeText(getContext(), "请输入车次", Toast.LENGTH_SHORT).show();
            return;
        }
        // TODO: 2018/7/28
        startActivity(TrainDetailActivity.startActivity(getContext(), selectedDate, code));

    }


}
