package com.by5388.sy95306.fragment;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.by5388.sy95306.R;
import com.by5388.sy95306.detail.TrainDetailActivity;

import java.util.Calendar;

/**
 * 车次查询
 *
 * @author Administrator
 */
public class DetailFragment extends BaseFragment {

    private EditText showTrainNumber;
    private Button mButtonGetDate;

    private Calendar calendar;


    private int selectedDate = 20180606;

    public DetailFragment() {
    }

    public static DetailFragment newInstance() {
        DetailFragment fragment = new DetailFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initData();
    }

    private void initData() {
        calendar = Calendar.getInstance();
        selectedDate = getData(calendar);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_train_code, container, false);
        initView(view);
        return view;
    }

    private void initView(View view) {
        showTrainNumber = view.findViewById(R.id.show_textView_train_number);
        mButtonGetDate = view.findViewById(R.id.button_select_date);
        mButtonGetDate.setText(String.valueOf(selectedDate));
        mButtonGetDate.setOnClickListener(v -> selectDate(new MyListener(), calendar));
        Button mButtonQuery = view.findViewById(R.id.button_query);
        mButtonQuery.setOnClickListener(v -> search());
    }


    /**
     * 查询
     */
    private void search() {
        String code = showTrainNumber.getText().toString();
        if (TextUtils.isEmpty(code)) {
            Toast.makeText(getContext(), "请输入车次", Toast.LENGTH_SHORT).show();
            return;
        }
        startActivity(TrainDetailActivity.newIntent(getContext(), selectedDate, code));
    }


    @Override
    public void updateView(int year, int month, int dayOfMonth) {
        calendar = Calendar.getInstance();
        calendar.set(year, month, dayOfMonth);
        selectedDate = getData(calendar);
        mButtonGetDate.setText(String.valueOf(selectedDate));
    }

}
