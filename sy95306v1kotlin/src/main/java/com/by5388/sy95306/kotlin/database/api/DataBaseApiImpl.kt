package com.by5388.sy95306.kotlin.database.api

import android.content.ContentValues
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.by5388.sy95306.kotlin.App
import com.by5388.sy95306.kotlin.bean.Station
import com.by5388.sy95306.kotlin.database.api.StationTable.TableStation.ID
import com.by5388.sy95306.kotlin.database.api.StationTable.TableStation.NAME_EN
import com.by5388.sy95306.kotlin.database.api.StationTable.TableStation.NAME_FIRST
import com.by5388.sy95306.kotlin.database.api.StationTable.TableStation.NAME_LOWER
import com.by5388.sy95306.kotlin.database.api.StationTable.TableStation.NAME_UPPER
import com.by5388.sy95306.kotlin.database.api.StationTable.TableStation.STATION_CODE
import com.by5388.sy95306.kotlin.database.api.StationTable.TableStation.STATION_NAME
import com.by5388.sy95306.kotlin.database.api.StationTable.TableStation.TABLE_NAME
import io.reactivex.Observable

/**
 * 单例
 * @author  by5388  on 2019/1/7.
 */
class DataBaseApiImpl : DataBaseApi {

    private val values = ContentValues()
    private val helper: SQLiteOpenHelper = TrainDataBaseHelper(App.getInstance()!!)
    private val db: SQLiteDatabase = helper.writableDatabase
    override fun getStationList(): List<Station> {
        val stationList: MutableList<Station> = ArrayList()
        val cursor = db.query(TABLE_NAME, null, null, null, null, null, null)
        if (cursor != null) {
            cursor.moveToFirst()
            while (cursor.moveToNext()) {
                stationList.add(getStation(cursor))
            }
            cursor.close()
        }
        return stationList
    }


    override fun deleteStationList() = db.delete(TABLE_NAME, null, emptyArray<String>())

    override fun getStationByName(name: String): Station {
        val selection = "$STATION_NAME = ?"
        val selectionArgs = arrayOf(name)
        val cursor = db.query(TABLE_NAME, null, selection, selectionArgs, null, null, null)
        var station =  Station(-1, "", "", "", "", "", -1)
        if (cursor != null) {
            cursor.moveToFirst()
            station = getStation(cursor)
            cursor.close()
        }
        return station
    }

    override fun getStationByKeyWord(keyWord: String): List<Station> {
        val stationList: MutableList<Station> = ArrayList();
        val selection = "$STATION_NAME like ? or $NAME_LOWER like ? or $NAME_EN like ? or $NAME_UPPER like ? "
        val selectionArgs = arrayOf("%$keyWord%", "%$keyWord%", "%$keyWord%", "%$keyWord%")
        val cursor = db.query(true, TABLE_NAME, null, selection, selectionArgs, null, null, null, null)
        if (cursor != null) {
            cursor.moveToFirst()
            while (cursor.moveToNext()) {
                stationList.add(getStation(cursor))
            }
            cursor.close()
        }
        return stationList
    }

    override fun insertStationList(stationList: List<Station>): Observable<Int> {
        return Observable.create { emitter ->
            if (!emitter.isDisposed) {
                try {
                    var position = 1
                    db.beginTransaction()
                    for (station in stationList) {
                        val contentValues = getContentValues(station)
                        db.insert(TABLE_NAME, null, contentValues)
                        if (position % 50 == 0) {
                            emitter.onNext(position)
                        }
                        position++
                    }
                    db.setTransactionSuccessful()
                    db.endTransaction()
                    emitter.onNext(position)
                    emitter.onComplete()
                } catch (e: Exception) {
                    emitter.onError(e)
                }
            }
        }

    }

    private fun getStation(cursor: Cursor): Station {
        return Station(
                cursor.getInt(cursor.getColumnIndex(ID)),
                cursor.getString(cursor.getColumnIndex(NAME_FIRST)),
                cursor.getString(cursor.getColumnIndex(STATION_NAME)),
                cursor.getString(cursor.getColumnIndex(NAME_UPPER)),
                cursor.getString(cursor.getColumnIndex(NAME_EN)),
                cursor.getString(cursor.getColumnIndex(NAME_LOWER)),
                cursor.getInt(cursor.getColumnIndex(STATION_CODE)))
    }

    private fun getContentValues(station: Station): ContentValues {
        val values = values
        values.clear()
        values.put(NAME_FIRST, station.nameFirst)
        values.put(STATION_NAME, station.name)
        values.put(NAME_UPPER, station.nameUpper)
        values.put(NAME_EN, station.nameEn)
        values.put(NAME_LOWER, station.nameLower)
        values.put(STATION_CODE, station.code)
        return values
    }

}