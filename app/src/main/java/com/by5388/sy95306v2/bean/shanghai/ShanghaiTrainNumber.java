package com.by5388.sy95306v2.bean.shanghai;

/**
 * 车次查询后的返回结果item
 *
 * @author by5388
 * @date 2018/8/8 16:24
 */
public class ShanghaiTrainNumber {

    /**
     * stationName : 深圳坪山
     * stayTime : 2
     * stationNum : 2
     * trainCode : D2286
     * endTime : 10:15
     * startTime : 10:17
     */

    private String stationName;
    private String stayTime;
    private String stationNum;
    private String trainCode;
    private String endTime;
    private String startTime;

    public String getStationName() {
        return stationName;
    }

    public void setStationName(String stationName) {
        this.stationName = stationName;
    }

    public String getStayTime() {
        return stayTime;
    }

    public void setStayTime(String stayTime) {
        this.stayTime = stayTime;
    }

    public String getStationNum() {
        return stationNum;
    }

    public void setStationNum(String stationNum) {
        this.stationNum = stationNum;
    }

    public String getTrainCode() {
        return trainCode;
    }

    public void setTrainCode(String trainCode) {
        this.trainCode = trainCode;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    @Override
    public String toString() {
        return "ShanghaiTrainNumber{" +
                "stationName='" + stationName + '\'' +
                ", stayTime='" + stayTime + '\'' +
                ", stationNum='" + stationNum + '\'' +
                ", trainCode='" + trainCode + '\'' +
                ", endTime='" + endTime + '\'' +
                ", startTime='" + startTime + '\'' +
                '}';
    }
}
