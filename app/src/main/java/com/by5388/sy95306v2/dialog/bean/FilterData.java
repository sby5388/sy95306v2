package com.by5388.sy95306v2.dialog.bean;

import java.util.ArrayList;
import java.util.List;

/**
 * @author by5388  on 2018/8/8.
 */

public class FilterData {
    private static List<FilterBean> items;

    public static List<FilterBean> getFilterItems() {
        if (items == null) {
            items = new ArrayList<>();
            items.add(new FilterBean("高铁", true));
            items.add(new FilterBean("动车", true));
            items.add(new FilterBean("特快", true));
            items.add(new FilterBean("普快", true));
            items.add(new FilterBean("其他", true));
        }
        return items;
    }

    public static boolean isAllSelect() {
        if (null == items) {
            getFilterItems();
        }
        boolean isAllSelect = true;
        for (FilterBean bean : items) {
            if (!bean.isSelected()) {
                isAllSelect = false;
                break;
            }
        }
        return isAllSelect;
    }
}
