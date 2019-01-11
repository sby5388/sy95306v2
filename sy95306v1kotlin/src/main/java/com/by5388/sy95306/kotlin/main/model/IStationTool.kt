package com.by5388.sy95306.kotlin.main.model

import com.by5388.sy95306.kotlin.bean.Station
import java.io.InputStream

/**
 * json2Station
 * run on work thread
 * 不能在主线程上运行
 * @author  by5388  on 2019/1/10.
 */
interface IStationTool {
    fun getStationCount(stationList: String, stationListFile: StringBuilder): Int
    fun getTextLineWithVersion(inputStream: InputStream): String?
    fun getVersion(text: String): String
    fun getStationList(stationText: String): List<Station>
    fun getStationList(inputStream: InputStream): String
}