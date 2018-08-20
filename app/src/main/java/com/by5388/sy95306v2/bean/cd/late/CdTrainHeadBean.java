package com.by5388.sy95306v2.bean.cd.late;

import java.util.List;

/**
 * @author by5388
 * @date 2018/8/16 19:13
 */
@SuppressWarnings("all")
public class CdTrainHeadBean {
    /**
     * endStn : 成都东
     * endDate : 2018-08-16 17:21
     * trainCode : ["D1849","D1852"]
     * setType :
     * zlc : 1488
     * zsj : 593
     * startStn : 广州
     * train_no : 63000D184900
     * startTime : 20180816
     * endTime : 20180816
     * startDate : 2018-08-16 07:28
     * train : D1849
     */

    private String endStn;
    private String endDate;
    private String setType;
    private String zlc;
    private int zsj;
    private String startStn;
    private String train_no;
    private String startTime;
    private String endTime;
    private String startDate;
    private String train;
    private List<String> trainCode;

    public String getEndStn() {
        return endStn;
    }

    public void setEndStn(String endStn) {
        this.endStn = endStn;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getSetType() {
        return setType;
    }

    public void setSetType(String setType) {
        this.setType = setType;
    }

    public String getZlc() {
        return zlc;
    }

    public void setZlc(String zlc) {
        this.zlc = zlc;
    }

    public int getZsj() {
        return zsj;
    }

    public void setZsj(int zsj) {
        this.zsj = zsj;
    }

    public String getStartStn() {
        return startStn;
    }

    public void setStartStn(String startStn) {
        this.startStn = startStn;
    }

    public String getTrain_no() {
        return train_no;
    }

    public void setTrain_no(String train_no) {
        this.train_no = train_no;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getTrain() {
        return train;
    }

    public void setTrain(String train) {
        this.train = train;
    }

    public List<String> getTrainCode() {
        return trainCode;
    }

    public void setTrainCode(List<String> trainCode) {
        this.trainCode = trainCode;
    }
}