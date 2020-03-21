package com.by5388.sy95306v2.database;

import android.database.sqlite.SQLiteDatabase;

import androidx.annotation.NonNull;

/**
 * @author by5388  on 2018/7/21.
 */

public final class CommonStationTable {
    public static final String SQL_CREATE_TABLE
            = "create table " + " if not exists " + TableStation.TABLE_NAME + "("
            + TableStation.ID + "  integer primary key autoincrement,"
            + TableStation.NAME_FIRST + ","
            + TableStation.STATION_NAME + " ,"
            + TableStation.NAME_UPPER + ","
            + TableStation.NAME_EN + ","
            + TableStation.NAME_LOWER + ","
            + TableStation.STATION_CODE
            + ");";

    static void createTableStation(@NonNull SQLiteDatabase db) {
        try {
            db.execSQL(SQL_CREATE_TABLE);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public interface TableStation {
        String TABLE_NAME = "station";
        String ID = "id";
        String NAME_FIRST = "nameFirst";
        String STATION_NAME = "name";
        String NAME_UPPER = "nameUpper";
        String NAME_EN = "nameEn";
        String NAME_LOWER = "nameLower";
        String STATION_CODE = "code";
    }
}
