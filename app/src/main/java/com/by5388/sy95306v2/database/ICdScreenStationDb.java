package com.by5388.sy95306v2.database;

import com.by5388.sy95306v2.bean.cd.screen.ScreenStation;

import java.util.List;

/**
 * @author by5388  on 2018/8/20.
 */
public interface ICdScreenStationDb {
    // 2018/8/18 需要增加成都铁路的车站大屏幕所对应的车站信息

    /**
     * 成都车站大屏幕
     *
     * @return 成都车站大屏幕车站列表
     */
    List<ScreenStation> getCdScreenStations();

    /**
     * 删除所有数据
     *
     * @return 标记
     */
    int deleteAllCdScreenStations();

    /**
     * 增加数据
     *
     * @param screenStation 车站数据
     */
    void addCdScreenStation(ScreenStation screenStation);

    /**
     * 是否为空
     *
     * @return true:空
     */
    boolean cdStationIsEmpty();
}
