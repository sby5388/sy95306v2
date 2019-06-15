package com.by5388.sy95306v2.module.tiezong.api.version2;

import com.by5388.sy95306v2.base.BaseNetTools;

/**
 * @author Administrator  on 2019/6/14.
 */
public class TzNetTools2 extends BaseNetTools {
    private static final String BASE_URL = "https://www.12306.cn/kfzmpt/";

    @Override
    protected String getBaseUrl() {
        return BASE_URL;
    }
}
