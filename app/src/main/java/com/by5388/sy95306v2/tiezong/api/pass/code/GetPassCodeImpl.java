package com.by5388.sy95306v2.tiezong.api.pass.code;

import com.by5388.sy95306v2.tiezong.bean.TzResult;
import com.by5388.sy95306v2.tiezong.bean.number.NumberDataBean;
import com.by5388.sy95306v2.tiezong.bean.number.NumberListDataBean;
import com.by5388.sy95306v2.tiezong.bean.temp.DataBeanX;
import com.by5388.sy95306v2.tiezong.bean.yp.success.SuccessDataBean;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.functions.Function;

/**
 * @author by5388  on 2018/8/17.
 */
public class GetPassCodeImpl implements IGetPassCodeService {
    private final IPassCodeService service;

    public GetPassCodeImpl() {
        service = new PassCodeNetTools().getRetrofit().create(IPassCodeService.class);
    }

    @Override
    public Observable<TzResult<SuccessDataBean>> getZzCxData(String queryDate, String fromStationCode, String toStationCode, String fromStationName, String toStationName, String randCode) {
        final String changeStationText = "";
        return service.getZzCxData(queryDate, fromStationCode, toStationCode, fromStationName, toStationName, randCode, changeStationText);
    }

    @Override
    public Observable<List<NumberDataBean>> getListNumberDataBean(String trainNo, String fromStationCOde, String toStationCOde, String date) {
        return service.getNumberList(trainNo, fromStationCOde, toStationCOde, date)
                .flatMap((Function<TzResult<NumberListDataBean>, ObservableSource<List<NumberDataBean>>>) result -> {
                    List<NumberDataBean> list = new ArrayList<>();
                    if (null == result || !result.isStatus()) {
                        return Observable.just(list);
                    }
                    NumberListDataBean bean = result.getData();
                    if (null == bean) {
                        return Observable.just(list);
                    }
                    List<NumberDataBean> listA = bean.getData();
                    if (null == listA || listA.isEmpty()) {
                        return Observable.just(list);
                    }
                    return Observable.just(listA);
                });

    }


    @Override
    public Observable<TzResult<DataBeanX>> getStationAllTrain(String date, String stationName, String stationCode) {
        final String randCode = "";
        return service.getStationAllTrain(date, stationName, stationCode, randCode);
    }
}
