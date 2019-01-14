package com.by5388.sy95306.kotlin.main.presenter

import android.util.Log
import com.by5388.sy95306.kotlin.R
import com.by5388.sy95306.kotlin.main.model.IMainModel
import com.by5388.sy95306.kotlin.main.model.MainModel
import com.by5388.sy95306.kotlin.main.view.IMainView
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

/**
 * @author  by5388  on 2019/1/10.
 */
class MainPresenter(private val view: IMainView) : IMainPresenter {
    private val model: IMainModel
    private val disposable: CompositeDisposable

    init {
        model = MainModel()
        disposable = CompositeDisposable()
    }

    companion object {
        private const val TAG = "MainPresenter"
    }

    override fun checkUpdate() {
        // TODO: 操作前 检查网络状态
        disposable.add(model.checkUpdate()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    if (it) {
                        view.notifyUpdate()
                    } else {
                        view.tip(R.string.is_update_data)
                    }
                }, {
                    view.tip(R.string.error_try_again)
                }))
    }

    override fun startUpdate() {
        disposable.add(model.getStationCount()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    view.showUpdateDiaglog(it)
                    clearData()
                }, { view.tip(R.string.error_try_again) })
        )
    }

    override fun unSubject() {
        disposable.clear()
    }

    private fun clearData() {
        val errorCode = -1
        disposable.add(model.clearData()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    if (errorCode != it) {
                        insertData()
                    }
                }, {
                    view.tip(R.string.error_try_again)
                })
        )
    }

    private fun insertData() {
        disposable.add(model.insertStationList()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    Log.d(TAG, "insertData = $it")
                    view.updateProgress(it)
                }, {
                    view.tip(R.string.error_try_again)
                }, {
                    Log.d(TAG, "加载完成")
                    view.finishUpdate()
                    model.finishUpdate()
                })
        )
    }
}