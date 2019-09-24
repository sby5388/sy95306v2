package com.by5388.sy95306v2.module.tiezong.api.version2;

import org.junit.Before;
import org.junit.Test;

import java.util.List;

import io.reactivex.functions.Consumer;
import retrofit2.Retrofit;

/**
 * @author Administrator  on 2019/6/14.
 */
public class TzService2Test {

    private TzService2 mSubjects;
//https://www.12306.cn/kfzmpt/queryTrainInfo/query

    @Before
    public void setUp() throws Exception {
        Retrofit retrofit = new TzNetTools2().getRetrofit();
        mSubjects = retrofit.create(TzService2.class);
    }

    @Test
    public void getTrainDetail() {
        final String trainNo = "D7515";
        final String trainDate = "2019-09-22";
        String randCode = "";
        mSubjects.getTrainDetail(trainNo, trainDate, randCode)
                .subscribe(new Consumer<Temp>() {
                               @Override
                               public void accept(Temp temp) throws Exception {
                                   final List<Temp.DataBeanX.DataBean> dataBeans = temp.getData().getData();
                                   for (Temp.DataBeanX.DataBean bean : dataBeans) {
                                       System.out.println(bean.getStation_name() + " : " + bean.getArrive_day_str() + " : " + bean.getTrain_class_name());
                                   }
                               }
                           }
//                        , new Consumer<Throwable>() {
//                            @Override
//                            public void accept(Throwable throwable) throws Exception {
//                                System.err.println(throwable.getLocalizedMessage());
//                            }
//                        }
                );
    }
}