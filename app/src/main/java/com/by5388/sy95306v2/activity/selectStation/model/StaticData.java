package com.by5388.sy95306v2.activity.selectStation.model;

import com.by5388.sy95306v2.bean.Station;
import com.by5388.sy95306v2.database.IShenYangStationDb;
import com.by5388.sy95306v2.database.DataBaseTempAction;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

/**
 * @author by5388  on 2018/7/28.
 */

public class StaticData {
    /**
     * 城市选择列表：默认的城市
     */
    static final String[] STATION_NAME_UPPER = {
            "BJP", "SHH", "TJP", "GZQ",
            "CQW", "CSQ", "CDW", "HHC",
            "HBB", "HFH", "NCG", "NJH",
            "SZQ", "JNK", "HZH", "WHN",
            "TYV", "SJP", "ZZF", "XAY",
            "SYT", "CCT", "DLT", "JLL",
            "JZD", "TLD", "CFD", "THL",
            "YJL", "TML", "BXT", "DUT",
            "TLT", "AST", "LYT", "BCT",
    };

    static List<Station> stations;

    public static List<Station> getStations() {
        stations = new ArrayList<>();
        if (null == stations) {
            stations = new ArrayList<>();
            initData(stations);
        }
        return stations;
    }

    private static void initData(List<Station> stations) {
        IShenYangStationDb dataBaseService = DataBaseTempAction.getInstance();
        Observable.fromArray(STATION_NAME_UPPER)
                .map(new Function<String, Station>() {
                    @Override
                    public Station apply(String nameUpper) {
                        return dataBaseService.selectStationByNameUpper(nameUpper);
                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Station>() {
                    @Override
                    public void accept(Station station) {
                        stations.add(station);
                    }
                });

    }
}
