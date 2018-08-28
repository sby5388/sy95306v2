package com.by5388.sy95306v2.activity.tz.view;

import com.by5388.sy95306v2.bean.IRemainingTicket;
import com.by5388.sy95306v2.fragment.tz.zzcx.view.ITzZzCxView;

/**
 * @author by5388  on 2018/8/19.
 */
public interface ICombinationView extends ITzZzCxView {
    /**
     * 查询时 刷新数据
     *
     * @param ticket 余票信息
     */
    void addIRemainingTicket(IRemainingTicket ticket);

    /**
     * 筛选对话框
     */
    void showFilterDialog();


}
