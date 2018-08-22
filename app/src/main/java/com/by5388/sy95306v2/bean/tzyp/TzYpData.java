package com.by5388.sy95306v2.bean.tzyp;

import com.by5388.sy95306v2.bean.IRemainingTicket;

/**
 * @author by5388
 * @date 2018/8/13 16:07
 */
public class TzYpData implements IRemainingTicket {


    //2018-08-13,1,D672,深圳北,饶平,16:14,18:56,02:42,--,--,无,无,,无,;
    private String date;
    private String order;
    private String code;
    private String fromStation;
    private String toStation;
    private String startTime;
    private String endTime;
    private String speedTime;
    /**
     * 软卧
     */
    private String rw;
    /**
     * 硬卧
     */
    private String yw;
    /**
     * 一等座/软座
     */
    private String rz;
    /**
     * 二等座/硬座
     */
    private String yz;
    /**
     * 其他：某些车辆上提供的特殊位席，如商务座、特等座、
     */
    private String qt;
    /**
     * 无座票，站票
     */
    private String wz;

    //2018-08-13,1,D672,深圳北,饶平,16:14,18:56,02:42,--,--,无,无,,无,;

    public TzYpData(String[] param) {
        this.date = param[0];
        this.order = param[1];
        this.code = param[2];
        this.fromStation = param[3];
        this.toStation = param[4];
        this.startTime = param[5];
        this.endTime = param[6];
        this.speedTime = param[7];
        this.rw = param[8];
        this.yw = param[9];
        this.rz = param[10];
        this.yz = param[11];
        this.qt = param[12];
        this.wz = param[13];
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getOrder() {
        return order;
    }

    public void setOrder(String order) {
        this.order = order;
    }

    @Override
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Override
    public String getFromStation() {
        return fromStation;
    }

    public void setFromStation(String fromStation) {
        this.fromStation = fromStation;
    }

    @Override
    public String getToStation() {
        return toStation;
    }

    public void setToStation(String toStation) {
        this.toStation = toStation;
    }

    @Override
    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    @Override
    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    @Override
    public String getSpeedTime() {
        return speedTime;
    }

    public void setSpeedTime(String speedTime) {
        this.speedTime = speedTime;
    }

    @Override
    public String getRw() {
        return rw;
    }

    public void setRw(String rw) {
        this.rw = rw;
    }

    @Override
    public String getYw() {
        return yw;
    }

    public void setYw(String yw) {
        this.yw = yw;
    }

    @Override
    public String getRz() {
        return rz;
    }

    public void setRz(String rz) {
        this.rz = rz;
    }

    @Override
    public String getYz() {
        return yz;
    }

    public void setYz(String yz) {
        this.yz = yz;
    }

    @Override
    public String getQt() {
        return qt;
    }

    public void setQt(String qt) {
        this.qt = qt;
    }

    @Override
    public String getWz() {
        return wz;
    }

    public void setWz(String wz) {
        this.wz = wz;
    }
}
