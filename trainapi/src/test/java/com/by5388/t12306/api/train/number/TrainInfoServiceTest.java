package com.by5388.t12306.api.train.number;

import com.by5388.t12306.api.BaseNetTool;
import com.by5388.t12306.api.bean.ListDataBean;
import com.by5388.t12306.api.bean.QueryResult;

import org.junit.Before;
import org.junit.Test;

import java.util.List;

import io.reactivex.functions.Consumer;

/**
 * @author by5388  on 2019/5/4.
 */
public class TrainInfoServiceTest {
    private static final String QUERY_DATE = "2019-05-28";
    private static final String QUERY_NUMBER = "D7515";

    private Consumer<Throwable> mThrowableConsumer;
    private TrainInfoService mSubject;

    @Before
    public void setUp() throws Exception {
        BaseNetTool baseNetTool = new TrainInfoTool();
        mSubject = baseNetTool.getRetrofit().create(TrainInfoService.class);
        mThrowableConsumer = new Consumer<Throwable>() {
            @Override
            public void accept(Throwable throwable) throws Exception {
                System.err.println(throwable);
            }
        };
    }


    @Test
    public void getTrainInfoTest() {
        mSubject.getTrainInfo(QUERY_NUMBER, QUERY_DATE)
                .subscribe(new Consumer<QueryResult<TrainNumber>>() {
                               @Override
                               public void accept(QueryResult<TrainNumber> dataBeanQueryResult) {
                                   ListDataBean<TrainNumber> data = dataBeanQueryResult.getData();
                                   List<TrainNumber> dataList = data.getDataList();
                                   for (TrainNumber bean : dataList) {
                                       System.out.println(bean.toString());
                                   }
                               }
                           }
                );
    }
}