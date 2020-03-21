package com.by5388.sy95306v2.get.all.station.list;

import com.by5388.sy95306v2.exception.NetworkException;
import com.by5388.sy95306v2.main.model.IStationJson;
import com.by5388.sy95306v2.main.model.StationJson;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * @author Administrator  on 2019/11/26.
 */
public class GetDataList {
    public static final String NEW_PATH = "https://kyfw.12306.cn/otn/resources/js/framework/station_name.js?station_version=1.9121";
    public static final String OLD_PATH_0 = "https://kyfw.12306.cn/otn/resources/js/framework/stationName.js?station_version=";
    public static final String OLD_PATH = "https://kyfw.12306.cn/otn/resources/js/framework/stationName.js?station_version=";
    // TODO: 2019/11/26
    private final static String FILE_NAME = "/otn/resources/js/framework/station_name.js?station_version=";
    private static final String PATH = "https://kyfw.12306.cn";
    private final static int TIME_OUT = 5000;
    private final IStationJson mJson;
    private final StringBuilder mStringBuilder;

    public GetDataList() {
        mJson = new StationJson();
        mStringBuilder = new StringBuilder();
    }

    public int getCount() throws MalformedURLException, IOException, NetworkException {
//        final URL url = new URL(PATH + FILE_NAME);
//        final URL url = new URL("https://kyfw.12306.cn/otn/leftTicket/init");
        final URL url = new URL(NEW_PATH);
        System.out.println(url);
        final HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setConnectTimeout(TIME_OUT);
        connection.setRequestMethod("GET");

        if (HttpURLConnection.HTTP_OK != connection.getResponseCode()) {
            throw new NetworkException();
        }
        final InputStream inputStream = connection.getInputStream();
        final String version = mJson.getVersion(inputStream);
        final int stationCount = mJson.getStationCount(version, mStringBuilder);
        inputStream.close();
        return stationCount;
    }


}
