package com.by5388.sy95306.common;

import android.support.annotation.NonNull;

import com.by5388.sy95306.bean.Station;
import com.by5388.sy95306.bean.TrainNumber;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 静态的数据
 *
 * @author by5388  on 2018/6/16.
 */

public class StaticData {
    @SuppressWarnings("unused")
    private final static String TAG = "StaticData";
    private static List<Station> defaultStations;
    public static final String[] TYPES = {"全部", "高铁", "动车", "特快", "普快", "其他"};
    public static final boolean[] SELECTED = {true, true, true, true, true, true};


    /**
     * 车型对应的描述语句
     */
    private static Map<String, String> TRAIN_TYPE;

    private static Map<Integer, String> trainType;

    public static Map<Integer, String> getTrainType() {
        if (null == trainType) {
            //TODO use SparseArray??
            trainType = new HashMap<>(6);
            //99:全部车型
            trainType.put(0, String.valueOf(99));
            //8：G、C
            trainType.put(1, String.valueOf(8));
            //D:D
            trainType.put(2, "D");
            //1：Z
            trainType.put(3, String.valueOf(1));
            //2:T
            trainType.put(4, String.valueOf(2));
            //0:K,Y,
            trainType.put(5, String.valueOf(0));
            //3:普速列车
            trainType.put(6, String.valueOf(3));
        }
        return trainType;
    }

    /**
     * 列车类型的描述
     *
     * @param trainNumber 动车、高速动车、等等
     *                    <p>
     *                    车型::TCCODE
     *                    0:全部    99
     *                    1:G、C    TCCODE:8 高速
     *                    2:D       TCCODE:D 动车
     *                    3:Z       TCCODE:1 直特
     *                    4:T       TCCODE:2 特快
     *                    5:K、Y    TCCODE:0 快速
     *                    6:普客    TCCODE:3 普快
     *                    -----
     *                    空调：AC
     *                    AC：1有空调
     *                    AC：0无空调
     * @return 车次等级+空调
     */
    public static String getDescription(TrainNumber trainNumber) {
        return getTrainNumberClass(trainNumber.getTCCODE()) + " " + trainNumber.isACTrain();
    }

    /**
     * @param info 车次字头
     * @return 车次描述
     */
    private static String getTrainNumberClass(String info) {
        if (null == TRAIN_TYPE) {
            TRAIN_TYPE = new HashMap<>(8);
            initTrainNumberHashMap(TRAIN_TYPE);
        }
        //TODO HashMap的遍历有待熟悉
        for (String key : TRAIN_TYPE.keySet()) {
            if (info.equals(key)) {
                return  TRAIN_TYPE.get(key);
            }
        }
        return "";

    }


    private static void initTrainNumberHashMap(@NonNull Map<String, String> trainType) {
        trainType.put("0", "快速");
        trainType.put("1", "直特");
        trainType.put("2", "特快");
        trainType.put("3", "普快");
        trainType.put("4", "普客");
        trainType.put("5", "市郊");
        trainType.put("6", "混合");
        trainType.put("7", "准搞");
        trainType.put("8", "高速");
        trainType.put("9", "快慢");
        trainType.put("D", "动车");
    }


    public static List<Station> getDefaultStations() {
        if (null == defaultStations) {
            defaultStations = new ArrayList<>();
            Tools.getDefaultStation(defaultStations);
        }
        return defaultStations;
    }

}
