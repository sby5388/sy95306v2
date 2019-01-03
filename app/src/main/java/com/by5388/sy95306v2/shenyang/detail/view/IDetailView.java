package com.by5388.sy95306v2.shenyang.detail.view;

import com.by5388.sy95306v2.shenyang.bean.TrainDetail;

import java.util.List;

/**
 * @author by5388  on 2018/7/29.
 */

public interface IDetailView {
    /**
     * 加载中
     */
    void showLoading();

    /**
     * 完成加载
     */
    void finishLoading();

    /**
     * 设置车车次信息
     *
     * @param details 信息
     */
    void setStations(List<TrainDetail> details);

    /**
     * 显示错误信息
     *
     * @param message 错误
     */
    void showErrorMessage(String message);

    /**
     * 关闭时，取消订阅关系，防止内存泄露
     */
    void unSubscribe();

    /**
     * 加载成功后，修改小细节
     *
     * @param trainName 车次对应的始发站-终到站,北京南-上海虹桥
     * @param code      车次编号 如G1
     */
    void updateTopView(String trainName, String code);
}
