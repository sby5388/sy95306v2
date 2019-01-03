package com.by5388.sy95306v2.shenyang.select.presenter;

/**
 * @author by5388  on 2018/7/28.
 */

public interface IStationPresenter {
    /**
     * 获取车站信息
     *
     * @param keyWord name
     */
    void getStation(String keyWord);

    /**
     * 获取默认的车站信息
     */
    void getDefaultStation();

    /**
     * 关闭时，取消订阅关系，防止内存泄露
     */
    void unSubscribe();
}
