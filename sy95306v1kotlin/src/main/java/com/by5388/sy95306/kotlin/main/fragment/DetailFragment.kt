package com.by5388.sy95306.kotlin.main.fragment


import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import com.by5388.sy95306.kotlin.R
import com.by5388.sy95306.kotlin.detail.TrainDetailActivity
import kotlinx.android.synthetic.main.fragment_detail.*
import java.util.*

class DetailFragment : BaseFragment() {

    override fun getLayoutId() = R.layout.fragment_detail


    override fun initView(view: View) {

        val buttonSelectDate = view.findViewById<Button>(R.id.buttonSelectDate)
        buttonSelectDate.setOnClickListener {
            showDataPickerDialog()
        }
        buttonSelectDate.text = getShowDate()
        val textView = view.findViewById<TextView>(R.id.textViewTrainCode)

        view.findViewById<Button>(R.id.button_query).setOnClickListener {
            val text = textView.text.toString().trim()
            if (text.isEmpty()) {
                textView.error = context.getString(R.string.pleaseInputCode)
                return@setOnClickListener
            }
//            Log.d(TAG, text)
//            Log.d(TAG, buttonSelectDate.text.toString().trim().toInt().toString())
            startActivity(TrainDetailActivity.newIntent(context, buttonSelectDate.text.toString().trim().toInt(), text))
//            startActivity(TrainDetailActivity.newIntent(context, 20190110, "D7515"))
        }
    }


    override fun initData() {

    }

    override fun updateData(calendar: Calendar) {
        buttonSelectDate.text = getShowDate(calendar)

    }

    companion object {
        const val TAG = "DetailFragment"
        @JvmStatic
        fun newInstance() =
                DetailFragment().apply {
                    arguments = Bundle().apply {
                    }
                }
    }
}
