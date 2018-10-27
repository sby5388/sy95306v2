package com.by5388.sy95306.common;

import android.content.Context;
import android.support.annotation.NonNull;

import com.by5388.sy95306.adapter.StationAdapter;
import com.by5388.sy95306.bean.Station;
import com.by5388.sy95306.database.DataBaseImpl;
import com.by5388.sy95306.database.DataBaseTempAction;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * @author by5388  on 2018/6/6.
 */

public class Tools {

    /**
     * 时间字符串长度
     */
    private static final int TIME_LENGTH = 4;
    /**
     * 始发站、终到站时刻表
     */
    private static final String FIRST_END_STATION_TIME = "----";
    /**
     * 60分钟
     */
    private static final int MINUTE_OF_HOUR = 60;
    /**
     * 一天分钟数
     */
    private static final int MINUTE_OF_DAY = 1440;
    private final static String TAG = "Tools";

    /**
     * 城市选择列表：默认的城市
     */
    private static final String[] STATION_NAME_UPPER = {
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
    private static final String[] STATION_NAME = {
            "北京", "上海", "天津", "广州",
            "重庆", "长沙", "成都", "呼和浩特",
            "哈尔滨", "合肥", "南昌", "南京",
            "深圳", "济南", "杭州", "武汉",
            "太原", "石家庄", "郑州", "西安",
            "沈阳", "长春", "大连", "吉林",
            "锦州", "通辽", "赤峰", "通化",
            "延吉", "图们", "本溪", "丹东",
            "铁岭", "鞍山", "辽阳", "白城",
    };

    /**
     * 正则表达式：全部数字，匹配:code
     */
    private final static String REG_NUMBER = "^[0-9]*$";
    /**
     * 匹配英文
     */
    private final static String REG_ABC = "^[a-zA-Z]*$";


    public static void getDefaultStation(@NonNull List<Station> emptyStations) {
        DataBaseImpl dataBaseService = DataBaseTempAction.getInstance();
       Disposable disposable = Observable.fromArray(STATION_NAME_UPPER).map(nameUpper ->
                dataBaseService.selectStationByNameUpper(nameUpper))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(station -> emptyStations.add(station));
                .subscribe(emptyStations::add);

    }

    /**
     * TODO
     *
     * @param context
     * @param string
     * @return
     */
    private static Observable<List<Station>> getObservableByString2(@NonNull Context context, String string) {
        DataBaseImpl dataBaseService = DataBaseTempAction.getInstance();


        return Observable.create(e -> {
            List<Station> stations;
            if (regNumber(string)) {
                //TODO  数字没有任何意义
                //数字
                stations = new ArrayList<>();
            } else if (regEnglish(string)) {
                //字母
                stations = dataBaseService.selectStationListByNameLower(string.toLowerCase());
            } else {
                //其他：中文
                stations = dataBaseService.selectStationListByName(string);
            }
            e.onNext(stations);
            e.onComplete();
        });
    }

    /**
     * 新的筛选条件：nameFirst/name/nameEn/nameLower，去掉数字
     *
     * @param string
     * @return
     */
    private static Observable<List<Station>> getObservableByString(String string) {
        DataBaseImpl dataBaseService = DataBaseTempAction.getInstance();
        if (regNumber(string)) {
            List<Station> empty = new ArrayList<>();
            //TODO  数字没有任何意义
            return Observable.just(empty);
        }
        if (regEnglish(string)) {
            // TODO: 2018/7/21 要同步互斥
            List<Station> nameFirst = dataBaseService.selectStationListByNameFirst(string.toLowerCase());
            List<Station> nameEn = dataBaseService.selectStationListByNameEn(string.toLowerCase());
            List<Station> nameLower = dataBaseService.selectStationListByNameLower(string.toLowerCase());
            Set<Station> stations = new HashSet<>();
            stations.addAll(nameFirst);
            stations.addAll(nameEn);
            stations.addAll(nameLower);
            List<Station> back = new ArrayList<>(stations);
            return Observable.just(back);
        }
        //中文
        List<Station> name = dataBaseService.selectStationListByName(string);
        return Observable.just(name);
    }

    public static void refreshStationData(@NonNull Context context, String string, StationAdapter adapter) {
        Observable<List<Station>> observable = getObservableByString(string);
        updateStationData(observable, adapter);
    }

    /**
     * 刷新数据
     *
     * @param observable 获取到的车站列表
     * @param adapter    设配器
     */
    private static void updateStationData(@NonNull Observable<List<Station>> observable, StationAdapter adapter) {
        observable.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(adapter::setStations);
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

    /**
     * 对String类型的时间字符串进行切割--->"xx:xx"
     */
    public static String showTime(String time) {
        if (TIME_LENGTH != time.length()) {
            return time;
        }
        return time.substring(0, 2) + ":" + time.substring(2, 4);
    }

    /**
     * 中途停站时间
     *
     * @param arriveTime 到达时间
     * @param leaveTime  出发时间
     * @return 停留时间
     */
    public static String stopTime(String arriveTime, String leaveTime) {
        if (FIRST_END_STATION_TIME.equals(arriveTime) || FIRST_END_STATION_TIME.equals(leaveTime)) {
            return String.valueOf(0);
        }
        int stopTime = calculatorTime(leaveTime) - calculatorTime(arriveTime);
        while (stopTime < 0) {
            stopTime += MINUTE_OF_DAY;
        }
        return stopTime + "分";
    }

    private static int calculatorTime(String time) {
        int hour = Integer.parseInt(time.substring(0, 2));
        int minute = Integer.parseInt(time.substring(2, 4));
        return hour * MINUTE_OF_HOUR + minute;
    }

    /**
     * 列车全程运行时间
     *
     * @param stringTime 历时
     * @return 列车全程运行时间
     */
    public static String getAllSpendTime(String stringTime) {
        int allTime = Integer.parseInt(stringTime);
        StringBuilder stringBuilder = new StringBuilder();
        if (allTime >= MINUTE_OF_DAY) {
            stringBuilder.append(allTime / MINUTE_OF_DAY)
                    .append("天");
            allTime = allTime % MINUTE_OF_DAY;
        }
        if (allTime >= MINUTE_OF_HOUR) {
            stringBuilder.append(allTime / MINUTE_OF_HOUR)
                    .append("小时");
            allTime = allTime % MINUTE_OF_HOUR;
        }
        stringBuilder.append(allTime)
                .append("分");
        return stringBuilder.toString();
    }


}
