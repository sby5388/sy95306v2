package com.by5388.sy95306v2;

import com.by5388.sy95306v2.module.chengdu.api.ICdQuery;
import com.by5388.sy95306v2.module.chengdu.api.QueryCd;
import com.by5388.sy95306v2.module.chengdu.bean.screen.ScreenArriveDetail;

import org.junit.Test;

/**
 * @author by5388  on 2019/1/11.
 */
public class ChengduTest {
    @Test
    public void testChengdu() {
        ICdQuery query = new QueryCd();
        String stationCode = "ICW";
        String date = "2020-03-23";
//        date = date.replace("-","");
        query.getArriveDetail(stationCode, date)
                .subscribe(mScreenArriveDetails -> {
                    if (mScreenArriveDetails == null) {
                        return;
                    }
                    for (ScreenArriveDetail detail : mScreenArriveDetails) {
                        System.out.println(detail.getTrainCode());
                    }
                }, mThrowable -> System.err.println(mThrowable.getLocalizedMessage()));
    }
}
