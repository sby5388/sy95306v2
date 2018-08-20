package com.by5388.sy95306v2.bean.tzyp;

/**
 * 余票查询请求参数
 *
 * @author by5388  on 2018/8/13.
 */
public class QueryParam {
    private String fromStation;
    private String toStation;
    private String code;
    private String date;

    public QueryParam(String fromStation, String toStation, String code, String date) {
        this.fromStation = fromStation;
        this.toStation = toStation;
        this.code = code;
        this.date = date;
    }

    public QueryParam(String fromStation, String toStation, String date) {
        this.fromStation = fromStation;
        this.toStation = toStation;
        this.date = date;
        this.code = "";
    }


    @Override
    public String toString() {
        return fromStation + "-" + toStation + "-" + code;
    }

    public String getFromStation() {
        return fromStation;
    }

    public void setFromStation(String fromStation) {
        this.fromStation = fromStation;
    }

    public String getToStation() {
        return toStation;
    }

    public void setToStation(String toStation) {
        this.toStation = toStation;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
