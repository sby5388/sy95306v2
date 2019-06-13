package com.android.train;

import android.content.Context;
import android.os.Handler;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import com.android.train.bean.QueryDetail;
import com.android.train.net.api.QueryThread;
import com.android.train.net.api.ResultCallback;

import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.assertEquals;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class ExampleInstrumentedTest {
    @Test
    public void useAppContext() {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getTargetContext();

        assertEquals("com.android.train", appContext.getPackageName());

        QueryThread queryThread = new QueryThread(new Handler(), new ResultCallback() {
        });
        queryThread.query(new QueryDetail("20190413","D7515"));
    }
}
