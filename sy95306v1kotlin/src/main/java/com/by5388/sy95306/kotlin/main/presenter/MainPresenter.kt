package com.by5388.sy95306.kotlin.main.presenter

import com.by5388.sy95306.kotlin.main.model.IMainModel
import com.by5388.sy95306.kotlin.main.model.MainModel
import com.by5388.sy95306.kotlin.main.view.IMainView

/**
 * @author  by5388  on 2019/1/10.
 */
class MainPresenter(private val view: IMainView) : IMainPresenter {
    private val model: IMainModel

    init {
        model = MainModel()
    }

    override fun checkUpdate() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun startUpdate() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}