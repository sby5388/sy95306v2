package com.by5388.sy95306v2.tiezong.combination.view;

import com.by5388.sy95306v2.bean.IRemainingTicket;
import com.by5388.sy95306v2.tiezong.zzcx.view.ITzZzCxView;

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
}
