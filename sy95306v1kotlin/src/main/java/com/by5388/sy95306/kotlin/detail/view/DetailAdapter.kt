package com.by5388.sy95306.kotlin.detail.view

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.by5388.sy95306.kotlin.R
import com.by5388.sy95306.kotlin.bean.TrainDetail
import com.by5388.sy95306.kotlin.common.getTime

/**
 * @author  by5388  on 2019/1/5.
 */
class DetailAdapter(private val context: Context) : RecyclerView.Adapter<DetailViewHolder>() {

    private var trainDetailList: List<TrainDetail> = ArrayList()

    fun setTrainDetailList(trainDetailList: List<TrainDetail>) {
        this.trainDetailList = trainDetailList
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): DetailViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_train_detail, parent, false);
        return DetailViewHolder(view);
    }

    override fun getItemCount(): Int = trainDetailList.size

    override fun onBindViewHolder(holder: DetailViewHolder?, position: Int) {
        val detail: TrainDetail = trainDetailList[position]
        holder!!.arriveTime.text = getTime(detail.arriveTime)
        holder.leaveTime.text = getTime(detail.leaveTime)
        holder.remainTime.text = "--"
        holder.stationName.text = detail.stationName
        holder.textViewIndex.text = detail.number
    }


}