package com.by5388.sy95306v2.chengdu.bean.screen;

import com.by5388.sy95306v2.chengdu.screen.CdScreenItem;

/**
 * 车站大屏幕：显示详情:出发
 *
 * @author by5388
 * @date 2018/8/17 23:45
 */
@SuppressWarnings("all")
public class ScreenLeaveDetail implements CdScreenItem {
    @Override
    public String getTrainCode() {
        return getTRAIN_DEP();
    }

    @Override
    public String getStartStation() {
        return getSTART_STN();
    }

    @Override
    public String getEndStation() {
        return getEND_STN();
    }

    @Override
    public String getTime() {
        return getTD_DATE_DEP11();
    }

    @Override
    public String getPlace() {
        return getCHECK_TICKET();
    }

    @Override
    public String getStatus() {
        return getCHECK_STATUS();
    }


    /**
     * CHECK_STATUS : 正在检票
     * CHECK_TICKET : 3候5,第三候车室
     * DATE_ARR : 23:26:00
     * END_CHECK_TIME : 2018/08/17 23:51:00
     * END_STN : 昆明
     * END_STN_CODE : KMM
     * IN_DATE : 2018/08/17 23:30:35
     * START_CHECK_TIME : 2018/08/17 23:24:00
     * START_STN : 上海南
     * START_STN_CODE : SNH
     * STATUS_TRAIN : 正点
     * STN_CODE : GIW
     * TD_DATE_DEP11 : 23:56
     * TRAIN_DEP : K739
     * WAIT_ROOM : 第3候车室
     * WGQBZ : 1
     */


    private String CHECK_STATUS;
    private String CHECK_TICKET;
    private String DATE_ARR;
    private String END_CHECK_TIME;
    private String END_STN;
    private String END_STN_CODE;
    private String IN_DATE;
    private String START_CHECK_TIME;
    private String START_STN;
    private String START_STN_CODE;
    private String STATUS_TRAIN;
    private String STN_CODE;
    private String TD_DATE_DEP11;
    private String TRAIN_DEP;
    private String WAIT_ROOM;
    private String WGQBZ;

    public String getCHECK_STATUS() {
        return CHECK_STATUS;
    }

    public void setCHECK_STATUS(String CHECK_STATUS) {
        this.CHECK_STATUS = CHECK_STATUS;
    }

    public String getCHECK_TICKET() {
        return CHECK_TICKET;
    }

    public void setCHECK_TICKET(String CHECK_TICKET) {
        this.CHECK_TICKET = CHECK_TICKET;
    }

    public String getDATE_ARR() {
        return DATE_ARR;
    }

    public void setDATE_ARR(String DATE_ARR) {
        this.DATE_ARR = DATE_ARR;
    }

    public String getEND_CHECK_TIME() {
        return END_CHECK_TIME;
    }

    public void setEND_CHECK_TIME(String END_CHECK_TIME) {
        this.END_CHECK_TIME = END_CHECK_TIME;
    }

    public String getEND_STN() {
        return END_STN;
    }

    public void setEND_STN(String END_STN) {
        this.END_STN = END_STN;
    }

    public String getEND_STN_CODE() {
        return END_STN_CODE;
    }

    public void setEND_STN_CODE(String END_STN_CODE) {
        this.END_STN_CODE = END_STN_CODE;
    }

    public String getIN_DATE() {
        return IN_DATE;
    }

    public void setIN_DATE(String IN_DATE) {
        this.IN_DATE = IN_DATE;
    }

    public String getSTART_CHECK_TIME() {
        return START_CHECK_TIME;
    }

    public void setSTART_CHECK_TIME(String START_CHECK_TIME) {
        this.START_CHECK_TIME = START_CHECK_TIME;
    }

    public String getSTART_STN() {
        return START_STN;
    }

    public void setSTART_STN(String START_STN) {
        this.START_STN = START_STN;
    }

    public String getSTART_STN_CODE() {
        return START_STN_CODE;
    }

    public void setSTART_STN_CODE(String START_STN_CODE) {
        this.START_STN_CODE = START_STN_CODE;
    }

    public String getSTATUS_TRAIN() {
        return STATUS_TRAIN;
    }

    public void setSTATUS_TRAIN(String STATUS_TRAIN) {
        this.STATUS_TRAIN = STATUS_TRAIN;
    }

    public String getSTN_CODE() {
        return STN_CODE;
    }

    public void setSTN_CODE(String STN_CODE) {
        this.STN_CODE = STN_CODE;
    }

    public String getTD_DATE_DEP11() {
        return TD_DATE_DEP11;
    }

    public void setTD_DATE_DEP11(String TD_DATE_DEP11) {
        this.TD_DATE_DEP11 = TD_DATE_DEP11;
    }

    public String getTRAIN_DEP() {
        return TRAIN_DEP;
    }

    public void setTRAIN_DEP(String TRAIN_DEP) {
        this.TRAIN_DEP = TRAIN_DEP;
    }

    public String getWAIT_ROOM() {
        return WAIT_ROOM;
    }

    public void setWAIT_ROOM(String WAIT_ROOM) {
        this.WAIT_ROOM = WAIT_ROOM;
    }

    public String getWGQBZ() {
        return WGQBZ;
    }

    public void setWGQBZ(String WGQBZ) {
        this.WGQBZ = WGQBZ;
    }
}
