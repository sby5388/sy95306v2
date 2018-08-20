package com.by5388.sy95306v2.net.cd.yp;

import com.by5388.sy95306v2.net.BaseNetTools;

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
