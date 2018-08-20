package com.by5388.sy95306v2.fragment;

import android.app.DatePickerDialog;
import android.widget.DatePicker;

/**
 * @author by5388  on 2018/8/7.
 */

public class MyListener implements DatePickerDialog.OnDateSetListener {
    private UpdateDate updateDate;

    public MyListener(UpdateDate updateDate) {
        this.updateDate = updateDate;
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        updateDate.updateView(year, month, dayOfMonth);
    }


    public interface UpdateDate {
        /**
         * 更新日期选择框的
         *
         * @param year       年
         * @param month      月
         * @param dayOfMonth 日
         */
        void updateView(int year, int month, int dayOfMonth);
    }

}