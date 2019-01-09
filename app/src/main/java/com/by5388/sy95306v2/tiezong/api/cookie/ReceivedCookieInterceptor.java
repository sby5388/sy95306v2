package com.by5388.sy95306v2.tiezong.api.cookie;

import android.support.annotation.NonNull;
import android.util.Log;

import com.by5388.sy95306v2.setting.ISettingSharedPreferences;
import com.by5388.sy95306v2.setting.SettingSharedPreferences;

import java.io.IOException;
import java.util.List;

import okhttp3.Interceptor;
import okhttp3.Response;

/**
 * cookie相关的“拦截器”
 * todo
 * 接收处理
 * 参考：https://www.jianshu.com/p/1caa92bf8079
 *
 * @author by5388  on 2018/8/20.
 */
class ReceivedCookieInterceptor implements Interceptor {
    private static final String TAG = "Interceptor";
    private final ISettingSharedPreferences sharedPreference;
    private static final String SET_COOKIE = "Set-Cookie";
    private static final String COOKIE_12306 = "cookie12306";

    public ReceivedCookieInterceptor() {
        sharedPreference = SettingSharedPreferences.getInstance();
    }

    @Override
    public Response intercept(@NonNull Chain chain) throws IOException {
        Response response = chain.proceed(chain.request());
        List<String> cookies = response.headers(SET_COOKIE);
        if (!cookies.isEmpty()) {
            StringBuilder cookieBuffer = new StringBuilder();
            for (String cookie : cookies) {
                String[] cookieArray = cookie.split(";");
                cookieBuffer.append(cookieArray[0]);
                cookieBuffer.append(";");
            }
            Log.d(TAG, "intercept: " + cookieBuffer.toString());
            sharedPreference.put(COOKIE_12306, cookieBuffer.toString());
        }
        return response;
    }
}
