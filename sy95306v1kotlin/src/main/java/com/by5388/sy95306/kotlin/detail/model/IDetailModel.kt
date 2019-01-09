package com.by5388.sy95306.kotlin.detail.model

import com.by5388.sy95306.kotlin.bean.TrainDetail
import io.reactivex.Observable

/**
 * @author  by5388  on 2019/1/5.
 */
interface IDetailModel {
    fun query(date: Int, trainCode: String): Observable<List<TrainDetail>>
}