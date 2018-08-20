package com.by5388.sy95306v2.activity.trainList.view;

import com.by5388.sy95306v2.bean.shenyang.TrainNumber;

import java.util.List;

/**
 * @author by5388  on 2018/7/29.
 */

public interface ITrainListView {

    /**
     * 更新车次列表
     *
     * @param numbers 车次
     */
    void updateTrainList(List<TrainNumber> numbers);


    /**
     * 显示错误信息
     *
     * @param message 错误
     */
    void showErrorMessage(String message);


    /**
     * 加载中
     */
    void showLoading();

    /**
     * 完成加载
     */
    void finishLoading();


}
