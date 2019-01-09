package com.by5388.sy95306.kotlin

import com.by5388.sy95306.kotlin.bean.TrainDetail
import com.by5388.sy95306.kotlin.net.api.NetTools
import com.by5388.sy95306.kotlin.net.api.TrainService
import io.reactivex.functions.Consumer
import org.junit.Test
import retrofit2.Retrofit

/**
 * @author  by5388  on 2019/1/5.
 */
class NetTest {
    @Test
    fun testNetService() {
        val retrofit: Retrofit = NetTools().getMyRetrofit()
        val service: TrainService = retrofit.create(TrainService::class.java)
        val date: Int = 20190110
        val trainCode: String = "D7515"

        val consumer: Consumer<List<TrainDetail>> =
                Consumer<List<TrainDetail>>() {
                    for (detail in it) {
                        println(detail.stationName)
                    }
                }
        val throwable: Consumer<Throwable> = Consumer<Throwable>() {
            System.err.println(it.localizedMessage)
        }

        service.getTrainByTrainCode(date, trainCode)
                .subscribe(consumer, throwable)
    }
}