package com.android.train.net.api;

import android.net.Uri;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;

import com.android.train.bean.QueryParam;

import java.lang.ref.WeakReference;

/**
 * @author Administrator  on 2019/4/12.
 */
public class QueryThread extends HandlerThread {
    public static final String TAG = "QueryThread";
    private Handler mResponseHandler;
    private Handler mRequestHandler;
    private ResultCallback mCallback;


    public QueryThread(Handler responseHandler, ResultCallback callback) {
        super(TAG);
        mResponseHandler = responseHandler;
        mCallback = callback;
        start();
        getLooper();
    }

    @Override
    protected void onLooperPrepared() {
        mRequestHandler = new RequestHandler(this);
    }


    public void query(QueryParam param) {
        mRequestHandler.obtainMessage(100, param).sendToTarget();
    }


    private static class RequestHandler extends Handler {
        WeakReference<QueryThread> mReference;

        public RequestHandler(QueryThread queryThread) {
            super();
            this.mReference = new WeakReference<>(queryThread);
        }


        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 100: {
                    QueryParam param = (QueryParam) msg.obj;
                    handle100(param);
                    break;
                }
                default:
                    break;
            }
        }

        private void handle100(QueryParam param) {
            Uri uri = param.getQueryParam();
            QueryTool tool = new QueryTool();
            try {
                byte[] result = tool.getString(uri);
                // TODO: 2019/4/25
            } catch (Exception e) {
                
            }
        }
    }


}
