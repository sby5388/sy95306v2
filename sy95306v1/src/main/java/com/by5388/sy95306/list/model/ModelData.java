package com.by5388.sy95306.list.model;

import java.util.ArrayList;
import java.util.List;

/**
 * @author by5388  on 2018/8/9.
 */

class ModelData {
    private static List<String> type;

    private static List<String> getTrainType() {
        if (null == type) {
            type = new ArrayList<>();
            //8：G、C
            type.add(String.valueOf(8));
            //D:D
            type.add("D");
            //1：Z
            type.add(String.valueOf(1));
            //2:T
            type.add(String.valueOf(2));
            //0:K,Y,
            type.add(String.valueOf(0));
            //3:普速列车
            type.add(String.valueOf(3));
        }
        return type;
    }

    static List<String> getSelectedType(List<Integer> selected) {
        List<String> types = getTrainType();
        List<String> selectedType = new ArrayList<>();
        for (String type : types) {
            if (selected.contains(types.indexOf(type))) {
                selectedType.add(type);
            }
        }
        return selectedType;
    }
}
