package com.by5388.sy95306v2.module.guangzhou.late.model;

import com.by5388.sy95306v2.module.guangzhou.api.GzNetTools;
import com.by5388.sy95306v2.module.guangzhou.api.GzService;
import com.by5388.sy95306v2.module.guangzhou.bean.GzResultData;
import com.by5388.sy95306v2.module.guangzhou.bean.late.GzLateDataBean;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.functions.Function;

/**
 * @author by5388  on 2018/8/20.
 */
public class GzLateModel implements IGzLateModel {
    private final GzService service;

    public GzLateModel() {
        service = new GzNetTools().getRetrofit().create(GzService.class);
    }

    @Override
    public Observable<List<GzLateDataBean>> getGzLateStationInfoBeans(String trainNo) {
        return service.getTrainLate(trainNo)
                .flatMap((Function<GzResultData<List<GzLateDataBean>>, ObservableSource<List<GzLateDataBean>>>) listGzResultData -> {
                    if (null == listGzResultData) {
                        return Observable.just(new ArrayList<>());
                    }
                    List<GzLateDataBean> beans = listGzResultData.getData();
                    return Observable.just(beans);
                });
    }
}
