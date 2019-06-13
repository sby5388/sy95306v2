package com.by5388.sy95306v2.main.model;

import com.by5388.sy95306v2.module.shenyang.bean.Station;

import java.io.InputStream;
import java.util.List;

/**
 * @author by5388  on 2019/1/2.
 */
public interface IStationJson {
    String TAG = "IStationJson";

    /**
     * 车站数量
     *
     * @param stationList     车站列表数据
     * @param stationListFile 结果
     * @return 车站数量
     */
    int getStationCount(final String stationList, StringBuilder stationListFile);


    /**
     * 获取版本号的那一行字符串
     * InputStream toString
     *
     * @param is 输入流
     * @return 获取版本号的那一行字符串
     */
    String jsonToString(InputStream is);

    /**
     * 获取版本号
     *
     * @param lineString 版本号的那一行字符串
     * @return 版本号
     * 获取网络上的版本号
     * todo 算法待优化
     */
    String getVersion(final String lineString);
    String getVersion(InputStream inputStream);

    /**
     * 提取车站列表
     *
     * @param cityList 车站文字
     * @return 车站列表
     */
    List<Station> getCityList(String cityList);

    /**
     * inputStream to String
     *
     * @param inputStream io
     * @return String
     */
    String getCityList(InputStream inputStream);
}
