package com.by5388.sy95306v2;

import com.by5388.sy95306v2.module.chengdu.api.ICdQuery;
import com.by5388.sy95306v2.module.chengdu.api.QueryCd;
import com.by5388.sy95306v2.module.chengdu.bean.screen.ScreenArriveDetail;

import org.junit.Test;

import java.util.List;

import io.reactivex.functions.Consumer;

/**
 * @author by5388  on 2019/1/11.
 */
public class ChengduTest {
    @Test
    public void testChengdu() {
        ICdQuery query = new QueryCd();
        String stationCode = "ICW";
        String date = "2019-01-11";
//        date = date.replace("-","");
        query.getArriveDetail(stationCode, date)
                .subscribe(new Consumer<List<ScreenArriveDetail>>() {
                    @Override
                    public void accept(List<ScreenArriveDetail> mScreenArriveDetails) {
                        if (mScreenArriveDetails == null) {
                            return;
                        }
                        for (ScreenArriveDetail detail : mScreenArriveDetails) {
                            System.out.println(detail.getTrainCode());
                        }
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable mThrowable) {
                        System.err.println(mThrowable.getLocalizedMessage());
                    }
                });
    }
}
