package com.by5388.sy95306.kotlin

import android.app.Application

/**
 * @author  by5388  on 2019/1/7.
 */
class App : Application() {


    override fun onCreate() {
        super.onCreate()
        app = this
    }

    companion object {
        private var app: App? = null
        @JvmStatic
        fun getInstance(): App? {
            return app
        }
    }

}