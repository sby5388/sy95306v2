package com.by5388.sy95306v2;

import android.app.Application;

/**
 * @author by5388  on 2018/7/28.
 */

public class App extends Application {
    private static App instance;

    public static App getInstance() {
        return instance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
    }
}
