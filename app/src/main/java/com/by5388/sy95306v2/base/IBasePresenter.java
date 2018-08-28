package com.by5388.sy95306v2.base;

/**
 * @author by5388  on 2018/8/25.
 */
public interface IBasePresenter {

    /**
     * 关闭时，取消订阅关系，防止内存泄露
     */
    void unSubscribe();
}
