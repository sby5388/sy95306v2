package com.by5388.sy95306v2.net.sy;

import com.by5388.sy95306v2.net.BaseNetTools;

/**
 * @author by5388  on 2018/6/16.
 */

public class SyNetTools extends BaseNetTools {
    private static final String BASE_URL = "http://61.161.203.55/mobile.myweixin.com/";
    @SuppressWarnings("unused")
    private static final String BASE_URL2 = "http://kyfw.sytlj.com/mobile.myweixin.com/";

    @Override
    protected String getBaseUrl() {
        return BASE_URL;
    }
}
