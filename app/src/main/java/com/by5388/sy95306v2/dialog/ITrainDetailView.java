package com.by5388.sy95306v2.dialog;

import com.by5388.sy95306v2.guangzhou.bean.station.TrainsBean;

/**
 * @author by5388  on 2018/8/2.
 */

public interface ITrainDetailView {

    /**
     * 显示
     */
    void show();

    /**
     * 关闭
     */
    void dismiss();
    /**
     * 正在显示？
     *
     * @return 正在显示
     */
    boolean isShowing();


    /**
     * 设置数据
     *
     * @param trainsBean 车次信息
     * @return 当前对象
     */
    ITrainDetailView setData(TrainsBean trainsBean);






}
