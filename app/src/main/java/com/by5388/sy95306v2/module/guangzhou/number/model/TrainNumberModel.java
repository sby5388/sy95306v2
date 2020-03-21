package com.by5388.sy95306v2.module.guangzhou.number.model;

import com.by5388.sy95306v2.module.guangzhou.api.GzNetTools;
import com.by5388.sy95306v2.module.guangzhou.api.GzService;
import com.by5388.sy95306v2.module.guangzhou.bean.GzResultData;
import com.by5388.sy95306v2.module.guangzhou.bean.station.TrainsBean;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.Retrofit;

/**
 * @author by5388  on 2018/8/2.
 */

public class TrainNumberModel implements ITrainNumberModel {
    private final GzService service;

    public TrainNumberModel() {
        Retrofit retrofit = new GzNetTools().getRetrofit();
        service = retrofit.create(GzService.class);
    }


    @Override
    public Observable<GzResultData<List<TrainsBean>>> getTrainByNo(String trainNo, String date) {
        return service.getTrainByNo(trainNo, date);
    }
}
