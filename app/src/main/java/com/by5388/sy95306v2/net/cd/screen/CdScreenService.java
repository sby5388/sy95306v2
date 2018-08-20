package com.by5388.sy95306v2.net.cd.screen;

import com.by5388.sy95306v2.bean.cd.screen.ScreenArriveDetail;
import com.by5388.sy95306v2.bean.cd.screen.ScreenLeaveDetail;
import com.by5388.sy95306v2.bean.cd.screen.ScreenStation;
import io.reactivex.Observable;
import retrofit2.http.POST;
import retrofit2.http.Query;

import java.util.List;

/**
 * 车站大屏幕
 *
 * @author by5388
 * @date 2018/8/12 20:20
 */
public interface CdScreenService {

    /**
     * 查询中携带的固定值
     * ex_1015：获取车次对应车站列表时
     * ex_1013:搜索全部车次
     * C50102:车站大屏幕的车站列表
     * C50101:车站大屏幕侯乘
     * C5054：车站大屏幕到达（接站）
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

    String QUERY = "GeneralProServlet";


    /**
     * 成都铁路局车站大屏幕
     *
     * @param typeCode    固定值 ：C50102
     * @param params      固定字段，为空
     * @param where       固定字段，为空
     * @param order       固定字段，为空
     * @param userMessage 固定值：用户信息？IP+帐号+密码？
     * @return 结果
     */
    @POST(QUERY)
    Observable<List<ScreenStation>> getScreenStationList(@Query(CODE) String typeCode,
                                                         @Query(LOGIN) String userMessage,
                                                         @Query(SQL) String params,
                                                         @Query(WHERE) String where,
                                                         @Query(ORDER) String order
    );

    //候乘：code=C50101&login=["10.192.111.79","hhs","hhs"]&sql=["20180817","GIW"]&where=[]&order=[]
    //接站：code=C5054&login=["10.192.111.79","hhs","hhs"]&sql=["20180817","GIW"]&where=[]&order=[]

    /**
     * 成都铁路局车站大屏幕:侯乘(出发)
     *
     * @param typeCode    固定值 ：C50101
     * @param userMessage 固定值：用户信息？IP+帐号+密码？
     * @param params      固定字段，日期、车站电报码
     * @param where       固定字段，为空
     * @param order       固定字段，为空
     * @return 结果
     */

    @POST(QUERY)
    Observable<List<ScreenLeaveDetail>> getLeaveDetail(@Query(CODE) String typeCode,
                                                       @Query(LOGIN) String userMessage,
                                                       @Query(SQL) String params,
                                                       @Query(WHERE) String where,
                                                       @Query(ORDER) String order
    );

    /**
     * 成都铁路局车站大屏幕:接站(到达)
     *
     * @param typeCode    固定值 ：C5054
     * @param userMessage 固定值：用户信息？IP+帐号+密码？
     * @param params      固定字段，日期、车站电报码
     * @param where       固定字段，为空
     * @param order       固定字段，为空
     * @return 结果
     */
    @POST(QUERY)
    Observable<List<ScreenArriveDetail>> getArriveDetail(@Query(CODE) String typeCode,
                                                         @Query(LOGIN) String userMessage,
                                                         @Query(SQL) String params,
                                                         @Query(WHERE) String where,
                                                         @Query(ORDER) String order
    );
}
