package com.by5388.sy95306v2.module.tiezong.bean.check;

/**
 * @author by5388
 * @date 2018/8/17 15:28
 */
public class PassCodeDataBean {
    private static final String RIGHT_CODE = "1";

    /**
     * "0":不正确
     * "1":正确
     */
    private String result;
    private String msg;

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public boolean isRight() {
        return RIGHT_CODE.equals(result);
    }


}