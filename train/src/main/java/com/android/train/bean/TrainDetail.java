package com.android.train.bean;

/**
 * @author by5388  on 2018/6/6.
 */
@SuppressWarnings("all")
public final class TrainDetail {

    /**
     * STNO : 01
     * ATIME : ----
     * SNAME : 深圳北
     * STIME : 1105
     * STCODE : D7414
     */

    private String STNO;
    private String ATIME;
    private String SNAME;
    private String STIME;
    private String STCODE;

    public String getSTNO() {
        return STNO;
    }

    public void setSTNO(String STNO) {
        this.STNO = STNO;
    }

    public String getATIME() {
        return ATIME;
    }

    public void setATIME(String ATIME) {
        this.ATIME = ATIME;
    }

    public String getSNAME() {
        return SNAME;
    }

    public void setSNAME(String SNAME) {
        this.SNAME = SNAME;
    }

    public String getSTIME() {
        return STIME;
    }

    public void setSTIME(String STIME) {
        this.STIME = STIME;
    }

    public String getSTCODE() {
        return STCODE;
    }

    public void setSTCODE(String STCODE) {
        this.STCODE = STCODE;
    }
}
