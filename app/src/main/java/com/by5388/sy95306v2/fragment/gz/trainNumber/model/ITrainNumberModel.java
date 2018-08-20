package com.by5388.sy95306v2.fragment.gz.trainNumber.model;

import com.by5388.sy95306v2.bean.guangzhou.ResultData;
import com.by5388.sy95306v2.bean.guangzhou.station.TrainsBean;

import java.util.List;

import io.reactivex.Observable;

/**
 * @author by5388  on 2018/8/2.
 */

public interface ITrainNumberModel {
    /**
     * 车次查询：模糊查询
     * 如D74,返回的有D7401，D7456等
     *
     * @param trainNo 车次
     * @param date    日期
     * @return 停站信息
     */
    Observable<ResultData<List<TrainsBean>>> getTrainByNo(String trainNo, String date);

}
