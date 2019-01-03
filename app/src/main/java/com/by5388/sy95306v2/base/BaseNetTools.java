package com.by5388.sy95306v2.base;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * @author by5388  on 2018/8/19.
 */
public abstract class BaseNetTools {

    protected Retrofit retrofit;

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
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .build();
        }
        return retrofit;
    }

    /**
     * 服务器地址
     *
     * @return 服务器地址
     */
    protected abstract String getBaseUrl();

}
