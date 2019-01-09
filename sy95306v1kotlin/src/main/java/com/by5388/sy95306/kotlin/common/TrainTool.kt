package com.by5388.sy95306.kotlin.common

import com.by5388.sy95306.kotlin.bean.TrainNumber

/**
 * 一些静态方法
 * @author  by5388  on 2019/1/7.
 */
fun getTime(time: String): String {
    if (time.length < 4) {
        return time
    }
    return time.substring(0, 2) + ":" + time.substring(2, 4)
}

fun getSpendTime(spendTime: String): String {
    try {
        var time = spendTime.toInt()
        val stringBuilder = java.lang.StringBuilder()
        if (time >= 1440) {
            stringBuilder.append(time / 1440).append("天")
            time %= 1440
        }
        if (time >= 60) {
            stringBuilder.append(time / 60).append("小时")
            time %= 60
        }
        stringBuilder.append(time).append("分")
        return stringBuilder.toString()
    } catch (e: NumberFormatException) {
        return spendTime
    }
}


fun getDescription(trainNumber: TrainNumber) = "${getTrainNumberClass(trainNumber.trainType)} ${getAirConditionStatus(trainNumber.airCondition)}"

fun getTrainNumberClass(cls: String): String {
    return when (cls) {
        "0" -> "快速"
        "1" -> "直特"
        "2" -> "特快"
        "3" -> "普快"
        "4" -> "普客"
        "5" -> "市郊"
        "6" -> "混合"
        "7" -> "准高"
        "8" -> "高速"
        "9" -> "快慢"
        "D" -> "动车"
        else -> ""
    }
}

fun getAirConditionStatus(ac: String): String {
    return when (ac) {
        "1" -> "空调"
        else -> " 无空调"
    }
}