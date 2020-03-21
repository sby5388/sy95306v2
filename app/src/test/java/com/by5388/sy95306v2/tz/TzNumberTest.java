package com.by5388.sy95306v2.tz;

import com.by5388.sy95306v2.module.tiezong.api.pass.code.GetPassCodeImpl;
import com.by5388.sy95306v2.module.tiezong.api.pass.code.IGetPassCodeService;
import com.by5388.sy95306v2.module.tiezong.bean.number.NumberDataBean;

import org.junit.Test;

/**
 * @author by5388  on 2018/8/24.
 */
public class TzNumberTest {
    @Test
    public void test() {
        System.out.println("start-----");
        IGetPassCodeService service = new GetPassCodeImpl();
        service.getListNumberDataBean(
                "6i000D22880C",
                "IOQ",
                "AOH",
                "2019-09-13")
                .subscribe(numberDataBeans -> {
                            System.out.println("start");
                            for (NumberDataBean bean : numberDataBeans) {
                                System.out.println(bean.getStationName());
                            }
                        }, throwable -> System.err.println(throwable.getLocalizedMessage()),
                        () -> System.out.println("finish-------------"));
    }
}
