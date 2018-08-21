package com.by5388.sy95306v2.fragment.shenyang;

import com.by5388.sy95306v2.fragment.BaseFragment;
import com.by5388.sy95306v2.fragment.MyListener;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * @author by5388  on 2018/7/28.
 */

public abstract class BaseShenYangFragment extends BaseFragment implements MyListener.UpdateDate {
    public static final String TAG = "BaseShenYangFragment";
    /**
     * 往后延长60天
     */
    private static final long MAX_TIME = 5184000000L;

    /**
     * 日期选择
     *
     * @param calendar 日期
     * @return 格式转化后的日期
     */
    int getData(Calendar calendar) {
        Locale locale = Locale.getDefault();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd", locale);
        Date date = calendar.getTime();
        return Integer.parseInt(sdf.format(date));
    }

}
