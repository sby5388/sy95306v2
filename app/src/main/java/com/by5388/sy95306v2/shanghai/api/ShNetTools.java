package com.by5388.sy95306v2.shanghai.api;

import com.by5388.sy95306v2.base.BaseNetTools;

/**
 * 上海铁路局
 *
 * @author by5388  on 2018/6/16.
 */

public class ShNetTools extends BaseNetTools {
    private static final String BASE_URL = "http://port.st12306.cn:8080/AppService/";

    @Override
    protected String getBaseUrl() {
        return BASE_URL;
    }
}
