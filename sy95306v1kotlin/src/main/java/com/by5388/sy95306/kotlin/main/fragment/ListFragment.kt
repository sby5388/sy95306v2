package com.by5388.sy95306.kotlin.main.fragment


import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import com.by5388.sy95306.kotlin.R
import com.by5388.sy95306.kotlin.list.TrainListActivity
import java.util.*

class ListFragment : BaseFragment() {
    override fun getLayoutId() = R.layout.fragment_list


    override fun initView(view: View) {
        view.findViewById<Button>(R.id.buttonQuery).setOnClickListener {
            val date = 20190128
            val toStation = "IOQ"
            val fromStation = "CBQ"
            startActivity(TrainListActivity.newIntent(context, date, fromStation, toStation))
        }

        view.findViewById<ImageView>(R.id.imageViewSwap).setOnClickListener {

        }
    }

    override fun initData() {

    }

    override fun updateData(calendar: Calendar) {

    }

    companion object {
        @JvmStatic
        fun newInstance() =
                ListFragment().apply {
                    arguments = Bundle().apply {
                    }
                }
    }

}
