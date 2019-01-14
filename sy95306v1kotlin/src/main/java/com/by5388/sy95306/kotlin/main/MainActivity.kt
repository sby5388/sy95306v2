package com.by5388.sy95306.kotlin.main

import android.content.DialogInterface
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentStatePagerAdapter
import android.support.v4.view.ViewPager
import android.support.v7.app.AlertDialog
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.View
import android.widget.*
import com.by5388.sy95306.kotlin.R
import com.by5388.sy95306.kotlin.main.adapter.FragmentAdapter
import com.by5388.sy95306.kotlin.main.fragment.DetailFragment
import com.by5388.sy95306.kotlin.main.fragment.ListFragment
import com.by5388.sy95306.kotlin.main.presenter.IMainPresenter
import com.by5388.sy95306.kotlin.main.presenter.MainPresenter
import com.by5388.sy95306.kotlin.main.view.IMainView
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), ViewPager.OnPageChangeListener, IMainView {
    var adapter: FragmentStatePagerAdapter? = null
    var updateTipDialog: AlertDialog? = null
    var updatingDialog: AlertDialog? = null
    var presenter: IMainPresenter? = null
    private var allCountTextView: TextView? = null
    private var allCount: Int? = null
    private var currentCount: TextView? = null
    private var progressBar: ProgressBar? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initData()
        initView()
        loadData()
    }


    private fun initData() {
        val fragments: ArrayList<Fragment> = ArrayList()
        fragments.add(ListFragment.newInstance())
        fragments.add(DetailFragment.newInstance())
        adapter = FragmentAdapter(supportFragmentManager, fragments)
        presenter = MainPresenter(this)
    }

    private fun initView() {
        viewPager.adapter = adapter
        viewPager.addOnPageChangeListener(this)
        radioGroup.setOnCheckedChangeListener { _, checkedId ->
            if (checkedId == radioButton0.id) {
                viewPager.setCurrentItem(0, true)
            }
            if (checkedId == radioButton1.id) {
                viewPager.setCurrentItem(1, true)
            }
        }
    }

    private fun loadData() {
        presenter!!.checkUpdate()
    }

    override fun onPageScrollStateChanged(state: Int) {

    }

    override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {

    }

    override fun onPageSelected(position: Int) {
        if (position == 0) {
            radioGroup.check(radioButton0.id)
        } else {
            radioGroup.check(radioButton1.id)
        }
    }

    override fun showUpdateDiaglog(allCount: Int) {
        if (updatingDialog == null) {
            updatingDialog = AlertDialog.Builder(this)
                    .setTitle(R.string.updateing)
                    .setView(getDialogView())
                    .setPositiveButton(android.R.string.ok) { dialog, _ -> dialog.dismiss() }
                    .create()
        }
        updatingDialog!!.setCancelable(false)
        updatingDialog!!.setOnShowListener { _ ->
            val positiveButton: Button = updatingDialog!!.getButton(DialogInterface.BUTTON_POSITIVE)
            positiveButton.isEnabled = false
        }
        this.allCount = allCount
        this.allCountTextView!!.text = String.format(getString(R.string.all_station_count), allCount)
        progressBar!!.progress = 0
        updatingDialog!!.show()
    }

    private fun getDialogView(): View {
        val view: View = layoutInflater!!.inflate(R.layout.dialog_updating, LinearLayout(this), false);
        allCountTextView = view.findViewById(R.id.textView_all_count) as TextView
        currentCount = view.findViewById(R.id.textView_current_count) as TextView
        progressBar = view.findViewById(R.id.progressBar) as ProgressBar
        return view
    }

    override fun updateProgress(progress: Int) {
        Log.d(TAG, "updateProgress :progress = $progress ")
        val percent: Double = progress * 100.0 / allCount!!
        progressBar!!.progress = percent.toInt()
        this.currentCount!!.text = String.format(getString(R.string.current_station_count), progress)
    }

    override fun notifyUpdate() {
        if (updateTipDialog == null) {
            updateTipDialog = AlertDialog.Builder(this)
                    .setTitle(R.string.title_update_tip)
                    .setMessage(R.string.message_update_tip)
                    //todo
                    .setPositiveButton(android.R.string.ok) { _, _ -> presenter!!.startUpdate() }
                    .setNegativeButton(android.R.string.cancel, null)
                    .create()
        }
        if (updateTipDialog!!.isShowing) {
            updateTipDialog!!.dismiss()
        }
        updateTipDialog!!.show()
    }

    override fun tip(tip: Int) {
        Toast.makeText(this, tip, Toast.LENGTH_SHORT).show()
    }

    override fun showUpdating() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun finishUpdate() {
        updatingDialog!!.setCancelable(true)
        updatingDialog!!.setOnShowListener { _ ->
            val positiveButton: Button = updatingDialog!!.getButton(DialogInterface.BUTTON_POSITIVE)
            positiveButton.isEnabled = true
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter!!.unSubject()
        presenter = null
    }

    companion object {
        private const val TAG = "MainActivity"
    }
}
