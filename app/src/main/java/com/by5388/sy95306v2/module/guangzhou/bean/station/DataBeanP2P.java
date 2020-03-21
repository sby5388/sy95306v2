package com.by5388.sy95306v2.module.guangzhou.bean.station;

import java.util.List;

/**
 * @author by5388  on 2018/8/1.
 */

public class DataBeanP2P {
    private List<StationsBean> stations;
    private List<TrainsBean> trains;

    public List<StationsBean> getStations() {
        return stations;
    }

    public void setStations(List<StationsBean> stations) {
        this.stations = stations;
    }

    public List<TrainsBean> getTrains() {
        return trains;
    }

    public void setTrains(List<TrainsBean> trains) {
        this.trains = trains;
    }


}
