package com.by5388.sy95306.kotlin.detail

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.view.KeyEvent
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import com.by5388.sy95306.kotlin.R
import com.by5388.sy95306.kotlin.bean.TrainDetail
import com.by5388.sy95306.kotlin.detail.presenter.DetailPresenter
import com.by5388.sy95306.kotlin.detail.presenter.IDetailPresenter
import com.by5388.sy95306.kotlin.detail.view.DetailAdapter
import com.by5388.sy95306.kotlin.detail.view.IDetailView
import kotlinx.android.synthetic.main.activity_train_detail.*

class TrainDetailActivity : AppCompatActivity(), IDetailView {
    var presenter: IDetailPresenter? = null
    var adapter: DetailAdapter? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_train_detail)
        if (supportActionBar != null) {
            supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        }
        initData()
        initView()
        loadData()
    }


    private fun initData() {
        presenter = DetailPresenter(this)
        adapter = DetailAdapter(this)
    }

    private fun initView() {
        swipeRefreshLayout.setColorSchemeResources(R.color.colorPrimary,
                R.color.colorPrimaryDark, R.color.colorAccent,
                R.color.colorAccent)
        swipeRefreshLayout.setOnRefreshListener { loadData() }
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.addItemDecoration(DividerItemDecoration(this, DividerItemDecoration.VERTICAL))
        recyclerView.adapter = adapter
    }


    private fun loadData() {
        val bundle: Bundle = intent.getBundleExtra(KEY_BUNDLE)
        if (bundle.isEmpty) {
            finish()
            return
        }
        val date = bundle.get(KEY_DATE) as Int
        val code = bundle.get(KEY_CODE) as String
        if (code.isEmpty() || date.toString().isEmpty()) {
            finish()
            return
        }
        title = "$code $date"
        presenter!!.query(date, code)
    }

    companion object {
        private const val KEY_BUNDLE = "bundle"
        private const val KEY_DATE = "date"
        private const val KEY_CODE = "code"

        @SuppressWarnings("unused")
        @JvmStatic
        fun newIntent(context: Context, date: Int, code: String): Intent {
            val intent = Intent(context, TrainDetailActivity::class.java)
            val bundle = Bundle()
            bundle.putString(KEY_CODE, code)
            bundle.putInt(KEY_DATE, date)
            intent.putExtra(KEY_BUNDLE, bundle)
            return intent
        }
    }


    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
        return when (keyCode) {
            KeyEvent.KEYCODE_DPAD_DOWN -> {
                recyclerView.layoutManager.scrollToPosition(0)
                Toast.makeText(this, "向下", Toast.LENGTH_SHORT).show()
                true
            }
            else -> super.onKeyDown(keyCode, event)
        }
    }


    override fun showData(trainDetailList: List<TrainDetail>) {
        adapter!!.setTrainDetailList(trainDetailList)
        recyclerView.requestFocus()
    }

    override fun showError(tip: String) {
        Toast.makeText(this, tip, Toast.LENGTH_SHORT).show()
        finish()
    }

    override fun startLoad() {
        swipeRefreshLayout.isRefreshing = true
        recyclerView.visibility = View.GONE
    }

    override fun stopLoad() {
        swipeRefreshLayout.isRefreshing = false
        recyclerView.visibility = View.VISIBLE
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        var result = false
        when (item!!.itemId) {
            android.R.id.home -> {
                finish();
                result = true
            }
        }
        return result
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter!!.unSubject()
    }
}
