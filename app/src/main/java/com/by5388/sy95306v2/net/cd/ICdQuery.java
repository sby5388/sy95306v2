package com.by5388.sy95306v2.net.cd;

import com.by5388.sy95306v2.bean.cd.late.CdLateStation;
import com.by5388.sy95306v2.bean.cd.late.CdTrainAllNodeBean;
import com.by5388.sy95306v2.bean.cd.screen.ScreenArriveDetail;
import com.by5388.sy95306v2.bean.cd.screen.ScreenLeaveDetail;
import com.by5388.sy95306v2.bean.cd.screen.ScreenStation;
import com.by5388.sy95306v2.bean.cd.yupiao.CdYpDetailBean;

import java.util.List;

import io.reactivex.Observable;

/**
 * 成都铁路局对外统一暴露接口
 *
 * @author by5388
 * @date 2018/8/16 11:13
 */
public interface ICdQuery {

    /**
     * 成都铁路局余票查询
     *
     * @param fromStation 出发
     * @param toStation   终点
     * @param date        日期
     * @return 余票数据
     */
    Observable<List<CdYpDetailBean>> getCdYp(String fromStation, String toStation, String date);

    /**
     * 正晚点查询：返回车次所对应的车站列表,详细信息
     *
     * @param trainCode   车次
     * @param date        日期
     * @param stationName 车站名称
     * @return 详细信息
     */
    Observable<List<CdTrainAllNodeBean>> getLateDetail(String trainCode, String date, String stationName);

    /**
     * 正晚点查询：返回车次所对应的车站列表
     *
     * @param trainCode 车次
     * @param date      日期
     * @return 车次所对应的车站列表
     */
    Observable<List<CdLateStation>> getLate(String trainCode, String date);


    /**
     * 获取车站大屏幕的车站列表
     *
     * @return 车站列表
     */
    Observable<List<ScreenStation>> getCdScreenStationList();

    /**
     * 成都铁路局车站大屏幕:侯乘(出发)
     *
     * @param stationCode 车站电报码
     * @param date        日期
     * @return 数据
     */
    Observable<List<ScreenLeaveDetail>> getLeaveDetail(String stationCode, String date);

    /**
     * 成都铁路局车站大屏幕:接站(到达)
     *
     * @param stationCode 车站电报码
     * @param date        日期
     * @return 数据
     */
    Observable<List<ScreenArriveDetail>> getArriveDetail(String stationCode, String date);

}
