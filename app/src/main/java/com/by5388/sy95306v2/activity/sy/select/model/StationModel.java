package com.by5388.sy95306v2.activity.sy.select.model;

import com.by5388.sy95306v2.bean.Station;
import com.by5388.sy95306v2.database.DataBaseTempAction;
import com.by5388.sy95306v2.database.IShenYangStationDb;

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
    /**
     * 城市列表应该修改
     * 城市选择列表：默认的城市
     */
    //var favorite_names = '@bji|北京|BJP|0@sha|上海|SHH|1@tji|天津|TJP|2@cqi|重庆|CQW|3@csh|长沙|CSQ|4@cch|长春|CCT|5@cdu|成都|CDW|6@fzh|福州|FZS|7@gzh|广州|GZQ|8@gya|贵阳|GIW|9@hht|呼和浩特|HHC|10@heb|哈尔滨|HBB|11@hfe|合肥|HFH|12@hzh|杭州|HZH|13@hko|海口|VUQ|14@jna|济南|JNK|15@kmi|昆明|KMM|16@lsa|拉萨|LSO|17@lzh|兰州|LZJ|18@nni|南宁|NNZ|19@nji|南京|NJH|20@nch|南昌|NCG|21@sya|沈阳|SYT|22@sjz|石家庄|SJP|23@tyu|太原|TYV|24@wlq|乌鲁木齐南|WMR|25@wha|武汉|WHN|26@xni|西宁|XNO|27@xan|西安|XAY|28@ych|银川|YIJ|29@zzh|郑州|ZZF|30@szh|深圳|SZQ|shenzhen|sz|31@xme|厦门|XMS|xiamen|xm|32';

    private static final String[] STATION_NAME_UPPER = {
            "BJP", "SHH", "TJP", "CQW",
            "CSQ", "CCT", "CDW", "FZS",
            "GZQ", "GIW", "HHC", "HBB",
            "HFH", "HZH", "VUQ", "JNK",
            "KMM", "LSO", "LZJ", "NNZ",
            "NJH", "NCG", "SYT", "SJP",
            "TYV", "WAR", "WHN", "XNO",
            "XAY", "YIJ", "ZZF", "SZQ",
            "XMS",
    };
    @SuppressWarnings("unused")
    private static final String[] STATION_NAME_UPPER_OLD = {
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
    @SuppressWarnings("unused")
    private final static String TAG = "StationModel";
    /**
     * 正则表达式：全部数字，匹配:code
     */
    private final static String REG_NUMBER = "^[0-9]*$";
    /**
     * 匹配英文
     */
    private final static String REG_ABC = "^[a-zA-Z]*$";

    private List<Station> defaultStations;

    private final IShenYangStationDb dataBaseService;

    public StationModel() {
        this.dataBaseService = DataBaseTempAction.getInstance();
        this.defaultStations = new ArrayList<>();
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
            List<Station> back = new ArrayList<>(stations);
            return Observable.just(back);
        }
        //中文
        List<Station> name = dataBaseService.selectStationListByName(keyWord);
        return Observable.just(name);
    }

    @Override
    public Observable<List<Station>> getDefaultStation() {
        if (null == defaultStations) {
            defaultStations = new ArrayList<>();
        }
        if (defaultStations.isEmpty()) {
            for (String nameUpper : STATION_NAME_UPPER) {
                defaultStations.add(dataBaseService.selectStationByNameUpper(nameUpper));
            }
        }
        return Observable.just(defaultStations);
    }


}
