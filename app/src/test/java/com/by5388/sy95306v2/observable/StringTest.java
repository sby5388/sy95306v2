package com.by5388.sy95306v2.observable;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * @author by5388  on 2018/8/2.
 */

public class StringTest {
    @Test
    public void test() {
        String A = "2018-08-01";
        String B = "2018/08/01";

        String C = B.replace("/", "-");
        System.out.println(C);
        assertEquals(A, C);
    }

    @Test
    public void test2() {
        String A = "151--";
        String B = "151";

        String C = A.replaceAll("[^0-9]", "");
        System.out.println(C);
        assertEquals(B, C);
    }
}
