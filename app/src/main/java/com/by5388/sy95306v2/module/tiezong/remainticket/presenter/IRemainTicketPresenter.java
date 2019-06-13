package com.by5388.sy95306v2.module.tiezong.remainticket.presenter;

/**
 * @author by5388  on 2018/8/13.
 */
public interface IRemainTicketPresenter {

    /**
     * 查询余票
     *
     * @param fromStation 出发
     * @param toStation   目的
     * @param code        车次
     * @param trainDate   日期
     */
    void getRemainTicketMessage(String fromStation, String toStation, String code, String trainDate);

    /**
     * 关闭时，取消订阅关系，防止内存泄露
     */
    void unSubscribe();
}
