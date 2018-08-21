package com.by5388.sy95306v2.fragment.shanghai.number.model;

import com.by5388.sy95306v2.bean.shanghai.InfoBeanTrainNo;
import com.by5388.sy95306v2.bean.shanghai.QueryMethod;
import com.by5388.sy95306v2.bean.shanghai.ShanghaiTrainNumber;
import com.by5388.sy95306v2.net.shanghai.ShangHaiNetTools;
import com.by5388.sy95306v2.net.shanghai.ShanghaiService;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.Retrofit;

/**
 * @author by5388  on 2018/8/9.
 */

public class NumberModel implements INumberModel {

    private final ShanghaiService shanghaiService;


    public NumberModel() {
        Retrofit retrofit =new  ShangHaiNetTools().getRetrofit();
        shanghaiService = retrofit.create(ShanghaiService.class);
    }

    @Override
    public Observable<List<ShanghaiTrainNumber>> getTrainNumber(InfoBeanTrainNo trainNo) {
        return shanghaiService.queryTrainNumber(new QueryMethod<>(trainNo));
    }
}
