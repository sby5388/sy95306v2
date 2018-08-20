package com.by5388.sy95306v2.database;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.annotation.NonNull;

import com.by5388.sy95306v2.App;
import com.by5388.sy95306v2.bean.Station;
import com.by5388.sy95306v2.bean.cd.screen.ScreenStation;

import java.util.ArrayList;
import java.util.List;

/**
 * @author by5388  on 2018/7/21.
 */

public class DataBaseTempAction implements IShenYangStationDb, ICdScreenStationDb {
    private static final int SUCCESS = 1;
    private static final int FAIL = 0;

    private SQLiteDatabase db;

    private DataBaseTempAction() {
        MyDataBaseHelper helper = new MyDataBaseHelper(App.getInstance());
        db = helper.getWritableDatabase();
    }

    public static DataBaseTempAction getInstance() {
        return SingletonHandler.instance;
    }


    @Override
    public boolean cdStationIsEmpty() {
        boolean isEmpty = true;
        Cursor cursor = db.rawQuery("select count(*) from " + CdStationTable.TableStation.TABLE_NAME, null);
        if (cursor.moveToFirst()) {
            long result = cursor.getLong(0);
            if (result > 0) {
                isEmpty = false;
            }
        }
        cursor.close();
        return isEmpty;
    }

    @Override
    public List<ScreenStation> getCdScreenStations() {
        List<ScreenStation> stationList = new ArrayList<>();
        Cursor cursor = db.rawQuery("select * from " + CdStationTable.TableStation.TABLE_NAME, null);
        while (cursor.moveToNext()) {
            ScreenStation station = getScreenStation(cursor);
            stationList.add(station);
        }
        cursor.close();
        return stationList;
    }

    @Override
    public int deleteAllCdScreenStations() {
        String deleteSql = "delete from " + CdStationTable.TableStation.TABLE_NAME;
        db.execSQL(deleteSql);
        return 0;
    }

    @Override
    public void addCdScreenStation(ScreenStation station) {
        ContentValues values = getValues(station);
        db.insert(CdStationTable.TableStation.TABLE_NAME, null, values);
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

    @NonNull
    private static ScreenStation getScreenStation(Cursor cursor) {
        ScreenStation station = new ScreenStation();
        station.setPY(cursor.getString(cursor.getColumnIndex(CdStationTable.TableStation.PY)));
        station.setPYSZM(cursor.getString(cursor.getColumnIndex(CdStationTable.TableStation.PY_SZM)));
        station.setZM(cursor.getString(cursor.getColumnIndex(CdStationTable.TableStation.STATION_NAME)));
        station.setZMLM(cursor.getString(cursor.getColumnIndex(CdStationTable.TableStation.STATION_CODE)));
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

    private static ContentValues getValues(ScreenStation station) {
        ContentValues values = new ContentValues();
        values.put(CdStationTable.TableStation.PY, station.getPY());
        values.put(CdStationTable.TableStation.PY_SZM, station.getPYSZM());
        values.put(CdStationTable.TableStation.STATION_NAME, station.getZM());
        values.put(CdStationTable.TableStation.STATION_CODE, station.getZMLM());
        return values;
    }

    public List<Station> getStations() {
        List<Station> stations = new ArrayList<>();
        String sql = "select * from " + StationTable.TableStation.TABLE_NAME;
        Cursor cursor = db.rawQuery(sql, null);
        while (cursor.moveToNext()) {
            stations.add(getStation(cursor));
        }
        cursor.close();
        return stations;
    }

    @Override
    public int deleteAllStation() {
        String deleteSql = "delete from station";
        db.execSQL(deleteSql);
        return 0;
    }


    @Override
    public Station selectStationByNameUpper(String nameUpper) {
        final String colName = StationTable.TableStation.NAME_UPPER;
        return getStationByList(colName, nameUpper);
    }

    private Station getStationByList(String colName, String stationName) {
        Cursor cursor = db.rawQuery("select * from station where " + colName + "  = ?", new String[]{stationName});
        Station station = new Station();
        if (cursor.moveToFirst()) {
            station = getStation(cursor);
        }
        cursor.close();
        return station;
    }

    @Override
    public Station selectStationByName(String stationName) {
        final String colName = StationTable.TableStation.STATION_NAME;
        return getStationByList(colName, stationName);
    }

    @Override
    public List<Station> selectStationListByNameLower(String nameLower) {
        final String colName = StationTable.TableStation.NAME_LOWER;
        return getStationListByName(colName, nameLower);
    }

    @Override
    public List<Station> selectStationListByNameEn(String nameEn) {
        final String colName = StationTable.TableStation.NAME_EN;
        return getStationListByName(colName, nameEn);
    }

    @Override
    public List<Station> selectStationListByName(String stationName) {
        final String colName = StationTable.TableStation.STATION_NAME;
        return getStationListByName(colName, stationName);
    }

    @Override
    public List<Station> selectStationListByNameFirst(String nameFirst) {
        final String colName = StationTable.TableStation.NAME_FIRST;
        return getStationListByName(colName, nameFirst);
    }

    @Override
    public void addStation(Station station) {
        ContentValues values = getValues(station);
        db.insert(StationTable.TableStation.TABLE_NAME, null, values);
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

    private static class SingletonHandler {
        private static DataBaseTempAction instance = new DataBaseTempAction();
    }
}
