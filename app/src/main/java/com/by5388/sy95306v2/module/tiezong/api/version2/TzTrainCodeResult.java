package com.by5388.sy95306v2.module.tiezong.api.version2;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * @author Administrator  on 2019/6/14.
 */
public class TzTrainCodeResult {


    /**
     * validateMessagesShowId : _validatorMessage
     * status : true
     * mHttpStatus : 200
     * data : {"data":[{"mArriveDayStr":"当日到达","mStationName":"饶平","mTrainClassName":"动车","mIsStart":"Y","service_type":"1","end_station_name":"广州东","mArriveTime":"----","mStartStationName":"饶平","mStationTrainCode":"D7515","mArriveDayDiff":"0","mStartTime":"13:59","mStationNo":"01","wz_num":"--","mRunningTime":"00:00"},{"mArriveDayStr":"当日到达","mArriveTime":"14:16","mStationTrainCode":"D7515","mStationName":"潮汕","mArriveDayDiff":"0","OT":[],"mStartTime":"14:19","wz_num":"--","mStationNo":"02","mRunningTime":"00:17"},{"mArriveDayStr":"当日到达","mArriveTime":"14:31","mStationTrainCode":"D7515","mStationName":"潮阳","mArriveDayDiff":"0","OT":[],"mStartTime":"14:33","wz_num":"--","mStationNo":"03","mRunningTime":"00:32"},{"mArriveDayStr":"当日到达","mArriveTime":"14:46","mStationTrainCode":"D7515","mStationName":"普宁","mArriveDayDiff":"0","OT":[],"mStartTime":"14:48","wz_num":"--","mStationNo":"04","mRunningTime":"00:47"},{"mArriveDayStr":"当日到达","mArriveTime":"15:23","mStationTrainCode":"D7515","mStationName":"汕尾","mArriveDayDiff":"0","OT":[],"mStartTime":"15:25","wz_num":"--","mStationNo":"05","mRunningTime":"01:24"},{"mArriveDayStr":"当日到达","mArriveTime":"16:07","mStationTrainCode":"D7515","mStationName":"深圳坪山","mArriveDayDiff":"0","OT":[],"mStartTime":"16:09","wz_num":"--","mStationNo":"06","mRunningTime":"02:08"},{"mArriveDayStr":"当日到达","mArriveTime":"17:02","mStationTrainCode":"D7514","mStationName":"东莞","mArriveDayDiff":"0","OT":[],"mStartTime":"17:04","wz_num":"--","mStationNo":"07","mRunningTime":"03:03"},{"mArriveDayStr":"当日到达","mArriveTime":"17:39","mStationTrainCode":"D7514","mStationName":"广州东","mArriveDayDiff":"0","OT":[],"mStartTime":"17:39","wz_num":"--","mStationNo":"08","mRunningTime":"03:40"}]}
     * messages : []
     * validateMessages : {}
     */

    public boolean status;
    @SerializedName("httpstatus")
    public int mHttpStatus;
    public DataBeanX data;

    public static class DataBeanX {
        public List<DataBean> data;

        public static class DataBean {
            /**
             * mArriveDayStr : 当日到达
             * mStationName : 饶平
             * mTrainClassName : 动车
             * mIsStart : Y
             * service_type : 1
             * end_station_name : 广州东
             * mArriveTime : ----
             * mStartStationName : 饶平
             * mStationTrainCode : D7515
             * mArriveDayDiff : 0
             * mStartTime : 13:59
             * mStationNo : 01
             * wz_num : --
             * mRunningTime : 00:00
             * OT : []
             */

            @SerializedName("arrive_day_str")
            public String mArriveDayStr;
            @SerializedName("station_name")
            public String mStationName;
            @SerializedName("train_class_name")
            public String mTrainClassName;
            @SerializedName("is_start")
            public String mIsStart;
            @SerializedName("end_station_name")
            public String mEndStationName;
            @SerializedName("arrive_time")
            public String mArriveTime;
            @SerializedName("start_station_name")
            public String mStartStationName;
            @SerializedName("station_train_code")
            public String mStationTrainCode;
            @SerializedName("arrive_day_diff")
            public String mArriveDayDiff;
            @SerializedName("start_time")
            public String mStartTime;
            @SerializedName("station_no")
            public String mStationNo;
            @SerializedName("running_time")
            public String mRunningTime;
        }
    }
}
