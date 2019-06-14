package com.by5388.sy95306v2.module.tiezong.api.pass.code;

import com.by5388.sy95306v2.base.BaseNetTools;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * 余票查询
 *
 * @author by5388  on 2018/6/16.
 * @author by5388  on 2019/6/9.
 */

public class PassCodeNetTools2 extends BaseNetTools {
    private static final String BASE_URL = "https://www.12306.cn/kfzmpt/lcxxcx/";
    private static final String TAG = "PassCodeNetTools";

    public PassCodeNetTools2() {
    }


//    https://kyfw.12306.cn/otn/zzzcx/
//    https://kyfw.12306.cn/otn/passcodeNew/checkRandCodeAnsyn
//    https://kyfw.12306.cn/otn/passcodeNew/getPassCodeNew

    @Override
    protected String getBaseUrl() {
        return BASE_URL;
    }

    @Override
    public Retrofit getRetrofit() {
        if (retrofit == null) {
            //  设置超时时间 10秒
            OkHttpClient client = new OkHttpClient.Builder()
                    .connectTimeout(10, TimeUnit.SECONDS)
                    .readTimeout(10, TimeUnit.SECONDS)
                    .writeTimeout(10, TimeUnit.SECONDS)
                    .build();

            retrofit = new Retrofit.Builder()
                    .client(client)
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .build();
        }
        return retrofit;
    }

}
