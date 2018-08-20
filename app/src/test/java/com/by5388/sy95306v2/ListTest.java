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

    public String getData(@NonNull List<String> strings, int position) {
        String s = strings.get(position);
        if (null == s) {
            s = getData(position);
            strings.add(position, s);
        }
        return s;
    }

    public String getData(int position) {
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


    public void sout(Object o) {
        System.out.println(o.toString());
    }
}
