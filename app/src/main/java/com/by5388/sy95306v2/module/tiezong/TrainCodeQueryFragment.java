package com.by5388.sy95306v2.module.tiezong;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.by5388.sy95306v2.MyListener;
import com.by5388.sy95306v2.R;

import java.util.Calendar;

/**
 * @author Administrator  on 2019/9/19.
 */
public class TrainCodeQueryFragment extends BaseTzFragment {

    public static BaseTzFragment newInstance() {
        return new TrainCodeQueryFragment();
    }

    private Button mButtonDate;
    private Button mButtonQuery;
    private EditText mEditText;
    private ListView mListView;
    private MyListener dateListener;
    private Calendar mCalendar;

    @Override
    public void updateView(int year, int month, int dayOfMonth) {
        mCalendar = Calendar.getInstance();
        mCalendar.set(year, month, dayOfMonth);
        mButtonDate.setText(getData(mCalendar));
    }


    @Override
    protected void initData() {
        dateListener = new MyListener(this);
        mCalendar = Calendar.getInstance();

    }


    @Override
    protected void loadData() {

    }

    @Override
    protected int getLayoutID() {
        return R.layout.tz_train_code_query;
    }

    @Override
    protected void initView(View view) {
        mButtonDate = view.findViewById(R.id.button_select_date);
        mButtonDate.setOnClickListener(v -> {
            selectDate(dateListener, mCalendar);
        });
        mButtonQuery = view.findViewById(R.id.button_query);
        mEditText = view.findViewById(R.id.input_train_code);
        mListView = view.findViewById(R.id.listView_result);
    }
}
