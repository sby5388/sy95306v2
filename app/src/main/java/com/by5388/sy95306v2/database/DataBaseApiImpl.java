package com.by5388.sy95306v2.database;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.annotation.NonNull;

import com.by5388.sy95306v2.App;
import com.by5388.sy95306v2.module.chengdu.bean.screen.ScreenStation;
import com.by5388.sy95306v2.module.shenyang.bean.Station;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;

/**
 * @author by5388  on 2018/7/21.
 */

public final class DataBaseApiImpl implements IShenYangDbApi, IChengDuDbApi {
    public static final String TAG = "DataBaseApiImpl";
    private MyDataBaseHelper mHelper;
    private static final int SUCCESS = 1;
    private static final int FAIL = 0;
    private static ContentValues values;
    private final String[] columns = new String[]{CommonStationTable.TableStation.STATION_NAME, CommonStationTable.TableStation.NAME_UPPER};

    private DataBaseApiImpl() {
        //System.out.println("实例化数据库");
        mHelper = new MyDataBaseHelper(App.getInstance());
        values = new ContentValues();
    }

    private SQLiteDatabase getDb() {
        return mHelper.getWritableDatabase();
    }

    private void closeDb() {
        mHelper.getWritableDatabase().close();
    }


    public static DataBaseApiImpl getInstance() {
        return SingletonHolder.INSTANCE;
    }


    @Override
    public boolean cdStationIsEmpty() {
        boolean isEmpty = true;
        final SQLiteDatabase db = getDb();
        Cursor cursor = db.rawQuery("select count(*) from " + CdStationTable.TableStation.TABLE_NAME, null);
        if (cursor.moveToFirst()) {
            long result = cursor.getLong(0);
            if (result > 0) {
                isEmpty = false;
            }
        }
        cursor.close();
        closeDb();
        return isEmpty;
    }

    @Override
    public List<ScreenStation> getCdScreenStations() {
        List<ScreenStation> stationList = new ArrayList<>();
        final SQLiteDatabase db = getDb();
        Cursor cursor = db.rawQuery("select * from " + CdStationTable.TableStation.TABLE_NAME, null);
        while (cursor.moveToNext()) {
            ScreenStation station = getScreenStation(cursor);
            stationList.add(station);
        }
        cursor.close();
        closeDb();
        return stationList;
    }

    @Override
    public int deleteAllCdScreenStations() {
        String deleteSql = "delete from " + CdStationTable.TableStation.TABLE_NAME;
        final SQLiteDatabase db = getDb();
        db.execSQL(deleteSql);
        db.close();
        return 0;
    }

    @Override
    public void addCdScreenStation(ScreenStation station) {
        ContentValues values = getValues(station);
        final SQLiteDatabase db = getDb();
        db.insert(CdStationTable.TableStation.TABLE_NAME, null, values);
        db.close();
    }

    @NonNull
    private static Station getStation(Cursor cursor) {
        //只查询名字+电报码
        //车站名称在表格中为第1列，下标为0
        final int indexName = 0;
        //车站电报码在表格中为第2列，下标为1
        final int indexNameUpper = 1;
        Station station = new Station();
        station.setName(cursor.getString(indexName));
        station.setNameUpper(cursor.getString(indexNameUpper));
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

    private static ContentValues getValues(final Station station) {
        final ContentValues values = DataBaseApiImpl.values;
        // TODO: 2019/9/12 重新利用
        values.clear();
        values.put(CommonStationTable.TableStation.NAME_FIRST, station.getNameFirst());
        values.put(CommonStationTable.TableStation.STATION_NAME, station.getName());
        values.put(CommonStationTable.TableStation.NAME_UPPER, station.getNameUpper());
        values.put(CommonStationTable.TableStation.NAME_EN, station.getNameEn());
        values.put(CommonStationTable.TableStation.NAME_LOWER, station.getNameLower());
        values.put(CommonStationTable.TableStation.STATION_CODE, station.getCode());
        return values;
    }

    private static ContentValues getValues(ScreenStation station) {
        ContentValues values = DataBaseApiImpl.values;
        values.clear();
        values.put(CdStationTable.TableStation.PY, station.getPY());
        values.put(CdStationTable.TableStation.PY_SZM, station.getPYSZM());
        values.put(CdStationTable.TableStation.STATION_NAME, station.getZM());
        values.put(CdStationTable.TableStation.STATION_CODE, station.getZMLM());
        return values;
    }

    public List<Station> getStations() {
        final SQLiteDatabase db = getDb();
        List<Station> stations = new ArrayList<>();
        String selection = "";
        String[] selectionArgs = {};
        Cursor cursor = db.query(true, CommonStationTable.TableStation.TABLE_NAME, columns, selection, selectionArgs,
                null, null, null, null);
        if (cursor != null) {
            while (cursor.moveToNext()) {
                stations.add(getStation(cursor));
            }
            cursor.close();
        }
        closeDb();
        return stations;
    }

    @Override
    public int deleteAllStation() {
        final SQLiteDatabase db = getDb();
        final String deleteSql = "delete from station";
        db.execSQL(deleteSql);
        closeDb();
        return ACTION_DELETE_SUCCESS;
    }


    @Override
    public Station selectStationByNameUpper(String nameUpper) {
        Station station = new Station();
        final SQLiteDatabase db = getDb();
        String selection = CommonStationTable.TableStation.NAME_UPPER + " = ?";
        final String[] selectionArgs = new String[]{nameUpper};
        Cursor cursor = db.query(true, CommonStationTable.TableStation.TABLE_NAME, columns, selection, selectionArgs,
                null, null, null, null);
        if (cursor != null) {
            if (cursor.moveToFirst()) {
                station = getStation(cursor);
            }
            cursor.close();
        }
        db.close();
        return station;
    }

    @Override
    public Station selectStationByName(String stationName) {
        Station station = new Station();
        String selection = CommonStationTable.TableStation.STATION_NAME + " = ?";
        String[] selectionArgs = new String[]{stationName};
        final SQLiteDatabase db = getDb();
        Cursor cursor = db.query(true, CommonStationTable.TableStation.TABLE_NAME, columns, selection, selectionArgs,
                null, null, null, null);
        if (cursor != null) {
            if (cursor.moveToFirst()) {
                station = getStation(cursor);
            }
            cursor.close();
        }
        db.close();
        return station;
    }

    @Override
    public Observable<Integer> insertStationList(List<Station> stations) {
        final SQLiteDatabase db = getDb();
        return Observable.create(emitter -> {
            if (!emitter.isDisposed()) {
                int currentUpdateCount = 1;
                db.beginTransaction();
                for (Station station : stations) {
                    ContentValues values = getValues(station);
                    db.insert(CommonStationTable.TableStation.TABLE_NAME, null, values);
                    if (currentUpdateCount % 50 == 0) {
                        emitter.onNext(currentUpdateCount);
                    }
                    currentUpdateCount++;
                }
                db.setTransactionSuccessful();
                db.endTransaction();
                emitter.onNext(currentUpdateCount);
                closeDb();
                emitter.onComplete();
            }
        });

    }


    @Override
    public List<Station> selectStationList(String key) {
        List<Station> stationList = new ArrayList<>();

        String selection = CommonStationTable.TableStation.NAME_LOWER + " like ? or " +
                CommonStationTable.TableStation.NAME_FIRST + " like ? or " +
                CommonStationTable.TableStation.NAME_EN + " like ? or " +
                CommonStationTable.TableStation.STATION_NAME + " like ? ";
        final String[] selectionArgs = new String[]{"%" + key + "%", "%" + key + "%", "%" + key + "%", "%" + key + "%"};
        final SQLiteDatabase db = getDb();
        Cursor cursor = db.query(true, CommonStationTable.TableStation.TABLE_NAME, columns, selection, selectionArgs,
                null, null, null, null);
        if (cursor != null) {
            while (cursor.moveToNext()) {
                stationList.add(getStation(cursor));
            }
            cursor.close();
        }
        closeDb();
        return stationList;
    }

    private static class SingletonHolder {
        private static final DataBaseApiImpl INSTANCE = new DataBaseApiImpl();
    }

    @Override
    public synchronized boolean isEmpty() {
        boolean empty = true;
        final SQLiteDatabase db = getDb();
        Cursor cursor = db.rawQuery("select * from " + CommonStationTable.TableStation.TABLE_NAME, null);
        if (cursor != null) {
            cursor.moveToFirst();
            if (cursor.getCount() > 0) {
                empty = false;
            }
            cursor.close();
        }
        closeDb();
        return empty;
    }
}
