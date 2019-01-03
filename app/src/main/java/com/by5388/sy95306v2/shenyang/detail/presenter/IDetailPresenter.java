package com.by5388.sy95306v2.shenyang.detail.presenter;

/**
 * @author by5388  on 2018/7/29.
 */

public interface IDetailPresenter {
    /**
     * 获取数据
     *
     * @param currentDate 日期
     * @param code        车次信息
     */
    void getDetailData(int currentDate, String code);

    /**
     * 关闭时，取消订阅关系，防止内存泄露
     */
    void unSubscribe();

}
