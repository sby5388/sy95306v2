package com.by5388.sy95306v2.module.chengdu.api.late;

import com.by5388.sy95306v2.module.chengdu.bean.late.CdLateResultTop;

import io.reactivex.Observable;
import okhttp3.ResponseBody;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * @author by5388
 * @date 2018/8/12 20:20
 */
public interface CdLateService {

    //获取车站列表
    //http://61.236.123.44/ExData/services/GeneralProServlet?code=ex_1015&sql=["D1849","20180816"]&where=[]&order=[]&login=["10.192.111.79","hhs","hhs"]
    //获取详细的停站信息(时刻表)
    //http://kf.cd-rail.com/CTKF/CTZX/testCC/lateTimeCCDetail.html?cc=D1868&rq=2018-08-12&cfz=广州南&zmArray=["广州南","重庆西"]

    /**
     * 查询中携带的固定值
     * ex_1015：获取车次对应车站列表时
     * ex_1013:搜索全部车次
     */
    String CODE = "code";
    /**
     * 车次+日期，String[]
     */
    String SQL = "sql";
    /**
     * 固定字段，为空
     */
    String WHERE = "where";
    /**
     * 固定字段，为空
     */
    String ORDER = "order";
    /**
     * 用户信息？IP+帐号+密码？
     * 固定值
     */
    String LOGIN = "login";

    /**
     * 通过车次查询晚点时的车站列表：返回的是不规范的json数据，需要重新手动解析
     *
     * @param typeCode    固定值 ：ex_1015
     * @param params      车次+日期，String[]
     * @param where       固定字段，为空
     * @param order       固定字段，为空
     * @param userMessage 固定值：用户信息？IP+帐号+密码？
     * @return 数据
     */

    @GET("GeneralProServlet")
    Observable<ResponseBody> lateTimeCCStation(@Query(CODE) String typeCode,
                                               @Query(SQL) String params,
                                               @Query(WHERE) String where,
                                               @Query(ORDER) String order,
                                               @Query(LOGIN) String userMessage
    );


    /**
     * 获取详细的停站信息(时刻表)
     *
     * @param typeCode    固定值 ：ex_1009
     * @param params      车次+日期+起点城市，
     * @param where       固定字段，为空
     * @param order       固定字段，为空
     * @param userMessage 固定值：用户信息？IP+帐号+密码？
     * @return 数据
     */
    @GET("GeneralProServlet")
    Observable<CdLateResultTop> lateTimeCCDetail(@Query(CODE) String typeCode,
                                                 @Query(SQL) String params,
                                                 @Query(WHERE) String where,
                                                 @Query(ORDER) String order,
                                                 @Query(LOGIN) String userMessage);
}
