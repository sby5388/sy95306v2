package com.by5388.sy95306.kotlin.list.view

import android.content.Context

/**
 * @author by5388  on 2019/1/7.
 */
class Temp {
    private fun test(mContext: Context) {
        val adapter = TrainListAdapter(mContext, object : ListOnClickListener {
            override fun toDetail(trainCode: String) {

            }
        })
    }
}
