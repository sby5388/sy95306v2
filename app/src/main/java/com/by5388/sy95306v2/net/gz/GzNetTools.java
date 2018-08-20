package com.by5388.sy95306v2.net.gz;

import com.by5388.sy95306v2.net.BaseNetTools;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * @author by5388  on 2018/6/16.
 */

public class GzNetTools extends BaseNetTools {
    /**
     * 广铁查询总接口
     */
    private static final String BASE_URL = "http://www.gtbyxx.com/wxg/inter/ky/";

    @Override
    protected String getBaseUrl() {
        return BASE_URL;
    }
}
