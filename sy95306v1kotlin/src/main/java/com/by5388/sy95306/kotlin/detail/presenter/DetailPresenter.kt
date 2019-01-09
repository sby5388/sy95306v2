package com.by5388.sy95306.kotlin.detail.presenter

import android.util.Log
import com.by5388.sy95306.kotlin.detail.model.DetailModel
import com.by5388.sy95306.kotlin.detail.model.IDetailModel
import com.by5388.sy95306.kotlin.detail.view.IDetailView
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

/**
 * @author  by5388  on 2019/1/5.
 */
class DetailPresenter(val view: IDetailView) : IDetailPresenter {
    private val disposable = CompositeDisposable()
    private val model: IDetailModel = DetailModel()
    override fun query(date: Int, trainCode: String) {
        view.startLoad()
        disposable.add(model.query(date, trainCode)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        {
                            if (it != null) {
                                view.showData(it)
                                view.stopLoad()
                            }
                        },
                        {
                            view.showError("异常")
                            Log.e("this", "query", it)
                            //view.stopLoad()
                        }
                ))
    }

    override fun unSubject() {
        disposable.clear()
    }
}