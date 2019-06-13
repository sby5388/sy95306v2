package com.android.train.net.api;

import android.net.Uri;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * TODO
 * 查询工具：
 * 解析+返回数据
 *
 * @author Administrator  on 2019/4/12.
 */
class QueryTool {
    byte[] getString(Uri path) throws IOException {
        URL url = new URL(path.toString());
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        connection.setReadTimeout(5000);
        connection.setConnectTimeout(5000);

        if (HttpURLConnection.HTTP_OK != connection.getResponseCode()) {
            connection.disconnect();
            return null;
        }

        byte[] buffer = new byte[1024];
        int length = 0;
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        InputStream inputStream = connection.getInputStream();
        while ((length = inputStream.read(buffer)) != 0) {
            bos.write(buffer,0,length);
        }
        inputStream.close();
        bos.close();
        return bos.toByteArray();

    }

}
