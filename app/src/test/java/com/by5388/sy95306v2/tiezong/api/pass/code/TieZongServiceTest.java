package com.by5388.sy95306v2.tiezong.api.pass.code;

import com.by5388.sy95306v2.module.tiezong.api.pass.code.PassCodeNetTools2;
import com.by5388.sy95306v2.module.tiezong.api.pass.code.TieZongService;
import com.by5388.sy95306v2.module.tiezong.bean.yp.success.TzDataBean;

import org.junit.Before;
import org.junit.Test;

import java.util.List;

/**
 * @author by5388  on 2019/6/9.
 */
public class TieZongServiceTest {
    private TieZongService mSubject;

    @Before
    public void setUp() throws Exception {
        mSubject = new PassCodeNetTools2().getRetrofit().create(TieZongService.class);
    }


    @Test
    public void getRemainTicketDataTest() {
        final String type = "ADULT";
        final String queryDate = "2019-10-14";
        final String fromStation = "CBQ";
        final String RaoPingStation = "RVQ";
        final String toStation = "GZQ";
        mSubject.getRemainTicketData(type, queryDate, RaoPingStation, toStation)
                .subscribe(successDataBeanTzResult -> {
                    List<TzDataBean> datas = successDataBeanTzResult.getData().getDatas();
                    System.out.println(datas.size());
                    for (TzDataBean data : datas) {
                        System.out.println(data);
                    }


                }, System.out::println);
    }
}