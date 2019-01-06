package com.by5388.sy95306v2.tiezong.bean.yp.fail;

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
    /**
     * 标记：true:有数据(成功)
     * false:无数据(无数据、异常)
     */
    private boolean flag;

    public final boolean isFlag() {
        return flag;
    }

    public final void setFlag(boolean flag) {
        this.flag = flag;
    }
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}

