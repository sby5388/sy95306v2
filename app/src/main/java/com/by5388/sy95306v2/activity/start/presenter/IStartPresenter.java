package com.by5388.sy95306v2.activity.start.presenter;

/**
 * @author by5388  on 2018/7/30.
 */

public interface IStartPresenter {

    /**
     * 检查车站列表版本
     * 数据来自12306网站
     */
    void checkStationListVersion();

    /**
     * 检查网络状态
     */
    void checkNetworkStatus();

    /**
     * 关闭时，取消订阅关系，防止内存泄露
     */
    void unSubscribe();

    /**
     * 开始更新
     */
    void startUpdate();

    /**
     * 更新完成
     */
    void finishUpdate();

    /**
     * 清除数据
     */
    void clearData();

    /**
     * 插入数据
     */
    void insertData();

}
