package com.by5388.sy95306v2.bean.tz.fail;

/**
 * @author by5388
 * @date 2018/8/17 15:31
 * 请求失败
 */
public class FailDataBean {
    /**
     * message : 没有查询到中转信息！
     * flag : false
     */

    private String message;
    private boolean flag;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean isFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }
}

