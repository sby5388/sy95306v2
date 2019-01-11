package com.by5388.sy95306v2.tiezong.zzcx.model;

import android.text.TextUtils;

import com.by5388.sy95306v2.database.DataBaseApiImpl;
import com.by5388.sy95306v2.database.IShenYangDbApi;
import com.by5388.sy95306v2.shenyang.bean.Station;
import com.by5388.sy95306v2.tiezong.api.pass.code.GetPassCodeImpl;
import com.by5388.sy95306v2.tiezong.api.pass.code.IGetPassCodeService;
import com.by5388.sy95306v2.tiezong.bean.TzResult;
import com.by5388.sy95306v2.tiezong.bean.yp.success.SuccessDataBean;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.functions.Function;

/**
 * @author by5388  on 2018/8/20.
 */
public class TzZzCxModel implements ITzZzCxModel {
    private final IGetPassCodeService service;
    private final IShenYangDbApi db;

    public TzZzCxModel() {
        service = new GetPassCodeImpl();
        db = DataBaseApiImpl.getInstance();
    }


    @Override
    public boolean isErrorStationName(String stationName) {
        Station station = db.selectStationByName(stationName.trim());
        return TextUtils.isEmpty(station.getName());
    }

    @Override
    public Observable<SuccessDataBean> getResult(String queryDate, String fromStationName, String toStationName, String randCode) {
        String fromStationCode = db.selectStationByName(fromStationName).getNameUpper();
        String toStationCode = db.selectStationByName(toStationName).getNameUpper();
        return service.getZzCxData(queryDate, fromStationCode,
                toStationCode, fromStationName, toStationName, randCode)
                .flatMap((Function<TzResult<SuccessDataBean>, ObservableSource<SuccessDataBean>>) successDataBeanTzResult -> Observable.just(successDataBeanTzResult.getData()));

    }
}

