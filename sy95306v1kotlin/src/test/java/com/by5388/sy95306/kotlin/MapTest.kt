package com.by5388.sy95306.kotlin

import io.reactivex.Observable
import io.reactivex.functions.Function

/**
 * @author by5388  on 2019/1/14.
 */
class MapTest {
    fun getString(mDoubleObservable: Observable<Double>): Observable<String> {
        return mDoubleObservable.map(object : Function<Double, String> {
            override fun apply(t: Double): String {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }
        })
    }
}
