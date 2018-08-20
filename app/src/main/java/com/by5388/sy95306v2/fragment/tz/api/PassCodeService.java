package com.by5388.sy95306v2.fragment.tz.api;

import io.reactivex.Observable;
import okhttp3.ResponseBody;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * 获取图片验证码
 *
 * @author by5388  on 2018/6/11.
 */

public interface PassCodeService {

    String MODULE = "module";
    String RAND = "rand";
    String VALUE = "";

    //module=other&rand=sjrand&0.4185230006624856

    /**
     * 获取图片验证码
     *
     * @param module       固定值："other"
     * @param rand         固定值："sjrand"
     * @param randomNumber 随机数
     * @return 获取图片验证码
     */
    @GET("getPassCodeNew")
    Observable<ResponseBody> getNewPassCode(
            @Query(MODULE) String module,
            @Query(RAND) String rand,
            @Query(VALUE) double randomNumber
    );

}
