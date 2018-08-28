package com.by5388.sy95306v2.activity.sy.select.view;

import com.by5388.sy95306v2.bean.Station;

import java.util.List;

/**
 * @author by5388  on 2018/7/28.
 */

public interface IStationView {

    /**
     * 设置车站列表
     *
     * @param stations
     */
    void setStations(List<Station> stations);

    /**
     * 显示错误信息
     *
     * @param message
     */
    void showErrorMessage(String message);


}
