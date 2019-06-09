package com.by5388.t12306.api.train.number;

import java.util.List;

/**
 * @author by5388  on 2019/5/4.
 */
public class TrainNumber {

    @Override
    public String toString() {
        return "TrainNumber{" +
                "" + station_train_code + '\'' +
                ", '" + start_station_name + '\'' +
                ", '" + end_station_name + '\'' +
                '}';
    }

    /**
     * arrive_day_str : 当日到达
     * station_name : 饶平
     * train_class_name : 动车
     * is_start : Y
     * service_type : 1
     * end_station_name : 广州东
     * arrive_time : ----
     * start_station_name : 饶平
     * station_train_code : D7515
     * arrive_day_diff : 0
     * start_time : 13:59
     * station_no : 01
     * wz_num : --
     * running_time : 00:00
     * OT : []
     */

    private String arrive_day_str;
    private String station_name;
    private String train_class_name;
    private String is_start;
    private String service_type;
    private String end_station_name;
    private String arrive_time;
    private String start_station_name;
    private String station_train_code;
    private String arrive_day_diff;
    private String start_time;
    private String station_no;
    private String wz_num;
    private String running_time;
    private List<?> OT;

    public String getArrive_day_str() {
        return arrive_day_str;
    }

    public void setArrive_day_str(String arrive_day_str) {
        this.arrive_day_str = arrive_day_str;
    }

    public String getStation_name() {
        return station_name;
    }

    public void setStation_name(String station_name) {
        this.station_name = station_name;
    }

    public String getTrain_class_name() {
        return train_class_name;
    }

    public void setTrain_class_name(String train_class_name) {
        this.train_class_name = train_class_name;
    }

    public String getIs_start() {
        return is_start;
    }

    public void setIs_start(String is_start) {
        this.is_start = is_start;
    }

    public String getService_type() {
        return service_type;
    }

    public void setService_type(String service_type) {
        this.service_type = service_type;
    }

    public String getEnd_station_name() {
        return end_station_name;
    }

    public void setEnd_station_name(String end_station_name) {
        this.end_station_name = end_station_name;
    }

    public String getArrive_time() {
        return arrive_time;
    }

    public void setArrive_time(String arrive_time) {
        this.arrive_time = arrive_time;
    }

    public String getStart_station_name() {
        return start_station_name;
    }

    public void setStart_station_name(String start_station_name) {
        this.start_station_name = start_station_name;
    }

    public String getStation_train_code() {
        return station_train_code;
    }

    public void setStation_train_code(String station_train_code) {
        this.station_train_code = station_train_code;
    }

    public String getArrive_day_diff() {
        return arrive_day_diff;
    }

    public void setArrive_day_diff(String arrive_day_diff) {
        this.arrive_day_diff = arrive_day_diff;
    }

    public String getStart_time() {
        return start_time;
    }

    public void setStart_time(String start_time) {
        this.start_time = start_time;
    }

    public String getStation_no() {
        return station_no;
    }

    public void setStation_no(String station_no) {
        this.station_no = station_no;
    }

    public String getWz_num() {
        return wz_num;
    }

    public void setWz_num(String wz_num) {
        this.wz_num = wz_num;
    }

    public String getRunning_time() {
        return running_time;
    }

    public void setRunning_time(String running_time) {
        this.running_time = running_time;
    }

    public List<?> getOT() {
        return OT;
    }

    public void setOT(List<?> OT) {
        this.OT = OT;
    }
}