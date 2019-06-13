package com.by5388.sy95306v2.module.tiezong.bean.temp;

import java.util.List;

/**
 * @author by5388  on 2019/1/6.
 */
public class DataBeanX {

    private boolean flag;
    /**
     * 同城车站
     */
    private List<DataBean> data;
    /**
     * 同城车站名称
     */
    private List<String> sameStations;

    public boolean isFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public List<String> getSameStations() {
        return sameStations;
    }

    public void setSameStations(List<String> sameStations) {
        this.sameStations = sameStations;
    }
}
