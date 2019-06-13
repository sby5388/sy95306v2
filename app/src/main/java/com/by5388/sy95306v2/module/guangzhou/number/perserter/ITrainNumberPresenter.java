package com.by5388.sy95306v2.module.guangzhou.number.perserter;

/**
 * @author by5388  on 2018/8/2.
 */

public interface ITrainNumberPresenter {



    /**
     * 搜索
     *
     * @param trainNo 车次
     * @param date    日期
     */
    void search(String trainNo, String date);

    /**
     * 关闭时，取消订阅关系，防止内存泄露
     */
    void unSubscribe();

}
