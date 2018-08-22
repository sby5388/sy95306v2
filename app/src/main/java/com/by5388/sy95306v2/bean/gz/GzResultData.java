package com.by5388.sy95306v2.bean.gz;

/**
 * @author by5388  on 2018/8/1.
 */

public class GzResultData<T> {
    /**
     * 数据类型
     * 1.站到站查询的返回类型是：@see {@link com.by5388.sy95306v2.bean.gz.station.DataBeanP2P}
     * 2.车次查询的返回结果是：@see {@link com.by5388.sy95306v2.bean.gz.number.DataBeanTrainNumber}
     * ,实际上就是List<TrainsBean>
     */
    private T data;
    /**
     * 错误信息
     */
    private String error;
    /**
     * 是否成功
     */
    private boolean success;

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

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
}
