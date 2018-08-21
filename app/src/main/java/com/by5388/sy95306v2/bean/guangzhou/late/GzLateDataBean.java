package com.by5388.sy95306v2.bean.guangzhou.late;

import java.util.List;

/**
 * @author by5388  on 2018/8/20.
 */
public class GzLateDataBean {
    /**
     * sfDate : 08-20
     * sfStation : 饶平
     * stationInfos : [{"bureauCode":"Q","exit":"","late":"","sjTime":"08-20 14:35","station":"饶平","tdTime":"08-20 14:35"},{"bureauCode":"Q","exit":"","late":"晚点2分","sjTime":"08-20 14:54","station":"潮汕","tdTime":"08-20 14:52"},{"bureauCode":"Q","exit":"","late":"晚点1分","sjTime":"08-20 16:16","station":"惠东","tdTime":"08-20 16:15"},{"bureauCode":"Q","exit":"","late":"晚点2分","sjTime":"08-20 16:34","station":"惠州南","tdTime":"08-20 16:32"},{"bureauCode":"Q","exit":"","late":"提前5分","sjTime":"08-20 17:02","station":"深圳北","tdTime":"08-20 17:07"}]
     * trainNo : D7415
     * zdStation : 深圳北
     */

    private String sfDate;
    private String sfStation;
    private String trainNo;
    private String zdStation;
    private List<GzLateStationInfoBean> stationInfos;

    public String getSfDate() {
        return sfDate;
    }

    public void setSfDate(String sfDate) {
        this.sfDate = sfDate;
    }

    public String getSfStation() {
        return sfStation;
    }

    public void setSfStation(String sfStation) {
        this.sfStation = sfStation;
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

    public List<GzLateStationInfoBean> getStationInfos() {
        return stationInfos;
    }

    public void setStationInfos(List<GzLateStationInfoBean> stationInfos) {
        this.stationInfos = stationInfos;
    }

}