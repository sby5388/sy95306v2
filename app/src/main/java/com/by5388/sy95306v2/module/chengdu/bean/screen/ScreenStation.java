package com.by5388.sy95306v2.module.chengdu.bean.screen;

/**
 * @author by5388
 * @date 2018/8/17 23:12
 */
@SuppressWarnings("all")
public class ScreenStation {


    /**
     * PY : ASX         拼音首字母
     * PYSZM : A        首字母
     * ZM : 安顺西      站名
     * ZMLM : ASE       电报码
     */

    private String PY;
    private String PYSZM;
    private String ZM;
    private String ZMLM;

    public String getPY() {
        return PY;
    }

    public void setPY(String PY) {
        this.PY = PY;
    }

    public String getPYSZM() {
        return PYSZM;
    }

    public void setPYSZM(String PYSZM) {
        this.PYSZM = PYSZM;
    }

    public String getZM() {
        return ZM;
    }

    public void setZM(String ZM) {
        this.ZM = ZM;
    }

    public String getZMLM() {
        return ZMLM;
    }

    public void setZMLM(String ZMLM) {
        this.ZMLM = ZMLM;
    }

    @Override
    public String toString() {
        return ZM;
    }
}
