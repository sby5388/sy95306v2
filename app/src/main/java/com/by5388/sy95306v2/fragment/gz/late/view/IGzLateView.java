package com.by5388.sy95306v2.fragment.gz.late.view;

import com.by5388.sy95306v2.base.IBaseView;
import com.by5388.sy95306v2.bean.gz.late.GzLateStationInfoBean;

import java.util.List;

/**
 * @author by5388  on 2018/8/19.
 */
public interface IGzLateView extends IBaseView {


    /**
     * 更新标题
     *
     * @param trainCode 车次
     * @param trainName 始发-终到
     */
    void setTitle(String trainCode, String trainName);

    /**
     * 更新晚点信息
     *
     * @param beans 晚点数据
     */
    void updateData(List<GzLateStationInfoBean> beans);

    /**
     * 如果选择的车次有多个，则提供列表供选择，View层以对话框的形式来实现
     *
     * @param trainDetail 说明
     */
    void showDialog(List<String> trainDetail);

    /**
     * 回调获取：presenter不知道相关的参数
     */
    void getLateDetail();

}
