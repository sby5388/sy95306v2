package com.by5388.sy95306v2.get.all.station.list;

import com.by5388.sy95306v2.exception.NetworkException;

import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

/**
 * @author Administrator  on 2019/11/26.
 */
public class GetDataListTest {
    private GetDataList mSubject;

    @Before
    public void setUp() throws Exception {
        mSubject = new GetDataList();
    }

    @Test
    public void getCount() throws IOException, NetworkException {
        final int count = mSubject.getCount();
        System.out.println(count);
    }
}