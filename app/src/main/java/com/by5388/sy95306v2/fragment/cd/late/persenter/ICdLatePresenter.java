package com.by5388.sy95306v2.fragment.cd.late.persenter;

import com.by5388.sy95306v2.base.IBasePresenter;

/**
 * @author by5388  on 2018/8/18.
 */
public interface ICdLatePresenter extends IBasePresenter {

    /**
     * 正晚点查询：返回车次所对应的车站列表
     *
     * @param trainCode 车次
     * @param date      日期
     */
    void getLateStationList(String trainCode, String date);

    /**
     * 正晚点查询：返回车次所对应的车站列表,详细信息
     *
     * @param trainCode   车次
     * @param date        日期
     * @param stationName 车站名称
     */
    void getLateDetail(String trainCode, String date, String stationName);
}
