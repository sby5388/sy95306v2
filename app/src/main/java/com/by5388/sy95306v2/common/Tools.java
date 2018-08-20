package com.by5388.sy95306v2.common;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author by5388  on 2018/6/6.
 */

public class Tools {

    /**
     * 时间字符串长度
     */
    private static final int TIME_LENGTH = 4;
    /**
     * 始发站、终到站时刻表
     */
    private static final String FIRST_END_STATION_TIME = "----";
    /**
     * 60分钟
     */
    private static final int MINUTE_OF_HOUR = 60;
    /**
     * 一天分钟数
     */
    private static final int MINUTE_OF_DAY = 1440;
    private final static String TAG = "Tools";

    /**
     * 城市选择列表：默认的城市
     */
    private static final String[] STATION_NAME_UPPER = {
            "BJP", "SHH", "TJP", "GZQ",
            "CQW", "CSQ", "CDW", "HHC",
            "HBB", "HFH", "NCG", "NJH",
            "SZQ", "JNK", "HZH", "WHN",
            "TYV", "SJP", "ZZF", "XAY",
            "SYT", "CCT", "DLT", "JLL",
            "JZD", "TLD", "CFD", "THL",
            "YJL", "TML", "BXT", "DUT",
            "TLT", "AST", "LYT", "BCT",
    };

    @SuppressWarnings("unused")
    private static final String[] STATION_NAME = {
            "北京", "上海", "天津", "广州",
            "重庆", "长沙", "成都", "呼和浩特",
            "哈尔滨", "合肥", "南昌", "南京",
            "深圳", "济南", "杭州", "武汉",
            "太原", "石家庄", "郑州", "西安",
            "沈阳", "长春", "大连", "吉林",
            "锦州", "通辽", "赤峰", "通化",
            "延吉", "图们", "本溪", "丹东",
            "铁岭", "鞍山", "辽阳", "白城",
    };

    /**
     * 正则表达式：全部数字，匹配:code
     */
    private final static String REG_NUMBER = "^[0-9]*$";
    /**
     * 匹配英文
     */
    private final static String REG_ABC = "^[a-zA-Z]*$";


    private static boolean regNumber(String string) {
        Pattern p = Pattern.compile(REG_NUMBER, Pattern.CASE_INSENSITIVE);
        Matcher matcher = p.matcher(string);
        return matcher.matches();
    }

    private static boolean regEnglish(String string) {
        Pattern p = Pattern.compile(REG_ABC, Pattern.CASE_INSENSITIVE);
        Matcher matcher = p.matcher(string);
        return matcher.matches();
    }

    /**
     * 对String类型的时间字符串进行切割--->"xx:xx"
     */
    public static String showTime(String time) {
        if (TIME_LENGTH != time.length()) {
            return time;
        }
        return time.substring(0, 2) + ":" + time.substring(2, 4);
    }

    /**
     * 中途停站时间
     *
     * @param arriveTime 到达时间
     * @param leaveTime  出发时间
     * @return 停留时间
     */
    public static String stopTime(String arriveTime, String leaveTime) {
        if (FIRST_END_STATION_TIME.equals(arriveTime) || FIRST_END_STATION_TIME.equals(leaveTime)) {
            return String.valueOf(0);
        }
        int stopTime = calculatorTime(leaveTime) - calculatorTime(arriveTime);
        while (stopTime < 0) {
            stopTime += MINUTE_OF_DAY;
        }
        return stopTime + "分";
    }

    public static int calculatorTime(String time) {
        int hour = Integer.parseInt(time.substring(0, 2));
        int minute = Integer.parseInt(time.substring(2, 4));
        return hour * MINUTE_OF_HOUR + minute;
    }

    /**
     * 列车全程运行时间
     *
     * @param stringTime 历时
     * @return 列车全程运行时间
     */
    public static String getAllSpendTime(String stringTime) {
        int allTime = Integer.parseInt(stringTime);
        StringBuilder stringBuilder = new StringBuilder();
        if (allTime >= MINUTE_OF_DAY) {
            stringBuilder.append(allTime / MINUTE_OF_DAY)
                    .append("天");
            allTime = allTime % MINUTE_OF_DAY;
        }
        if (allTime >= MINUTE_OF_HOUR) {
            stringBuilder.append(allTime / MINUTE_OF_HOUR)
                    .append("小时");
            allTime = allTime % MINUTE_OF_HOUR;
        }
        stringBuilder.append(allTime)
                .append("分");
        return stringBuilder.toString();
    }


}
