package com.by5388.sy95306v2.t201906;

import android.net.TrafficStats;
import android.net.Uri;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;
import android.util.Log;

import com.google.gson.Gson;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.ref.WeakReference;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

/**
 * @author Administrator  on 2019/6/15.
 */
public final class QueryThread extends HandlerThread implements IQueryThread {
    public static final String TAG = "QueryThread";
    private final Handler mResponseHandler;
    private Handler mRequestHandler;
    private static final String BASE_PATH = "https://www.12306.cn/kfzmpt/";
    private final static Uri BASE_URI = Uri.parse(BASE_PATH);

    public QueryThread(Handler responseHandler) {
        super(TAG);
        mResponseHandler = responseHandler;
        getLooper();
    }

    @Override
    protected void onLooperPrepared() {
        mRequestHandler = new RequestHandler(this);
    }

    @Override
    public void queryTicketPrices(String date, String from, String to, String type) {
        // TODO: 2019/6/17 没有检查网络状态！ 当网络状态不可用时，联网照成了闪退！-->使用前应当检测网络状态
        mRequestHandler.post(new TicketPricesRunnable(date, from, to, type));
    }


    private static class TicketPricesRunnable implements Runnable {
        private final String date;
        private final String from;
        private final String to;
        private final String type;

        TicketPricesRunnable(String date, String from, String to, String type) {
            this.date = date;
            this.from = from;
            this.to = to;
            this.type = type;
        }

        @Override
        public void run() {
            // TODO: 2019/6/15
            final Uri uri = BASE_URI
                    .buildUpon()
                    .appendPath("leftTicketPrice")
                    .appendPath("query")
                    .appendQueryParameter("leftTicketDTO.train_date", date)
                    .appendQueryParameter("leftTicketDTO.from_station", from)
                    .appendQueryParameter("leftTicketDTO.to_station", to)
                    .appendQueryParameter("leftTicketDTO.ticket_type", type)
                    .appendQueryParameter("randCode", "")
                    .build();
            final String path = uri.toString();
            try {
                getData(path);
            } catch (Exception e) {
                System.err.println(e.getLocalizedMessage());
            }

        }
    }

    private static void getData(String urlSpecial) throws IOException {
        Log.d(TAG, "getData: url = " + urlSpecial);
        final URL url = new URL(urlSpecial);
        final HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        try {
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            TrafficStats.setThreadStatsTag(100);
            final InputStream inputStream = connection.getInputStream();
            final int responseCode = connection.getResponseCode();
            if (responseCode != HttpURLConnection.HTTP_OK) {
                Log.e(TAG, "getData: error ResponseCode = " + responseCode);
                return;
            }
            int byteRead = 0;
            byte[] buffer = new byte[1024];
            while ((byteRead = inputStream.read(buffer)) > 0) {
                outputStream.write(buffer, 0, byteRead);
            }
            inputStream.close();
            outputStream.close();
            final byte[] byteArray = outputStream.toByteArray();
            final String s = new String(byteArray);
//            Log.d(TAG, "getData: " + Arrays.toString(byteArray));
            Log.d(TAG, "getData: " + s);
            Gson gson = new Gson();
            // TODO: 2019/9/12 String ->gson -->JavaBean
            final TrainResult trainResult = gson.fromJson(s, TrainResult.class);
            final List<TrainResult.DataBean> data = trainResult.data;
            for (TrainResult.DataBean bean : data) {
                System.out.println(bean.queryLeftNewDTO.toString());
            }
        } finally {
            connection.disconnect();
        }
    }


    private static class RequestHandler extends Handler {
        private WeakReference<QueryThread> mReference;

        RequestHandler(QueryThread queryThread) {
            mReference = new WeakReference<>(queryThread);
        }

        @Override
        public void handleMessage(Message msg) {
            // TODO: 2019/6/15
            final QueryThread queryThread = mReference.get();
            if (queryThread == null) {
                return;
            }
            switch (msg.what) {
                default:
                    break;
            }
        }
    }


}
