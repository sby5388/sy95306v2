package com.by5388.sy95306v2.activity.start.model;

import android.accounts.NetworkErrorException;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;

import com.by5388.sy95306v2.App;
import com.by5388.sy95306v2.bean.Station;
import com.by5388.sy95306v2.database.IShenYangStationDb;
import com.by5388.sy95306v2.database.DataBaseTempAction;
import com.by5388.sy95306v2.setting.SettingSharedPreferences;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.functions.Function;

/**
 * @author by5388  on 2018/7/30.
 */

public class StartModel implements IStartModel {
    private final static String TAG = "StartModel";
    /**
     * 12306购票页面，包含了车站的信息
     */
    private final static String BUY_TICKET_PAGE = "https://kyfw.12306.cn/otn/leftTicket/init";
    private final static String FILE_NAME = "/otn/resources/js/framework/station_name.js?station_version=";

    private final static String START_NAME_SHARED_PREFERENCES = "stationVersion";
    private final static int TIME_OUT = 5000;
    private static int currentUpdateCount = 1;
    /**
     * 车站列表字符串
     */
    private static String stationListFile = "";
    private final String path = "https://kyfw.12306.cn/otn/leftTicket/init";
    private final int successCode = 200;
    /**
     * 当前的版本号：20180730时网站上的最新版
     */
    private static String defaultStationVersion = "0.0";
    private String getVersion = "";
    private final SettingSharedPreferences preferences;
    private final IShenYangStationDb service;


    public StartModel() {
        preferences = SettingSharedPreferences.getInstance();
        service = DataBaseTempAction.getInstance();
        currentUpdateCount = 1;
    }

    private static int getStationCount2(String stationList) {
        String[] newList = stationList.split("'");
        if (newList.length > 2) {
            stationListFile = newList[1];
        }
        String[] stationNames = stationListFile.split("@");
        return stationNames.length;
    }

    private static String jsonToString(InputStream is) {

        InputStreamReader reader = new InputStreamReader(is);
        BufferedReader bufferedReader = new BufferedReader(reader);
        String line = "";
        try {
            while ((line = bufferedReader.readLine()) != null) {
                if (line.contains(FILE_NAME)) {
                    break;
                }
            }
            bufferedReader.close();
            reader.close();
        } catch (IOException e) {
            Log.w(TAG, "jsonToString: " + e.getLocalizedMessage());
            e.printStackTrace();
        }

        return getVersion(line);

    }

    /**
     * 获取网络上的版本号
     * todo 算法待优化
     *
     * @param line 网页中的行数
     * @return 版本号
     */
    private static String getVersion(String line) {
        String[] parts = line.split(" ");
        if (parts.length >= 3) {
            String part2 = parts[2];
            String[] part2s = part2.split("\"");
            if (part2s.length > 1) {
                String part3 = part2s[1];
                String[] part3s = part3.split("=");
                if (part3s.length > 1) {
                    return part3s[1];
                }
            }
        }
        return line;
    }

    private static List<Station> getCityList(String cityList) {
        List<Station> stations = new ArrayList<>();
        String[] stationNames = cityList.split("@");
        for (String stationName : stationNames) {
            String[] itemCity = stationName.split("\\|");
            if (itemCity.length == 6) {
                stations.add(new Station(itemCity[0], itemCity[1], itemCity[2],
                        itemCity[3], itemCity[4], Integer.parseInt(itemCity[5])));
            }
        }
        return stations;

    }

    @Override
    public Observable<Integer> insertProgressBar() {
        return Observable.create(emitter -> {
            if (!emitter.isDisposed()) {
                List<Station> stations = getCityList(stationListFile);
                for (Station station : stations) {
                    service.addStation(station);
                    currentUpdateCount++;
                    if (currentUpdateCount % 100 == 0) {
                        emitter.onNext(currentUpdateCount);
                    }
                }
                emitter.onNext(currentUpdateCount);
                emitter.onComplete();
            }
        });
    }

    @Override
    public Observable<Integer> getStationCount() {
        final String serverPath = "https://kyfw.12306.cn";
        final String stationVersionPath = FILE_NAME + getVersion;
        return Observable.create(emitter -> {
            if (!emitter.isDisposed()) {
                URL url = new URL(serverPath + stationVersionPath);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setConnectTimeout(TIME_OUT);
                connection.setRequestMethod("GET");
                if (successCode == connection.getResponseCode()) {
                    InputStream inputStream = connection.getInputStream();
                    String stationVersionName = getCityList(inputStream);
                    int count = getStationCount2(stationVersionName);
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
        double currentVersion = getCurrentVersion();
        Log.d(TAG, "isNeedUpdate: 当前版本：" + currentVersion);
        return Observable.create((ObservableOnSubscribe<Double>) emitter -> {
            if (!emitter.isDisposed()) {
                URL url = new URL(path);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setConnectTimeout(TIME_OUT);
                connection.setRequestMethod("GET");
                if (successCode == connection.getResponseCode()) {
                    InputStream inputStream = connection.getInputStream();
                    String stationVersion = jsonToString(inputStream);
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
        Log.d(TAG, "getCurrentVersion: 1");
        if (!preferences.contains(START_NAME_SHARED_PREFERENCES)) {
            Log.d(TAG, "getCurrentVersion: 2");
            preferences.put(START_NAME_SHARED_PREFERENCES, defaultStationVersion);
        } else {
            Log.d(TAG, "getCurrentVersion: 3");
            defaultStationVersion = (String) preferences.getSharedPreference(START_NAME_SHARED_PREFERENCES, defaultStationVersion);
        }
        Log.d(TAG, "getCurrentVersion: 4");
        return Double.parseDouble(defaultStationVersion);
    }

    @Override
    public boolean getNetWordCanUse() {
        ConnectivityManager manager = (ConnectivityManager) App.getInstance().getSystemService(Context.CONNECTIVITY_SERVICE);
        if (null == manager) {
            return false;
        }
        NetworkInfo info = manager.getActiveNetworkInfo();
        return null != info && info.isAvailable();
    }

    private String getCityList(InputStream inputStream) {
        try {
            InputStreamReader inputReader = new InputStreamReader(inputStream, "utf8");
            BufferedReader bufReader = new BufferedReader(inputReader);
            String line;
            StringBuilder stringBuilder = new StringBuilder();
            while ((line = bufReader.readLine()) != null) {
                stringBuilder.append(line);
            }
            return stringBuilder.toString();
        } catch (Exception e) {
            return "";
        }
    }
    //TODO  数据初始化过程中  要屏蔽返回键的点击事件


    @Override
    public Observable<Integer> clearData() {
        return Observable.just(service.deleteAllStation());
    }

    @Override
    public void finishUpdate() {
        Log.d(TAG, "finishUpdate: 写入数据库：：" + defaultStationVersion);
        preferences.put(START_NAME_SHARED_PREFERENCES, defaultStationVersion);
//        Observable.create(new ObservableOnSubscribe<Integer>() {
//            @Override
//            public void subscribe(ObservableEmitter<Integer> emitter) throws Exception {
//                preferences.put(START_NAME_SHARED_PREFERENCES, defaultStationVersion);
//                emitter.onNext(0);
//            }
//        }).subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer<Integer>() {
//            @Override
//            public void accept(Integer integer) throws Exception {
//
//            }
//        });

    }
}
