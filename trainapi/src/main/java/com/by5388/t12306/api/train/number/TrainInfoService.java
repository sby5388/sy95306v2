package com.by5388.t12306.api.train.number;


import com.by5388.t12306.api.bean.QueryResult;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * @author by5388  on 2019/5/4.
 */
public interface TrainInfoService {

    /**
     * 查询车次信息
     *
     * @param numberNumber 车次编号
     * @param date         日期
     * @return
     */
    @GET("queryTrainInfo/query")
    Observable<QueryResult<TrainNumber>> getTrainInfo(@Query("leftTicketDTO.train_no") String numberNumber, @Query("leftTicketDTO.train_date") String date);
}
