package com.by5388.sy95306v2.start.presenter;

/**
 * @author by5388  on 2018/7/30.
 */

public interface IStartPresenter {


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


}
