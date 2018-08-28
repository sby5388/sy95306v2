package com.by5388.sy95306v2.fragment.cd.yupiao.model;

import com.by5388.sy95306v2.bean.IRemainingTicket;

import java.util.List;

import io.reactivex.Observable;

/**
 * @author by5388  on 2018/8/18.
 */
public interface ICdRemainTicketModel {
    /**
     * 成都铁路局余票查询
     *
     * @param fromStation 出发
     * @param toStation   终点
     * @param date        日期
     * @return 余票数据
     */
    Observable<List<IRemainingTicket>> getCdYp(String fromStation, String toStation, String date);
}
