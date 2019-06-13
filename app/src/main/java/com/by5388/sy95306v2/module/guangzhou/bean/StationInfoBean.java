package com.by5388.sy95306v2.module.guangzhou.bean;

/**
 * @author by5388  on 2018/8/1.
 */

public class StationInfoBean {
    /**
     * arrDate : 20180726
     * arrTime : 当天07:13
     * arrTimestamp : {"date":26,"day":4,"hours":7,"minutes":13,"month":6,"nanos":0,"seconds":0,"time":1532560380000,"timezoneOffset":-480,"year":118}
     * goTime : 07:13
     * goTimestamp : {"date":26,"day":4,"hours":7,"minutes":13,"month":6,"nanos":0,"seconds":0,"time":1532560380000,"timezoneOffset":-480,"year":118}
     * late :
     * stationName : 泉州
     */

    private String arrDate;
    private String arrTime;
    private ArrTimestampBean arrTimestamp;
    private String goTime;
    private GoTimestampBean goTimestamp;
    private String late;
    private String stationName;

    public String getArrDate() {
        return arrDate;
    }

    public void setArrDate(String arrDate) {
        this.arrDate = arrDate;
    }

    public String getArrTime() {
        return arrTime;
    }

    public void setArrTime(String arrTime) {
        this.arrTime = arrTime;
    }

    public ArrTimestampBean getArrTimestamp() {
        return arrTimestamp;
    }

    public void setArrTimestamp(ArrTimestampBean arrTimestamp) {
        this.arrTimestamp = arrTimestamp;
    }

    public String getGoTime() {
        return goTime;
    }

    public void setGoTime(String goTime) {
        this.goTime = goTime;
    }

    public GoTimestampBean getGoTimestamp() {
        return goTimestamp;
    }

    public void setGoTimestamp(GoTimestampBean goTimestamp) {
        this.goTimestamp = goTimestamp;
    }

    public String getLate() {
        return late;
    }

    public void setLate(String late) {
        this.late = late;
    }

    public String getStationName() {
        return stationName;
    }

    public void setStationName(String stationName) {
        this.stationName = stationName;
    }

}
