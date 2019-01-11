package com.by5388.sy95306.kotlin.database.api

/**
 * @author  by5388  on 2019/1/11.
 */
interface IMySharedPreference {
    fun put(key: String, obj: Any)
    fun get(key: String, obj: Any): Any
}