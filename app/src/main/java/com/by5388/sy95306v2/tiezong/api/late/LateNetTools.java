package com.by5388.sy95306v2.tiezong.api.late;

import com.by5388.sy95306v2.base.BaseNetTools;

/**
 * 正晚点查询
 * 12306官网数据
 *
 * @author by5388  on 2018/6/16.
 */

public class LateNetTools extends BaseNetTools {
    private static final String BASE_URL = "http://dynamic.12306.cn/";

    @Override
    protected String getBaseUrl() {
        return BASE_URL;
    }
}
