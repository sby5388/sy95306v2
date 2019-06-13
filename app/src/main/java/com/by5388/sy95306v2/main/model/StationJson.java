package com.by5388.sy95306v2.main.model;

import android.util.Log;

import com.by5388.sy95306v2.module.shenyang.bean.Station;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * @author by5388  on 2019/1/2.
 */
public class StationJson implements IStationJson {
    private final static String FILE_NAME = "/otn/resources/js/framework/station_name.js?station_version=";
    // TODO: 2019/3/26 仅仅作为一个表达式，而是应该包含数据


    private int mCount = 0;
    private String longString;

    @Override
    public int getStationCount(String stationList, StringBuilder stationListFile) {
        mCount = 0;
        String stationListFileTemp = "";
        final int minLength = 2;
        String[] newList = stationList.split("'");
        if (newList.length > minLength) {
            stationListFileTemp = newList[1];
            stationListFile.delete(0, stationListFile.length());
            stationListFile.append(stationListFileTemp);
        }
        String[] stationNames = stationListFileTemp.split("@");
        mCount = stationNames.length;
        return stationNames.length;
    }

    @Override
    public String jsonToString(InputStream is) {
        InputStreamReader reader = new InputStreamReader(is);
        BufferedReader bufferedReader = new BufferedReader(reader);
        String line;
        try {
            while ((line = bufferedReader.readLine()) != null) {
                if (line.contains(FILE_NAME)) {
                    break;
                }
            }
            bufferedReader.close();
            reader.close();
        } catch (IOException e) {
            Log.w(TAG, "jsonToString: " + e.getLocalizedMessage());
            e.printStackTrace();
            line = "";
        }
        return line;
    }

    @Override
    public String getVersion(final String lineString) {
        String[] parts = lineString.split(" ");
        if (parts.length >= 3) {
            String part2 = parts[2];
            String[] part2s = part2.split("\"");
            if (part2s.length > 1) {
                String part3 = part2s[1];
                String[] part3s = part3.split("=");
                if (part3s.length > 1) {
                    return part3s[1];
                }
            }
        }
        return lineString;
    }

    @Override
    public String getVersion(InputStream inputStream) {
        return getVersion(jsonToString(inputStream));
    }

    @Override
    public List<Station> getCityList(String cityList) {
        mCount = 0;
        List<Station> stations = new ArrayList<>();
        String[] stationNames = cityList.split("@");
        for (String stationName : stationNames) {
            String[] itemCity = stationName.split("\\|");
            if (itemCity.length == 6) {
                stations.add(new Station(itemCity[0], itemCity[1], itemCity[2],
                        itemCity[3], itemCity[4], Integer.parseInt(itemCity[5])));
                mCount++;
            }
        }
        return stations;
    }

    @Override
    public String getCityList(InputStream inputStream) {
        try {
            InputStreamReader inputReader = new InputStreamReader(inputStream, "utf8");
            BufferedReader bufReader = new BufferedReader(inputReader);
            String line;
            StringBuilder stringBuilder = new StringBuilder();
            while ((line = bufReader.readLine()) != null) {
                stringBuilder.append(line);
            }
            return stringBuilder.toString();
        } catch (Exception e) {
            return "";
        }
    }
}
