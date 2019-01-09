package com.by5388.sy95306v2.tiezong.combination.model;

import com.by5388.sy95306v2.shenyang.bean.TrainDetail;
import com.by5388.sy95306v2.tiezong.bean.yp.success.SuccessDataBean;
import com.by5388.sy95306v2.tiezong.zzcx.model.ITzZzCxModel;

import java.util.List;

import io.reactivex.Observable;

/**
 * @author by5388  on 2018/8/22.
 */
public interface ICombinationModel extends ITzZzCxModel {

    /**
     * 中转查询:精准查询
     *
     * @param queryDate       查询的日期
     * @param fromStationName 出发站
     * @param toStationName   目的站
     * @param randCode        验证码
     * @param trainCode       车次
     * @return 结果
     */
    Observable<SuccessDataBean> getOnlyResult(String queryDate, String fromStationName, String toStationName, String randCode, String trainCode);

//    /**
//     * 中转查询  ：没有目的站，一直从出发站 查到最后一站
//     *
//     * @param queryDate       查询的日期
//     * @param fromStationName 出发站
//     * @param randCode        验证码
//     * @param trainCode       车次
//     * @return 结果
//     */
//    Observable<List<Observable<SuccessDataBean>>> getTrainListByEmptyToStation(String queryDate, String fromStationName, String randCode, String trainCode);

    /**
     * 车次查询:第二个页面使用、第一个页面查询后的条目点击事件也是这个
     *
     * @param date      日期
     * @param trainCode 车次
     * @return 车次的停站信息
     */
    Observable<List<TrainDetail>> getTrainByTrainCode(int date, String trainCode);

}
