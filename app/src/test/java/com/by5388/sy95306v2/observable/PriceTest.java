package com.by5388.sy95306v2.observable;

import org.junit.Test;

/**
 * @author by5388  on 2018/8/10.
 */

public class PriceTest {
    @Test
    public void test() throws NumberFormatException {
        String oPrice = "商务座:1301.50,一等座:694.00,二等座:417.00,99999,67,98";
        String[] prices = oPrice.split(",");
        float minValue = Float.MAX_VALUE;
        for (String price : prices) {
            price = price.replaceAll("[^0-9.]", "");
            System.out.println(price);
            float currentValue = Float.parseFloat(price);
            if (currentValue < minValue) {
                minValue = currentValue;
            }
        }
        System.out.println("最小值：" + minValue);

    }
}
