package com.by5388.t12306.api.late.status;

import com.by5388.t12306.api.BaseNetTool;

/**
 * https://www.12306.cn/kfzmpt/zwdch/init
 * @author by5388  on 2019/5/5.
 */
public class LateNetTools extends BaseNetTool {
    private static final String BASE_URL = "https://www.12306.cn/kfzmpt/";

    @Override
    protected String getBaseUrl() {
        return BASE_URL;
    }
}
