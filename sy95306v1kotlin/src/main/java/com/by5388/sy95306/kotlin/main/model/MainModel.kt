package com.by5388.sy95306.kotlin.main.model

import android.accounts.NetworkErrorException
import com.by5388.sy95306.kotlin.database.api.DataBaseApi
import com.by5388.sy95306.kotlin.database.api.DataBaseApiImpl
import com.by5388.sy95306.kotlin.database.api.IMySharedPreference
import com.by5388.sy95306.kotlin.database.api.MySharedPreferencesImpl
import io.reactivex.Observable
import io.reactivex.ObservableOnSubscribe
import io.reactivex.functions.Function
import java.net.HttpURLConnection
import java.net.HttpURLConnection.HTTP_OK
import java.net.URL

/**
 * @author  by5388  on 2019/1/10.
 */
class MainModel : IMainModel {

    private val dbApi: DataBaseApi
    private val stringBuilder: StringBuilder
    private val tool: IStationTool
    private val preferences: IMySharedPreference
    private var defaultVersion: String = "0.0"
    private var version: String = ""

    companion object {
        private const val STATION_VERSION = "stationVersion";
        private const val TIME_OUT = 5000
        private const val SERVER_PATH = "https://kyfw.12306.cn/otn/leftTicket/init"
        private const val FILE_NAME = "/otn/resources/js/framework/station_name.js?station_version="

    }

    init {
        dbApi = DataBaseApiImpl()
        stringBuilder = StringBuilder()
        tool = StationTool()
        preferences = MySharedPreferencesImpl.getInstance()
    }


    override fun insertStationList(): Observable<Int> {
        println(stringBuilder.toString())
        println(stringBuilder.length)
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
        return Observable.create(ObservableOnSubscribe<Double> { emitter ->
            if (!emitter.isDisposed) {
                val url: URL = URL(SERVER_PATH)
                val connection: HttpURLConnection = url.openConnection() as HttpURLConnection
                connection.connectTimeout = TIME_OUT
                connection.requestMethod = "GET"
                if (HTTP_OK == connection.responseCode) {
                    val inputStream = connection.inputStream
                    val stationVersion = tool.getVersion(inputStream)
                    version = stationVersion
                    emitter.onNext(stationVersion.toDouble())
                } else {
                    emitter.onNext(currentVersion)
                }
                emitter.onComplete()
            }
        }).map(object : Function<Double, Boolean> {
            override fun apply(version: Double): Boolean {
                if (version > currentVersion) {
                    defaultVersion = version.toString()
                }
                return version > currentVersion
            }
        })

    }

    private fun getCurrentVersion(): Double {
        if (!preferences.contains(STATION_VERSION)) {
            preferences.put(STATION_VERSION, defaultVersion)
        } else {
            defaultVersion = preferences.get(STATION_VERSION, defaultVersion) as String
        }
        return defaultVersion.toDouble()
    }

    override fun getStationCount(): Observable<Int> {
        val serverPath = "https://kyfw.12306.cn"
        val stationListVersionPath = FILE_NAME + version
        return Observable.create { emitter ->
            val url = URL(serverPath + stationListVersionPath)
            val connection = url.openConnection() as HttpURLConnection
            connection.connectTimeout = TIME_OUT
            connection.requestMethod = "GET"
            if (HTTP_OK == connection.responseCode) {
                val inputStream = connection.inputStream
                val stationVersionName = tool.getStationList(inputStream)
                val count = tool.getStationCount(stationVersionName, stringBuilder)
                emitter.onNext(count)
            } else {
                emitter.onError(NetworkErrorException())
            }
            emitter.onComplete()
        }
    }

    override fun clearData(): Observable<Int> {
        return Observable.create { emitter ->
            emitter.onNext(dbApi.deleteStationList());
            emitter.onComplete()
        }
    }

    override fun finishUpdate() {
        preferences.put(STATION_VERSION, version)
    }
}