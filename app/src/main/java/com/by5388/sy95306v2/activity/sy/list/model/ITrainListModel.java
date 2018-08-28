package com.by5388.sy95306v2.activity.sy.list.model;

import com.by5388.sy95306v2.bean.shenyang.TrainNumber;

import java.util.List;

import io.reactivex.Observable;

/**
 * @author by5388  on 2018/7/29.
 */

public interface ITrainListModel {

    /**
     * 查询车次：站到站
     *
     * @param date        日期 转化成20180506这种格式
     * @param fromStation 出发站/城市
     * @param toStation   终点站/城市
     * @return 车次信息
     */
    Observable<List<TrainNumber>> getTrainNumber(int date, String fromStation, String toStation);

    /**
     * 对车次进行排序
     *
     * @param position     排序规则
     * @param trainNumbers 车次
     * @param isUp         向上：向下
     * @return 车次信息
     */
    Observable<List<TrainNumber>> sortTrainNumber(List<TrainNumber> trainNumbers, int position, boolean isUp);


    /**
     * 车次始终点
     *
     * @param fromStationCode
     * @param toStationCode
     * @return
     */
    String getStationNames(String fromStationCode, String toStationCode);
}
