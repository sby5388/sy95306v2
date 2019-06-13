package com.by5388.sy95306v2.module.chengdu.api.yp;

import com.by5388.sy95306v2.module.chengdu.bean.yupiao.CdYpTop;

import io.reactivex.Observable;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * @author by5388
 * @date 2018/8/12 20:20
 */
public interface CdYpService {

    String START_STATION = "startStation";
    String END_STATION = "endStation";
    String START_DATE = "startDate";
    String MY_PASS_TYPE = "myPassType";
    /**
     * 查询类型：余票
     */
    @SuppressWarnings("all")
    String QUERY_YP = "Ypcx";


    /**
     * 成都铁路局余票查询
     *
     * @param fromStation 出发
     * @param toStation   终点
     * @param date        日期
     * @param queryType   类型，固定为"QB" 全部
     * @return 结果
     */
    @POST(QUERY_YP)
    Observable<CdYpTop> queryYp(@Query(START_STATION) String fromStation,
                                @Query(END_STATION) String toStation,
                                @Query(START_DATE) String date,
                                @Query(MY_PASS_TYPE) String queryType
    );

}
