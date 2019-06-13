package com.by5388.sy95306v2.module.guangzhou.bean.station;

/**
 * @author by5388  on 2018/8/1.
 */

public class StationsBean {
    /**
     * arriveTime : 当天10:15
     * cc : D682
     * fromStation : 饶平
     * leaveTime : 09:10
     * sfStation : 深圳北
     * spendTime : 1小时5分
     * stopFlag : 1
     * toStation : 厦门北
     * zdStation : 厦门北
     */

    private String arriveTime;
    private String cc;
    private String fromStation;
    private String leaveTime;
    private String sfStation;
    private String spendTime;
    private int stopFlag;
    private String toStation;
    private String zdStation;

    public String getArriveTime() {
        return arriveTime;
    }

    public void setArriveTime(String arriveTime) {
        this.arriveTime = arriveTime;
    }

    public String getCc() {
        return cc;
    }

    public void setCc(String cc) {
        this.cc = cc;
    }

    public String getFromStation() {
        return fromStation;
    }

    public void setFromStation(String fromStation) {
        this.fromStation = fromStation;
    }

    public String getLeaveTime() {
        return leaveTime;
    }

    public void setLeaveTime(String leaveTime) {
        this.leaveTime = leaveTime;
    }

    public String getSfStation() {
        return sfStation;
    }

    public void setSfStation(String sfStation) {
        this.sfStation = sfStation;
    }

    public String getSpendTime() {
        return spendTime;
    }

    public void setSpendTime(String spendTime) {
        this.spendTime = spendTime;
    }

    public int getStopFlag() {
        return stopFlag;
    }

    public void setStopFlag(int stopFlag) {
        this.stopFlag = stopFlag;
    }

    public String getToStation() {
        return toStation;
    }

    public void setToStation(String toStation) {
        this.toStation = toStation;
    }

    public String getZdStation() {
        return zdStation;
    }

    public void setZdStation(String zdStation) {
        this.zdStation = zdStation;
    }
}
