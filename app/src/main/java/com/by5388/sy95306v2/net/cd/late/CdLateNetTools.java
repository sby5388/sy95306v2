package com.by5388.sy95306v2.net.cd.late;

import com.by5388.sy95306v2.net.BaseNetTools;

/**
 * @author by5388  on 2018/6/16.
 */

public class CdLateNetTools extends BaseNetTools {

    private static final String BASE_URL = "http://61.236.123.44/ExData/services/";

    @Override
    protected String getBaseUrl() {
        return BASE_URL;
    }
}
