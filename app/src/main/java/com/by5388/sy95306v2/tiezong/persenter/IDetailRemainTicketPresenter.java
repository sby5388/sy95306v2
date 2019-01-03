package com.by5388.sy95306v2.tiezong.persenter;

import com.by5388.sy95306v2.tiezong.zzcx.persenter.ITzZzCxPresenter;

/**
 * @author by5388  on 2018/8/22.
 */
public interface IDetailRemainTicketPresenter extends ITzZzCxPresenter {

    /**
     * 中转查询，获取列车信息
     *
     * @param date        日期
     * @param fromStation 出发站名
     * @param toStation   到达站名
     * @param randCode    验证码
     * @param trainCode   车次
     */
    void getOnlyOneTrainList(String date, String fromStation, String toStation, String randCode, String trainCode);


    /**
     * 中转查询，获取列车信息 :没有出发站
     *
     * @param date      日期
     * @param toStation 到达站名
     * @param randCode  验证码
     * @param trainCode 车次
     */
    void getTrainListByEmptyFromStation(String date, String toStation, String randCode, String trainCode);

    /**
     * 中转查询，获取列车信息 :没有车次
     *
     * @param date        日期
     * @param fromStation 出发站名
     * @param toStation   到达站名
     * @param randCode    验证码
     */
    void getTrainListByEmptyTrainCode(String date, String fromStation, String toStation, String randCode);

    /**
     * 中转查询，获取列车信息:没有目的站
     *
     * @param date        日期
     * @param fromStation 出发站名
     * @param randCode    验证码
     * @param trainCode   车次
     */
    void getTrainListByEmptyToStation(String date, String fromStation, String randCode, String trainCode);
}
