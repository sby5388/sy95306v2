package com.by5388.sy95306.kotlin.net.api

import com.by5388.sy95306.kotlin.bean.TrainDetail
import com.by5388.sy95306.kotlin.bean.TrainNumber
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * @author  by5388  on 2019/1/5.
 */
interface TrainService {

    companion object {
        /**
         * 车次：如D7412
         */
        const val TRAIN_CODE = "train_code"
        const val TRAIN_DATE = "train_date"
        const val FROM_STATION = "from_station"
        const val TO_STATION = "to_station"

        const val QUERY_FIRST = "GetRsultInfo1"
        const val QUERY_SECOND = "GetRsultInfo2"
    }

    /**
     * 查询车次：站到站
     *
     * @param date        日期 转化成20180506这种格式
     * @param fromStation 出发站/城市
     * @param toStation   终点站/城市
     * @return 所有车次信息
     */
    @GET(QUERY_FIRST)
    fun getTrainNumberByP2P(@Query(TRAIN_DATE) date: Int,
                            @Query(FROM_STATION) fromStation: String,
                            @Query(TO_STATION) toStation: String)
            : Observable<List<TrainNumber>>

    /**
     * 车次查询:第二个页面使用、第一个页面查询后的条目点击事件也是这个
     *
     * @param date      日期
     * @param trainCode 车次
     * @return 车次的停站信息
     */
    @GET(QUERY_SECOND)
    fun getTrainByTrainCode(@Query(TRAIN_DATE) date: Int,
                            @Query(TRAIN_CODE) trainCode: String)
            : Observable<List<TrainDetail>>


}