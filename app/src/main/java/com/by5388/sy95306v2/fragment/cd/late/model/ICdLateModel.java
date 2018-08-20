package com.by5388.sy95306v2.fragment.cd.late.model;

import com.by5388.sy95306v2.bean.cd.late.CdLateDetail;
import com.by5388.sy95306v2.bean.cd.late.CdLateStation;
import com.by5388.sy95306v2.bean.cd.late.CdTrainAllNodeBean;

import java.util.List;

import io.reactivex.Observable;

/**
 * @author by5388  on 2018/8/18.
 */
public interface ICdLateModel {

    /**
     * 正晚点查询：返回车次所对应的车站列表
     *
     * @param trainCode 车次
     * @param date      日期
     * @return 车次所对应的车站列表
     */
    Observable<List<CdLateStation>> getLateStationList(String trainCode, String date);


    /**
     * 正晚点查询：返回车次所对应的车站列表,详细信息
     *
     * @param trainCode   车次
     * @param date        日期
     * @param stationName 车站名称
     * @return 详细信息
     */
    Observable<List<CdTrainAllNodeBean>> getLateDetail(String trainCode, String date, String stationName);
}
