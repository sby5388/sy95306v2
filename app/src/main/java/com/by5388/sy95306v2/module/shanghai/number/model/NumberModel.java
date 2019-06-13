package com.by5388.sy95306v2.module.shanghai.number.model;

import com.by5388.sy95306v2.module.shanghai.bean.InfoBeanTrainNo;
import com.by5388.sy95306v2.module.shanghai.bean.QueryMethod;
import com.by5388.sy95306v2.module.shanghai.bean.ShanghaiTrainNumber;
import com.by5388.sy95306v2.module.shanghai.api.ShNetTools;
import com.by5388.sy95306v2.module.shanghai.api.ShanghaiService;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.Retrofit;

/**
 * @author by5388  on 2018/8/9.
 */

public class NumberModel implements INumberModel {

    private final ShanghaiService shanghaiService;


    public NumberModel() {
        Retrofit retrofit =new ShNetTools().getRetrofit();
        shanghaiService = retrofit.create(ShanghaiService.class);
    }

    @Override
    public Observable<List<ShanghaiTrainNumber>> getTrainNumber(InfoBeanTrainNo trainNo) {
        return shanghaiService.queryTrainNumber(new QueryMethod<>(trainNo));
    }
}
