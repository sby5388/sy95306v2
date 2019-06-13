package com.by5388.sy95306v2.module.tiezong.bean.temp2;

import com.google.gson.annotations.SerializedName;

/**
 * @author by5388  on 2019/1/22.
 */
public class DataSimpleBean {

    /**
     * date : 20190123
     * fromStation : 南京
     * trainCodeSimple : D2281
     * toStation : 深圳北
     * totalStationNumber : 33
     * trainCodeFull : 54000D228150
     */

    private String date;
    @SerializedName("from_station")
    private String fromStation;
    @SerializedName("station_train_code")
    private String trainCodeSimple;
    @SerializedName("to_station")
    private String toStation;
    @SerializedName("total_num")
    private String totalStationNumber;
    @SerializedName("train_no")
    private String trainCodeFull;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getFromStation() {
        return fromStation;
    }

    public void setFromStation(String fromStation) {
        this.fromStation = fromStation;
    }

    public String getTrainCodeSimple() {
        return trainCodeSimple;
    }

    public void setTrainCodeSimple(String trainCodeSimple) {
        this.trainCodeSimple = trainCodeSimple;
    }

    public String getToStation() {
        return toStation;
    }

    public void setToStation(String toStation) {
        this.toStation = toStation;
    }

    public String getTotalStationNumber() {
        return totalStationNumber;
    }

    public void setTotalStationNumber(String totalStationNumber) {
        this.totalStationNumber = totalStationNumber;
    }

    public String getTrainCodeFull() {
        return trainCodeFull;
    }

    public void setTrainCodeFull(String trainCodeFull) {
        this.trainCodeFull = trainCodeFull;
    }
}
