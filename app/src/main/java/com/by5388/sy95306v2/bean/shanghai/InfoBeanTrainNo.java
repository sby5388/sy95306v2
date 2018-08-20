package com.by5388.sy95306v2.bean.shanghai;

/**
 * @author by5388
 * @date 2018/8/8 16:36
 */
public class InfoBeanTrainNo {

    public InfoBeanTrainNo(String trainCode, String trainDate) {
        this.trainCode = trainCode;
        this.trainDate = trainDate;
        this.type = "1";
    }

    /**
     * type : 1
     * trainCode : G2
     * trainDate : 2018-08-08
     */


    private String type;
    private String trainCode;
    private String trainDate;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTrainCode() {
        return trainCode;
    }

    public void setTrainCode(String trainCode) {
        this.trainCode = trainCode;
    }

    public String getTrainDate() {
        return trainDate;
    }

    public void setTrainDate(String trainDate) {
        this.trainDate = trainDate;
    }
}
