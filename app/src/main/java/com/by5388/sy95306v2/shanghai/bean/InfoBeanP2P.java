package com.by5388.sy95306v2.shanghai.bean;

/**
 * @author by5388
 * @date 2018/8/8 16:35
 */
public class InfoBeanP2P {


    /**
     * type : 2
     * trainDate : 2018-08-08
     * fromStation : 上海
     * toStation : 徐州
     */
    public InfoBeanP2P(String fromStation, String toStation, String trainDate) {
        this.trainDate = trainDate;
        this.fromStation = fromStation;
        this.toStation = toStation;
        this.type = "2";
    }

    private String type;
    private String trainDate;
    private String fromStation;
    private String toStation;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTrainDate() {
        return trainDate;
    }

    public void setTrainDate(String trainDate) {
        this.trainDate = trainDate;
    }

    public String getFromStation() {
        return fromStation;
    }

    public void setFromStation(String fromStation) {
        this.fromStation = fromStation;
    }

    public String getToStation() {
        return toStation;
    }

    public void setToStation(String toStation) {
        this.toStation = toStation;
    }
}

