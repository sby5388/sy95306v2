package com.by5388.sy95306v2.module.guangzhou.p2p.model;

import com.by5388.sy95306v2.module.guangzhou.bean.GzResultData;
import com.by5388.sy95306v2.module.guangzhou.bean.station.DataBeanP2P;

import io.reactivex.Observable;

/**
 * @author by5388  on 2018/8/10.
 */

public interface IGzP2pModel {
    /**
     * 站站查询
     *
     * @param fromStation 出发站
     * @param toStation   目的站
     * @param date        日期
     * @return 车次信息
     */
    Observable<GzResultData<DataBeanP2P>> getTrainByStation(String fromStation,
                                                            String toStation,
                                                            String date);
}
