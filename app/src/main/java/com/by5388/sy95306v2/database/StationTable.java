package com.by5388.sy95306v2.database;

import android.database.sqlite.SQLiteDatabase;
import android.support.annotation.NonNull;

/**
 * @author by5388  on 2018/7/21.
 */

final class StationTable {

    static void createTableStation(@NonNull SQLiteDatabase db) {
        String string = "create table " + TableStation.TABLE_NAME + "("
                + TableStation.ID + "  integer primary key autoincrement,"
                + TableStation.NAME_FIRST + ","
                + TableStation.STATION_NAME + " ,"
                + TableStation.NAME_UPPER + ","
                + TableStation.NAME_EN + ","
                + TableStation.NAME_LOWER + ","
                + TableStation.STATION_CODE
                + ");";
        db.execSQL(string);
    }

    static class TableStation {
        static final String TABLE_NAME = "station";
        static final String ID = "id";
        static final String NAME_FIRST = "nameFirst";
        static final String STATION_NAME = "name";
        static final String NAME_UPPER = "nameUpper";
        static final String NAME_EN = "nameEn";
        static final String NAME_LOWER = "nameLower";
        static final String STATION_CODE = "code";
    }
}
