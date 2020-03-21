package com.by5388.sy95306v2.dialog;

import android.text.TextUtils;

import java.util.ArrayList;

/**
 * @author Administrator  on 2020/3/19.
 */
public class TrainType implements ITrainType {
    public static final String TAG = TrainFilterDialog2.TAG;
    private static final int COUNT_TYPE = 5;
    private String[] mNames = new String[COUNT_TYPE];
    private boolean[] mChecks = new boolean[COUNT_TYPE];

    private TrainType() {
        final ArrayList<String> arrayList = new ArrayList<>(COUNT_TYPE);
        arrayList.add("高铁");
        arrayList.add("动车");
        arrayList.add("特快");
        arrayList.add("普快");
        arrayList.add("其他");
        mNames = arrayList.toArray(mNames);
        for (int i = 0; i < COUNT_TYPE; i++) {
            mChecks[i] = true;
        }
    }

    public static ITrainType getInstance() {
        return Singleton.INSTANCE;
    }

    @Override
    public String[] getNames() {
        return mNames;
    }

    @Override
    public boolean[] getChecks() {
        return mChecks;
    }

    @Override
    public void updateCheck(boolean[] newChecks) {
        if (newChecks == null) {
            return;
        }
        if (newChecks.length != COUNT_TYPE) {
            return;
        }
        System.arraycopy(newChecks, 0, mChecks, 0, COUNT_TYPE);
    }

    @Override
    public String getTitle() {
        if (isSelectAll()) {
            return "全部";
        }
        return getSelectItem();
    }

    private String getSelectItem() {
        final StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < COUNT_TYPE; i++) {
            if (mChecks[i]) {
                stringBuilder.append(mNames[i]).append(";");
            }
        }
        if (TextUtils.isEmpty(stringBuilder)) {
            stringBuilder.append("未选择");
        }
        return stringBuilder.toString();
    }

    private boolean isSelectAll() {
        boolean selectAll = true;
        for (boolean b : mChecks) {
            if (!b) {
                selectAll = false;
                break;
            }
        }
        return selectAll;
    }

    private static final class Singleton {
        private static final ITrainType INSTANCE = new TrainType();
    }

}
