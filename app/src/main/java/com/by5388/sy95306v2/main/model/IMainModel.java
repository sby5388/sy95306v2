package com.by5388.sy95306v2.main.model;

import io.reactivex.Observable;

/**
 * @author by5388  on 2019/1/2.
 */
public interface IMainModel {
    /**
     * 当前更新的进度、百分比
     *
     * @return 数量
     */
    Observable<Integer> insertProgressBar();

    /**
     * 车站总数量
     *
     * @return 总数量
     */
    Observable<Integer> getStationCount();

    /**
     * 是否需要更新，对比本地的数据版本与服务器的数据库版本
     *
     * @return 是否需要更新
     */
    Observable<Boolean> isNeedUpdate();

    /**
     * 网络是否可用
     *
     * @return 网络是否可用
     */
    boolean getNetWordCanUse();

    /**
     * 清空数据库
     *
     * @return 0:清空完成
     */
    Observable<Integer> clearData();

    /**
     * 更新完成，更新版本号
     */
    void finishUpdate();
}
