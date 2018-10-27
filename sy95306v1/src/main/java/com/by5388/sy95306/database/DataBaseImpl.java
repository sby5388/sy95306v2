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
     * 搜索多个:firstName,nameEn,nameLower,name：模糊搜索
     * @param nameLower
     * @return
     */
    List<Station> selectStationListByNameLower(String nameLower);

    List<Station> selectStationListByNameEn(String nameEn);

    List<Station> selectStationListByName(String stationName);

    List<Station> selectStationListByNameFirst(String nameFirst);
}
