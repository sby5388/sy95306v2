package com.by5388.sy95306v2;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

/**
 * @author Administrator  on 2019/9/10.
 */
public abstract class BaseActivity extends AppCompatActivity {
    // TODO: 2019/9/10 sendBroadcast toClose App#broadrecevicer
    private BroadcastReceiver mReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            // TODO: 2019/9/10
            final String action = intent.getAction();
            if (!ConnectivityManager.CONNECTIVITY_ACTION.equals(action)) {
                return;
            }
            final boolean connected = isNetworkAvailableAndConnected();
            if (mConnected == connected) {
                return;
            }
            mConnected = connected;
            onNetStatusChange(mConnected);
        }
    };

    private boolean mConnected;
    private IntentFilter mIntentFilter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mIntentFilter = getIntentFilter();
        initConnectStatus();
    }

    private IntentFilter getIntentFilter() {
        final IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(ConnectivityManager.CONNECTIVITY_ACTION);
        return intentFilter;
    }

    private void initConnectStatus() {
        mConnected = isNetworkAvailableAndConnected();
        if (!mConnected) {
            // TODO: 2019/9/10  网络错误
            onNetStatusChange(false);
        }
    }

    /**
     * 来自28章
     */
    private boolean isNetworkAvailableAndConnected() {
        final ConnectivityManager cm =
                (ConnectivityManager) getSystemService(CONNECTIVITY_SERVICE);
        if (cm == null) {
            throw new RuntimeException("");
        }
        boolean isNetworkAvailable = cm.getActiveNetworkInfo() != null;
        boolean isNetworkConnected = isNetworkAvailable &&
                cm.getActiveNetworkInfo().isConnected();
        return isNetworkConnected;
    }


    @Override
    protected void onResume() {
        super.onResume();
        // 2019/9/10 增加网络监听广播
        registerReceiver(mReceiver, mIntentFilter);
    }

    @Override
    protected void onPause() {
        super.onPause();
        // 2019/9/10 解除注册网络更新广播
        unregisterReceiver(mReceiver);

    }

    /**
     * 网络状态发生变化时
     */
    public abstract void onNetStatusChange(boolean enable);
}
