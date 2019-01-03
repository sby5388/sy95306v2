package com.by5388.sy95306v2.tiezong.api;

import com.by5388.sy95306v2.tiezong.bean.TzResult;
import com.by5388.sy95306v2.tiezong.bean.check.PassCodeDataBean;
import com.by5388.sy95306v2.tiezong.bean.number.NumberListDataBean;
import com.by5388.sy95306v2.tiezong.bean.yp.success.SuccessDataBean;

import io.reactivex.Observable;
import okhttp3.ResponseBody;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * 获取图片验证码
 *
 * @author by5388  on 2018/6/11.
 */

public interface IPassCodeService {
    //    https://kyfw.12306.cn/otn/zzzcx/
    //    https://kyfw.12306.cn/otn/passcodeNew/checkRandCodeAnsyn
    //    https://kyfw.12306.cn/otn/passcodeNew/getPassCodeNew
    //    https://kyfw.12306.cn/otn/czxx/queryByTrainNo

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
     */
    @GET("zzzcx/query")
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
     * 获取图片验证码
     * module=other&rand=sjrand&0.4185230006624856
     *
     * @param module       固定值："other"
     * @param rand         固定值："sjrand"
     * @param randomNumber 随机数
     * @return 获取图片验证码
     */
    @GET("passcodeNew/getPassCodeNew")
    Observable<ResponseBody> getNewPassCode(
            @Query(MODULE) String module,
            @Query(RAND) String rand,
            @Query(VALUE) double randomNumber
    );

    /**
     * 检验验证码
     *
     * @param rand     固定值："sjrand"
     * @param randCode 验证码
     * @return 结果
     */
    @GET("passcodeNew/checkRandCodeAnsyn")
    Observable<TzResult<PassCodeDataBean>> checkRandCodeAnsyn(
            @Query(RAND) String rand,
            @Query(RAND_CODE) String randCode
    );

    /**
     * 车次查询：
     *
     * @param trainNo         车次的全称，6k0000D92200
     * @param fromStationCOde 出发站电报码，估计没啥用，待验证
     * @param toStationCOde   目的站电报码，估计没啥用，待验证
     * @param date            日期，格式yyyy-MM-dd
     * @return 数据， {@link NumberListDataBean}
     */
    @GET("czxx/queryByTrainNo")
    Observable<TzResult<NumberListDataBean>> getNumberList(
            @Query("train_no") String trainNo,
            @Query("from_station_telecode") String fromStationCOde,
            @Query("to_station_telecode") String toStationCOde,
            @Query("depart_date") String date

    );

}
