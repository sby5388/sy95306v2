package com.by5388.sy95306v2.module.tiezong.api.pass.code;

import com.by5388.sy95306v2.module.tiezong.bean.TzResult;
import com.by5388.sy95306v2.module.tiezong.bean.check.PassCodeDataBean;
import com.by5388.sy95306v2.module.tiezong.bean.number.NumberListDataBean;
import com.by5388.sy95306v2.module.tiezong.bean.temp.DataBeanX;
import com.by5388.sy95306v2.module.tiezong.bean.yp.success.SuccessDataBean;

import io.reactivex.Observable;
import okhttp3.ResponseBody;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * 铁总12306 查询api
 *
 * @author by5388  on 2018/6/11.
 */

public interface TieZongService {
    //    https://kyfw.12306.cn/otn/zzzcx/
    //    https://kyfw.12306.cn/otn/passcodeNew/checkRandCodeAnsyn
    //    https://kyfw.12306.cn/otn/passcodeNew/getPassCodeNew
    //    https://kyfw.12306.cn/otn/czxx/queryByTrainNo
    //     https://kyfw.12306.cn/otn/czxx/
    String MODULE = "module";
    String RAND = "rand";
    String RAND_CODE = "randCode";
    String VALUE = "";
    /**
     * 日期
     */
    String QUERY_DATA = "queryDate";
    String FROM_STATION = "from_station";
    String TO_STATION = "to_station";
    String FROM_STATION_NAME = "from_station_name";
    String TO_STATION_NAME = "to_station_name";
    /**
     * 换乘站：这里为空
     */
    String CHANGE_STATION_TEXT = "changeStationText";
    /**
     * 中转查询
     */
    String ZZ_CX = "zzzcx/query";
    /**
     * 获取新的验证码
     */
    @SuppressWarnings("all")
    String GET_NEW_VERIFICATION_CODE = "passcodeNew/getPassCodeNew";
    /**
     * 检验验证码
     */
    @SuppressWarnings("all")
    String CHECK_VERIFICATION_CODE = "passcodeNew/checkRandCodeAnsyn";

    /**
     * 中转查询：实际是余票查询
     * FIXME: 2018/8/20  TODO 需要增加Gson对泛型 支持
     * https://www.baidu.com/s?ie=UTF-8&wd=Gson%20%E8%BD%AC%E5%8C%96%E6%88%90%20%E6%B3%9B%E5%9E%8B
     * queryDate=2018-08-20&from_station=BJQ&to_station=GZQ&from_station_name=深圳东&
     * to_station_name=广州&randCode=ut5p&changeStationText=
     *
     * @param queryDate         日期，格式"2018-08-20"
     * @param fromStationCode   出发站，电报码
     * @param toStationCode     目的站，电报码
     * @param fromStationName   出发站
     * @param toStationName     目的站
     * @param randCode          验证码
     * @param changeStationText 中转站，此处为空
     * @return 结果，可能是成功也可能是失败
     * @deprecated  已经无效的，
     * @see #getRemainTicketData(String, String, String, String)
     */
    @Deprecated
    @GET(ZZ_CX)
    Observable<TzResult<SuccessDataBean>> getZzCxData(
            @Query(QUERY_DATA) String queryDate,
            @Query(FROM_STATION) String fromStationCode,
            @Query(TO_STATION) String toStationCode,
            @Query(FROM_STATION_NAME) String fromStationName,
            @Query(TO_STATION_NAME) String toStationName,
            @Query(RAND_CODE) String randCode,
            @Query(CHANGE_STATION_TEXT) String changeStationText
    );


    /**
     * 车站车次查询：
     *
     * @param trainNo         车次的全称，6k0000D92200
     * @param fromStationCode 出发站电报码
     * @param toStationCode   目的站电报码
     * @param date            日期，格式yyyy-MM-dd
     * @return 数据， {@link NumberListDataBean}
     */
    @GET("czxx/queryByTrainNo")
    Observable<TzResult<NumberListDataBean>> getNumberList(
            @Query("train_no") String trainNo,
            @Query("from_station_telecode") String fromStationCode,
            @Query("to_station_telecode") String toStationCode,
            @Query("depart_date") String date

    );

    /**
     * 车站车次查询：
     * {@link DataBeanX}
     *
     * @param date        日期  格式：yyyy-MM-dd
     * @param stationName 车站中文名，如“饶平”
     * @param stationCode 车站电报码，如 “RVQ”
     * @param randCode    随机数，可为空
     * @return 某个车站（包括同城车站）当天的所有车次
     */
    @GET("czxx/query")
    Observable<TzResult<DataBeanX>> getStationAllTrain(
            @Query("train_start_date") String date,
            @Query("train_station_name") String stationName,
            @Query("train_station_code") String stationCode,
            @Query(RAND_CODE) String randCode
    );

    /*
     * TODO 车次模糊查询
     */


    /*
     *TODO  车次停站查询
     */

    /**
     * 获取余票信息
     * @param type 类型有成人票（普通）："ADULT"、学生票:"0X00" 2种
     * @param queryDate 日期
     * @param fromStation 出发车站电报码
     * @param toStation 到达车站电报码
     * @return
     */
    @GET("query")
    Observable<TzResult<SuccessDataBean>> getRemainTicketData(@Query("purpose_codes") String type,
                                           @Query("queryDate")String queryDate,
                                           @Query("from_station")String fromStation,
                                           @Query("to_station")String toStation
                                           );

}
