package com.by5388.sy95306.kotlin.detail.view

import com.by5388.sy95306.kotlin.bean.TrainDetail

/**
 * @author  by5388  on 2019/1/5.
 */
interface IDetailView {
    fun showData(trainDetailList: List<TrainDetail>);
    fun showError(tip: String);
    fun startLoad();
    fun stopLoad();
}