package com.by5388.sy95306v2.bean.gz.number;

import com.by5388.sy95306v2.bean.gz.station.TrainsBean;

import java.util.List;

/**
 * 车次 查询 返回的数据结构
 *
 * @author by5388  on 2018/8/1.
 */

class DataBeanTrainNumber {

    /**
     * data : [{"sfStation":"泉州","sfTime":"07:13","spendTime":"4小时10分","stationInfos":[{"arrDate":"20180726","arrTime":"当天07:13","arrTimestamp":{"date":26,"day":4,"hours":7,"minutes":13,"month":6,"nanos":0,"seconds":0,"time":1532560380000,"timezoneOffset":-480,"year":118},"goTime":"07:13","goTimestamp":{"date":26,"day":4,"hours":7,"minutes":13,"month":6,"nanos":0,"seconds":0,"time":1532560380000,"timezoneOffset":-480,"year":118},"late":"","stationName":"泉州"},{"arrDate":"20180726","arrTime":"当天07:38","arrTimestamp":{"date":26,"day":4,"hours":7,"minutes":38,"month":6,"nanos":0,"seconds":0,"time":1532561880000,"timezoneOffset":-480,"year":118},"goTime":"07:42","goTimestamp":{"date":26,"day":4,"hours":7,"minutes":42,"month":6,"nanos":0,"seconds":0,"time":1532562120000,"timezoneOffset":-480,"year":118},"late":"","stationName":"厦门北"},{"arrDate":"20180726","arrTime":"当天08:01","arrTimestamp":{"date":26,"day":4,"hours":8,"minutes":1,"month":6,"nanos":0,"seconds":0,"time":1532563260000,"timezoneOffset":-480,"year":118},"goTime":"08:03","goTimestamp":{"date":26,"day":4,"hours":8,"minutes":3,"month":6,"nanos":0,"seconds":0,"time":1532563380000,"timezoneOffset":-480,"year":118},"late":"","stationName":"漳州"},{"arrDate":"20180726","arrTime":"当天08:47","arrTimestamp":{"date":26,"day":4,"hours":8,"minutes":47,"month":6,"nanos":0,"seconds":0,"time":1532566020000,"timezoneOffset":-480,"year":118},"goTime":"08:49","goTimestamp":{"date":26,"day":4,"hours":8,"minutes":49,"month":6,"nanos":0,"seconds":0,"time":1532566140000,"timezoneOffset":-480,"year":118},"late":"","stationName":"饶平"},{"arrDate":"20180726","arrTime":"当天09:06","arrTimestamp":{"date":26,"day":4,"hours":9,"minutes":6,"month":6,"nanos":0,"seconds":0,"time":1532567160000,"timezoneOffset":-480,"year":118},"goTime":"09:08","goTimestamp":{"date":26,"day":4,"hours":9,"minutes":8,"month":6,"nanos":0,"seconds":0,"time":1532567280000,"timezoneOffset":-480,"year":118},"late":"","stationName":"潮汕"},{"arrDate":"20180726","arrTime":"当天09:33","arrTimestamp":{"date":26,"day":4,"hours":9,"minutes":33,"month":6,"nanos":0,"seconds":0,"time":1532568780000,"timezoneOffset":-480,"year":118},"goTime":"09:35","goTimestamp":{"date":26,"day":4,"hours":9,"minutes":35,"month":6,"nanos":0,"seconds":0,"time":1532568900000,"timezoneOffset":-480,"year":118},"late":"","stationName":"普宁"},{"arrDate":"20180726","arrTime":"当天10:21","arrTimestamp":{"date":26,"day":4,"hours":10,"minutes":21,"month":6,"nanos":0,"seconds":0,"time":1532571660000,"timezoneOffset":-480,"year":118},"goTime":"10:23","goTimestamp":{"date":26,"day":4,"hours":10,"minutes":23,"month":6,"nanos":0,"seconds":0,"time":1532571780000,"timezoneOffset":-480,"year":118},"late":"","stationName":"鲘门"},{"arrDate":"20180726","arrTime":"当天10:38","arrTimestamp":{"date":26,"day":4,"hours":10,"minutes":38,"month":6,"nanos":0,"seconds":0,"time":1532572680000,"timezoneOffset":-480,"year":118},"goTime":"10:40","goTimestamp":{"date":26,"day":4,"hours":10,"minutes":40,"month":6,"nanos":0,"seconds":0,"time":1532572800000,"timezoneOffset":-480,"year":118},"late":"","stationName":"惠东"},{"arrDate":"20180726","arrTime":"当天11:23","arrTimestamp":{"date":26,"day":4,"hours":11,"minutes":23,"month":6,"nanos":0,"seconds":0,"time":1532575380000,"timezoneOffset":-480,"year":118},"goTime":"11:23","goTimestamp":{"date":26,"day":4,"hours":11,"minutes":23,"month":6,"nanos":0,"seconds":0,"time":1532575380000,"timezoneOffset":-480,"year":118},"late":"","stationName":"深圳北"}],"stopFlag":1,"trainNo":"D2315","zdStation":"深圳北","zdTime":"当天11:23"}]
     * error :
     * success : true
     */


    private String error;
    private boolean success;
    private List<TrainsBean> data;

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public List<TrainsBean> getData() {
        return data;
    }

    public void setData(List<TrainsBean> data) {
        this.data = data;
    }

}
