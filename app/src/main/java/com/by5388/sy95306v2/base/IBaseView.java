package com.by5388.sy95306v2.base;

/**
 * @author by5388  on 2018/8/19.
 */
public interface IBaseView {
    /**
     * 开始查询
     */
    void startQuery();

    /**
     * 结束查询
     */
    void finishQuery();

    /**
     * 错误提示
     *
     * @param tip 提示
     */
    void showError(String tip);
}
