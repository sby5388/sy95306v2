package com.by5388.sy95306v2.bean.second;

import android.text.TextUtils;

import com.by5388.sy95306v2.bean.IRemainingTicket;

/**
 * 第三方查询接口
 *
 * @author by5388
 * @date 2018/8/13 16:07
 */
public class SecondRemainTicketData implements IRemainingTicket {
    private final static String EMPTY = "--";
    private final static String EMPTY2 = "无";
    private final static String RESULT_EMPTY = "";

    /**
     * 2018-08-13,
     * 1,
     * D672,
     * 深圳北,
     * 饶平,
     * 16:14,
     * 18:56,
     * 02:42,
     * --,
     * --,
     * 无,
     * 无,
     * ,
     * 无,
     */


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

    public SecondRemainTicketData(String[] param) {
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
        if (EMPTY.equals(rw) || EMPTY2.equals(rw)) {
            return RESULT_EMPTY;
        }
        return rw;
    }

    public void setRw(String rw) {
        this.rw = rw;
    }

    @Override
    public String getYw() {
        if (EMPTY.equals(yw) || EMPTY2.equals(yw)) {
            return RESULT_EMPTY;
        }
        return yw;
    }

    public void setYw(String yw) {
        this.yw = yw;
    }

    @Override
    public String getRz() {
        if (EMPTY.equals(rz) || EMPTY2.equals(rz)) {
            return RESULT_EMPTY;
        }
        return rz;
    }

    public void setRz(String rz) {
        this.rz = rz;
    }

    @Override
    public String getYz() {
        if (EMPTY.equals(yz) || EMPTY2.equals(yz)) {
            return RESULT_EMPTY;
        }
        return yz;
    }

    public void setYz(String yz) {
        this.yz = yz;
    }

    @Override
    public String getQt() {
        //TODO
        if (TextUtils.isEmpty(qt)) {
            return RESULT_EMPTY;
        }
        StringBuilder stringBuilder = new StringBuilder();
        String[] items = qt.split(" ");
        for (String item : items) {
            if (!item.contains(EMPTY2)) {
                if (!TextUtils.isEmpty(stringBuilder)) {
                    stringBuilder.append(";");
                }
                stringBuilder.append(item);
            }
        }

        return stringBuilder.toString();
    }

    public void setQt(String qt) {
        this.qt = qt;
    }

    @Override
    public String getWz() {
        if (EMPTY.equals(wz) || EMPTY2.equals(wz)) {
            return RESULT_EMPTY;
        }
        return wz;
    }

    public void setWz(String wz) {
        this.wz = wz;
    }
}
