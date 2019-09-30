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

    private static final int HTTP_OK = 200;
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
        final String trainDate = "2019-10-10";
        String randCode = "";
        mSubjects.getTrainDetail(trainNo, trainDate, randCode)
                .subscribe(new Consumer<TzTrainCodeResult>() {
                               @Override
                               public void accept(TzTrainCodeResult tzTrainCodeResult) throws Exception {
                                   if (!tzTrainCodeResult.status || tzTrainCodeResult.mHttpStatus != HTTP_OK) {
                                       throw new Exception("没有获取到正确的数值");
                                   }
                                   final List<TzTrainCodeResult.DataBeanX.DataBean> dataBeans = tzTrainCodeResult.data.data;
                                   for (TzTrainCodeResult.DataBeanX.DataBean bean : dataBeans) {
                                       System.out.println(bean.mStationName + " : " + bean.mArriveTime + " : " + bean.mStartTime);
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