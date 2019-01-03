package com.by5388.sy95306v2.shanghai.bean;

/**
 * 某车次在某个站的延迟（到达）时间
 *
 * @author by5388
 * @date 2018/8/8 18:57
 */
public class ShanghaiTrainDelay {

    /**
     * arrive : 19:51
     * expectArriveTime : 19:43
     * message : 预计延迟8分钟
     * order : 1
     * station : 上海虹桥
     */

    private String arrive;
    private String expectArriveTime;
    private String message;
    private String order;
    private String station;

    public String getArrive() {
        return arrive;
    }

    public void setArrive(String arrive) {
        this.arrive = arrive;
    }

    public String getExpectArriveTime() {
        return expectArriveTime;
    }

    public void setExpectArriveTime(String expectArriveTime) {
        this.expectArriveTime = expectArriveTime;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getOrder() {
        return order;
    }

    public void setOrder(String order) {
        this.order = order;
    }

    public String getStation() {
        return station;
    }

    public void setStation(String station) {
        this.station = station;
    }
}
