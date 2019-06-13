package com.by5388.sy95306v2.module.chengdu.yupiao.model;

import com.by5388.sy95306v2.bean.IRemainingTicket;
import com.by5388.sy95306v2.module.chengdu.bean.yupiao.CdRemainTicketDetailBean;
import com.by5388.sy95306v2.module.chengdu.api.ICdQuery;
import com.by5388.sy95306v2.module.chengdu.api.QueryCd;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.functions.Function;

/**
 * @author by5388  on 2018/8/18.
 */
public class CdRemainTicketModel implements ICdRemainTicketModel {
    private final ICdQuery query;

    public CdRemainTicketModel() {
        query = new QueryCd();
    }

    @Override
    public Observable<List<IRemainingTicket>> getCdYp(String fromStation, String toStation, String date) {
        return query.getCdRemainTicket(fromStation, toStation, date)
                .flatMap((Function<List<CdRemainTicketDetailBean>, ObservableSource<List<IRemainingTicket>>>) cdRemainTicketDetail -> {
                    List<IRemainingTicket> list = new ArrayList<>(cdRemainTicketDetail);
                    return Observable.just(list);
                });
    }
}
