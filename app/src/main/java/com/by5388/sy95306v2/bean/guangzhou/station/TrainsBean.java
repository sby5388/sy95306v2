package com.by5388.sy95306v2.bean.guangzhou.station;

import com.by5388.sy95306v2.bean.guangzhou.StationInfosBean;

import java.util.List;

/**
 * @author by5388  on 2018/8/1.
 */


public class TrainsBean {
    /**
     * sfStation : 深圳北
     * sfTime : 07:24
     * spendTime : 7小时5分
     * stationInfos : [{"arrDate":"20180726","arrTime":"当天07:24","arrTimestamp":{"date":26,"day":4,"hours":7,"minutes":24,"month":6,"nanos":0,"seconds":0,"time":1532561040000,"timezoneOffset":-480,"year":118},"goTime":"07:24","goTimestamp":{"date":26,"day":4,"hours":7,"minutes":24,"month":6,"nanos":0,"seconds":0,"time":1532561040000,"timezoneOffset":-480,"year":118},"late":"","stationName":"深圳北"},{"arrDate":"20180726","arrTime":"当天07:45","arrTimestamp":{"date":26,"day":4,"hours":7,"minutes":45,"month":6,"nanos":0,"seconds":0,"time":1532562300000,"timezoneOffset":-480,"year":118},"goTime":"07:47","goTimestamp":{"date":26,"day":4,"hours":7,"minutes":47,"month":6,"nanos":0,"seconds":0,"time":1532562420000,"timezoneOffset":-480,"year":118},"late":"","stationName":"深圳坪山"},{"arrDate":"20180726","arrTime":"当天08:28","arrTimestamp":{"date":26,"day":4,"hours":8,"minutes":28,"month":6,"nanos":0,"seconds":0,"time":1532564880000,"timezoneOffset":-480,"year":118},"goTime":"08:30","goTimestamp":{"date":26,"day":4,"hours":8,"minutes":30,"month":6,"nanos":0,"seconds":0,"time":1532565000000,"timezoneOffset":-480,"year":118},"late":"","stationName":"汕尾"},{"arrDate":"20180726","arrTime":"当天09:06","arrTimestamp":{"date":26,"day":4,"hours":9,"minutes":6,"month":6,"nanos":0,"seconds":0,"time":1532567160000,"timezoneOffset":-480,"year":118},"goTime":"09:08","goTimestamp":{"date":26,"day":4,"hours":9,"minutes":8,"month":6,"nanos":0,"seconds":0,"time":1532567280000,"timezoneOffset":-480,"year":118},"late":"","stationName":"普宁"},{"arrDate":"20180726","arrTime":"当天09:37","arrTimestamp":{"date":26,"day":4,"hours":9,"minutes":37,"month":6,"nanos":0,"seconds":0,"time":1532569020000,"timezoneOffset":-480,"year":118},"goTime":"09:40","goTimestamp":{"date":26,"day":4,"hours":9,"minutes":40,"month":6,"nanos":0,"seconds":0,"time":1532569200000,"timezoneOffset":-480,"year":118},"late":"","stationName":"潮汕"},{"arrDate":"20180726","arrTime":"当天09:59","arrTimestamp":{"date":26,"day":4,"hours":9,"minutes":59,"month":6,"nanos":0,"seconds":0,"time":1532570340000,"timezoneOffset":-480,"year":118},"goTime":"10:01","goTimestamp":{"date":26,"day":4,"hours":10,"minutes":1,"month":6,"nanos":0,"seconds":0,"time":1532570460000,"timezoneOffset":-480,"year":118},"late":"","stationName":"饶平"},{"arrDate":"20180726","arrTime":"当天10:45","arrTimestamp":{"date":26,"day":4,"hours":10,"minutes":45,"month":6,"nanos":0,"seconds":0,"time":1532573100000,"timezoneOffset":-480,"year":118},"goTime":"10:47","goTimestamp":{"date":26,"day":4,"hours":10,"minutes":47,"month":6,"nanos":0,"seconds":0,"time":1532573220000,"timezoneOffset":-480,"year":118},"late":"","stationName":"漳州"},{"arrDate":"20180726","arrTime":"当天11:07","arrTimestamp":{"date":26,"day":4,"hours":11,"minutes":7,"month":6,"nanos":0,"seconds":0,"time":1532574420000,"timezoneOffset":-480,"year":118},"goTime":"11:11","goTimestamp":{"date":26,"day":4,"hours":11,"minutes":11,"month":6,"nanos":0,"seconds":0,"time":1532574660000,"timezoneOffset":-480,"year":118},"late":"","stationName":"厦门北"},{"arrDate":"20180726","arrTime":"当天11:36","arrTimestamp":{"date":26,"day":4,"hours":11,"minutes":36,"month":6,"nanos":0,"seconds":0,"time":1532576160000,"timezoneOffset":-480,"year":118},"goTime":"11:38","goTimestamp":{"date":26,"day":4,"hours":11,"minutes":38,"month":6,"nanos":0,"seconds":0,"time":1532576280000,"timezoneOffset":-480,"year":118},"late":"","stationName":"泉州"},{"arrDate":"20180726","arrTime":"当天11:56","arrTimestamp":{"date":26,"day":4,"hours":11,"minutes":56,"month":6,"nanos":0,"seconds":0,"time":1532577360000,"timezoneOffset":-480,"year":118},"goTime":"12:04","goTimestamp":{"date":26,"day":4,"hours":12,"minutes":4,"month":6,"nanos":0,"seconds":0,"time":1532577840000,"timezoneOffset":-480,"year":118},"late":"","stationName":"仙游"},{"arrDate":"20180726","arrTime":"当天12:15","arrTimestamp":{"date":26,"day":4,"hours":12,"minutes":15,"month":6,"nanos":0,"seconds":0,"time":1532578500000,"timezoneOffset":-480,"year":118},"goTime":"12:17","goTimestamp":{"date":26,"day":4,"hours":12,"minutes":17,"month":6,"nanos":0,"seconds":0,"time":1532578620000,"timezoneOffset":-480,"year":118},"late":"","stationName":"莆田"},{"arrDate":"20180726","arrTime":"当天12:36","arrTimestamp":{"date":26,"day":4,"hours":12,"minutes":36,"month":6,"nanos":0,"seconds":0,"time":1532579760000,"timezoneOffset":-480,"year":118},"goTime":"12:38","goTimestamp":{"date":26,"day":4,"hours":12,"minutes":38,"month":6,"nanos":0,"seconds":0,"time":1532579880000,"timezoneOffset":-480,"year":118},"late":"","stationName":"福清"},{"arrDate":"20180726","arrTime":"当天12:53","arrTimestamp":{"date":26,"day":4,"hours":12,"minutes":53,"month":6,"nanos":0,"seconds":0,"time":1532580780000,"timezoneOffset":-480,"year":118},"goTime":"12:56","goTimestamp":{"date":26,"day":4,"hours":12,"minutes":56,"month":6,"nanos":0,"seconds":0,"time":1532580960000,"timezoneOffset":-480,"year":118},"late":"","stationName":"福州南"},{"arrDate":"20180726","arrTime":"当天13:13","arrTimestamp":{"date":26,"day":4,"hours":13,"minutes":13,"month":6,"nanos":0,"seconds":0,"time":1532581980000,"timezoneOffset":-480,"year":118},"goTime":"13:16","goTimestamp":{"date":26,"day":4,"hours":13,"minutes":16,"month":6,"nanos":0,"seconds":0,"time":1532582160000,"timezoneOffset":-480,"year":118},"late":"","stationName":"福州"},{"arrDate":"20180726","arrTime":"当天13:55","arrTimestamp":{"date":26,"day":4,"hours":13,"minutes":55,"month":6,"nanos":0,"seconds":0,"time":1532584500000,"timezoneOffset":-480,"year":118},"goTime":"13:57","goTimestamp":{"date":26,"day":4,"hours":13,"minutes":57,"month":6,"nanos":0,"seconds":0,"time":1532584620000,"timezoneOffset":-480,"year":118},"late":"","stationName":"南平北"},{"arrDate":"20180726","arrTime":"当天14:29","arrTimestamp":{"date":26,"day":4,"hours":14,"minutes":29,"month":6,"nanos":0,"seconds":0,"time":1532586540000,"timezoneOffset":-480,"year":118},"goTime":"14:29","goTimestamp":{"date":26,"day":4,"hours":14,"minutes":29,"month":6,"nanos":0,"seconds":0,"time":1532586540000,"timezoneOffset":-480,"year":118},"late":"","stationName":"武夷山东"}]
     * stopFlag : 1
     * trainNo : D2352
     * zdStation : 武夷山东
     * zdTime : 当天14:29
     */

    private String sfStation;
    private String sfTime;
    private String spendTime;
    private int stopFlag;
    private String trainNo;
    private String zdStation;
    private String zdTime;
    private List<StationInfosBean> stationInfos;


    public String getSfStation() {
        return sfStation;
    }

    public void setSfStation(String sfStation) {
        this.sfStation = sfStation;
    }

    public String getSfTime() {
        return sfTime;
    }

    public void setSfTime(String sfTime) {
        this.sfTime = sfTime;
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

    public String getTrainNo() {
        return trainNo;
    }

    public void setTrainNo(String trainNo) {
        this.trainNo = trainNo;
    }

    public String getZdStation() {
        return zdStation;
    }

    public void setZdStation(String zdStation) {
        this.zdStation = zdStation;
    }

    public String getZdTime() {
        return zdTime;
    }

    public void setZdTime(String zdTime) {
        this.zdTime = zdTime;
    }

    public List<StationInfosBean> getStationInfos() {
        return stationInfos;
    }

    public void setStationInfos(List<StationInfosBean> stationInfos) {
        this.stationInfos = stationInfos;
    }


}