package com.by5388.sy95306v2.module.shenyang;

import com.by5388.sy95306v2.MyListener;
import com.by5388.sy95306v2.base.BaseFragment;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

/**
 * @author by5388  on 2018/7/28.
 */

public abstract class BaseShenYangFragment extends BaseFragment implements MyListener.UpdateDate {
    public static final String TAG = "BaseShenYangFragment";
    private SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd", Locale.getDefault());

    /**
     * 日期选择
     *
     * @param calendar 日期
     * @return 格式转化后的日期
     */
    int getData(Calendar calendar) {
        return Integer.parseInt(sdf.format(calendar.getTime()));
    }

}
