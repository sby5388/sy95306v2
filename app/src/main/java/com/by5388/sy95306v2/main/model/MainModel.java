package com.by5388.sy95306v2.main.model;

import android.util.Log;

import com.by5388.sy95306v2.database.DataBaseApiImpl;
import com.by5388.sy95306v2.database.IShenYangDbApi;
import com.by5388.sy95306v2.exception.NetworkException;
import com.by5388.sy95306v2.setting.ISettingSharedPreferences;
import com.by5388.sy95306v2.setting.SettingSharedPreferences;

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
public class MainModel implements IMainModel {
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
    private String mVersion = "";
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

    // TODO: 2019/11/26 不易读懂，需要拆分
    @Override
    public Observable<Integer> getStationCount() {

        Log.e(TAG, "getStationCount: ", new Exception());
        // TODO: 2019/9/10 网络不可用时，出现了闪退，所以应当检查网络，甚至注册网络监听者
        final String serverPath = "https://kyfw.12306.cn";
        final String stationListVersionPath = FILE_NAME + mVersion;
        return Observable.create(emitter -> {
            if (!emitter.isDisposed()) {
                final String spec = serverPath + stationListVersionPath;
                Log.d(TAG, "getStationCount: spec = " + spec);

                URL url = new URL(spec);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setConnectTimeout(TIME_OUT);
                connection.setRequestMethod("GET");
                try {
                    if (HTTP_OK == connection.getResponseCode()) {
                        InputStream inputStream = connection.getInputStream();
                        String stationVersionName = json.getCityList(inputStream);
                        int count = json.getStationCount(stationVersionName, stationListFile);
                        inputStream.close();
                        emitter.onNext(count);
                    } else {
                        emitter.onError(new NetworkException());
                    }
                } catch (Exception e) {
                    Log.e(TAG, "getStationCount: ", e);
                    emitter.onError(new NetworkException());
                }
                emitter.onComplete();
            }
        });
    }

    @Override
    public Observable<Boolean> isNeedUpdate() {
        boolean isEmpty = service.isEmpty();
        if (isEmpty) {
            Log.d(TAG, "isNeedUpdate: 本地为空");
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
//                    String stationVersion = json.mVersion(json.jsonToString(inputStream));
                    String stationVersion = json.getVersion(inputStream);
                    mVersion = stationVersion;
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
    public Observable<Integer> clearData() {
        return Observable.create(emitter -> {
            emitter.onNext(service.deleteAllStation());
            emitter.onComplete();
        });
    }

    @Override
    public void onFinishUpdate() {
        preferences.put(START_NAME_SHARED_PREFERENCES, defaultStationVersion);
    }

}
