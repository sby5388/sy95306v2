package com.by5388.sy95306v2.activity.trainDetail.model;

import android.support.annotation.NonNull;
import android.text.TextUtils;

import com.by5388.sy95306v2.bean.shenyang.TrainDetail;
import com.by5388.sy95306v2.net.shenYang.SyNetTools;
import com.by5388.sy95306v2.net.shenYang.SyService;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import io.reactivex.Observable;
import retrofit2.Retrofit;

/**
 * @author by5388  on 2018/7/29.
 */

public class DetailModel implements IDetailModel {
    private SyService trainNumberService;

    public DetailModel() {
        Retrofit retrofit = new SyNetTools().getRetrofit();
        trainNumberService = retrofit.create(SyService.class);
    }

    @Override
    public Observable<List<TrainDetail>> getTrainByTrainCode(int date, String trainCode) {
        return trainNumberService.getTrainByTrainCode(date, trainCode);
    }

    @Override
    public String getTrainName(@NonNull List<TrainDetail> details) {
        return details.get(0).getSNAME() + "-" + details.get(details.size() - 1).getSNAME();
    }

    @Override
    public String getCodeName(List<TrainDetail> trainDetails) {
        Set<String> codes = new HashSet<>();
        for (TrainDetail detail : trainDetails) {
            codes.add(detail.getSTCODE());
        }
        StringBuilder stringBuilder = new StringBuilder();
        for (String code : codes) {
            if (!TextUtils.isEmpty(stringBuilder)) {
                stringBuilder.append("/");
            }
            stringBuilder.append(code);
        }
        return stringBuilder.toString();
    }
}
