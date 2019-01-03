package com.by5388.sy95306.database;

import com.by5388.sy95306.bean.Station;

import java.util.List;

/**
 * @author by5388  on 2018/7/21.
 */

public interface DataBaseImpl {

    boolean getStationStatus();

    int addStation(List<Station> stationList);

    /**
     * 搜索单个:name,NameUpper:精准
     * @param nameUpper
     * @return
     */
    Station selectStationByNameUpper(String nameUpper);

    Station selectStationByName(String stationName);


    /**
     * 模糊查询
     *
     * @param key 关键字
     * @return 车站集合
     */
    List<Station> selectStationList(String key);
}
