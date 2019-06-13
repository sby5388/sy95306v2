package com.by5388.sy95306.kotlin.main.fragment


import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import com.by5388.sy95306.kotlin.R
import com.by5388.sy95306.kotlin.database.api.DataBaseApi
import com.by5388.sy95306.kotlin.database.api.DataBaseApiImpl
import com.by5388.sy95306.kotlin.list.TrainListActivity
import java.util.*

class ListFragment : BaseFragment() {
    private var api: DataBaseApi? = null
    private var editTextFrom: EditText? = null
    private var editTextTo: EditText? = null
    private var buttonDate: Button? = null
    override fun getLayoutId() = R.layout.fragment_list


    override fun initView(view: View) {
        editTextFrom = view.findViewById(R.id.editTextFrom) as EditText
        editTextTo = view.findViewById(R.id.editTextTo) as EditText


        buttonDate = view.findViewById(R.id.buttonSelectDate) as Button
        buttonDate!!.setOnClickListener {
            showDataPickerDialog()
        }
        view.findViewById<Button>(R.id.buttonQuery).setOnClickListener {
            val fromText = editTextFrom!!.text.toString().trim()
            val toText = editTextTo!!.text.toString().trim()

            if (fromText.isEmpty() || toText.isEmpty()) {
                Toast.makeText(context, "输入数据", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            val fromStation = api!!.getStationByName(fromText)
            val toStation = api!!.getStationByName(toText)
            if (fromStation == null || toStation == null) {
                Toast.makeText(context, "错误数据", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }


            val date = buttonDate!!.text.toString().trim().toInt()
            startActivity(TrainListActivity.newIntent(context, date, fromStation.nameUpper, toStation.nameUpper))
        }

        view.findViewById<ImageView>(R.id.imageViewSwap).setOnClickListener {
            val fromText = editTextFrom!!.text.toString().trim()
            val toText = editTextTo!!.text.toString().trim()
            editTextFrom!!.setText(toText)
            editTextTo!!.setText(fromText)
        }
        buttonDate!!.text = getShowDate()
    }

    override fun initData() {
        api = DataBaseApiImpl()
    }

    override fun updateData(calendar: Calendar) {
        buttonDate!!.text = getShowDate(calendar)
    }

    companion object {
//        @JvmStatic
//        fun newInstance() =
//                ListFragment().apply {
//                    arguments = Bundle().apply {
//                    }
//                }
        @JvmStatic
        fun newInstance():Fragment = ListFragment()

    }

}
