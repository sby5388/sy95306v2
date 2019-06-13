package com.by5388.sy95306v2.module.guangzhou.api;

import com.by5388.sy95306v2.base.BaseNetTools;

/**
 * @author by5388  on 2018/6/16.
 */

public class GzNetTools extends BaseNetTools {
    /**
     * 广铁查询总接口
     */
    private static final String BASE_URL = "http://www.gtbyxx.com/wxg/inter/ky/";

    @Override
    protected String getBaseUrl() {
        return BASE_URL;
    }
}
