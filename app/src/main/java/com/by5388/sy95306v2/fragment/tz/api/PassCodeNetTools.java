package com.by5388.sy95306v2.fragment.tz.api;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * 获取图片验证码
 *
 * @author by5388  on 2018/6/16.
 */

public class PassCodeNetTools {
    private static Retrofit retrofit;
    private static final String BASE_URL = "https://kyfw.12306.cn/otn/passcodeNew/";

    public static Retrofit getRetrofit() {
        if (retrofit == null) {
            //  设置超时时间 10秒
            OkHttpClient.Builder builder = new OkHttpClient.Builder();
            builder.connectTimeout(30, TimeUnit.SECONDS)
                    .readTimeout(30, TimeUnit.SECONDS)
                    .writeTimeout(30, TimeUnit.SECONDS);
            OkHttpClient client = builder.build();
//            client.cookieJar();

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
