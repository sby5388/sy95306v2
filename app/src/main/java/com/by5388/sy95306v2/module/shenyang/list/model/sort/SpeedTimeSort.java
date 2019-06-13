package com.by5388.sy95306v2.module.shenyang.list.model.sort;

import com.by5388.sy95306v2.module.shenyang.bean.TrainNumber;

/**
 * @author by5388  on 2018/11/10.
 */
class SpeedTimeSort extends BaseTrainNumberSort {

    private static SpeedTimeSort instance;

    static SpeedTimeSort getInstance() {
        if (instance == null) {
            instance = new SpeedTimeSort();
        }
        return instance;
    }

    @Override
    public int compare(TrainNumber o1, TrainNumber o2) {
        int result = o2.getSpendTime() - o1.getSpendTime();
        if (isUp) {
            result *= -1;
        }
        return result;
    }
}
