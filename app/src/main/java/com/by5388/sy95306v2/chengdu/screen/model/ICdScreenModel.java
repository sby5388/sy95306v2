package com.by5388.sy95306v2.chengdu.screen.model;

import com.by5388.sy95306v2.chengdu.bean.screen.ScreenStation;
import com.by5388.sy95306v2.chengdu.screen.CdScreenItem;

import java.util.List;

import io.reactivex.Observable;

/**
 * @author by5388  on 2018/8/18.
 */
public interface ICdScreenModel {
    /**
     * 出发
     */
    int TYPE_LEAVE = 0;
    /**
     * 到达
     */
    int TYPE_ARRIVE = 1;

    /**
     * 获取车站大屏幕的车站列表
     *
     * @return 车站列表
     */
    Observable<List<ScreenStation>> getCdScreenStationList();

    /**
     * 成都铁路局车站大屏幕
     *
     * @param type        0:侯乘(出发),1:接站(到达)
     * @param stationCode 车站电报码
     * @param date        日期
     * @return 数据
     */
    Observable<List<CdScreenItem>> getScreenItem(int type, String stationCode, String date);
}
