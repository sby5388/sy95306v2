package com.by5388.sy95306v2.module.tiezong.zzcx.view;

import com.by5388.sy95306v2.base.IBaseView;
import com.by5388.sy95306v2.bean.IRemainingTicket;

import java.util.List;

/**
 * @author by5388  on 2018/8/19.
 */
public interface ITzZzCxView extends IBaseView {
    /**
     * 车次列表
     *
     * @param dataBeans 车次列表
     */
    void updateList(List<IRemainingTicket> dataBeans);

}
