package com.by5388.sy95306v2.base;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;

import com.by5388.sy95306v2.MyListener;

import java.util.Calendar;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

/**
 * @author by5388  on 2018/7/28.
 */

public abstract class BaseFragment extends Fragment {

    @Override
    public final void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initData();
    }


    /**
     * 初始化数据
     */
    protected abstract void initData();

    @Nullable
    @Override
    public final View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(getLayoutID(), container, false);
        initView(view);
        loadData();
        return view;
    }

    /**
     * 数据绑定到View 上
     */
    protected abstract void loadData();

    /**
     * 获取布局文件ID
     *
     * @return 布局文件ID
     */
    @LayoutRes
    protected abstract int getLayoutID();

    /**
     * 初始化布局文件，布局文件的定义等
     *
     * @param view 布局
     */
    protected abstract void initView(View view);

    /**
     * 往后延长60天
     */
    private static final long MAX_TIME = TimeUnit.DAYS.toMillis(60);

    protected final void selectDate(MyListener dateListener, Calendar calendarData) {
        //  这里要记录已经选择的日期
        DatePickerDialog datePickerDialog = new DatePickerDialog(Objects.requireNonNull(getContext()), dateListener,
                calendarData.get(Calendar.YEAR), calendarData.get(Calendar.MONTH), calendarData.get(Calendar.DATE));
        Calendar calendar = Calendar.getInstance();
        DatePicker picker = datePickerDialog.getDatePicker();
        // 设置最大日期
        picker.setMaxDate(calendar.getTimeInMillis() + MAX_TIME);
        // 设置最小日期
        picker.setMinDate(calendar.getTimeInMillis());
        datePickerDialog.show();
    }


}
