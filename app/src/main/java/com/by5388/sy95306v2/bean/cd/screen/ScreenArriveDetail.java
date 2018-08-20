package com.by5388.sy95306v2.bean.cd.screen;

import com.by5388.sy95306v2.fragment.cd.screen.CdScreenItem;

/**
 * 车站大屏幕：显示详情:到达
 *
 * @author by5388
 * @date 2018/8/17 23:54
 */
@SuppressWarnings("all")
public class ScreenArriveDetail implements CdScreenItem {

    /**
     * CHECK_STATUS : 停止检票
     * DATE_ARR11 : 00:01
     * END_STN : 昆明
     * IN_DATE : 2018/08/17 23:03:47
     * LATE_ARR : 0
     * START_STN : 北京西
     * STATUS_TRAIN : 正点
     * STN_CODE : GIW
     * STN_EXIT :
     * TRAIN_DEP : Z53
     * WAIT_ROOM : 第3候车室
     * WGQBZ : 0
     */


    private String CHECK_STATUS;
    private String DATE_ARR11;
    private String END_STN;
    private String IN_DATE;
    private String LATE_ARR;
    private String START_STN;
    private String STATUS_TRAIN;
    private String STN_CODE;
    private String STN_EXIT;
    private String TRAIN_DEP;
    private String WAIT_ROOM;
    private String WGQBZ;

    public String getCHECK_STATUS() {
        return CHECK_STATUS;
    }

    public void setCHECK_STATUS(String CHECK_STATUS) {
        this.CHECK_STATUS = CHECK_STATUS;
    }

    public String getDATE_ARR11() {
        return DATE_ARR11;
    }

    public void setDATE_ARR11(String DATE_ARR11) {
        this.DATE_ARR11 = DATE_ARR11;
    }

    public String getEND_STN() {
        return END_STN;
    }

    public void setEND_STN(String END_STN) {
        this.END_STN = END_STN;
    }

    public String getIN_DATE() {
        return IN_DATE;
    }

    public void setIN_DATE(String IN_DATE) {
        this.IN_DATE = IN_DATE;
    }

    public String getLATE_ARR() {
        return LATE_ARR;
    }

    public void setLATE_ARR(String LATE_ARR) {
        this.LATE_ARR = LATE_ARR;
    }

    public String getSTART_STN() {
        return START_STN;
    }

    public void setSTART_STN(String START_STN) {
        this.START_STN = START_STN;
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

    public String getSTN_EXIT() {
        return STN_EXIT;
    }

    public void setSTN_EXIT(String STN_EXIT) {
        this.STN_EXIT = STN_EXIT;
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
        return getDATE_ARR11();
    }

    @Override
    public String getPlace() {
        return getSTN_EXIT();
    }

    @Override
    public String getStatus() {
        return getSTATUS_TRAIN();
    }

}
