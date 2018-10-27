package com.by5388.sy95306;

import android.app.Application;

/**
 * @author by5388  on 2018/6/15.
 */

public class App extends Application {
    private static App instance;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
    }

    public static App getInstance() {
        return instance;
    }
}
