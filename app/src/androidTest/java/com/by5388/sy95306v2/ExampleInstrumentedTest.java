package com.by5388.sy95306v2;

import android.content.Context;
import android.widget.EditText;

import org.junit.Test;
import org.junit.runner.RunWith;

import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.platform.app.InstrumentationRegistry;

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

        EditText editText = new EditText(appContext);
        editText.setText("*132#");
        System.out.println(editText.getText());
        System.out.println(editText.getText().toString());
        System.out.println(editText.getText().toString().trim());
    }
}
