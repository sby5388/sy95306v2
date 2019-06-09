package com.by5388.t12306.api.residual.ticket;

import com.by5388.t12306.api.BaseNetTool;

/**
 * 余票查询
 *
 * @author by5388  on 2019/5/4.
 */
public class ResidualTicketTool extends BaseNetTool {
    private static final String BASE_URL = "https://www.12306.cn/kfzmpt/";

    @Override
    protected String getBaseUrl() {
        return BASE_URL;
    }
}
