package com.by5388.sy95306v2.dialog;

/**
 * @author Administrator  on 2020/3/19.
 */
public interface ITrainType {
    String[] getNames();

    boolean[] getChecks();

    void updateCheck(boolean[] newChecks);

    String getTitle();
}
