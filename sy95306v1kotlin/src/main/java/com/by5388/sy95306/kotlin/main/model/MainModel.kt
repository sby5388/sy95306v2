package com.by5388.sy95306.kotlin.main.model

import com.by5388.sy95306.kotlin.database.api.DataBaseApi
import com.by5388.sy95306.kotlin.database.api.DataBaseApiImpl
import com.by5388.sy95306.kotlin.database.api.IMySharedPreference
import com.by5388.sy95306.kotlin.database.api.MySharedPreferencesImpl
import io.reactivex.Observable
import io.reactivex.ObservableOnSubscribe

/**
 * @author  by5388  on 2019/1/10.
 */
class MainModel : IMainModel {

    private val dbApi: DataBaseApi
    private val stringBuilder: StringBuilder
    private val tool: IStationTool
    private val preferences: IMySharedPreference

    companion object {
        private const val TIME_OUT = 5000
        private const val SERVER_PATH = "https://kyfw.12306.cn/otn/leftTicket/init"

    }

    init {
        dbApi = DataBaseApiImpl()
        stringBuilder = StringBuilder()
        tool = StationTool()
        preferences = MySharedPreferencesImpl.getInstance()
    }


    override fun updateProgress(): Observable<Int> {
        return dbApi.insertStationList(tool.getStationList(stringBuilder.toString()))
    }

    override fun getNetWordStatus(): Observable<Int> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun checkUpdate(): Observable<Boolean> {
        val empty = dbApi.isEmpty()
        if (empty) {
            return Observable.just(true)
        }
        val currentVersion: Double = getCurrentVersion()
        return Observable.create(ObservableOnSubscribe { emitter ->
            if (!emitter.isDisposed) {
//                tool.
            }
        })

    }

    private fun getCurrentVersion(): Double {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.

    }
}