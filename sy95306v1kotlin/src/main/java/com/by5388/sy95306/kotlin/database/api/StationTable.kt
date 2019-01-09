package com.by5388.sy95306.kotlin.database.api

/**
 * @author  by5388  on 2019/1/8.
 */
class StationTable {

    object TableStation {
        const val TABLE_NAME = "station"
        const val ID = "id"
        const val NAME_FIRST = "nameFirst"
        const val STATION_NAME = "name"
        const val NAME_UPPER = "nameUpper"
        const val NAME_EN = "nameEn"
        const val NAME_LOWER = "nameLower"
        const val STATION_CODE = "code"
    }

    companion object {

        @JvmStatic
        fun getCreateSql() =
                ("create table " + TableStation.TABLE_NAME + "("
                        + TableStation.ID + "  integer primary key autoincrement,"
                        + TableStation.NAME_FIRST + ","
                        + TableStation.STATION_NAME + " ,"
                        + TableStation.NAME_UPPER + ","
                        + TableStation.NAME_EN + ","
                        + TableStation.NAME_LOWER + ","
                        + TableStation.STATION_CODE
                        + ");")
    }
}