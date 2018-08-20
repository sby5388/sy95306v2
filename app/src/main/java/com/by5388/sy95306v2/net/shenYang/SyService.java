package com.by5388.sy95306v2.net.shenYang;

import com.by5388.sy95306v2.bean.shenyang.TrainDetail;
import com.by5388.sy95306v2.bean.shenyang.TrainNumber;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * 查询车次：站到站
 *
 * @author by5388  on 2018/6/11.
 */

public interface SyService {

    /**
     * 车次：如D7412
     */
    String TRAIN_CODE = "train_code";
    String TRAIN_DATE = "train_date";
    String FROM_STATION = "from_station";
    String TO_STATION = "to_station";

    String QUERY_FIRST = "GetRsultInfo1";
    String QUERY_SECOND = "GetRsultInfo2";


    /**
     * 查询车次：站到站
     *
     * @param date        日期 转化成20180506这种格式
     * @param fromStation 出发站/城市
     * @param toStation   终点站/城市
     * @return 所有车次信息
     */
    @GET(QUERY_FIRST)
    Observable<List<TrainNumber>> getTrainNumberByP2P(@Query(TRAIN_DATE) int date,
                                                      @Query(FROM_STATION) String fromStation,
                                                      @Query(TO_STATION) String toStation);

    /**
     * 车次查询:第二个页面使用、第一个页面查询后的条目点击事件也是这个
     *
     * @param date      日期
     * @param trainCode 车次
     * @return 车次的停站信息
     */
    @GET(QUERY_SECOND)
    Observable<List<TrainDetail>> getTrainByTrainCode(@Query(TRAIN_DATE) int date,
                                                      @Query(TRAIN_CODE) String trainCode);

}
