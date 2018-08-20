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
import com.by5388.sy95306v2.bean.tzyp.TzYpData;
import com.by5388.sy95306v2.fragment.MyListener;
import com.by5388.sy95306v2.fragment.tz.BaseTzFragment;
import com.by5388.sy95306v2.fragment.tz.api.GetPassCode;
import com.by5388.sy95306v2.fragment.tz.api.IGetPassCodeService;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Random;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * @author by5388  on 2018/8/17.
 */
public class ZzCxFragment extends BaseTzFragment {

    public static final String TAG = "GetTrainByStation";
    private TextInputEditText fromStation, toStation, passCode;
    /**
     * 图片验证码
     */
    private ImageView imageViewPassCode;
    private Button buttonSearch, buttonDate, buttonCheck;
    private ListView listView;
    private final static List<TzYpData> EMPTY_LIST = new ArrayList<>();
    MyListener dateListener;
    Calendar calendar;
    Random random;

    IGetPassCodeService service;

    public static ZzCxFragment newInstance() {
        ZzCxFragment fragment = new ZzCxFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected void initData() {
        dateListener = new MyListener(this);
        calendar = Calendar.getInstance();
        random = new Random();
    }

    @Override
    protected void loadData() {
        buttonDate.setText(getData(calendar));
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

    }

    /**
     * 刷新验证码
     */
    private void refreshPassCode() {
        imageViewPassCode.setEnabled(false);
        // TODO: 2018/8/17
        if (null == service) {
            service = new GetPassCode();
        }

        double value = random.nextDouble();
        Log.d(TAG, "refreshPassCode: " + value);
        service.getBitmap(value)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Bitmap>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Bitmap bitmap) {
                        if (null == bitmap) {
                            return;
                        }
                        imageViewPassCode.setImageBitmap(bitmap);
                    }

                    @Override
                    public void onError(Throwable e) {
                        Toast.makeText(getContext(), e.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                        imageViewPassCode.setEnabled(true);
                    }

                    @Override
                    public void onComplete() {
                        imageViewPassCode.setEnabled(true);
                    }
                });


    }

    private void switchStations() {
        String to = toStation.getText().toString().trim();
        toStation.setText(fromStation.getText().toString().trim());
        fromStation.setText(to);
    }

    private void searchTrainNumber() {

    }

    @Override
    public void updateView(int year, int month, int dayOfMonth) {
        calendar = Calendar.getInstance();
        calendar.set(year, month, dayOfMonth);
        buttonDate.setText(String.valueOf(getData(calendar)));
    }
}
