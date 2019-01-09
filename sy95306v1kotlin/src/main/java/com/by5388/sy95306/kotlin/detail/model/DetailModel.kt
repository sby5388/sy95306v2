package com.by5388.sy95306.kotlin.detail.model

import com.by5388.sy95306.kotlin.bean.TrainDetail
import com.by5388.sy95306.kotlin.net.api.NetTools
import com.by5388.sy95306.kotlin.net.api.TrainService
import io.reactivex.Observable

/**
 * @author  by5388  on 2019/1/5.
 */
class DetailModel : IDetailModel {
    private val service: TrainService? = NetTools().getMyRetrofit().create(TrainService::class.java)

    override fun query(date: Int, trainCode: String): Observable<List<TrainDetail>> {
        return service!!.getTrainByTrainCode(date, trainCode);
    }
}