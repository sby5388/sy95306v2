package com.by5388.sy95306v2.other.step1.sy;

import com.by5388.sy95306v2.base.BaseNetTools;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * @author by5388  on 2018/6/16.
 */

public class Step1SyNetTools extends BaseNetTools {
    private static final String BASE_URL = "http://61.161.203.55/mobile.myweixin.com/";
    @SuppressWarnings("unused")
    private static final String BASE_URL2 = "http://kyfw.sytlj.com/mobile.myweixin.com/";

    @Override
    protected String getBaseUrl() {
        return BASE_URL;
    }

    @Override
    public Retrofit getRetrofit() {
        if (retrofit == null) {
            //  设置超时时间 10秒
            //沈阳管内车 查询时间长(包含余票信息)，需要延长至30秒
            OkHttpClient.Builder builder = new OkHttpClient.Builder();
            builder.connectTimeout(30, TimeUnit.SECONDS)
                    .readTimeout(30, TimeUnit.SECONDS)
                    .writeTimeout(30, TimeUnit.SECONDS);

            retrofit = new Retrofit.Builder()
                    .client(builder.build())
                    .baseUrl(getBaseUrl())
                    .addConverterFactory(GsonConverterFactory.create())
                    //.addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .build();
        }
        return retrofit;
    }
}
