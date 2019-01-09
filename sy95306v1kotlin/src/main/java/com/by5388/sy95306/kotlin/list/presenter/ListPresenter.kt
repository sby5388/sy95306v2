package com.by5388.sy95306.kotlin.list.presenter

import android.util.Log
import com.by5388.sy95306.kotlin.list.model.IListModel
import com.by5388.sy95306.kotlin.list.model.ListModel
import com.by5388.sy95306.kotlin.list.view.IListView
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

/**
 * @author  by5388  on 2019/1/7.
 */
class ListPresenter(val view: IListView) : IListPresenter {

    private val disposable = CompositeDisposable()
    private val model: IListModel = ListModel()
    override fun query(date: Int, fromStation: String, toStation: String) {
        view.startLoad()
        disposable.add(model.query(date, fromStation, toStation)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    view.showData(it)
                    view.stopLoad()
                }, {
                    view.showError("异常")
                    Log.e(TAG, "query()", it)
                }))
    }

    override fun unSubject() {
        disposable.clear()
    }

    companion object {
        const val TAG = "ListPresenter"
    }
}