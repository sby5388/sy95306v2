package com.by5388.sy95306v2.module.chengdu.bean.screen;

import com.by5388.sy95306v2.module.chengdu.screen.CdScreenItem;
import com.google.gson.annotations.SerializedName;

/**
 * 车站大屏幕：显示详情:出发
 *
 * @author by5388
 * @date 2018/8/17 23:45
 */
//@SuppressWarnings("all")
public class ScreenLeaveDetail implements CdScreenItem {
    /**
     * checkStatus : 正在检票
     * checkTicket : 3候5,第三候车室
     * arriveDate : 23:26:00
     * endCheckTime : 2018/08/17 23:51:00
     * endStationName : 昆明
     * endStationCode : KMM
     * IN_DATE : 2018/08/17 23:30:35
     * startCheckTime : 2018/08/17 23:24:00
     * startStationName : 上海南
     * startStationCode : SNH
     * trainStatus : 正点
     * stationCode : GIW
     * TD_DATE_DEP11 : 23:56
     * trainCode : K739
     * waitRoom : 第3候车室
     * unKnow : 1
     */


    @SerializedName("CHECK_STATUS")
    private String checkStatus;
    @SerializedName("CHECK_TICKET")
    private String checkTicket;
    @SerializedName("DATE_ARR")
    private String arriveDate;
    @SerializedName("END_CHECK_TIME")
    private String endCheckTime;
    @SerializedName("END_STN")
    private String endStationName;
    @SerializedName("END_STN_CODE")
    private String endStationCode;
    private String IN_DATE;
    @SerializedName("START_CHECK_TIME")
    private String startCheckTime;
    @SerializedName("START_STN")
    private String startStationName;
    @SerializedName("START_STN_CODE")
    private String startStationCode;
    @SerializedName("STATUS_TRAIN")
    private String trainStatus;
    @SerializedName("STN_CODE")
    private String stationCode;
    private String TD_DATE_DEP11;
    @SerializedName("TRAIN_DEP")
    private String trainCode;
    @SerializedName("WAIT_ROOM")
    private String waitRoom;
    @SerializedName("WGQBZ")
    private String unKnow;

    @Override
    public String getTrainCode() {
        return trainCode;
    }

    @Override
    public String getStartStation() {
        return getStartStationName();
    }

    @Override
    public String getEndStation() {
        return getEndStationName();
    }

    @Override
    public String getTime() {
        return getTD_DATE_DEP11();
    }

    @Override
    public String getPlace() {
        return getCheckTicket();
    }

    @Override
    public String getStatus() {
        return getCheckStatus();
    }

    public String getCheckStatus() {
        return checkStatus;
    }

    public String getCheckTicket() {
        return checkTicket;
    }

    public String getArriveDate() {
        return arriveDate;
    }

    public String getEndCheckTime() {
        return endCheckTime;
    }

    public String getEndStationName() {
        return endStationName;
    }

    public String getEndStationCode() {
        return endStationCode;
    }

    public String getIN_DATE() {
        return IN_DATE;
    }

    public String getStartCheckTime() {
        return startCheckTime;
    }

    public String getStartStationName() {
        return startStationName;
    }

    public String getStartStationCode() {
        return startStationCode;
    }

    public String getTrainStatus() {
        return trainStatus;
    }

    public String getStationCode() {
        return stationCode;
    }

    public String getTD_DATE_DEP11() {
        return TD_DATE_DEP11;
    }

    public String getWaitRoom() {
        return waitRoom;
    }

    public String getUnKnow() {
        return unKnow;
    }
}
