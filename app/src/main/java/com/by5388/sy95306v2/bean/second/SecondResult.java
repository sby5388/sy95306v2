package com.by5388.sy95306v2.bean.second;

/**
 * @author by5388
 * @date 2018/8/13 18:39
 */
public class SecondResult {

    /**
     * query : {"des":"贵阳","source":"","time":"2018-08-13 18:33:08","ori":"深圳","date":"2018-08-14","jsoncallback":"","tc":""}
     * cache : true
     * data : 2018-08-14,0,G2926,深圳北,贵阳北,07:58,12:56,04:58,--,--,无,无,商务:无,无,;2018-08-14,1,K486,深圳,贵阳,11:05,08:58,21:53,4,无,--,无,,100,;2018-08-14,2,K830,深圳东,贵阳,24:00,24:00,99:59,--,--,--,--,,--,;2018-08-14,3,G2930,深圳北,贵阳北,14:54,20:15,05:21,--,--,无,无,商务:无,--,;2018-08-14,4,G2922,深圳北,贵阳北,15:25,20:24,04:59,--,--,无,无,商务:无,--,
     */

    private QueryBean query;
    private String cache;
    private String data;

    public QueryBean getQuery() {
        return query;
    }

    public void setQuery(QueryBean query) {
        this.query = query;
    }

    public String getCache() {
        return cache;
    }

    public void setCache(String cache) {
        this.cache = cache;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

}
