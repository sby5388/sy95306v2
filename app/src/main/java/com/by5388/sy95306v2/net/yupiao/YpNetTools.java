package com.by5388.sy95306v2.net.yupiao;

import com.by5388.sy95306v2.net.BaseNetTools;

/**
 * 余票查询
 *
 * @author by5388  on 2018/6/16.
 */

public class YpNetTools extends BaseNetTools {
    private static final String BASE_URL = "http://yupiao.info/api/";

    @Override
    protected String getBaseUrl() {
        return BASE_URL;
    }
}
