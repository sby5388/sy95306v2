package com.by5388.sy95306.kotlin.database.api

import com.by5388.sy95306.kotlin.bean.Station
import io.reactivex.Observable

/**
 * 增删查
 * 改：不实现，不现实
 * @author  by5388  on 2019/1/7.
 */
interface DataBaseApi {
    fun getStationList(): List<Station>
    /**
     * delete all data
     */
    fun deleteStationList(): Int
    fun getStationByName(name: String): Station
    fun getStationByKeyWord(keyWord: String): List<Station>
    fun insertStationList(stationList: List<Station>): Observable<Int>
}