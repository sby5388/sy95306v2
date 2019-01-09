package com.by5388.sy95306.kotlin.list.model

import com.by5388.sy95306.kotlin.App
import com.by5388.sy95306.kotlin.bean.TrainNumber
import com.by5388.sy95306.kotlin.net.api.NetTools
import com.by5388.sy95306.kotlin.net.api.TrainService
import io.reactivex.Observable

/**
 * @author  by5388  on 2019/1/7.
 */
class ListModel : IListModel {
    val app: App? = App.getInstance()
    private val service: TrainService = NetTools().getMyRetrofit().create(TrainService::class.java)
    override fun query(date: Int, fromStationCode: String, toStationCode: String): Observable<List<TrainNumber>> {
        return service.getTrainNumberByP2P(date, fromStationCode, toStationCode);
    }

    override fun checkStation(stationName: String): Observable<Boolean> {
        return Observable.just(true)
    }
}