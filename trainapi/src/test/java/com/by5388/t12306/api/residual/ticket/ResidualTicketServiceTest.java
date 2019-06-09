package com.by5388.t12306.api.residual.ticket;

import com.by5388.t12306.api.BaseNetTool;
import com.by5388.t12306.api.bean.QueryResult;

import org.junit.Before;
import org.junit.Test;

import java.util.List;

import io.reactivex.functions.Consumer;

/**
 * 余票查询
 *
 * @author by5388  on 2019/5/4.
 */
public class ResidualTicketServiceTest {
    private static final String PURPOSE_CODES = "ADULT";
    private static final String QUERY_DATE = "2019-05-28";
    private static final String FROM_STATION = "GZQ";
    private static final String TO_STATION = "RVQ";


    private ResidualTicketService mSubject;

    @Before
    public void setUp() throws Exception {
        BaseNetTool baseNetTool = new ResidualTicketTool();
        mSubject = baseNetTool.getRetrofit().create(ResidualTicketService.class);
    }


    @Test
    public void getResidualTicketTest() throws Exception {
        mSubject.getResidualTicket(PURPOSE_CODES, QUERY_DATE, FROM_STATION, TO_STATION)
                .subscribe(new Consumer<QueryResult<ResidualTicket>>() {
                    @Override
                    public void accept(QueryResult<ResidualTicket> datasBeanQueryResult) throws Exception {
                        List<ResidualTicket> datas = datasBeanQueryResult.getData().getDataList();
                        for (ResidualTicket bean : datas) {
                            System.out.println(bean.toString());
                        }
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        System.err.println(throwable.getLocalizedMessage());
                    }
                });
    }


}