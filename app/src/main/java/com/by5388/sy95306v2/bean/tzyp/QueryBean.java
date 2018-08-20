package com.by5388.sy95306v2.bean.tzyp;

/**
 * @author by5388
 * @date 2018/8/13 18:54
 */
public class QueryBean {
    /**
     * des : 贵阳
     * source :
     * time : 2018-08-13 18:33:08
     * ori : 深圳
     * date : 2018-08-14
     * jsoncallback :
     * tc :
     */

    private String des;
    private String source;
    private String time;
    private String ori;
    private String date;
    private String jsoncallback;
    private String tc;

    public String getDes() {
        return des;
    }

    public void setDes(String des) {
        this.des = des;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getOri() {
        return ori;
    }

    public void setOri(String ori) {
        this.ori = ori;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getJsoncallback() {
        return jsoncallback;
    }

    public void setJsoncallback(String jsoncallback) {
        this.jsoncallback = jsoncallback;
    }

    public String getTc() {
        return tc;
    }

    public void setTc(String tc) {
        this.tc = tc;
    }

    @Override
    public String toString() {
        return "QueryBean{" +
                "des='" + des + '\'' +
                ", source='" + source + '\'' +
                ", time='" + time + '\'' +
                ", ori='" + ori + '\'' +
                ", date='" + date + '\'' +
                ", jsoncallback='" + jsoncallback + '\'' +
                ", tc='" + tc + '\'' +
                '}';
    }
}