package com.by5388.sy95306v2.jsoup;

import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

/**
 * @author Administrator  on 2019/12/20.
 */
public class VersionToolTest {
    private VersionTool mSubjects;

    @Before
    public void setUp() throws Exception {
        mSubjects = new VersionTool();
    }

    @Test
    public void loadData() throws IOException {
        mSubjects.loadData("https://kyfw.12306.cn/otn/leftTicket/init");
    }
}