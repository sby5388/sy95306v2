package com.by5388.sy95306v2;

import android.support.annotation.NonNull;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * @author by5388  on 2018/8/10.
 */

public class ListTest {
    @Test
    public void listTest() {
        List<String> strings = new ArrayList<>(4);
        strings.add(null);
        strings.add(null);
        strings.add(null);
        strings.add(null);

        sout(strings.size());
        sout(getData(strings, 0));
        sout(getData(strings, 0));
        sout(getData(strings, 1));
        assertEquals(4, 2 + 2);
    }

    private String getData(@NonNull List<String> strings, int position) {
        String s = strings.get(position);
        if (null == s) {
            s = getData(position);
            strings.add(position, s);
        }
        return s;
    }

    private String getData(int position) {
        sout("获取数据：" + position);
        String s = "";
        switch (position) {
            case 0:
                s = "00";
                break;
            case 1:
                s = "11";
                break;
            case 2:
                s = "22";
                break;
            case 3:
                s = "33";
                break;
            default:
                break;
        }
        return s;
    }


    private void sout(Object o) {
        System.out.println(o.toString());
    }


    @Test
    public void getFavoriteStations() {
        String file = "bji|北京|BJP|0@sha|上海|SHH|1@tji|天津|TJP|2@cqi|重庆|CQW|3@csh|长沙|CSQ|4" +
                "@cch|长春|CCT|5@cdu|成都|CDW|6@fzh|福州|FZS|7@gzh|广州|GZQ|8" +
                "@gya|贵阳|GIW|9@hht|呼和浩特|HHC|10@heb|哈尔滨|HBB|11@hfe|合肥|HFH|12" +
                "@hzh|杭州|HZH|13@hko|海口|VUQ|14@jna|济南|JNK|15@kmi|昆明|KMM|16" +
                "@lsa|拉萨|LSO|17@lzh|兰州|LZJ|18@nni|南宁|NNZ|19@nji|南京|NJH|20" +
                "@nch|南昌|NCG|21@sya|沈阳|SYT|22@sjz|石家庄|SJP|23@tyu|太原|TYV|24" +
                "@wlq|乌鲁木齐南|WMR|25@wha|武汉|WHN|26@xni|西宁|XNO|27@xan|西安|XAY|28@ych|银川|YIJ|29@zzh|郑州|ZZF|30@szh|深圳|SZQ|shenzhen|sz|31@xme|厦门|XMS|xiamen|xm|32";
        String[] cities = file.split("@");
        for (String city : cities) {

            String[] item = city.split("\\|");
            System.out.print("\"" + item[2] + "\",");
        }
    }
}
