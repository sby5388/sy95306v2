package com.by5388.sy95306v2.database;

import com.by5388.sy95306v2.module.shenyang.bean.Station;

import java.util.List;

import io.reactivex.Observable;

/**
 * 沈阳车站数据
 *
 * @author by5388  on 2018/7/21.
 */

public interface IShenYangDbApi {
    int ACTION_DELETE_SUCCESS =  0;

    /**
     * 删除所有车站数据
     *
     * {@link #ACTION_DELETE_SUCCESS}
     * @return 0:成功
     */
    int deleteAllStation();


    /**
     * 批量插入输入库：使用“事务”提高效率
     * copy from :https://blog.csdn.net/jbb0403/article/details/46680393
     *
     * @param stations 车站集合
     * @return 进度值
     */
    Observable<Integer> insertStationList(List<Station> stations);

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
     * 模糊查询
     *
     * @param key 关键字
     * @return 车站集合
     */
    List<Station> selectStationList(String key);

    /**
     * 是否存在数据
     * @return true:空
     */
    boolean isEmpty();

}
