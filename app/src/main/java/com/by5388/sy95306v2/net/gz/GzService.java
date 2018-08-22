package com.by5388.sy95306v2.net.gz;

import com.by5388.sy95306v2.bean.gz.GzResultData;
import com.by5388.sy95306v2.bean.gz.late.GzLateDataBean;
import com.by5388.sy95306v2.bean.gz.station.DataBeanP2P;
import com.by5388.sy95306v2.bean.gz.station.TrainsBean;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * 广铁查询服务相关
 *
 * @author by5388  on 2018/8/1.
 */

public interface GzService {

    /**
     * 站站查询接口
     */
    String GET_TRAIN_BY_STATION = "getTrainByStation";
    /**
     * 车次查询接口
     */
    String GET_TRAIN_BY_NO = "getTrainByNo";
    /**
     * 车次正晚点
     */
    String GET_TRAIN_LATE = "getTrainZwd";

    /**
     * 查询车次
     */
    String TRAIN_NO = "trainNo";

    /**
     * 查询日期
     */
    String DATE = "date";

    /**
     * 出发站
     */
    String FROM_STATION = "fromStation";
    /**
     * 目的站
     */
    String TO_STATION = "toStation";


    /**
     * 车次查询
     * trainNo: "D2315", date: "2018-08-01"
     *
     * @param trainNo 车次
     * @param date    日期
     * @return 停站信息
     */
    @GET(GET_TRAIN_BY_NO)
    Observable<GzResultData<List<TrainsBean>>> getTrainByNo(@Query(TRAIN_NO) String trainNo, @Query(DATE) String date);

    /**
     * 站站查询
     * fromStation: "饶平", toStation: "厦门", date: "2018-08-01"
     *
     * @param fromStation 出发站
     * @param toStation   目的站
     * @param date        日期
     * @return 车次信息
     */
    @GET(GET_TRAIN_BY_STATION)
    Observable<GzResultData<DataBeanP2P>> getTrainByStation(@Query(FROM_STATION) String fromStation,
                                                            @Query(TO_STATION) String toStation,
                                                            @Query(DATE) String date);

    /**
     * 管内车次正晚点
     *
     * @param trainNo 车次
     * @return 数据
     */
    @GET(GET_TRAIN_LATE)
    Observable<GzResultData<List<GzLateDataBean>>> getTrainLate(@Query(TRAIN_NO) String trainNo);

}
