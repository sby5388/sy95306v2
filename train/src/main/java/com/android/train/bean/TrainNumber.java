package com.android.train.bean;

/**
 * 点到点查询的返回数据
 *
 * @author by5388  on 2018/5/26.
 */
@SuppressWarnings("all")
public final class TrainNumber {
    /**
     * 60分钟
     */
    private static final int MINUTE_OF_HOUR = 60;
    /**
     * 空调车
     */
    private static final String AC_TRAIN = "1";


    /**
     * DAYD : 0
     * STIME : 0940
     * TRNO : 6i000D235209
     * DESPRI5 :
     * DESPRI2 : 一等座:
     * DESPRI1 : 商务座:
     * TIC4 :
     * AC : 1
     * DESPRI4 :
     * TIC3 :
     * DESPRI3 : 二等座:
     * TIC2 :
     * TIC1 :
     * <p>
     * LISHI : 19
     * DESTIC4 :
     * EST : WCS:::终点站
     * SST : IOQ :::始发站
     * FST : CBQ:::上车站
     * TST : RVQ:::下车站
     * DESTIC3 :
     * PRI4 :
     * DESTIC2 :
     * PRI3 : 14.0元
     * DESTIC1 :
     * PRI5 :
     * PRI2 : 23.0元
     * PRI1 : 43.0元
     * STCODE : D2352
     * ATIME : 0959
     * TCCODE : D
     */

    private String DAYD;
    private String STIME;
    private String TRNO;
    private String DESPRI5;
    private String DESPRI2;
    private String DESPRI1;
    private String TIC4;
    private String AC;
    private String DESPRI4;
    private String TIC3;
    private String DESPRI3;
    private String TIC2;
    private String TIC1;
    private String FST;
    /**
     * 全程历时
     */
    private String LISHI;
    private String DESTIC4;
    private String EST;
    private String DESTIC3;
    private String PRI4;
    private String DESTIC2;
    private String PRI3;
    private String DESTIC1;
    private String PRI5;
    private String PRI2;
    private String PRI1;
    private String STCODE;
    private String TST;
    private String ATIME;
    private String SST;
    private String TCCODE;

    public String getDAYD() {
        return DAYD;
    }

    public void setDAYD(String DAYD) {
        this.DAYD = DAYD;
    }

    public String getSTIME() {
        return STIME;
    }

    public void setSTIME(String STIME) {
        this.STIME = STIME;
    }

    public String getTRNO() {
        return TRNO;
    }

    public void setTRNO(String TRNO) {
        this.TRNO = TRNO;
    }

    public String getDESPRI5() {
        return DESPRI5;
    }

    public void setDESPRI5(String DESPRI5) {
        this.DESPRI5 = DESPRI5;
    }

    public String getDESPRI2() {
        return DESPRI2;
    }

    public void setDESPRI2(String DESPRI2) {
        this.DESPRI2 = DESPRI2;
    }

    public String getDESPRI1() {
        return DESPRI1;
    }

    public void setDESPRI1(String DESPRI1) {
        this.DESPRI1 = DESPRI1;
    }

    public String getTIC4() {
        return TIC4;
    }

    public void setTIC4(String TIC4) {
        this.TIC4 = TIC4;
    }

    public String getAC() {
        return AC;
    }

    public void setAC(String AC) {
        this.AC = AC;
    }

    public String getDESPRI4() {
        return DESPRI4;
    }

    public void setDESPRI4(String DESPRI4) {
        this.DESPRI4 = DESPRI4;
    }

    public String getTIC3() {
        return TIC3;
    }

    public void setTIC3(String TIC3) {
        this.TIC3 = TIC3;
    }

    public String getDESPRI3() {
        return DESPRI3;
    }

    public void setDESPRI3(String DESPRI3) {
        this.DESPRI3 = DESPRI3;
    }

    public String getTIC2() {
        return TIC2;
    }

    public void setTIC2(String TIC2) {
        this.TIC2 = TIC2;
    }

    public String getTIC1() {
        return TIC1;
    }

    public void setTIC1(String TIC1) {
        this.TIC1 = TIC1;
    }

    public String getFST() {
        return FST;
    }

    public void setFST(String FST) {
        this.FST = FST;
    }

    public String getLISHI() {
        return LISHI;
    }

    public void setLISHI(String LISHI) {
        this.LISHI = LISHI;
    }

    public String getDESTIC4() {
        return DESTIC4;
    }

    public void setDESTIC4(String DESTIC4) {
        this.DESTIC4 = DESTIC4;
    }

    public String getEST() {
        return EST;
    }

    public void setEST(String EST) {
        this.EST = EST;
    }

    public String getDESTIC3() {
        return DESTIC3;
    }

    public void setDESTIC3(String DESTIC3) {
        this.DESTIC3 = DESTIC3;
    }

    public String getPRI4() {
        return PRI4;
    }

    public void setPRI4(String PRI4) {
        this.PRI4 = PRI4;
    }

    public String getDESTIC2() {
        return DESTIC2;
    }

    public void setDESTIC2(String DESTIC2) {
        this.DESTIC2 = DESTIC2;
    }

    public String getPRI3() {
        return PRI3;
    }

    public void setPRI3(String PRI3) {
        this.PRI3 = PRI3;
    }

    public String getDESTIC1() {
        return DESTIC1;
    }

    public void setDESTIC1(String DESTIC1) {
        this.DESTIC1 = DESTIC1;
    }

    public String getPRI5() {
        return PRI5;
    }

    public void setPRI5(String PRI5) {
        this.PRI5 = PRI5;
    }

    public String getPRI2() {
        return PRI2;
    }

    public void setPRI2(String PRI2) {
        this.PRI2 = PRI2;
    }

    public String getPRI1() {
        return PRI1;
    }

    public void setPRI1(String PRI1) {
        this.PRI1 = PRI1;
    }

    public String getSTCODE() {
        return STCODE;
    }

    public void setSTCODE(String STCODE) {
        this.STCODE = STCODE;
    }

    public String getTST() {
        return TST;
    }

    public void setTST(String TST) {
        this.TST = TST;
    }

    public String getATIME() {
        return ATIME;
    }

    public void setATIME(String ATIME) {
        this.ATIME = ATIME;
    }

    public String getSST() {
        return SST;
    }

    public void setSST(String SST) {
        this.SST = SST;
    }

    public String getTCCODE() {
        return TCCODE;
    }

    public void setTCCODE(String TCCODE) {
        this.TCCODE = TCCODE;
    }

    /**
     * @return 出发时间：分钟
     */
    public int getStartTime() {
        return calculatorTime(STIME);
    }

    /**
     * @return 到达时间：分钟
     */
    public int getArriveTime() {
        return calculatorTime(ATIME);
    }

    /**
     * @return 全程时间：分钟
     */
    public int getSpendTime() {
        try {
            return Integer.parseInt(LISHI);
        } catch (NumberFormatException e) {
            return 0;
        }
    }

    /**
     * 计算时间
     *
     * @param time 时间字符串
     * @return 对应的时间：分钟
     */
    private static int calculatorTime(String time) {
        int hour = Integer.parseInt(time.substring(0, 2));
        int minute = Integer.parseInt(time.substring(2, 4));
        return hour * MINUTE_OF_HOUR + minute;
    }

    /**
     * @return 是否是终到站
     */
    public boolean isLastStation() {
        return EST.equals(TST);
    }

    /**
     * @return 是否是始发站
     */
    public boolean isFirstStation() {
        return SST.equals(FST);
    }

    /**
     * 是否有空调
     * <p>
     * 0 无空调
     * 1 有空调
     *
     * @return 空调
     */
    public String isACTrain() {
        if (AC_TRAIN.equals(AC)) {
            return "有空调";
        } else {
            return "无空调";
        }
    }
}
