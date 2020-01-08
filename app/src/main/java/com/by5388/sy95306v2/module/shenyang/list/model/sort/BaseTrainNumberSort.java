package com.by5388.sy95306v2.module.shenyang.list.model.sort;

import com.by5388.sy95306v2.module.shenyang.bean.TrainNumber;

import java.util.Comparator;

/**
 * @author by5388  on 2018/11/10.
 */
public abstract class BaseTrainNumberSort implements Comparator<TrainNumber> {

    boolean isUp;
    /**
     * 按出发时间排序
     */
    public static final int SORT_BY_START_TIME = 0;
    /**
     * 按耗时排序
     */
    private static final int SORT_BY_SPEND_TIME = 1;
    /**
     * 按到达时间排序
     */
    private static final int SORT_BY_ARRIVE_TIME = 2;


    public static BaseTrainNumberSort getTrainNumberSort(boolean isUp, int position) {
        BaseTrainNumberSort sort;
        switch (position) {
            case SORT_BY_SPEND_TIME:
                sort = SpeedTimeSort.getInstance();
                break;
            case SORT_BY_ARRIVE_TIME:
                sort = ArriveTimeSort.getInstance();
                break;
            case SORT_BY_START_TIME:
            default:
                sort = StartTimeSort.getInstance();
                break;
        }
        return sort.setUp(isUp);
    }

    public BaseTrainNumberSort setUp(boolean up) {
        isUp = up;
        return this;
    }
}
