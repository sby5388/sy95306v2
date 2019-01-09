package com.by5388.sy95306.kotlin.main.fragment

import android.app.DatePickerDialog
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.DatePicker
import java.text.SimpleDateFormat
import java.util.*
import java.util.Calendar.*

/**
 * @author  by5388  on 2019/1/8.
 */
abstract class BaseFragment : Fragment(), DatePickerDialog.OnDateSetListener {
    private var datePickerDialog: DatePickerDialog? = null
    private var calendar: Calendar? = null
    private val simpleDateFormat = SimpleDateFormat("yyyyMMdd", Locale.getDefault())

    abstract fun initView(view: View)
    abstract fun getLayoutId(): Int
    abstract fun initData()
    abstract fun updateData(calendar: Calendar)

    final override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                                    savedInstanceState: Bundle?): View? {
        val view: View = inflater.inflate(getLayoutId(), container, false)
        initView(view)
        return view
    }

    final override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        calendar = Calendar.getInstance(Locale.getDefault())
        initData()
    }

    fun showDataPickerDialog() {
        if (datePickerDialog == null) {
            datePickerDialog = DatePickerDialog(context, this, calendar!![YEAR], calendar!![MONTH], calendar!![DATE])
            val picker: DatePicker = datePickerDialog!!.datePicker
            picker.maxDate = calendar!!.timeInMillis + MAX_TIME
            picker.minDate = calendar!!.timeInMillis
        }
        datePickerDialog!!.show()
    }

    override fun onDateSet(view: DatePicker?, year: Int, month: Int, dayOfMonth: Int) {
        calendar!!.set(year, month, dayOfMonth)
        updateData(calendar!!)
    }

    fun getShowDate(calendar: Calendar): String = simpleDateFormat.format(calendar.time).toString()
    fun getShowDate(): String = getShowDate(calendar!!)

    companion object {
        /**
         * 最长查询2个月:应当取消这个时间限制，但是可能会失去一定的准确性
         */
        internal var MAX_TIME: Long = 5184000000L
    }


}