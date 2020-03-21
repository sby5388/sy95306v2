package com.by5388.sy95306v2;

import android.app.Application;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.os.StrictMode;
import android.widget.Toast;

import androidx.annotation.StringRes;

/**
 * @author by5388  on 2018/7/28.
 */

public class App extends Application {
    public static boolean DEBUG = BuildConfig.DEBUG;
    // TODO: 2019/9/10 需要判断网络网络是否可用，
    private static App instance;
    private boolean mEnableNetwork = false;
    private Toast mToast;

    private BroadcastReceiver mBroadcastReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            // TODO: 2019/9/10 检测网络状态
        }
    };

    public static App getInstance() {
        return instance;
    }

    private IntentFilter getIntentFilter() {
        // TODO: 2019/9/10
        final IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(ConnectivityManager.CONNECTIVITY_ACTION);
        return intentFilter;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        final boolean release = getResources().getBoolean(R.bool.release);
        if (release) {
            // TODO: 2020/3/17 all false
            StrictMode.enableDefaults();
            //registerReceiver(mBroadcastReceiver, getIntentFilter());
            ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
            checkNet(connectivityManager);
//        final Network activeNetwork = mConnectivityManager.getActiveNetwork();
//        if (activeNetwork)

        }

    }


    private void checkNet(Object o) {
        if (o == null) {
            throw new RuntimeException("Object is null");
        }
    }


    //  2019/1/22  通过车票口查询接口相关的API也可以获得相关的车次、检票口信息
    // 2019/1/22  https://www.12306.cn/index/view/infos/ticket_check.html
    // 2019/1/24  查询到的数据是难以处理的，且没有相应的时刻表。应当予以放弃。


    /**
     * 检查网络是否可用，不可用时直接抛弃，或者打开网络设置
     *
     * @return
     */
    public boolean networkEnable() {
        final ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        if (cm == null) {
            return false;
        }
        boolean isNetworkAvailable = cm.getActiveNetworkInfo() != null;
        return isNetworkAvailable &&
                cm.getActiveNetworkInfo().isConnected();
    }

    public void toast(String s) {
        if (mToast != null) {
            mToast.cancel();
        }
        mToast = Toast.makeText(this, s, Toast.LENGTH_SHORT);
        mToast.show();
    }

    public void toast(@StringRes int id) {

        if (mToast != null) {
            mToast.cancel();
        }
        mToast = Toast.makeText(this, id, Toast.LENGTH_SHORT);
        mToast.show();
    }
}
