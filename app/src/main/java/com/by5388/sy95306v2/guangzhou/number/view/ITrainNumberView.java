package com.by5388.sy95306v2.guangzhou.number.view;

import com.by5388.sy95306v2.base.IBaseView;
import com.by5388.sy95306v2.guangzhou.bean.station.TrainsBean;

import java.util.List;

/**
 * @author by5388  on 2018/8/2.
 */

public interface ITrainNumberView extends IBaseView {
    /**
     * 更新数据
     *
     * @param trainsBeans 数据
     */
    void updateDate(List<TrainsBean> trainsBeans);

}
