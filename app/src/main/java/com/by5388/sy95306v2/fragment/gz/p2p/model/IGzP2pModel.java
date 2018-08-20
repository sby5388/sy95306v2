package com.by5388.sy95306v2.fragment.gz.p2p.model;

import com.by5388.sy95306v2.bean.guangzhou.ResultData;
import com.by5388.sy95306v2.bean.guangzhou.station.DataBeanP2P;

import io.reactivex.Observable;

/**
 * @author by5388  on 2018/8/10.
 */

public interface IGzP2pModel {
    /**
     * 站站查询
     *
     * @param fromStation
     * @param toStation
     * @param date
     * @return
     */
    Observable<ResultData<DataBeanP2P>> getTrainByStation(String fromStation,
                                                          String toStation,
                                                          String date);
}
