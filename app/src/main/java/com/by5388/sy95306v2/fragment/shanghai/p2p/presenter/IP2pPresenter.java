package com.by5388.sy95306v2.fragment.shanghai.p2p.presenter;

/**
 * @author by5388  on 2018/8/10
 */

public interface IP2pPresenter {

    /**
     * 搜索
     *
     * @param fromStation 出发
     * @param toStation   目的
     * @param trainDate   日期
     */
    void search(String fromStation, String toStation, String trainDate);

    /**
     * 关闭时，取消订阅关系，防止内存泄露
     */
    void unSubscribe();

}
