package com.by5388.sy95306.kotlin.database.api

import android.content.Context
import android.content.SharedPreferences
import com.by5388.sy95306.kotlin.App

/**
 * // TODO: date 单例模式，待实现
 * @author  by5388  on 2019/1/11.
 */
class MySharedPreferencesImpl private constructor() : IMySharedPreference {
    private val sharedPreferences: SharedPreferences

    init {
        sharedPreferences = App.getInstance()!!.getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE)
    }


    override fun put(key: String, obj: Any) {
        val editor = sharedPreferences.edit()
        if (obj is String) {
            editor.putString(key, obj)
        } else if (obj is Int) {
            editor.putInt(key, obj)
        } else if (obj is Boolean) {
            editor.putBoolean(key, obj)
        } else if (obj is Float) {
            editor.putFloat(key, obj)
        } else if (obj is Long) {
            editor.putLong(key, obj)
        } else {
            editor.putString(key, obj.toString())
        }
        editor.apply()
    }

    override fun get(key: String, obj: Any): Any {
        return if (obj is String) {
            sharedPreferences.getString(key, obj)
        } else if (obj is Int) {
            sharedPreferences.getInt(key, obj)
        } else if (obj is Boolean) {
            sharedPreferences.getBoolean(key, obj)
        } else if (obj is Float) {
            sharedPreferences.getFloat(key, obj)
        } else if (obj is Long) {
            sharedPreferences.getLong(key, obj)
        } else {
            sharedPreferences.getString(key, null)
        }
    }

    override fun contains(key: String): Boolean {
        return sharedPreferences.contains(key)
    }

    companion object {
        private const val FILE_NAME = "stationVersion"
        @JvmStatic
        fun getInstance(): IMySharedPreference = MySharedPreferencesImpl()
    }
}