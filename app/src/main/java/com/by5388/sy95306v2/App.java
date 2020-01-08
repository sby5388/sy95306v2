package com.by5388.sy95306v2;

import android.app.Application;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.Network;
import android.os.StrictMode;

/**
 * @author by5388  on 2018/7/28.
 */

public class App extends Application {
    // TODO: 2019/9/10 需要判断网络网络是否可用，
    private static App instance;
    private ConnectivityManager mConnectivityManager;
    private boolean mEnableNetwork = false;
    public static boolean DEBUG = BuildConfig.DEBUG;

    private BroadcastReceiver mBroadcastReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            // TODO: 2019/9/10 检测网络状态
        }
    };

    private IntentFilter getIntentFilter() {
        // TODO: 2019/9/10
        final IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(ConnectivityManager.CONNECTIVITY_ACTION);
        return intentFilter;
    }


    public static App getInstance() {
        return instance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        StrictMode.enableDefaults();
        //registerReceiver(mBroadcastReceiver, getIntentFilter());
        mConnectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        checkNet(mConnectivityManager);
//        final Network activeNetwork = mConnectivityManager.getActiveNetwork();
//        if (activeNetwork)

    }


    private void checkNet(Object o) {
        if (o == null) {
            throw new RuntimeException("Object is null");
        }
    }


    //  2019/1/22  通过车票口查询接口相关的API也可以获得相关的车次、检票口信息
    // 2019/1/22  https://www.12306.cn/index/view/infos/ticket_check.html
    // 2019/1/24  查询到的数据是难以处理的，且没有相应的时刻表。应当予以放弃。
}
