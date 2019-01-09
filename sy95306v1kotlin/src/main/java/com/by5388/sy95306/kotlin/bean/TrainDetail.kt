package com.by5388.sy95306.kotlin.bean

import com.google.gson.annotations.SerializedName

/**
 * @author  by5388  on 2019/1/5.
 */
data class TrainDetail(@SerializedName("STNO") val number: String,
                       @SerializedName("ATIME") val arriveTime: String,
                       @SerializedName("SNAME") val stationName: String,
                       @SerializedName("STIME")  val leaveTime: String,
                       @SerializedName("STCODE")   val trainCode: String
)