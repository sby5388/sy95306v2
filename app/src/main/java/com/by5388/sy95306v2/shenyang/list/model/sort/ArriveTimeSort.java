package com.by5388.sy95306v2.shenyang.list.model.sort;

import com.by5388.sy95306v2.shenyang.bean.TrainNumber;

/**
 * @author by5388  on 2018/11/10.
 */
class ArriveTimeSort extends BaseTrainNumberSort {

    private static ArriveTimeSort instance;

    static ArriveTimeSort getInstance() {
        if (null == instance) {
            instance = new ArriveTimeSort();
        }
        return instance;
    }

    @Override
    public int compare(TrainNumber o1, TrainNumber o2) {
        int result = o2.getArriveTime() - o1.getArriveTime();
        if (isUp) {
            result *= -1;
        }
        return result;
    }
}
