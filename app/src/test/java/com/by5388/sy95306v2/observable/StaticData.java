package com.by5388.sy95306v2.observable;

import java.util.ArrayList;
import java.util.List;

/**
 * @author by5388  on 2018/7/28.
 */

class StaticData {
    static final Integer[] number = {
            1, 2, 3, 4, 5, 6, 7, 8, 9
    };

    static List<String> names;

    public static List<String> getNames() {
        if (null == names) {
            names = new ArrayList<>();
        }
        return names;
    }
}
