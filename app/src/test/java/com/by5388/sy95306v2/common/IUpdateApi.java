package com.by5388.sy95306v2.common;

/**
 * @author Administrator  on 2019/9/19.
 */
public interface IUpdateApi {
    boolean isEmpty();

    void insert();

    void clear();

    void getVersion();

    boolean setVersion(String version);

    boolean isNeedUpgrade();

}
