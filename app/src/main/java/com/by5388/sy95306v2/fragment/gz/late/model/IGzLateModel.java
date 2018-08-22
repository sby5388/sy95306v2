package com.by5388.sy95306v2.fragment.gz.late.model;

import com.by5388.sy95306v2.bean.gz.late.GzLateDataBean;

import java.util.List;

import io.reactivex.Observable;

/**
 * @author by5388  on 2018/8/20.
 */
public interface IGzLateModel {
    /**
     * 管内车次正晚点
     *
     * @param trainNo 车次
     * @return 数据
     */
    Observable<List<GzLateDataBean>> getGzLateStationInfoBeans(String trainNo);
}
