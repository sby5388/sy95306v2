package com.by5388.sy95306.kotlin.detail.view

import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.TextView
import com.by5388.sy95306.kotlin.R

/**
 * @author  by5388  on 2019/1/5.
 */
class DetailViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    val textViewIndex = view.findViewById(R.id.textView_number) as TextView
    val stationName = view.findViewById<TextView>(R.id.station_name)
    val arriveTime = view.findViewById<TextView>(R.id.arrive_time)
    val leaveTime = view.findViewById<TextView>(R.id.leave_time)
    val remainTime = view.findViewById<TextView>(R.id.remain_time)
}