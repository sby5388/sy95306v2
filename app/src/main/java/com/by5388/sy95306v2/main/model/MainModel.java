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
     * 当前的版本号：20180730时网站上的最新版
     */
    private static String defaultStationVersion = "0.0";
    /**
     * 车站列表字符串
     */
    private final StringBuilder mStationListStringBuilder;
    private final String URL_PATH = "https://kyfw.12306.cn/otn/leftTicket/init";
    private final ISettingSharedPreferences mSharedPreferences;
    private final IShenYangDbApi mService;
    private final IStationJson mJson;
    private String mVersion = "";

    public MainModel() {
        mSharedPreferences = SettingSharedPreferences.getInstance();
        mService = DataBaseApiImpl.getInstance();
        mJson = new StationJson();
        mStationListStringBuilder = new StringBuilder();
    }


    @Override
    public Observable<Integer> insertData() {
        return mService.insertStationList(mJson.getWebPageContent(mStationListStringBuilder.toString()));
    }

    // TODO: 2019/11/26 不易读懂，需要拆分
    @Override
    public Observable<Integer> getStationCount() {
        // TODO: 2019/9/10 网络不可用时，出现了闪退，所以应当检查网络，甚至注册网络监听者
        final String serverPath = "https://kyfw.12306.cn";
        final String stationListVersionPath = FILE_NAME + mVersion;
        return Observable.create(emitter -> {
            if (!emitter.isDisposed()) {
                final String spec = serverPath + stationListVersionPath;
                URL url = new URL(spec);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setConnectTimeout(TIME_OUT);
                connection.setRequestMethod("GET");
                try {
                    if (HTTP_OK == connection.getResponseCode()) {
                        final InputStream inputStream = connection.getInputStream();
                        final String stationVersionName = mJson.getWebPageContent(inputStream);
                        final int count = mJson.getStationCount(stationVersionName, mStationListStringBuilder);
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
        boolean isEmpty = mService.isEmpty();
        if (isEmpty) {
            return Observable.just(true);
        }
        final double currentVersion = getCurrentVersion();
        Log.d(TAG, "isNeedUpdate: 当前版本：" + currentVersion);
        return Observable.create((ObservableOnSubscribe<Double>) emitter -> {
            if (!emitter.isDisposed()) {
                final URL url = new URL(URL_PATH);
                final HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setConnectTimeout(TIME_OUT);
                connection.setRequestMethod("GET");
                if (HTTP_OK == connection.getResponseCode()) {
                    final InputStream inputStream = connection.getInputStream();
                    String stationVersion = mJson.getVersion(inputStream);
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
        if (!mSharedPreferences.contains(START_NAME_SHARED_PREFERENCES)) {
            mSharedPreferences.put(START_NAME_SHARED_PREFERENCES, defaultStationVersion);
        } else {
            defaultStationVersion = (String) mSharedPreferences.getSharedPreference(START_NAME_SHARED_PREFERENCES, defaultStationVersion);
        }
        return Double.parseDouble(defaultStationVersion);
    }

    @Override
    public Observable<Integer> clearData() {
        return Observable.create(emitter -> {
            emitter.onNext(mService.deleteAllStation());
            emitter.onComplete();
        });
    }

    @Override
    public void onFinishUpdate() {
        mSharedPreferences.put(START_NAME_SHARED_PREFERENCES, defaultStationVersion);
    }

}
