package com.by5388.sy95306v2.module.tiezong.combination.model;

import android.support.annotation.NonNull;
import android.text.TextUtils;

import com.by5388.sy95306v2.database.DataBaseApiImpl;
import com.by5388.sy95306v2.database.IShenYangDbApi;
import com.by5388.sy95306v2.module.shenyang.bean.Station;
import com.by5388.sy95306v2.module.shenyang.bean.TrainDetail;
import com.by5388.sy95306v2.module.shenyang.net.api.SyNetTools;
import com.by5388.sy95306v2.module.shenyang.net.api.SyService;
import com.by5388.sy95306v2.module.tiezong.api.pass.code.GetPassCodeImpl;
import com.by5388.sy95306v2.module.tiezong.api.pass.code.IGetPassCodeService;
import com.by5388.sy95306v2.module.tiezong.bean.TzResult;
import com.by5388.sy95306v2.module.tiezong.bean.yp.success.SuccessDataBean;
import com.by5388.sy95306v2.module.tiezong.bean.yp.success.TzDataBean;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.functions.Function;

/**
 * @author by5388  on 2018/8/20.
 */
public class CombinationModel implements ICombinationModel {
    private final IGetPassCodeService service;
    private final SyService syService;
    private final IShenYangDbApi db;

    public CombinationModel() {
        service = new GetPassCodeImpl();
        syService = new SyNetTools().getRetrofit().create(SyService.class);
        db = DataBaseApiImpl.getInstance();
    }


    @Override
    public boolean isErrorStationName(String stationName) {
        Station station = db.selectStationByName(stationName.trim());
        return TextUtils.isEmpty(station.getName());
    }


    @Override
    public Observable<SuccessDataBean> getResult(String queryDate, String fromStationName, String toStationName) {
        String fromStationCode = db.selectStationByName(fromStationName).getNameUpper();
        String toStationCode = db.selectStationByName(toStationName).getNameUpper();
        return service.getZzCxData(queryDate, fromStationCode, toStationCode, fromStationName, toStationName)
                .flatMap((Function<TzResult<SuccessDataBean>, ObservableSource<SuccessDataBean>>) successDataBeanTzResult -> Observable.just(successDataBeanTzResult.getData()));
    }

    @Override
    public Observable<SuccessDataBean> getOnlyResult(String queryDate, String fromStationName, String toStationName, String trainCode) {
        return getResult(queryDate, fromStationName, toStationName)
                .flatMap((Function<SuccessDataBean, ObservableSource<SuccessDataBean>>) successDataBean -> {
                    SuccessDataBean result = getSuccessDataBeanByTrainCode(successDataBean, trainCode);
                    return Observable.just(result);
                });
    }

    /**
     * 结果中 筛符合的数据
     *
     * @param successDataBean 请求结果
     * @param trainCode       车次
     * @return 真正结果
     */
    @NonNull
    private SuccessDataBean getSuccessDataBeanByTrainCode(SuccessDataBean successDataBean, String trainCode) {
        SuccessDataBean result = new SuccessDataBean();
        result.setFlag(successDataBean.isFlag());
        result.setIsThrough(successDataBean.getIsThrough());
        List<TzDataBean> dataBeans = new ArrayList<>();
        for (TzDataBean data : successDataBean.getDatas()) {
            if (data.getStation_train_code().contains(trainCode)) {
                dataBeans.add(new TzDataBean(data));
            }
        }
        result.setDatas(dataBeans);
        return result;
    }


    @Override
    public Observable<List<TrainDetail>> getTrainByTrainCode(int date, String trainCode) {

        return syService.getTrainByTrainCode(date, trainCode);
    }
}

