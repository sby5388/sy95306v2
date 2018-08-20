package com.by5388.sy95306v2.bean.cd.yupiao;

import com.by5388.sy95306v2.bean.IYp;

/**
 * @author by5388
 * @date 2018/8/13 11:19
 */
@SuppressWarnings("all")
public class CdYpDetailBean implements IYp {
    private final static String EMPTY = "0";
    private final static String RESULT_EMPTY = "-";
    /**
     * station_train_code : G2926
     * start_station_name : 深圳北
     * to_station_name : 贵阳北
     * lishi : 04:58
     * yz_num : 0
     * yw_num : 0
     * rz_num : 0
     * rw_num : 0
     * wz_num : 0
     * start_time : 07:58
     * arrive_time : 12:56
     * zy_num : 4
     * ze_num : 0
     * fzx : 01
     */

    private String station_train_code;
    private String start_station_name;
    private String to_station_name;
    private String lishi;
    private String yz_num;
    private String yw_num;
    private String rz_num;
    private String rw_num;
    private String wz_num;
    private String start_time;
    private String arrive_time;
    private String zy_num;
    private String ze_num;
    private String fzx;

    public String getStation_train_code() {
        return station_train_code;
    }

    public void setStation_train_code(String station_train_code) {
        this.station_train_code = station_train_code;
    }

    public String getStart_station_name() {
        return start_station_name;
    }

    public void setStart_station_name(String start_station_name) {
        this.start_station_name = start_station_name;
    }

    public String getTo_station_name() {
        return to_station_name;
    }

    public void setTo_station_name(String to_station_name) {
        this.to_station_name = to_station_name;
    }

    public String getLishi() {
        return lishi;
    }

    public void setLishi(String lishi) {
        this.lishi = lishi;
    }

    public String getYz_num() {
        return yz_num;
    }

    public void setYz_num(String yz_num) {
        this.yz_num = yz_num;
    }

    public String getYw_num() {
        return yw_num;
    }

    public void setYw_num(String yw_num) {
        this.yw_num = yw_num;
    }

    public String getRz_num() {
        return rz_num;
    }

    public void setRz_num(String rz_num) {
        this.rz_num = rz_num;
    }

    public String getRw_num() {
        return rw_num;
    }

    public void setRw_num(String rw_num) {
        this.rw_num = rw_num;
    }

    public String getWz_num() {
        return wz_num;
    }

    public void setWz_num(String wz_num) {
        this.wz_num = wz_num;
    }

    public String getStart_time() {
        return start_time;
    }

    public void setStart_time(String start_time) {
        this.start_time = start_time;
    }

    public String getArrive_time() {
        return arrive_time;
    }

    public void setArrive_time(String arrive_time) {
        this.arrive_time = arrive_time;
    }

    public String getZy_num() {
        return zy_num;
    }

    public void setZy_num(String zy_num) {
        this.zy_num = zy_num;
    }

    public String getZe_num() {
        return ze_num;
    }

    public void setZe_num(String ze_num) {
        this.ze_num = ze_num;
    }

    public String getFzx() {
        return fzx;
    }

    public void setFzx(String fzx) {
        this.fzx = fzx;
    }

    @Override
    public String toString() {
        return
                station_train_code
                        + " : " + start_station_name
                        + " : " + to_station_name
                        + " : " + start_time
                        + " : " + arrive_time
                        + " : " + lishi
                        //+ " : " + fzx
                        + " : " + zy_num
                        + " : " + ze_num
                        + " : " + yz_num
                        + " : " + yw_num
                        + " : " + rz_num
                        + " : " + rw_num
                        + " : " + wz_num
                ;
    }

    @Override
    public String getCode() {
        return station_train_code;
    }

    @Override
    public String getFromStation() {
        return start_station_name;
    }

    @Override
    public String getToStation() {
        return to_station_name;
    }

    @Override
    public String getStartTime() {
        return start_time;
    }

    @Override
    public String getEndTime() {
        return arrive_time;
    }

    @Override
    public String getSpeedTime() {
        return lishi;
    }

    @Override
    public String getRw() {
        if (EMPTY.equals(rw_num)) {
            return RESULT_EMPTY;
        }
        return rw_num;
    }

    @Override
    public String getYw() {
        if (EMPTY.equals(yw_num)) {
            return RESULT_EMPTY;
        }
        return yw_num;
    }

    @Override
    public String getRz() {
        if (EMPTY.equals(rz_num)) {
            if (!EMPTY.equals(zy_num)) {
                return zy_num;
            }
        }

        if (EMPTY.equals(zy_num)) {
            if (!EMPTY.equals(rz_num)) {
                return rz_num;
            }
        }

        return RESULT_EMPTY;
    }

    @Override
    public String getYz() {
        if (EMPTY.equals(yz_num)) {
            if (!EMPTY.equals(ze_num)) {
                return ze_num;
            }
        }
        if (EMPTY.equals(ze_num)) {
            if (!EMPTY.equals(yz_num)) {
                return yz_num;
            }
        }
        return RESULT_EMPTY;
    }

    @Override
    public String getQt() {
        return "";
    }

    @Override
    public String getWz() {
        if (EMPTY.equals(wz_num)) {
            return RESULT_EMPTY;
        }
        return wz_num;
    }
}
