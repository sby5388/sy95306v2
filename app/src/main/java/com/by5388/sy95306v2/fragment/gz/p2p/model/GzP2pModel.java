package com.by5388.sy95306v2.fragment.gz.p2p.model;

import com.by5388.sy95306v2.bean.gz.GzResultData;
import com.by5388.sy95306v2.bean.gz.station.DataBeanP2P;
import com.by5388.sy95306v2.net.gz.GzNetTools;
import com.by5388.sy95306v2.net.gz.GzService;

import io.reactivex.Observable;
import retrofit2.Retrofit;

/**
 * @author by5388  on 2018/8/10.
 */

public class GzP2pModel implements IGzP2pModel {

    private final GzService service;

    public GzP2pModel() {
        Retrofit retrofit = new GzNetTools().getRetrofit();
        service = retrofit.create(GzService.class);
    }

    @Override
    public Observable<GzResultData<DataBeanP2P>> getTrainByStation(String fromStation, String toStation, String date) {
        return service.getTrainByStation(fromStation, toStation, date);
    }
}
