package com.by5388.sy95306v2.tiezong.bean.number;

import com.google.gson.annotations.SerializedName;

/**
 * 12306 车次查询返回item
 *
 * @author by5388  on 2018/8/24.
 */
@SuppressWarnings("all")
public class NumberDataBean {
    /**
     * startStationName : 湛江西
     * arriveTime : 09:20
     * stationTrainCode : D7458
     * stationName : 湛江西
     * trainClassName : 动车
     * serviceType : 1
     * startTime : 09:20
     * stopoverTime : ----
     * endStationName : 广州南
     * stationNo : 01
     * isEnabled : false
     */
    @SerializedName("start_station_name")
    private String startStationName;
    @SerializedName("arrive_time")
    private String arriveTime;
    @SerializedName("station_train_code")
    private String stationTrainCode;
    @SerializedName("station_name")
    private String stationName;
    @SerializedName("train_class_name")
    private String trainClassName;
    @SerializedName("service_type")
    private String serviceType;
    @SerializedName("start_time")
    private String startTime;
    @SerializedName("stopover_time")
    private String stopoverTime;
    @SerializedName("end_station_name")
    private String endStationName;
    @SerializedName("station_no")
    private String stationNo;
    private boolean isEnabled;

    public String getStartStationName() {
        return startStationName;
    }

    public void setStartStationName(String startStationName) {
        this.startStationName = startStationName;
    }

    public String getArriveTime() {
        return arriveTime;
    }

    public void setArriveTime(String arriveTime) {
        this.arriveTime = arriveTime;
    }

    public String getStationTrainCode() {
        return stationTrainCode;
    }

    public void setStationTrainCode(String stationTrainCode) {
        this.stationTrainCode = stationTrainCode;
    }

    public String getStationName() {
        return stationName;
    }

    public void setStationName(String stationName) {
        this.stationName = stationName;
    }

    public String getTrainClassName() {
        return trainClassName;
    }

    public void setTrainClassName(String trainClassName) {
        this.trainClassName = trainClassName;
    }

    public String getServiceType() {
        return serviceType;
    }

    public void setServiceType(String serviceType) {
        this.serviceType = serviceType;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getStopoverTime() {
        return stopoverTime;
    }

    public void setStopoverTime(String stopoverTime) {
        this.stopoverTime = stopoverTime;
    }

    public String getEndStationName() {
        return endStationName;
    }

    public void setEndStationName(String endStationName) {
        this.endStationName = endStationName;
    }

    public String getStationNo() {
        return stationNo;
    }

    public void setStationNo(String stationNo) {
        this.stationNo = stationNo;
    }

    public boolean isIsEnabled() {
        return isEnabled;
    }

    public void setIsEnabled(boolean isEnabled) {
        this.isEnabled = isEnabled;
    }
}