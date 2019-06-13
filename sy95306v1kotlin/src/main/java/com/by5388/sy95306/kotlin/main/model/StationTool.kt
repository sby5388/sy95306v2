package com.by5388.sy95306.kotlin.main.model

import com.by5388.sy95306.kotlin.bean.Station
import java.io.BufferedReader
import java.io.InputStream
import java.io.InputStreamReader

/**
 * @author  by5388  on 2019/1/10.
 */
class StationTool : IStationTool {


    companion object {
        private const val FILE_NAME = "/otn/resources/js/framework/station_name.js?station_version="
    }

    override fun getStationList(inputStream: InputStream): String {
        val stringBuilder = StringBuilder()
        try {
            val inputReader = InputStreamReader(inputStream, "utf8")
            val bufReader = BufferedReader(inputReader)
            var line: String?
            do {
                line = bufReader.readLine()
                stringBuilder.append(line)
            } while (line != null)
        } catch (e: Exception) {
            return ""
        }
        return stringBuilder.toString()
    }

    // TODO: date这里需要重新测试
    override fun getStationList(stationText: String): List<Station> {
        println("获取车站")

        val stationParam = 6
        val stationList: MutableList<Station> = ArrayList()
        val stationItems: List<String> = stationText.split("@")
        println("车站数量 = ${stationItems.size}")
        for (item in stationItems) {
            val params = item.split("\\|".toRegex()).dropLastWhile({ it.isEmpty() }).toTypedArray()
            if (stationParam == params.size) {
                stationList.add(Station(
                        -1,
                        params[0],
                        params[1],
                        params[2],
                        params[3],
                        params[4],
                        params[5].toInt()))
            }
        }
        println(stationList.size)
        return stationList
    }

    override fun getVersion(inputStream: InputStream): String {
        val text = getTextLineWithVersion(inputStream)
        val parts = text!!.split(" ")
        if (parts.size >= 3) {
            val part2 = parts[2]
            val part2s = part2.split("\"")
            if (part2s.size > 1) {
                val part3 = part2s[1]
                val part3s = part3.split("=")
                if (part3s.size > 1) {
                    return part3s[1]
                }
            }
        }
        return text
    }


    private fun getTextLineWithVersion(inputStream: InputStream): String? {
        val reader = InputStreamReader(inputStream)
        val bufferedReader = BufferedReader(reader)
        var temp: String?
        try {
            do {
                temp = bufferedReader.readLine()
                if (temp!!.contains(FILE_NAME)) {
                    break
                }
            } while (temp != null)
            bufferedReader.close()
            reader.close()
        } catch (e: Exception) {
            temp = null
        }
        return temp
    }

    override fun getStationCount(stationList: String, stationListFile: StringBuilder): Int {
        var stationListFileTemp = ""
        val minLength = 2
        val newList = stationList.split("'")
        if (newList.size > minLength) {
            stationListFileTemp = newList[1]
            stationListFile.delete(0, stationListFile.length)
            stationListFile.append(stationListFileTemp)
        }
        val stationNames = stationListFileTemp.split("@")
        return stationNames.size

    }


}