package com.by5388.sy95306v2.module.tiezong.api.pass.code;

import com.by5388.sy95306v2.module.tiezong.bean.TzResult;
import com.by5388.sy95306v2.module.tiezong.bean.number.NumberDataBean;
import com.by5388.sy95306v2.module.tiezong.bean.number.NumberListDataBean;
import com.by5388.sy95306v2.module.tiezong.bean.temp.DataBeanX;
import com.by5388.sy95306v2.module.tiezong.bean.yp.success.SuccessDataBean;

import java.util.List;

import io.reactivex.Observable;


/**
 * @author by5388  on 2018/8/17.
 */
public interface IGetPassCodeService {

    /**
     * 中转查询：实际是余票查询
     *
     * @param queryDate       日期，格式"2018-08-20"
     * @param fromStationCode 出发站，电报码
     * @param toStationCode   目的站，电报码
     * @param fromStationName 出发站
     * @param toStationName   目的站
     * @return 结果，可能是成功也可能是失败
     * @see #getRemainTicketData(boolean, String, String, String)
     */
    Observable<TzResult<SuccessDataBean>> getZzCxData(
            String queryDate,
            String fromStationCode,
            String toStationCode,
            String fromStationName,
            String toStationName
    );


    /**
     * 车次查询：
     *
     * @param trainNo         车次的全称，6k0000D92200
     * @param fromStationCOde 出发站电报码
     * @param toStationCOde   目的站电报码
     * @param date            日期，格式yyyy-MM-dd
     * @return 数据， {@link NumberListDataBean}
     */
    Observable<List<NumberDataBean>> getListNumberDataBean(String trainNo,
                                                           String fromStationCOde,
                                                           String toStationCOde,
                                                           String date);

    /**
     * 车站车次查询：
     * {@link DataBeanX}
     *
     * @param date        日期  格式：yyyy-MM-dd
     * @param stationName 车站中文名，如“饶平”
     * @param stationCode 车站电报码，如 “RVQ”
     * @return 某个车站（包括同城车站）当天的所有车次
     */
    Observable<TzResult<DataBeanX>> getStationAllTrain(
            String date,
            String stationName,
            String stationCode
    );

    /**
     * 获取余票信息
     *
     * @param isStudent   类型有成人票（普通）："ADULT"、学生票:"0X00" 2种
     * @param queryDate   日期
     * @param fromStation 出发车站电报码
     * @param toStation   到达车站电报码
     * @return
     */
    Observable<TzResult<SuccessDataBean>> getRemainTicketData(boolean isStudent,
                                                              String queryDate,
                                                              String fromStation,
                                                              String toStation
    );

}
