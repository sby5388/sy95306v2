package com.by5388.sy95306v2.module.tiezong.zzcx.model;

import com.by5388.sy95306v2.module.tiezong.bean.yp.success.SuccessDataBean;

import io.reactivex.Observable;

/**
 * @author by5388  on 2018/8/20.
 */
public interface ITzZzCxModel {


    /**
     * 站名是否正确：存在数据库中
     *
     * @param stationName 站名
     * @return 正确与否
     */
    boolean isErrorStationName(String stationName);

    /**
     * 中转查询
     *
     * @param queryDate       查询的日期
     * @param fromStationName 出发站
     * @param toStationName   目的站
     * @return 结果
     */
    Observable<SuccessDataBean> getResult(String queryDate, String fromStationName,
                                          String toStationName);

}
