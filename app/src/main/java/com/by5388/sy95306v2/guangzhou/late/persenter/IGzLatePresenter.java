package com.by5388.sy95306v2.guangzhou.late.persenter;

/**
 * @author by5388  on 2018/8/20.
 */
public interface IGzLatePresenter {
    /**
     * 管内车次正晚点
     *
     * @param trainCode 车次
     */
    void getLateDetail(String trainCode);

    /**
     * 管内车次正晚点
     *
     * @param position 序号
     */
    void getLateDetail(int position);

    /**
     * 关闭时，取消订阅关系，防止内存泄露
     */
    void unSubscribe();
}
