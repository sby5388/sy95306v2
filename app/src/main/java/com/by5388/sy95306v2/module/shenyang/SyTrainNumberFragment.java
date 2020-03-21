package com.by5388.sy95306v2.module.shenyang;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import com.by5388.sy95306v2.App;
import com.by5388.sy95306v2.MyListener;
import com.by5388.sy95306v2.R;
import com.by5388.sy95306v2.common.Tools;
import com.by5388.sy95306v2.module.shenyang.detail.TrainDetailActivity;
import com.google.android.material.textfield.TextInputEditText;

import java.util.Calendar;

/**
 * @author by5388  on 2018/7/28.
 */

public final class SyTrainNumberFragment extends BaseShenYangFragment {
    public static final String TAG = "GetTrainByTrain";
    private Button buttonDate;
    private TextInputEditText trainCode;
    private MyListener dateListener;
    private Calendar calendar;
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
        ListView listView = view.findViewById(R.id.listView_train_code);
        Button buttonSearch = view.findViewById(R.id.button_search);
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
            toast("请输入车次");
            return;
        }
        // TODO: 2018/7/28
        // TODO: 2020/3/17 检查网络
        final boolean networkEnable = App.getInstance().networkEnable();
        if (!networkEnable) {
            Tools.openSetting(getContext(), trainCode);
            return;
        }
        startActivity(TrainDetailActivity.newIntent(getContext(), selectedDate, code));

    }


}
