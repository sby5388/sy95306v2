package com.by5388.sy95306v2.module.tiezong.api.version2;

import org.junit.Before;
import org.junit.Test;

import io.reactivex.functions.Consumer;
import retrofit2.Retrofit;

/**
 * @author Administrator  on 2019/6/14.
 */
public class TzService2Test {

    private TzService2 mSubjects;


    @Before
    public void setUp() throws Exception {
        Retrofit retrofit = new TzNetTools2().getRetrofit();
        mSubjects = retrofit.create(TzService2.class);
    }

    @Test
    public void getTrainDetail() {
        final String trainNo = "D7515";
        final String trainDate = "2019-08-22";
        String randCode = "";
        mSubjects.getTrainDetail(trainNo, trainDate, randCode)
                .subscribe(new Consumer<Temp>() {
                               @Override
                               public void accept(Temp temp) throws Exception {
                                   System.out.println(temp);
                               }
                           }
//        , new Consumer<Throwable>() {
//            @Override
//            public void accept(Throwable throwable) throws Exception {
//                System.err.println(throwable.getLocalizedMessage());
//            }
//        }
                );
    }
}