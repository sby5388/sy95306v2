package com.by5388.t12306.api.vague;

import com.by5388.t12306.api.vague.bean.VagueQueryResult;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * @author by5388  on 2019/1/24.
 */
public interface VagueService {
    String QUERY_KEY_WORD = "keyword";
    String QUERY_DATE = "date";

    /**
     * 模糊查询所有与关键字相匹配的车次
     *
     * @param keyWord 关键字
     * @param date 日期
     * @return 车次
     */
    @GET("search")
    Observable<VagueQueryResult> getSimpleTrainCodeList(@Query(QUERY_KEY_WORD) String keyWord, @Query(QUERY_DATE) String date);
}
