package com.by5388.t12306.api.vague;

import com.by5388.t12306.api.BaseNetTool;

/**
 * @author by5388  on 2019/1/23.
 */
public class VagueNetTool extends BaseNetTool {
    private static final String BASE_URL = "https://search.12306.cn/search/v1/train/";
    @Override
    protected String getBaseUrl() {
        return BASE_URL;
    }
}
