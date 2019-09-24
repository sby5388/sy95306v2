package com.by5388.sy95306v2.module.tiezong.api.pass.code;

import com.by5388.sy95306v2.module.tiezong.bean.TzResult;
import com.by5388.sy95306v2.module.tiezong.bean.number.NumberDataBean;
import com.by5388.sy95306v2.module.tiezong.bean.number.NumberListDataBean;
import com.by5388.sy95306v2.module.tiezong.bean.temp.DataBeanX;
import com.by5388.sy95306v2.module.tiezong.bean.yp.success.SuccessDataBean;
import com.by5388.sy95306v2.module.tiezong.api.pass.code.IGetPassCodeService;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.functions.Function;

/**
 * @author by5388  on 2018/8/17.
 */
public class GetPassCodeImpl implements IGetPassCodeService {
    private final TieZongService service;
    private final TieZongService service2;

    public GetPassCodeImpl() {
        service = new PassCodeNetTools().getRetrofit().create(TieZongService.class);
        service2 = new PassCodeNetTools2().getRetrofit().create(TieZongService.class);
    }

    @Override
    public Observable<TzResult<SuccessDataBean>> getZzCxData(String queryDate, String fromStationCode, String toStationCode, String fromStationName, String toStationName) {
        final boolean isStudent = false;
        return getRemainTicketData(isStudent, queryDate, fromStationCode, toStationCode);
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
        return service.getStationAllTrain(date, stationName, stationCode);
    }

    @Override
    public Observable<TzResult<SuccessDataBean>> getRemainTicketData(boolean isStudent, String queryDate, String fromStation, String toStation) {
        //成人票
        final String typeAdult = "ADULT";
        //学生票
        final String typeStudent = "0X00";
        final String type = isStudent ? typeStudent : typeAdult;
        return service2.getRemainTicketData(type, queryDate, fromStation, toStation);
    }
}
