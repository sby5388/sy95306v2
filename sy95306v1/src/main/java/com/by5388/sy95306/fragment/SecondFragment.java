package com.by5388.sy95306.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.by5388.sy95306.R;
import com.by5388.sy95306.TrainDetailActivity;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import static com.by5388.sy95306.fragment.FirstFragment.DATA_BUNDLE;
import static com.by5388.sy95306.net.TrainNumberService.TRAIN_CODE;
import static com.by5388.sy95306.net.TrainNumberService.TRAIN_DATE;

/**
 * 车次查询
 */
public class SecondFragment extends BaseFragment implements View.OnClickListener {

    private TextView showTrainNumber, showDate;
    private static final String EMPTY = "";

    private List<View> buttons;
    private Calendar calendar;

    private final static int[] BUTTON_IDS = {
            R.id.button_0, R.id.button_1, R.id.button_2, R.id.button_3, R.id.button_4,
            R.id.button_5, R.id.button_6, R.id.button_7, R.id.button_8, R.id.button_9,
            R.id.button_g,
            R.id.button_d,
            R.id.button_c,
            R.id.button_z,
            R.id.button_t,
            R.id.button_k,
            R.id.button_l,
            R.id.button_backspace,
            R.id.button_clear,
            R.id.button_search
    };

    private int selectedDate = 20180606;

    public SecondFragment() {
    }

    public static SecondFragment newInstance() {
        SecondFragment fragment = new SecondFragment();
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

    private void initButtons(View view) {
        buttons = new ArrayList<>(BUTTON_IDS.length);
        for (@IdRes Integer buttonId : BUTTON_IDS) {
            buttons.add(view.findViewById(buttonId));
        }
        for (View button : buttons) {
            button.setOnClickListener(this);
        }


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_second, container, false);
        initView(view);
        return view;
    }

    private void initView(View view) {
        initButtons(view);
        showTrainNumber = view.findViewById(R.id.show_textView_train_number);
        showDate = view.findViewById(R.id.show_textView_date);
        showDate.setText(String.valueOf(selectedDate));
        showDate.setOnClickListener(v -> selectDate(new MyListener(), calendar));
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button_0:
                setNumber(0);
                break;
            case R.id.button_1:
                setNumber(1);
                break;
            case R.id.button_2:
                setNumber(2);
                break;
            case R.id.button_3:
                setNumber(3);
                break;
            case R.id.button_4:
                setNumber(4);
                break;
            case R.id.button_5:
                setNumber(5);
                break;
            case R.id.button_6:
                setNumber(6);
                break;
            case R.id.button_7:
                setNumber(7);
                break;
            case R.id.button_8:
                setNumber(8);
                break;
            case R.id.button_9:
                setNumber(9);
                break;
            case R.id.button_c:
                setTrainLevel("c");
                break;
            case R.id.button_g:
                setTrainLevel("g");
                break;
            case R.id.button_d:
                setTrainLevel("d");
                break;
            case R.id.button_z:
                setTrainLevel("z");
                break;
            case R.id.button_t:
                setTrainLevel("t");
                break;
            case R.id.button_k:
                setTrainLevel("k");
                break;
            case R.id.button_l:
                setTrainLevel("l");
                break;
            case R.id.button_clear:
                clearTrain();
                break;
            case R.id.button_search:
                search();
                break;
            case R.id.button_backspace:
                backSpace();
                break;
            default:
                break;
        }
    }

    /**
     * 输入字母
     */
    private void setTrainLevel(String level) {
        showTrainNumber.setText(level.toUpperCase());
    }

    /**
     * 输入数字
     *
     * @param number 数字
     */
    private void setNumber(int number) {
        String currentInput = showTrainNumber.getText().toString().trim() + number;
        showTrainNumber.setText(currentInput);
    }

    /**
     * 退格
     */
    private void backSpace() {
        String currentInput = showTrainNumber.getText().toString().trim();
        if (TextUtils.isEmpty(currentInput)) {
            return;
        }
        showTrainNumber.setText(currentInput.substring(0, currentInput.length() - 1));
    }


    /**
     * 清空
     */
    private void clearTrain() {
        showTrainNumber.setText(EMPTY);
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

        Intent intent = new Intent(getContext(), TrainDetailActivity.class);
        Bundle bundle = new Bundle();
        bundle.putInt(TRAIN_DATE, selectedDate);
        bundle.putString(TRAIN_CODE, code);
        intent.putExtra(DATA_BUNDLE, bundle);
        startActivity(intent);
    }


    @Override
    public void updateView(int year, int month, int dayOfMonth) {
        //TODO
        calendar = Calendar.getInstance();
        calendar.set(year, month, dayOfMonth);
        selectedDate = getData(calendar);
        showDate.setText(String.valueOf(selectedDate));
    }

}
