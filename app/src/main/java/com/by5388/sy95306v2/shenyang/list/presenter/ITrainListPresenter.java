package com.by5388.sy95306v2.shenyang.list.presenter;

/**
 * @author by5388  on 2018/7/29.
 */

public interface ITrainListPresenter {
    /**
     * 查询车次：站到站
     *
     * @param date        日期 转化成20180506这种格式
     * @param fromStation 出发站/城市
     * @param toStation   终点站/城市
     */
    void getTrainList(int date, String fromStation, String toStation);

    /**
     * 关闭时，取消订阅关系，防止内存泄露
     */
    void unSubscribe();

    /**
     * 排序+过滤
     *
     * @param method 排序规则
     * @param isUp   向上：向下
     */
    void sortTrainList(int method, boolean isUp);

    /**
     * 车次始终点
     *
     * @param fromStationCode
     * @param toStationCode
     * @return
     */
    String getStationNames(String fromStationCode, String toStationCode);
}
