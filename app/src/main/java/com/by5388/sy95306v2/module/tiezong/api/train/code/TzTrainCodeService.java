package com.by5388.sy95306v2.module.tiezong.api.train.code;


import com.by5388.sy95306v2.module.tiezong.bean.temp2.ResultQueryTrainCode;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * @author by5388  on 2019/1/22.
 */
public interface TzTrainCodeService {
    String QUERY_KEY_WORD = "keyword";
    String QUERY_DATE = "date";

    /**
     * 模糊查询所有与关键字相匹配的车次
     *
     * @param keyWord
     * @param date
     * @return
     */
    @GET("search")
    Observable<ResultQueryTrainCode> getSimpleTrainCodeList(@Query(QUERY_KEY_WORD) String keyWord, @Query(QUERY_DATE) String date);
}
