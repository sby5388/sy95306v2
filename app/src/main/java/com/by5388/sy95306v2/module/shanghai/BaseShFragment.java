package com.by5388.sy95306v2.module.shanghai;

import com.by5388.sy95306v2.base.BaseFragment;
import com.by5388.sy95306v2.MyListener;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * 上海12306
 *
 * @author by5388  on 2018/8/8.
 */

public abstract class BaseShFragment extends BaseFragment implements MyListener.UpdateDate {
    /**
     * 日期选择
     *
     * @param calendar 日期
     * @return 格式转化后的日期
     */
    protected String getData(Calendar calendar) {
        Locale locale = Locale.getDefault();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", locale);
        Date date = calendar.getTime();
        return sdf.format(date);
    }
}
