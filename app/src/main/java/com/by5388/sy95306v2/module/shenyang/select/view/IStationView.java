package com.by5388.sy95306v2.module.shenyang.select.view;

import com.by5388.sy95306v2.module.shenyang.bean.Station;

import java.util.List;

/**
 * @author by5388  on 2018/7/28.
 */

public interface IStationView {

    /**
     * 设置车站列表
     *
     * @param stations 车站列表
     */
    void setStations(List<Station> stations);

    /**
     * 显示错误信息
     *
     * @param message
     */
    void showErrorMessage(String message);


}
