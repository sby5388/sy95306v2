package com.by5388.sy95306v2.tiezong.bean.temp2;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * @author by5388  on 2019/1/22.
 */
public class ResultQueryTrainCode {

    /**
     * mSimpleBeanList : [{"date":"20190123","from_station":"南京","station_train_code":"D2281","to_station":"深圳北","total_num":"33","train_no":"54000D228150"},{"date":"20190123","from_station":"深圳北","station_train_code":"D2282","to_station":"南京","total_num":"32","train_no":"6i000D22820A"},{"date":"20190123","from_station":"上海虹桥","station_train_code":"D2283","to_station":"深圳北","total_num":"25","train_no":"5l000D228380"},{"date":"20190123","from_station":"深圳北","station_train_code":"D2284","to_station":"上海虹桥","total_num":"27","train_no":"6i000D22840A"},{"date":"20190123","from_station":"上海虹桥","station_train_code":"D2285","to_station":"深圳北","total_num":"27","train_no":"5l000D228521"},{"date":"20190123","from_station":"深圳北","station_train_code":"D2286","to_station":"上海虹桥","total_num":"29","train_no":"6i000D22860B"},{"date":"20190123","from_station":"上海虹桥","station_train_code":"D2287","to_station":"深圳北","total_num":"23","train_no":"5l000D228750"},{"date":"20190123","from_station":"深圳北","station_train_code":"D2288","to_station":"上海虹桥","total_num":"28","train_no":"6i000D22880C"}]
     * status : true
     * errorMsg :
     */

    private boolean status;
    private String errorMsg;
    @SerializedName("data")
    private List<DataSimpleBean> mSimpleBeanList;

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    public List<DataSimpleBean> getSimpleBeanList() {
        return mSimpleBeanList;
    }

    public void setSimpleBeanList(List<DataSimpleBean> simpleBeanList) {
        this.mSimpleBeanList = simpleBeanList;
    }


}
