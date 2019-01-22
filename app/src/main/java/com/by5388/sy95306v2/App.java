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
    // TODO: 2019/1/22  通过车票口查询接口相关的API也可以获得相关的车次、检票口信息
    // TODO: 2019/1/22 https://www.12306.cn/index/view/infos/ticket_check.html
}
