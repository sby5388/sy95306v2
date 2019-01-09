package com.by5388.sy95306.kotlin.net.api

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

/**
 * @author  by5388  on 2019/1/5.
 */
class NetTools {

    // TODO: 使用单例模式

    companion object {
       const val BASE_URL = "http://61.161.203.55/mobile.myweixin.com/"
       const val BASE_URL3 = "http://kyfw.sytlj.com/mobile.myweixin.com/"
       const val BASE_URL4 = "http://kyfw.sytlj.com/mobile.myweixin.com/"

    }

    fun getMyRetrofit(): Retrofit {
        val builder: OkHttpClient.Builder = OkHttpClient.Builder()
                .connectTimeout(30, TimeUnit.SECONDS)
                .writeTimeout(30, TimeUnit.SECONDS)
                .readTimeout(30, TimeUnit.SECONDS);
        val retrofit: Retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(builder.build())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
        return retrofit;
    }

}