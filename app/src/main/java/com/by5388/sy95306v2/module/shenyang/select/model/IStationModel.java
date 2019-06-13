package com.by5388.sy95306v2.module.shenyang.select.model;

import com.by5388.sy95306v2.module.shenyang.bean.Station;

import java.util.List;

import io.reactivex.Observable;

/**
 * @author by5388  on 2018/7/28.
 */

public interface IStationModel {

    /**
     * 获取默认的车站信息
     *
     * @return 车站信息
     */
    Observable<List<Station>> getDefaultStation();


    /**
     * 获取车站名称
     *
     * @param keyWord 关键字
     * @return 车站集合
     */
    Observable<List<Station>> getStation(String keyWord);

}
