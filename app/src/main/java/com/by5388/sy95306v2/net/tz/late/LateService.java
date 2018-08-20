package com.by5388.sy95306v2.net.tz.late;

import io.reactivex.Observable;
import okhttp3.ResponseBody;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * 晚点查询
 *
 * @author by5388  on 2018/6/11.
 */

public interface LateService {
    /**
     * 车站名称
     */
    String CZ = "cz";
    /**
     * 车次
     */
    String CC = "cc";
    /**
     * 查询类型：0：到达，1：出发
     */
    String CXLX = "cxlx";
    /**
     * 日期
     */
    String RQ = "rq";
    /**
     * 车站名称的"utf-8"编码
     */
    String CZ_EN = "czEn";
    /**
     * 时间戳
     */
    String TP = "tp";

    //http://dynamic.12306.cn/mapping/kfxt/zwdcx/LCZWD/cx.jsp?
    // cz=%C8%C4%C6%BD&cc=D2293&cxlx=0&rq=2018-08-14&czEn=-E9-A5-B6-E5-B9-B3&tp=1534234037271

    /**
     * 正晚点查询
     *
     * @param stationName 车站名称
     * @param code        车次
     * @param queryType   查询类型：0：到达，1：出发
     * @param date        日期
     * @param czEn        车站名称的"utf-8"编码
     * @param time        时间戳:毫秒
     * @return 正晚点情况
     */
    @GET("mapping/kfxt/zwdcx/LCZWD/cx.jsp")
    Observable<ResponseBody> queryLate(
            @Query(CZ) String stationName,
            @Query(CC) String code,
            @Query(CXLX) int queryType,
            @Query(RQ) String date,
            @Query(CZ_EN) String czEn,
            @Query(TP) long time
    );

}
