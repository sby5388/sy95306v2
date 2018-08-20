package com.by5388.sy95306v2.activity.selectStation.model;

import android.util.Log;

import com.by5388.sy95306v2.bean.Station;
import com.by5388.sy95306v2.database.IShenYangStationDb;
import com.by5388.sy95306v2.database.DataBaseTempAction;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import io.reactivex.Observable;

/**
 * @author by5388  on 2018/7/28.
 */

public class StationModel implements IStationModel {

    private final static String TAG = "StationModel";
    /**
     * 正则表达式：全部数字，匹配:code
     */
    private final static String REG_NUMBER = "^[0-9]*$";
    /**
     * 匹配英文
     */
    private final static String REG_ABC = "^[a-zA-Z]*$";

    private IShenYangStationDb dataBaseService;

    public StationModel() {
        this.dataBaseService = DataBaseTempAction.getInstance();
    }

    private static boolean regNumber(String string) {
        Pattern p = Pattern.compile(REG_NUMBER, Pattern.CASE_INSENSITIVE);
        Matcher matcher = p.matcher(string);
        return matcher.matches();
    }

    private static boolean regEnglish(String string) {
        Pattern p = Pattern.compile(REG_ABC, Pattern.CASE_INSENSITIVE);
        Matcher matcher = p.matcher(string);
        return matcher.matches();
    }

    @Override
    public Observable<List<Station>> getStation(String keyWord) {
        if (regNumber(keyWord)) {
            List<Station> empty = new ArrayList<>();
            // 数字没有任何意义
            return Observable.just(empty);
        }
        if (regEnglish(keyWord)) {
            // 2018/7/21 要同步互斥
            List<Station> nameFirst = dataBaseService.selectStationListByNameFirst(keyWord.toLowerCase());
            List<Station> nameEn = dataBaseService.selectStationListByNameEn(keyWord.toLowerCase());
            List<Station> nameLower = dataBaseService.selectStationListByNameLower(keyWord.toLowerCase());
            Set<Station> stations = new HashSet<>();
            stations.addAll(nameFirst);
            stations.addAll(nameEn);
            stations.addAll(nameLower);
            List<Station> back = new ArrayList<>();
            back.addAll(stations);
            return Observable.just(back);
        }
        //中文
        List<Station> name = dataBaseService.selectStationListByName(keyWord);
        Log.d(TAG, "getStation中文: " + name.size());
        return Observable.just(name);
    }

    @Override
    public Observable<List<Station>> getDefaultStation() {
        if (StaticData.getStations().isEmpty()) {
            return Observable.create(emitter -> {
                if (!emitter.isDisposed()) {
                    for (String nameUpper : StaticData.STATION_NAME_UPPER) {
                        StaticData.stations.add(dataBaseService.selectStationByNameUpper(nameUpper));
                    }
                    emitter.onNext(StaticData.stations);
                    emitter.onComplete();
                }
            });
        } else {
            return Observable.just(StaticData.getStations());
        }
    }
}
