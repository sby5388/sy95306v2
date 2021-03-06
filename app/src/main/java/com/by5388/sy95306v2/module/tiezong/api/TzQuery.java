package com.by5388.sy95306v2.module.tiezong.api;

import com.by5388.sy95306v2.module.tiezong.api.late.TzLateNetTools;
import com.by5388.sy95306v2.module.tiezong.api.late.TzLateService;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import io.reactivex.Observable;

/**
 * 统一的查询接口
 *
 * @author by5388  on 2018/8/14.
 */
public class TzQuery {
    /**
     * 到达
     */
    public static final int ARRIVE = 0;
    /**
     * 离开
     */
    public static final int LEAVE = 1;
    private static final TzLateService SERVICE = new TzLateNetTools().getRetrofit().create(TzLateService.class);

    /**
     * 统一的查询接口
     *
     * @param stationName 名称
     * @param code        编码
     * @param queryType   查询类型：到或者发
     * @param date        日期
     * @return 状态
     */
    public static Observable<String> queryLate(String stationName, String code, int queryType, String date) {
        if (ARRIVE != queryType && LEAVE != queryType) {
            throw new RuntimeException();
        }
        String stationCode = getCode(stationName);
        return SERVICE.queryLate(stationName, code, queryType, date, stationCode, System.currentTimeMillis())
                .map(responseBody -> {
                    String message = "";
                    try {
                        message = responseBody.string();
                        message = getRealMessage(message.trim());
                    } catch (IOException e) {
                        //
                    }
                    return message;
                });
    }

    /**
     * 对消息进行简化处理
     *
     * @param message 原始文本
     * @return 处理后的
     */
    private static String getRealMessage(String message) {
        final String previous = "预计";
        final String firstStation = "始发站";
        final String lastStation = "终点站";
        final String currentWithOut = "目前暂无";
        final String timeOut = "不在查询时间内";
        if (message.contains(firstStation)) {
            return firstStation;
        }
        if (message.contains(lastStation)) {
            return lastStation;
        }
        if (message.contains(currentWithOut)) {
            return timeOut;
        }
        StringBuilder builder = new StringBuilder();
        if (message.contains(previous)) {
            builder.append(previous);
        }
        String[] detail = message.split("，");
        if (detail.length > 1) {
            builder.append(detail[1]);
            return builder.toString();
        }
        return message;
    }

    /**
     * 车站名称转成UTF-8编码
     *
     * @param stationName 车站
     * @return 编码
     */
    private static String getCode(String stationName) {
        try {
            return URLEncoder.encode(stationName, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            return stationName;
        }
    }
}
