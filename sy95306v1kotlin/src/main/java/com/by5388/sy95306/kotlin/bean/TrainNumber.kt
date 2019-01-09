package com.by5388.sy95306.kotlin.bean

import com.google.gson.annotations.SerializedName

/**
 * @author  by5388  on 2019/1/5.
 */
data class TrainNumber(
        /**
         * 跨日期的天数，一天为内0
         */
        @SerializedName("DAYD")
        val days: String,
        @SerializedName("STIME")
        val startTime: String,
        @SerializedName("TRNO")
        val trainNumberLong: String,
        @SerializedName("AC")
        val airCondition: String,
        /**
         * 全程历时
         */
        @SerializedName("LISHI")
        val spendTime: String,
        @SerializedName("STCODE")
        val trainCode: String,

        @SerializedName("ATIME")
        val arriveTime: String,
        @SerializedName("TCCODE")
        val trainType: String,
        /**
         * 始发
         */
        @SerializedName("SST")
        val shiFa: String,
        /**
         * 终到
         */
        @SerializedName("EST")
        val zhongDao: String,
        /**
         * 上车
         */
        @SerializedName("FST")
        val shangChe: String,
        /**
         * 下车
         */
        @SerializedName("TST")
        val xiaChe: String,
        /**
         * 五种席位的价格
         */
        val PRI1: String,
        val PRI2: String,
        val PRI3: String,
        val PRI4: String,
        val PRI5: String,
        /**
         * 座席名称
         */
        val DESPRI1: String,
        val DESPRI2: String,
        val DESPRI3: String,
        val DESPRI4: String,
        val DESPRI5: String,
        /**
         * 余票数量：沈阳铁路局相关的车次才可能有数据
         */
        val TIC1: String,
        val TIC2: String,
        val TIC3: String,
        val TIC4: String,
        /**
         * 作用不明
         */
        val DESTIC1: String,
        val DESTIC2: String,
        val DESTIC3: String,
        val DESTIC4: String

        )