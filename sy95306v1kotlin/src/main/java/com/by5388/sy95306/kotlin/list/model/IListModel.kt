package com.by5388.sy95306.kotlin.list.model

import com.by5388.sy95306.kotlin.bean.TrainNumber
import io.reactivex.Observable

/**
 * @author  by5388  on 2019/1/7.
 */
interface IListModel {


    fun query(date: Int, fromStationCode: String, toStationCode: String): Observable<List<TrainNumber>>

    fun checkStation(stationName: String): Observable<Boolean>;
}