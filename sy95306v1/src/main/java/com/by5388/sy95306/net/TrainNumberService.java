package com.by5388.sy95306.net;

import com.by5388.sy95306.bean.TrainDetail;
import com.by5388.sy95306.bean.TrainNumber;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * 查询车次：站到站
 *
 * @author by5388  on 2018/6/11.
 */

public interface TrainNumberService {

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


    @GET(QUERY_SECOND)
    Observable<List<TrainDetail>> getTrainByTrainCode(@Query(TRAIN_DATE) int date,
                                                       @Query(TRAIN_CODE) String trainCode);

}
