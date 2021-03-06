package com.by5388.sy95306v2.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * @author by5388  on 2018/7/21.
 */

public class MyDataBaseHelper extends SQLiteOpenHelper {

    private final static String NAME = "12306.db";
    public static final String DATABASE_NAME = NAME;
    private final static int VERSION = 1;

    MyDataBaseHelper(Context context) {
        super(context, NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        CommonStationTable.createTableStation(db);
        CdStationTable.createTableStation(db);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
