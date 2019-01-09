package com.by5388.sy95306.detail.model;


import com.by5388.sy95306.bean.TrainDetail;

import java.util.List;

import io.reactivex.Observable;

/**
 * @author by5388  on 2018/7/29.
 */

public interface IDetailModel {
    /**
     * 车次查询:第二个页面使用、第一个页面查询后的条目点击事件也是这个
     *
     * @param date      日期
     * @param trainCode 车次
     * @return 车次的停站信息
     */
    Observable<List<TrainDetail>> getTrainByTrainCode(int date, String trainCode);

    /**
     * 获取车次名，车次对应的始发站-终到站,北京南-上海虹桥
     *
     * @param details 车次
     * @return 名称
     */
    String getTrainName(List<TrainDetail> details);

    /**
     * 车次编号
     *
     * @param trainDetails 车次
     * @return 编号
     */
    String getCodeName(List<TrainDetail> trainDetails);
}
