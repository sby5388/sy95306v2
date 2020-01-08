package com.by5388.sy95306v2.jsoup;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;

/**
 * @author Administrator  on 2019/12/20.
 */
public class VersionTool {
    private String mVersion = "un-know";


    public void loadData(String url) throws IOException {
        final Document document = Jsoup.connect(url).method(Connection.Method.GET).get();
        System.out.println(document.toString());

    }

    public String getVersion() {
        return mVersion;
    }
}
