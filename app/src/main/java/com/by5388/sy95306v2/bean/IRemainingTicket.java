package com.by5388.sy95306v2.bean;

/**
 * 余票结果显示的接口：
 *
 * @author by5388  on 2018/8/18.
 */
public interface IRemainingTicket {

    /**
     * 车次
     *
     * @return 车次
     */
    String getCode();

    /**
     * 出发站
     *
     * @return 出发站
     */
    String getFromStation();

    /**
     * 目的站
     *
     * @return 目的站
     */
    String getToStation();

    /**
     * 出发时间
     *
     * @return 出发时间
     */
    String getStartTime();

    /**
     * 达到时间
     *
     * @return 达到时间
     */
    String getEndTime();

    /**
     * 花费时间
     *
     * @return 花费时间
     */
    String getSpeedTime();

    /**
     * 软卧余票
     *
     * @return 软卧余票
     */
    String getRw();

    /**
     * 硬卧余票
     *
     * @return 硬卧余票
     */
    String getYw();

    /**
     * 软座余票或者一等座
     *
     * @return 软座余票或者一等座
     */
    String getRz();

    /**
     * 硬座余票或者二等座
     *
     * @return 硬座余票或者二等座
     */
    String getYz();

    /**
     * 其他席位余票：商务、特等、高级软座
     *
     * @return 其他席位余票
     */
    String getQt();

    /**
     * 无座余票：站票
     *
     * @return 无座余票
     */
    String getWz();

}
