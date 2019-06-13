package com.by5388.sy95306v2.module.chengdu.yupiao.persenter;

/**
 * @author by5388  on 2018/8/18.
 */
public interface ICdRemainTicketPresenter {
    /**
     * 关闭时，取消订阅关系，防止内存泄露
     */
    void unSubscribe();

    /**
     * 成都铁路局余票查询
     *
     * @param fromStation 出发
     * @param toStation   终点
     * @param date        日期
     */
    void getRemainTicketMessage(String fromStation, String toStation, String date);
}
