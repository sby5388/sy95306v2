package com.by5388.sy95306v2.module.shanghai.number.model;

import com.by5388.sy95306v2.module.shanghai.bean.InfoBeanTrainNo;
import com.by5388.sy95306v2.module.shanghai.bean.ShanghaiTrainNumber;

import java.util.List;

import io.reactivex.Observable;

/**
 * @author by5388  on 2018/8/9.
 */

public interface INumberModel {
    /**
     * 车次查询
     *
     * @param trainNo 车次
     * @return 停站列表
     */
    Observable<List<ShanghaiTrainNumber>> getTrainNumber(InfoBeanTrainNo trainNo);


}
