package com.by5388.sy95306v2.fragment.cd.yupiao.model;

import com.by5388.sy95306v2.bean.IYp;
import com.by5388.sy95306v2.bean.cd.yupiao.CdYpDetailBean;
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
public class CdYpModel implements ICdYpModel {
    private ICdQuery query;

    public CdYpModel() {
        query = new QueryCd();
    }

    @Override
    public Observable<List<IYp>> getCdYp(String fromStation, String toStation, String date) {
        return query.getCdYp(fromStation, toStation, date)
                .flatMap(new Function<List<CdYpDetailBean>, ObservableSource<List<IYp>>>() {
                    @Override
                    public ObservableSource<List<IYp>> apply(List<CdYpDetailBean> cdYpDetailBeans) throws Exception {
                        List<IYp> list = new ArrayList<>();
                        list.addAll(cdYpDetailBeans);
                        return Observable.just(list);
                    }
                });
    }
}
