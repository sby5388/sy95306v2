package com.by5388.sy95306v2.fragment.gz.trainNumber.view;

import com.by5388.sy95306v2.bean.guangzhou.station.TrainsBean;

import java.util.List;

/**
 * @author by5388  on 2018/8/2.
 */

public interface ITrainNumberView {
    /**
     * 开始查询
     */
    void startQuery();

    /**
     * 结束查询
     */
    void finishQuery();

    /**
     * 错误提示
     *
     * @param tip 提示
     */
    void showError(String tip);

    /**
     * 更新数据
     *
     * @param trainsBeans 数据
     */
    void updateDate(List<TrainsBean> trainsBeans);


}
