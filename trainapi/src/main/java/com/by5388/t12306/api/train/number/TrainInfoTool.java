package com.by5388.t12306.api.train.number;

import com.by5388.t12306.api.BaseNetTool;

/**
 * @author by5388  on 2019/5/4.
 */
public class TrainInfoTool extends BaseNetTool {

    public static final String BASE_URL = "https://www.12306.cn/kfzmpt/";

    @Override
    protected String getBaseUrl() {
        return BASE_URL;
    }
}
