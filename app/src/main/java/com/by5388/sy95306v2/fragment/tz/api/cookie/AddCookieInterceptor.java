package com.by5388.sy95306v2.fragment.tz.api.cookie;

import android.support.annotation.NonNull;
import android.util.Log;

import com.by5388.sy95306v2.setting.ISettingSharedPreferences;
import com.by5388.sy95306v2.setting.SettingSharedPreferences;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * 添加请求
 *
 * @author by5388  on 2018/8/20.
 */
class AddCookieInterceptor implements Interceptor {
    private static final String TAG = "Interceptor";
    private final ISettingSharedPreferences sharedPreference;
    private static final String SET_COOKIE = "Set-Cookie";
    private static final String COOKIE_12306 = "cookie12306";

    public AddCookieInterceptor() {
        sharedPreference = SettingSharedPreferences.getInstance();
    }

    @Override
    public Response intercept(@NonNull Interceptor.Chain chain) throws IOException {
        // TODO: 2018/8/20
        Request.Builder builder = chain.request().newBuilder();
        String value = (String) sharedPreference.getSharedPreference(COOKIE_12306, "value");
        Log.d(TAG, "intercept: " + value);
        builder.addHeader("Cookie", value);
        return chain.proceed(builder.build());
    }
}
