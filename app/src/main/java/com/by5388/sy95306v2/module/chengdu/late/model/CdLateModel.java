package com.by5388.sy95306v2.module.chengdu.late.model;

import com.by5388.sy95306v2.module.chengdu.api.ICdQuery;
import com.by5388.sy95306v2.module.chengdu.api.QueryCd;
import com.by5388.sy95306v2.module.chengdu.bean.late.CdLateStation;
import com.by5388.sy95306v2.module.chengdu.bean.late.CdTrainAllNodeBean;

import java.util.List;

import io.reactivex.Observable;

/**
 * @author by5388  on 2018/8/18.
 */
public class CdLateModel implements ICdLateModel {
    public static final String TAG = "CdLateModel";
    private final ICdQuery query;

    public CdLateModel() {
        query = new QueryCd();
    }

    @Override
    public Observable<List<CdLateStation>> getLateStationList(String trainCode, String date) {
        return query.getLate(trainCode, date);
    }

    @Override
    public Observable<List<CdTrainAllNodeBean>> getLateDetail(String trainCode, String date, String stationName) {
        return query.getLateDetail(trainCode, date, stationName);
    }
}
