package com.by5388.sy95306v2.database;

import com.by5388.sy95306v2.bean.Station;

import java.util.List;

/**
 * 沈阳车站数据
 *
 * @author by5388  on 2018/7/21.
 */

public interface IShenYangStationDb {

    /**
     * 删除所有车站数据
     *
     * @return 0:成功
     */
    int deleteAllStation();


    /**
     * 增加一个车站
     *
     * @param station 车站
     */
    void addStation(Station station);

    /**
     * 搜索单个:name,
     *
     * @param nameUpper NameUpper:精准
     * @return 车站
     */
    Station selectStationByNameUpper(String nameUpper);

    /**
     * 搜索单个:name,
     *
     * @param stationName 站名
     * @return 车站
     */
    Station selectStationByName(String stationName);

    /**
     * 搜索多个:firstName,nameEn,nameLower,name：模糊搜索
     *
     * @param nameLower 站名
     * @return 车站
     */
    List<Station> selectStationListByNameLower(String nameLower);

    /**
     * 搜索多个:firstName,nameEn,nameLower,name：模糊搜索
     *
     * @param nameEn 站名
     * @return 车站
     */
    List<Station> selectStationListByNameEn(String nameEn);

    /**
     * 搜索多个:firstName,nameEn,nameLower,name：模糊搜索
     *
     * @param stationName 站名
     * @return 车站
     */
    List<Station> selectStationListByName(String stationName);

    /**
     * 搜索多个:firstName,nameEn,nameLower,name：模糊搜索
     *
     * @param nameFirst 站名
     * @return 车站
     */
    List<Station> selectStationListByNameFirst(String nameFirst);

}
