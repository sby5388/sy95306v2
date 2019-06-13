package com.by5388.sy95306v2.module.chengdu.api.yp;

import com.by5388.sy95306v2.base.BaseNetTools;

/**
 * 成都余票查询
 *
 * @author by5388  on 2018/6/16.
 */

public class CdYpNetTools extends BaseNetTools {

    private static final String BASE_URL = "http://www.cd-rail.cn/RailWay/";

    @Override
    protected String getBaseUrl() {
        return BASE_URL;
    }
}
