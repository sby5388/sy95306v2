package com.by5388.sy95306.kotlin.list

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import com.by5388.sy95306.kotlin.R
import com.by5388.sy95306.kotlin.bean.TrainNumber
import com.by5388.sy95306.kotlin.detail.TrainDetailActivity
import com.by5388.sy95306.kotlin.list.presenter.IListPresenter
import com.by5388.sy95306.kotlin.list.presenter.ListPresenter
import com.by5388.sy95306.kotlin.list.view.IListView
import com.by5388.sy95306.kotlin.list.view.ListOnClickListener
import com.by5388.sy95306.kotlin.list.view.TrainListAdapter
import kotlinx.android.synthetic.main.activity_train_detail.*

class TrainListActivity : AppCompatActivity(), IListView, ListOnClickListener {
    var date: Int? = null
    var presenter: IListPresenter? = null
    var adapter: TrainListAdapter? = null
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
        presenter = ListPresenter(this)
        adapter = TrainListAdapter(this, this)
    }

    override fun toDetail(trainCode: String) {
        startActivity(TrainDetailActivity.newIntent(this, date!!, trainCode))
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
        date = bundle.get(KEY_DATE) as Int
        val codeFrom = bundle.get(KEY_CODE_FROM) as String
        val codeTo = bundle.get(KEY_CODE_TO) as String
        if (codeFrom.isEmpty() || codeTo.isEmpty() || date.toString().isEmpty()) {
            finish()
            return
        }
        presenter!!.query(date!!, codeFrom, codeTo)
    }

    companion object {
        private const val KEY_BUNDLE = "bundle"
        private const val KEY_DATE = "date"
        private const val KEY_CODE_FROM = "code_from"
        private const val KEY_CODE_TO = "code_to"

        @SuppressWarnings("unused")
        @JvmStatic
        fun newIntent(context: Context, date: Int, codeFrom: String, codeTo: String): Intent {
            val intent = Intent(context, TrainListActivity::class.java)
            val bundle = Bundle()
            bundle.putString(KEY_CODE_FROM, codeFrom)
            bundle.putString(KEY_CODE_TO, codeTo)
            bundle.putInt(KEY_DATE, date)
            intent.putExtra(KEY_BUNDLE, bundle)
            return intent
        }
    }

    override fun showData(trainNumberList: List<TrainNumber>) {
        adapter!!.setTrainNumberList(trainNumberList)
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
