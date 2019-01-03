package com.by5388.sy95306v2.chengdu.api.screen;

import com.by5388.sy95306v2.base.BaseNetTools;

/**
 * 成都车站大屏幕
 *
 * @author by5388  on 2018/6/16.
 */

public class CdScreenNetTools extends BaseNetTools {
    private static final String BASE_URL = "http://www.cd-rail.com:9090/CTKF/";
    //    http://www.cd-rail.com:9090/CTKF/GeneralProServlet
    //    http://www.cd-rail.com:9090/CTKF/GeneralProServlet
    // 车站：code=C50102&login=["10.192.111.79","hhs","hhs"]&sql=[]&where=[]&order=[]
    //候乘：code=C50101&login=["10.192.111.79","hhs","hhs"]&sql=["20180817","GIW"]&where=[]&order=[]
    //接站：code=C5054&login=["10.192.111.79","hhs","hhs"]&sql=["20180817","GIW"]&where=[]&order=[]

    @Override
    protected String getBaseUrl() {
        return BASE_URL;
    }
}
