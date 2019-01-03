package com.by5388.sy95306v2.chengdu.screen.persenter;

/**
 * @author by5388  on 2018/8/18.
 */
public interface ICdScreenPresenter {

    /**
     * 搜索
     *
     * @param stationCode 车站电报码
     * @param date        日期
     * @param type        到、发
     */
    void getScreenItems(String stationCode, String date, int type);

    /**
     * 关闭时，取消订阅关系，防止内存泄露
     */
    void unSubscribe();

    /**
     * 成都大屏幕中所有车站
     */
    void getScreenStation();

}
