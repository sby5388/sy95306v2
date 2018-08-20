package com.by5388.sy95306v2.database;

import android.database.sqlite.SQLiteDatabase;
import android.support.annotation.NonNull;

/**
 * @author by5388  on 2018/8/20.
 */
final class CdStationTable {


    static void createTableStation(@NonNull SQLiteDatabase db) {
        String string = "create table " + CdStationTable.TableStation.TABLE_NAME + "("
                + CdStationTable.TableStation.ID + "  integer primary key autoincrement,"
                + CdStationTable.TableStation.PY + ","
                + CdStationTable.TableStation.PY_SZM + ","
                + CdStationTable.TableStation.STATION_NAME + " ,"
                + CdStationTable.TableStation.STATION_CODE
                + ");";
        db.execSQL(string);
    }

    /**
     * PY : ASX         拼音首字母
     * PY_SZM : A        首字母
     * STATION_NAME : 安顺西      站名
     * STATION_CODE : ASE       电报码
     */
    static class TableStation {
        /**
         * 表名
         */
        static final String TABLE_NAME = "cdStation";
        static final String ID = "id";
        /**
         * 拼音首字母
         */
        static final String PY = "py";
        /**
         * 站名第一个字首字母
         */
        static final String PY_SZM = "pyszm";
        /**
         * 站名
         */
        static final String STATION_NAME = "stationName";
        /**
         * 电报码
         */
        static final String STATION_CODE = "code";
    }
}
