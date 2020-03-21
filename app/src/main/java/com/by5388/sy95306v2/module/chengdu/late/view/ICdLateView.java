package com.by5388.sy95306v2.module.chengdu.late.view;

import com.by5388.sy95306v2.module.chengdu.bean.late.CdTrainAllNodeBean;

import java.util.List;

/**
 * @author by5388  on 2018/8/18.
 */
public interface ICdLateView {
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

    /**
     * 如果选择的车次有跨日期，则提供列表供选择，View层以对话框的形式来实现
     *
     * @param trainDetail 说明
     */
    void showDialog(List<String> trainDetail);


    /**
     * 更新晚点车次的详细信息
     *
     * @param beans 信息
     */
    void updateDetailData(List<CdTrainAllNodeBean> beans);

    /**
     * 回调获取：presenter不知道相关的参数
     */
    void getLateDetail();
}
