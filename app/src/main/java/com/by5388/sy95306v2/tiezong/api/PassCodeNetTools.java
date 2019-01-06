package com.by5388.sy95306v2.tiezong.api;

import android.support.annotation.NonNull;
import android.util.Log;

import com.by5388.sy95306v2.base.BaseNetTools;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.TimeUnit;

import okhttp3.Cookie;
import okhttp3.CookieJar;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * 获取图片验证码
 *
 * @author by5388  on 2018/6/16.
 */

public class PassCodeNetTools extends BaseNetTools {
    private static final String BASE_URL = "https://kyfw.12306.cn/otn/";
    private static final String TAG = "PassCodeNetTools";
    private final HashMap<String, List<Cookie>> cookieStore;

    public PassCodeNetTools(@NonNull HashMap<String, List<Cookie>> cookieStore) {
        this.cookieStore = cookieStore;
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
            // TODO: 2018/8/21   okhttp3带cookie请求
            //参考：https://www.cnblogs.com/ganchuanpu/p/8075859.html
            CookieJar cookieJar = new CookieJar() {
                @Override
                public void saveFromResponse(HttpUrl url, @NonNull List<Cookie> cookies) {
                    //Log.d(TAG, "saveFromResponse: " + url.host());
                    cookieStore.put(url.host(), cookies);
                }

                @Override
                public List<Cookie> loadForRequest(HttpUrl url) {
                    //Log.d(TAG, "loadForRequest: " + url.host());
                    List<Cookie> cookies = cookieStore.get(url.host());
                    return cookies != null ? cookies : new ArrayList<>();
                }
            };
            //  设置超时时间 10秒
            OkHttpClient client = new OkHttpClient.Builder()
                    .connectTimeout(10, TimeUnit.SECONDS)
                    .readTimeout(10, TimeUnit.SECONDS)
                    .writeTimeout(10, TimeUnit.SECONDS)
                    //TODO 增加了2个cookie “过滤器”
                    //.addInterceptor(new AddCookieInterceptor())
                    //.addInterceptor(new ReceivedCookieInterceptor())
                    .cookieJar(cookieJar)
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
