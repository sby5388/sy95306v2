package com.by5388.sy95306.kotlin.database.api

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

/**
 * @author  by5388  on 2019/1/8.
 */
class TrainDataBaseHelper(context: Context) : SQLiteOpenHelper(context, NAME, null, VERSION) {
    companion object {
        private const val VERSION = 1
        private const val NAME = "train.db"
    }

    override fun onCreate(db: SQLiteDatabase?) {
        val createSql = StationTable.getCreateSql()
        db!!.execSQL(createSql)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        //
    }
}