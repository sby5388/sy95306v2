package com.by5388.sy95306.kotlin.list.view

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.by5388.sy95306.kotlin.R
import com.by5388.sy95306.kotlin.bean.TrainNumber
import com.by5388.sy95306.kotlin.common.getDescription
import com.by5388.sy95306.kotlin.common.getSpendTime
import com.by5388.sy95306.kotlin.common.getTime

/**
 * @author  by5388  on 2019/1/7.
 */
class TrainListAdapter(private val context: Context, private val listener: ListOnClickListener)
    : RecyclerView.Adapter<TrainListAdapter.ListViewHolder>(), View.OnClickListener {
    /**
     * todo :List<>:为不可变容器，没有clear()等操作
     *  [com.by5388.sy95306.kotlin.detail.view.DetailAdapter]
     * todo：MutableList<>:为可变容器，有clear()等方法
     */
    private var trainNumberList: List<TrainNumber> = ArrayList()
    private val allPriceFormat: String = context.resources.getString(R.string.all_price)

    fun setTrainNumberList(list: List<TrainNumber>) {
        trainNumberList = list
        this.notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ListViewHolder {
        val itemView = LayoutInflater.from(context).inflate(R.layout.item_train_number_list, parent, false)
        return ListViewHolder(itemView)
    }

    override fun getItemCount() = trainNumberList.size

    override fun onBindViewHolder(holder: ListViewHolder?, position: Int) {
        val trainNumber = trainNumberList[position]
        holder!!.trainNumberName.text = trainNumber.trainCode
        holder.description.text = getDescription(trainNumber)
        holder.startStationName.text = trainNumber.shangChe
        holder.endStationName.text = trainNumber.xiaChe

//        setStartStationIcon(trainNumber, holder.imageViewStart, context)
//        setEndStationIcon(trainNumber, holder.imageViewEnd, context)

        holder.startStationTime.text = getTime(trainNumber.startTime)
        holder.endStationTime.text = getTime(trainNumber.arriveTime)
        holder.spendTime.text = getSpendTime(trainNumber.spendTime)

        val price1 = trainNumber.DESPRI1 + trainNumber.PRI1
        val price2 = trainNumber.DESPRI2 + trainNumber.PRI2
        val price3 = trainNumber.DESPRI3 + trainNumber.PRI3
        val price4 = trainNumber.DESPRI4 + trainNumber.PRI4
        holder.price.text = String.format(allPriceFormat, price1, price2, price3, price4)
        holder.rootView.tag = position
        holder.rootView.setOnClickListener(this)
    }

    class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val rootView: View = itemView.findViewById(R.id.lly_item_all)
        val imageViewStart: TextView = itemView.findViewById(R.id.textView_start_station)
        val imageViewEnd: TextView = itemView.findViewById(R.id.textView_end_station)
        val startStationName: TextView = itemView.findViewById(R.id.textView_start_station_name)
        val endStationName: TextView = itemView.findViewById(R.id.textView_end_station_name)
        val startStationTime: TextView = itemView.findViewById(R.id.textView_start_station_time)
        val endStationTime: TextView = itemView.findViewById(R.id.textView_end_station_time)
        val spendTime: TextView = itemView.findViewById(R.id.textView_spend_time)
        val trainNumberName: TextView = itemView.findViewById(R.id.textView_train_number_name)
        val description: TextView = itemView.findViewById(R.id.textView_train_description)
        val price: TextView = itemView.findViewById(R.id.textView_price) as TextView
    }

    override fun onClick(v: View?) {
        val position = v!!.tag as Int
        if (position < trainNumberList.size) {
            listener.toDetail(trainNumberList[position].trainCode)
        }
    }
}