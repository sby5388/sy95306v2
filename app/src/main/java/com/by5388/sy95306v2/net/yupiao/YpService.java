package com.by5388.sy95306v2.net.yupiao;

import com.by5388.sy95306v2.bean.tzyp.YpResult;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * 查询车次：站到站
 *
 * @author by5388  on 2018/6/11.
 */

public interface YpService {
    //http://yupiao.info/api/yp/%E6%B7%B1%E5%9C%B3-%E8%B4%B5%E9%98%B3-/2018-08-14
    //http://yupiao.info/api/yp/深圳-贵阳-/2018-08-14
    /**
     * 请求参数：三部分组成（起始站-目的站-车次），车次可以省略
     */
    String PARAMS = "params";
    /**
     * 日期，格式为 yyyy--MM-dd
     */
    String DATE = "date";


    /**
     * 余票查询 ，结果需要手动解析
     *
     * @param params 请求参数：三部分组成（起始站-目的站-车次），车次可以省略
     * @param date   日期，格式为 yyyy--MM-dd
     * @return 余票查询 ，结果需要手动解析
     */
    @GET("yp/{params}/{date}")
    Observable<YpResult> getYpMessage(@Path(PARAMS) String params, @Path(DATE) String date);


}
