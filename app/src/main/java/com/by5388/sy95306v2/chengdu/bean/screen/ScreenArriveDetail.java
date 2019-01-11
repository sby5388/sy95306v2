package com.by5388.sy95306v2.chengdu.bean.screen;

import com.by5388.sy95306v2.chengdu.screen.CdScreenItem;
import com.google.gson.annotations.SerializedName;

/**
 * 车站大屏幕：显示详情:到达
 *
 * @author by5388
 * @date 2018/8/17 23:54
 */
@SuppressWarnings("all")
public class ScreenArriveDetail implements CdScreenItem {

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
        return getLateArrive();
    }

    @Override
    public String getPlace() {
        return getStationExit();
    }

    @Override
    public String getStatus() {
        return getTrainStatus();
    }

    /**
     * checkStatus : 停止检票,检票状态
     * DATE_ARR11 : 00:01
     * endStationName : 昆明
     * IN_DATE : 2018/08/17 23:03:47
     * lateArrive : 0：是否晚点
     * startStationName : 北京西
     * trainStatus : 正点
     * stationCode : GIW
     * stationExit : 出站口
     * trainCode : Z53
     * waitRoom : 第3候车室
     * unKnow : 0
     */


    @SerializedName("CHECK_STATUS")
    private String checkStatus;
    private String DATE_ARR11;
    @SerializedName("END_STN")
    private String endStationName;
    private String IN_DATE;
    @SerializedName("LATE_ARR")
    private String lateArrive;
    @SerializedName("START_STN")
    private String startStationName;
    @SerializedName("STATUS_TRAIN")
    private String trainStatus;
    @SerializedName("STN_CODE")
    private String stationCode;
    @SerializedName("STN_EXIT")
    private String stationExit;
    @SerializedName("TRAIN_DEP")
    private String trainCode;
    @SerializedName("WAIT_ROOM")
    private String waitRoom;
    @SerializedName("WGQBZ")
    private String unKnow;

    public String getCheckStatus() {
        return checkStatus;
    }

    public void setCheckStatus(String mCheckStatus) {
        checkStatus = mCheckStatus;
    }

    public String getDATE_ARR11() {
        return DATE_ARR11;
    }

    public void setDATE_ARR11(String mDATE_ARR11) {
        DATE_ARR11 = mDATE_ARR11;
    }

    public String getEndStationName() {
        return endStationName;
    }

    public void setEndStationName(String mEndStationName) {
        endStationName = mEndStationName;
    }

    public String getIN_DATE() {
        return IN_DATE;
    }

    public void setIN_DATE(String mIN_DATE) {
        IN_DATE = mIN_DATE;
    }

    public String getLateArrive() {
        return lateArrive;
    }

    public void setLateArrive(String mLateArrive) {
        lateArrive = mLateArrive;
    }

    public String getStartStationName() {
        return startStationName;
    }

    public void setStartStationName(String mStartStationName) {
        startStationName = mStartStationName;
    }

    public String getTrainStatus() {
        return trainStatus;
    }

    public void setTrainStatus(String mTrainStatus) {
        trainStatus = mTrainStatus;
    }

    public String getStationCode() {
        return stationCode;
    }

    public void setStationCode(String mStationCode) {
        stationCode = mStationCode;
    }

    public String getStationExit() {
        return stationExit;
    }

    public void setStationExit(String mStationExit) {
        stationExit = mStationExit;
    }

    public void setTrainCode(String mTrainCode) {
        trainCode = mTrainCode;
    }

    public String getWaitRoom() {
        return waitRoom;
    }

    public void setWaitRoom(String mWaitRoom) {
        waitRoom = mWaitRoom;
    }

    public String getUnKnow() {
        return unKnow;
    }

    public void setUnKnow(String mUnKnow) {
        unKnow = mUnKnow;
    }
}
