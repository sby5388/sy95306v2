package com.by5388.sy95306v2.module.tiezong.remainticket.view;

import com.by5388.sy95306v2.base.IBaseView;
import com.by5388.sy95306v2.bean.IRemainingTicket;

import java.util.List;

/**
 * TODO 接口类有待统一
 *
 * @author by5388  on 2018/8/13.
 */
public interface IRemainTicketView extends IBaseView {

    /**
     * 更新数据
     *
     * @param remainTicketData 数据
     */
    void updateDate(List<IRemainingTicket> remainTicketData);
}
