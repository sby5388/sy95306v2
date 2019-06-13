package com.by5388.sy95306v2.module.shanghai.number.presenter;

/**
 * @author by5388  on 2018/8/9
 */

public interface INumberPresenter {

    /**
     * 搜索
     *
     * @param trainCode 车次
     * @param trainDate 日期
     */
    void search(String trainCode, String trainDate);

    /**
     * 关闭时，取消订阅关系，防止内存泄露
     */
    void unSubscribe();

}
