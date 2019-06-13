package com.by5388.sy95306v2.module.shenyang.list.model.sort;

import com.by5388.sy95306v2.module.shenyang.bean.TrainNumber;

/**
 * @author by5388  on 2018/11/10.
 */
class StartTimeSort extends BaseTrainNumberSort {


    private static StartTimeSort instance;

    static StartTimeSort getInstance() {
        if (null == instance) {
            instance = new StartTimeSort();
        }
        return instance;
    }

    @Override
    public int compare(TrainNumber o1, TrainNumber o2) {
        int result = o2.getStartTime() - o1.getStartTime();
        if (isUp) {
            result *= -1;
        }
        return result;
    }
}
