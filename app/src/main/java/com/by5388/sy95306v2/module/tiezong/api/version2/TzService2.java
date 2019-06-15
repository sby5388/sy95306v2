package com.by5388.sy95306v2.module.tiezong.api.version2;

import com.by5388.sy95306v2.module.tiezong.bean.TzResult;
import com.by5388.sy95306v2.module.tiezong.bean.number.NumberListDataBean;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * @author Administrator  on 2019/6/14.
 */
public interface TzService2 {
    @GET("queryTrainInfo/init")
    Observable<Temp> getTrainDetail(@Query("leftTicketDTO.train_no") String trainNo,
                                                            @Query("leftTicketDTO.train_date") String trainDate,
                                                            @Query("rand_code") String randCode

    );
}
