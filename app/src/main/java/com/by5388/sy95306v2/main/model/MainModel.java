package com.by5388.sy95306v2.main.model;

import android.accounts.NetworkErrorException;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;

import com.by5388.sy95306v2.App;
import com.by5388.sy95306v2.database.DataBaseApiImpl;
import com.by5388.sy95306v2.database.IShenYangDbApi;
import com.by5388.sy95306v2.setting.ISettingSharedPreferences;
import com.by5388.sy95306v2.setting.SettingSharedPreferences;
import com.by5388.sy95306v2.start.model.IStartModel;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import io.reactivex.Observable;
import io.reactivex.ObservableOnSubscribe;

import static java.net.HttpURLConnection.HTTP_OK;

/**
 * 必须在IO线程上
 *
 * @author by5388  on 2019/1/2.
 */
public class MainModel implements IStartModel, IMainModel {
    private final static String TAG = "MainModel";
    /**
     * 12306购票页面，包含了车站的信息
     * -->TODO 页面应当替换成12306手机版的
     */
    private final static String BUY_TICKET_PAGE = "https://kyfw.12306.cn/otn/leftTicket/init";
    private final static String FILE_NAME = "/otn/resources/js/framework/station_name.js?station_version=";

    private final static String START_NAME_SHARED_PREFERENCES = "stationVersion";
    private final static int TIME_OUT = 5000;
    /**
     * 车站列表字符串
     */
    private final StringBuilder stationListFile;
    private final String path = "https://kyfw.12306.cn/otn/leftTicket/init";
    /**
     * 当前的版本号：20180730时网站上的最新版
     */
    private static String defaultStationVersion = "0.0";
    private String getVersion = "";
    private final ISettingSharedPreferences preferences;
    private final IShenYangDbApi service;
    private final IStationJson json;

    public MainModel() {
        preferences = SettingSharedPreferences.getInstance();
        service = DataBaseApiImpl.getInstance();
        json = new StationJson();
        stationListFile = new StringBuilder();
    }


    @Override
    public Observable<Integer> insertData() {
        return service.insertStationList(json.getCityList(stationListFile.toString()));
    }

    @Override
    public Observable<Integer> getStationCount() {
        final String serverPath = "https://kyfw.12306.cn";
        final String stationListVersionPath = FILE_NAME + getVersion;
        return Observable.create(emitter -> {
            if (!emitter.isDisposed()) {
                URL url = new URL(serverPath + stationListVersionPath);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setConnectTimeout(TIME_OUT);
                connection.setRequestMethod("GET");
                if (HTTP_OK == connection.getResponseCode()) {
                    InputStream inputStream = connection.getInputStream();
                    String stationVersionName = json.getCityList(inputStream);
                    int count = json.getStationCount(stationVersionName, stationListFile);
                    emitter.onNext(count);
                } else {
                    emitter.onError(new NetworkErrorException());
                }
                emitter.onComplete();
            }
        });
    }

    @Override
    public Observable<Boolean> isNeedUpdate() {
        boolean isEmpty = service.isEmpty();
        if (isEmpty) {
            return Observable.just(true);
        }
        double currentVersion = getCurrentVersion();
        Log.d(TAG, "isNeedUpdate: 当前版本：" + currentVersion);
        return Observable.create((ObservableOnSubscribe<Double>) emitter -> {
            if (!emitter.isDisposed()) {
                URL url = new URL(path);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setConnectTimeout(TIME_OUT);
                connection.setRequestMethod("GET");
                if (HTTP_OK == connection.getResponseCode()) {
                    InputStream inputStream = connection.getInputStream();
//                    String stationVersion = json.getVersion(json.jsonToString(inputStream));
                    String stationVersion = json.getVersion(inputStream);
                    getVersion = stationVersion;
                    emitter.onNext(Double.parseDouble(stationVersion));
                } else {
                    emitter.onNext(currentVersion);
                }
                emitter.onComplete();
            }
        }).map(aDouble -> {
            Log.d(TAG, "apply: 新的版本：" + aDouble);
            if (aDouble > currentVersion) {
                defaultStationVersion = String.valueOf(aDouble);
            }
            return aDouble > currentVersion;
        });


    }

    /**
     * 当前记录的版本
     *
     * @return 版本号
     */
    private double getCurrentVersion() {
        if (!preferences.contains(START_NAME_SHARED_PREFERENCES)) {
            preferences.put(START_NAME_SHARED_PREFERENCES, defaultStationVersion);
        } else {
            defaultStationVersion = (String) preferences.getSharedPreference(START_NAME_SHARED_PREFERENCES, defaultStationVersion);
        }
        return Double.parseDouble(defaultStationVersion);
    }

    @Override
    public boolean getNetStatus() {
        ConnectivityManager manager = (ConnectivityManager) App.getInstance().getSystemService(Context.CONNECTIVITY_SERVICE);
        if (null == manager) {
            return false;
        }
        NetworkInfo info = manager.getActiveNetworkInfo();
        return null != info && info.isAvailable();
    }

    @Override
    public Observable<Integer> clearData() {
        return Observable.create(emitter -> {
            emitter.onNext(service.deleteAllStation());
            emitter.onComplete();
        });
    }

    @Override
    public void finishUpdate() {
        preferences.put(START_NAME_SHARED_PREFERENCES, defaultStationVersion);
    }

    @Override
    public boolean getNetWordCanUse() {
        return getNetStatus();
    }
}
