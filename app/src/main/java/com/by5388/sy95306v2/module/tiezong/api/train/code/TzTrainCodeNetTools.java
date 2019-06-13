package com.by5388.sy95306v2.module.tiezong.api.train.code;

import com.by5388.sy95306v2.base.BaseNetTools;

/**
 * @author by5388  on 2019/1/22.
 */
public class TzTrainCodeNetTools extends BaseNetTools {
    private static final String BASE_URL = "https://search.12306.cn/search/v1/train/";

    @Override
    protected String getBaseUrl() {
        return BASE_URL;
    }
}
