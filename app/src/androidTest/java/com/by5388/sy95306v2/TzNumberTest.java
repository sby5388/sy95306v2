package com.by5388.sy95306v2;

import com.by5388.sy95306v2.tiezong.bean.number.NumberDataBean;
import com.by5388.sy95306v2.tiezong.GetPassCodeImpl;
import com.by5388.sy95306v2.tiezong.IGetPassCodeService;

import org.junit.Test;

import java.util.HashMap;

/**
 * @author by5388  on 2018/8/24.
 */
public class TzNumberTest {

    //    public static void main(String[] args) {
//        TzNumberTest tzNumberTest = new TzNumberTest();
//        tzNumberTest.test();
//    }
    @Test
    public void test() {
        System.out.println("start-----");
        IGetPassCodeService service = new GetPassCodeImpl(new HashMap<>());
        service.getListNumberDataBean("6k0000D92200",
                "EFQ",
                "IZQ",
                "2018-08-27")
                .subscribe(numberDataBeans -> {
                    System.out.println("start");
                    for (NumberDataBean bean : numberDataBeans) {
                        System.out.println(bean.getStart_station_name());
                    }
                }, throwable -> System.err.println(throwable.getLocalizedMessage()), () -> System.out.println("finish-------------"));
    }
}
