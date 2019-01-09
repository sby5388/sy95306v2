package com.by5388.sy95306.kotlin.list.view

import com.by5388.sy95306.kotlin.bean.TrainDetail
import com.by5388.sy95306.kotlin.bean.TrainNumber

/**
 * @author by5388  on 2019/1/7.
 */
interface IListView{
    fun showData(trainNumberList: List<TrainNumber>);
    fun showError(tip: String);
    fun startLoad();
    fun stopLoad();
}
