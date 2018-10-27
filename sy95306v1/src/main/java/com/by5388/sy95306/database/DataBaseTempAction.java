package com.by5388.sy95306.database;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.annotation.NonNull;

import com.by5388.sy95306.App;
import com.by5388.sy95306.StartActivity;
import com.by5388.sy95306.bean.Station;

import java.util.ArrayList;
import java.util.List;

/**
 * @author by5388  on 2018/7/21.
 */

public class DataBaseTempAction implements DataBaseImpl {
    private final SQLiteDatabase db;

    private DataBaseTempAction() {
        MyDataBaseHelper helper = new MyDataBaseHelper(App.getInstance());
        db = helper.getWritableDatabase();
    }

    public static DataBaseTempAction getInstance() {
        return SingletonHandler.INSTANCE;
    }


    @Override
    public int addStation(List<Station> stationList) {
        if (null == stationList || stationList.isEmpty()) {
            return StartActivity.FAIL;
        }
        for (Station station : stationList) {
            addStation(station);
        }
        return StartActivity.SUCCESS;
    }

    @Override
    public Station selectStationByNameUpper(String nameUpper) {
        Cursor cursor = db.rawQuery("select * from station where nameUpper = ?", new String[]{nameUpper});
        Station station = new Station();
        if (cursor.moveToFirst()) {
            station = getStation(cursor);
        }
        cursor.close();
        return station;
    }

    @Override
    public Station selectStationByName(String stationName) {
        Cursor cursor = db.rawQuery("select * from station where name = ?", new String[]{stationName});
        Station station = new Station();
        if (cursor.moveToFirst()) {
            station = getStation(cursor);
        }
        cursor.close();
        return station;
    }

    @Override
    public List<Station> selectStationListByNameLower(String nameLower) {
        final String colName = "nameLower";
        return getStationListByName(colName, nameLower);
    }

    @Override
    public List<Station> selectStationListByNameEn(String nameEn) {
        final String colName = "nameEn";
        return getStationListByName(colName, nameEn);
    }

    @Override
    public List<Station> selectStationListByName(String stationName) {
        final String colName = "name";
        return getStationListByName(colName, stationName);
    }

    @Override
    public List<Station> selectStationListByNameFirst(String nameFirst) {
        final String colName = "nameFirst";
        return getStationListByName(colName, nameFirst);
    }


    private void addStation(Station station) {
        ContentValues values = getValues(station);
        db.insert(StationTable.TableStation.TABLE_NAME, null, values);

    }

    private static class SingletonHandler {
        private static final DataBaseTempAction INSTANCE = new DataBaseTempAction();
    }

    @Override
    public boolean getStationStatus() {
        boolean isEmpty = true;
        Cursor cursor = db.rawQuery("select count(*) from " + StationTable.TableStation.TABLE_NAME, null);
        if (cursor.moveToFirst()) {
            long result = cursor.getLong(0);
            if (result > 0) {
                isEmpty = false;
            }
        }
        cursor.close();
        return isEmpty;
    }

    @NonNull
    private static Station getStation(Cursor cursor) {
        Station station = new Station();
        station.setId(cursor.getInt(cursor.getColumnIndex(StationTable.TableStation.ID)));
        station.setNameFirst(cursor.getString(cursor.getColumnIndex(StationTable.TableStation.NAME_FIRST)));
        station.setName(cursor.getString(cursor.getColumnIndex(StationTable.TableStation.STATION_NAME)));
        station.setNameEn(cursor.getString(cursor.getColumnIndex(StationTable.TableStation.NAME_EN)));
        station.setNameLower(cursor.getString(cursor.getColumnIndex(StationTable.TableStation.NAME_LOWER)));
        station.setNameUpper(cursor.getString(cursor.getColumnIndex(StationTable.TableStation.NAME_UPPER)));
        station.setCode(cursor.getInt(cursor.getColumnIndex(StationTable.TableStation.STATION_CODE)));
        return station;
    }

    private static ContentValues getValues(Station station) {
        ContentValues values = new ContentValues();
        values.put(StationTable.TableStation.NAME_FIRST, station.getNameFirst());
        values.put(StationTable.TableStation.STATION_NAME, station.getName());
        values.put(StationTable.TableStation.NAME_UPPER, station.getNameUpper());
        values.put(StationTable.TableStation.NAME_EN, station.getNameEn());
        values.put(StationTable.TableStation.NAME_LOWER, station.getNameLower());
        values.put(StationTable.TableStation.STATION_CODE, station.getCode());
        return values;
    }

    /**
     * @param colName     列名
     * @param stationName 参数名
     * @return 符合的车站
     */
    private List<Station> getStationListByName(String colName, String stationName) {
        List<Station> stationList = new ArrayList<>();
        Cursor cursor = db.rawQuery("select * from station where " + colName + " like'%" + stationName + "%'", null);
        while (cursor.moveToNext()) {
            Station station = getStation(cursor);
            stationList.add(station);
        }
        cursor.close();
        return stationList;
    }
}
