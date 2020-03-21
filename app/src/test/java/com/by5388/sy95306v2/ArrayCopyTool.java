package com.by5388.sy95306v2;

import org.junit.Test;

import java.util.Arrays;

/**
 * @author Administrator  on 2020/3/19.
 */
public class ArrayCopyTool {
    @Test
    public void start() {
        final int[] array1 = {20, 30, 40};
        final int[] array2 = {60, 70, 80};
        System.out.println(Arrays.toString(array1));
        System.out.println(Arrays.toString(array2));
        //把2的内容复制到1
        System.arraycopy(array2, 0, array1, 0, array1.length);

        System.out.println(Arrays.toString(array1));
        System.out.println(Arrays.toString(array2));

    }
}
