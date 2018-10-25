package com.by5388.sy95306v2.activity.tz.model;

import com.by5388.sy95306v2.bean.shenyang.TrainDetail;
import com.by5388.sy95306v2.bean.tz.yp.success.SuccessDataBean;
import com.by5388.sy95306v2.fragment.tz.zzcx.model.ITzZzCxModel;

import java.util.List;

import io.reactivex.Observable;

/**
 * @author by5388  on 2018/8/22.
 */
public interface IDetailRemainTicketModel extends ITzZzCxModel {

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
    Observable<SuccessDataBean> getOnLyResult(String queryDate, String fromStationName, String toStationName, String randCode, String trainCode);

    /**
     * 车次查询:第二个页面使用、第一个页面查询后的条目点击事件也是这个
     *
     * @param date      日期
     * @param trainCode 车次
     * @return 车次的停站信息
     */
    Observable<List<TrainDetail>> getTrainByTrainCode(int date, String trainCode);

}
