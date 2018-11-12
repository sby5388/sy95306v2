package com.by5388.sy95306v2.bean.cd.late;

/**
 * 通过车次查询返回的数据：正晚点
 *
 * @author by5388
 * @date 2018/8/12 17:06
 */
public class CdLateStation {
    private final static String EMPTY = "";

    //["6c000D186801","02","佛山西","FOQ","1209","1211","0","33"]

    /**
     * 车次
     */
    private String trainCode;
    /**
     * 停靠顺序
     */
    private String serialNumber;
    /**
     * 车站名称
     */
    private String stationName;
    /**
     * 车站编码
     */
    private String stationCode;

    /**
     * 到达时间
     */
    private String arriveTime;
    /**
     * 出发时间
     */
    private String leaveTime;
    /**
     * 与始发站的日期差，0：同一天，1：次日，以此类推
     */
    private String dateDistance;
    /**
     * 与始发站之间的里程：公里
     */
    private String mileage;

    public String getTrainCode() {
        return trainCode;
    }

    public void setTrainCode(String trainCode) {
        this.trainCode = trainCode;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public String getStationName() {
        return stationName;
    }

    public void setStationName(String stationName) {
        this.stationName = stationName;
    }

    public String getStationCode() {
        return stationCode;
    }

    public void setStationCode(String stationCode) {
        this.stationCode = stationCode;
    }

    public String getArriveTime() {
        return arriveTime;
    }

    public void setArriveTime(String arriveTime) {
        this.arriveTime = arriveTime;
    }

    public String getLeaveTime() {
        return leaveTime;
    }

    public void setLeaveTime(String leaveTime) {
        this.leaveTime = leaveTime;
    }

    public String getDateDistance() {
        return dateDistance;
    }

    public void setDateDistance(String dateDistance) {
        this.dateDistance = dateDistance;
    }

    public String getMileage() {
        return mileage;
    }

    public void setMileage(String mileage) {
        this.mileage = mileage;
    }

    public static CdLateStation getStation(String[] params) {
        final int length = 8;
        if (null == params || params.length != length) {
            return new CdLateStation();
        }
        return new CdLateStation(params);
    }


    private CdLateStation(String[] params) {
        this.trainCode = params[0];
        this.serialNumber = params[1];
        this.stationName = params[2];
        this.stationCode = params[3];
        this.arriveTime = params[4];
        this.leaveTime = params[5];
        this.dateDistance = params[6];
        this.mileage = params[7];
    }


    private CdLateStation() {
        this.trainCode = EMPTY;
        this.serialNumber = EMPTY;
        this.stationName = EMPTY;
        this.stationCode = EMPTY;
        this.arriveTime = EMPTY;
        this.leaveTime = EMPTY;
        this.dateDistance = EMPTY;
        this.mileage = EMPTY;
    }

    @Override
    public String toString() {
        return "CdLateStation{" +
                "trainCode='" + trainCode + '\'' +
                ", serialNumber='" + serialNumber + '\'' +
                ", stationName='" + stationName + '\'' +
                ", stationCode='" + stationCode + '\'' +
                ", arriveTime='" + arriveTime + '\'' +
                ", leaveTime='" + leaveTime + '\'' +
                ", dateDistance='" + dateDistance + '\'' +
                ", mileage='" + mileage + '\'' +
                '}';
    }
}
