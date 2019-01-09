package com.by5388.sy95306.fragment;

import android.app.DatePickerDialog;
import android.net.Uri;
import android.support.v4.app.Fragment;
import android.widget.DatePicker;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * @author by5388  on 2018/5/28.
 */

public abstract class BaseFragment extends Fragment {
    public static final String TAG = "BaseFragment";
    /**
     * 往后延长60天
     */
    private static final long MAX_TIME = 5184000000L;

    static int getData(Calendar calendar) {
        //   待优化年月日的格式
        Locale locale = Locale.getDefault();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd", locale);
        Date date = calendar.getTime();
        return Integer.parseInt(sdf.format(date));
    }

    final void selectDate(MyListener dateListener, Calendar calendarData) {
        //  这里要记录已经选择的日期
        DatePickerDialog datePickerDialog = new DatePickerDialog(getContext(), dateListener,
                calendarData.get(Calendar.YEAR), calendarData.get(Calendar.MONTH), calendarData.get(Calendar.DATE));
        Calendar calendar = Calendar.getInstance();
        DatePicker picker = datePickerDialog.getDatePicker();
        // 设置最大日期
        picker.setMaxDate(calendar.getTimeInMillis() + MAX_TIME);
        // 设置最小日期
        picker.setMinDate(calendar.getTimeInMillis());
        datePickerDialog.show();
    }

    class MyListener implements DatePickerDialog.OnDateSetListener {
        @Override
        public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
            updateView(year, month, dayOfMonth);
        }
    }

    /**
     * 更新日期选择框的
     *
     * @param year       年
     * @param month      月
     * @param dayOfMonth 日
     */
    abstract void updateView(int year, int month, int dayOfMonth);


}
