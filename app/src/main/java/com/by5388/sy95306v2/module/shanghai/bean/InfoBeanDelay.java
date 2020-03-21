package com.by5388.sy95306v2.module.shanghai.bean;

/**
 * @author by5388
 * @date 2018/8/8 18:47
 */
public class InfoBeanDelay {
    /**
     * stationName : 上海虹桥
     * trainCode : G104
     */
    private String stationName;
    private String trainCode;

    public InfoBeanDelay(String stationName, String trainCode) {
        this.stationName = stationName;
        this.trainCode = trainCode;
    }

    public String getStationName() {
        return stationName;
    }

    public void setStationName(String stationName) {
        this.stationName = stationName;
    }

    public String getTrainCode() {
        return trainCode;
    }

    public void setTrainCode(String trainCode) {
        this.trainCode = trainCode;
    }

}
