package com.by5388.sy95306v2.shanghai.p2p.model;

import com.by5388.sy95306v2.shanghai.bean.InfoBeanP2P;
import com.by5388.sy95306v2.shanghai.bean.QueryMethod;
import com.by5388.sy95306v2.shanghai.bean.ShanghaiTrainP2P;
import com.by5388.sy95306v2.shanghai.api.ShNetTools;
import com.by5388.sy95306v2.shanghai.api.ShanghaiService;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.Retrofit;

/**
 * @author by5388  on 2018/8/10.
 */

public class P2pModel implements IP2pModel {

    private final ShanghaiService shanghaiService;


    public P2pModel() {
        Retrofit retrofit = new ShNetTools().getRetrofit();
        shanghaiService = retrofit.create(ShanghaiService.class);
    }

    @Override
    public Observable<List<ShanghaiTrainP2P>> queryTrainP2P(InfoBeanP2P info) {
        return shanghaiService.queryTrainP2P(new QueryMethod<>(info));
    }
}
