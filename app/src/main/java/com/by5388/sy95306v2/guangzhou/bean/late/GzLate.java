package com.by5388.sy95306v2.guangzhou.bean.late;

import com.by5388.sy95306v2.guangzhou.bean.GzResultData;

import java.util.List;

/**
 * @author by5388  on 2018/8/20.
 * @see GzResultData <List<GzLateDataBean>>
 * @deprecated
 */
class GzLate {


    /**
     * data : [{"sfDate":"08-20","sfStation":"饶平","stationInfos":[{"bureauCode":"Q","exit":"","late":"","sjTime":"08-20 14:35","station":"饶平","tdTime":"08-20 14:35"},{"bureauCode":"Q","exit":"","late":"晚点2分","sjTime":"08-20 14:54","station":"潮汕","tdTime":"08-20 14:52"},{"bureauCode":"Q","exit":"","late":"晚点1分","sjTime":"08-20 16:16","station":"惠东","tdTime":"08-20 16:15"},{"bureauCode":"Q","exit":"","late":"晚点2分","sjTime":"08-20 16:34","station":"惠州南","tdTime":"08-20 16:32"},{"bureauCode":"Q","exit":"","late":"提前5分","sjTime":"08-20 17:02","station":"深圳北","tdTime":"08-20 17:07"}],"trainNo":"D7415","zdStation":"深圳北"}]
     * error :
     * success : true
     */

    private String error;
    private boolean success;
    private List<GzLateDataBean> data;

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public List<GzLateDataBean> getData() {
        return data;
    }

    public void setData(List<GzLateDataBean> data) {
        this.data = data;
    }
}
