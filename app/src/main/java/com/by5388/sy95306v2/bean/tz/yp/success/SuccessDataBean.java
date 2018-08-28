package com.by5388.sy95306v2.bean.tz.yp.success;

import java.util.List;

/**
 * @author by5388
 * @date 2018/8/17 15:11
 * 请求成功时的车站列表
 */
@SuppressWarnings("all")
public class SuccessDataBean {


    /**
     * 标记：true:有数据(成功)
     * false:无数据(无数据、异常)
     */
    protected boolean flag;
    private String isThrough;
    private List<TzDataBean> datas;

    public final boolean isFlag() {
        return flag;
    }

    public final void setFlag(boolean flag) {
        this.flag = flag;
    }

    public String getIsThrough() {
        return isThrough;
    }

    public void setIsThrough(String isThrough) {
        this.isThrough = isThrough;
    }

    public List<TzDataBean> getDatas() {
        return datas;
    }

    public void setDatas(List<TzDataBean> datas) {
        this.datas = datas;
    }


}

