package com.by5388.t12306.api;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * @author by5388  on 2019/1/23.
 */
public abstract class BaseNetTool {
    protected Retrofit retrofit;

    public Retrofit getRetrofit() {
        if (retrofit == null) {
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
