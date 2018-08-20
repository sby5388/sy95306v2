package com.by5388.sy95306v2.fragment.cd.screen.model;

import com.by5388.sy95306v2.bean.cd.screen.ScreenArriveDetail;
import com.by5388.sy95306v2.bean.cd.screen.ScreenLeaveDetail;
import com.by5388.sy95306v2.bean.cd.screen.ScreenStation;
import com.by5388.sy95306v2.database.DataBaseTempAction;
import com.by5388.sy95306v2.database.ICdScreenStationDb;
import com.by5388.sy95306v2.fragment.cd.screen.CdScreenItem;
import com.by5388.sy95306v2.net.cd.ICdQuery;
import com.by5388.sy95306v2.net.cd.QueryCd;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.functions.Function;

/**
 * @author by5388  on 2018/8/18.
 */
public class CdScreenModel implements ICdScreenModel {
    private ICdQuery query;
    private ICdScreenStationDb service;

    public CdScreenModel() {
        query = new QueryCd();
        service = DataBaseTempAction.getInstance();
    }

    @Override
    public Observable<List<ScreenStation>> getCdScreenStationList() {
        return Observable.just(service.cdStationIsEmpty())
                .flatMap(new Function<Boolean, ObservableSource<List<ScreenStation>>>() {
                    @Override
                    public ObservableSource<List<ScreenStation>> apply(Boolean aBoolean) throws Exception {
                        if (!aBoolean) {
                            return Observable.just(service.getCdScreenStations());
                        } else {
                            return query.getCdScreenStationList()
                                    .doOnNext(screenStations -> {
                                        for (ScreenStation screenStation : screenStations) {
                                            service.addCdScreenStation(screenStation);
                                        }
                                    });
                        }
                    }
                });

    }

    @Override
    public Observable<List<CdScreenItem>> getScreenItem(int type, String stationCode, String date) {
        // TODO: 2018/8/18  这里的代码很不优雅
        if (TYPE_LEAVE == type) {
            return query.getLeaveDetail(stationCode, date)
                    .flatMap(new Function<List<ScreenLeaveDetail>, ObservableSource<List<CdScreenItem>>>() {
                        @Override
                        public ObservableSource<List<CdScreenItem>> apply(List<ScreenLeaveDetail> screenLeaveDetails) throws Exception {
                            // TODO: 2018/8/18 待优化
                            List<CdScreenItem> items = new ArrayList<>();
                            items.addAll(screenLeaveDetails);
                            return Observable.just(items);
                        }
                    });
        } else {
            return query.getArriveDetail(stationCode, date)
                    .flatMap(new Function<List<ScreenArriveDetail>, ObservableSource<List<CdScreenItem>>>() {
                        @Override
                        public ObservableSource<List<CdScreenItem>> apply(List<ScreenArriveDetail> screenArriveDetails) throws Exception {
                            // TODO: 2018/8/18 待优化
                            List<CdScreenItem> items = new ArrayList<>();
                            items.addAll(screenArriveDetails);
                            return Observable.just(items);
                        }
                    });

        }
    }
}
