package com.by5388.sy95306v2.module.guangzhou;

import com.by5388.sy95306v2.base.BaseFragment;
import com.by5388.sy95306v2.MyListener;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * 广铁集团微信公众号查询的功能
 * 6个子页面
 *
 * @author by5388  on 2018/7/28.
 */

public abstract class BaseGzFragment extends BaseFragment implements MyListener.UpdateDate {


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
