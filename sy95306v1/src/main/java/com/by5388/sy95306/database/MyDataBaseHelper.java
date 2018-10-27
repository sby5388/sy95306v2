package com.by5388.sy95306.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.by5388.sy95306.database.StationTable;

/**
 * @author by5388  on 2018/7/21.
 */

class MyDataBaseHelper extends SQLiteOpenHelper {
    private final static String NAME = "sy95306.db";
    private final static int VERSION = 1;

    MyDataBaseHelper(Context context) {
        super(context, NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        StationTable.createTableStation(db);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
