package com.by5388.sy95306v2;

import com.by5388.sy95306v2.bean.tz.number.NumberDataBean;
import com.by5388.sy95306v2.fragment.tz.GetPassCodeImpl;
import com.by5388.sy95306v2.fragment.tz.IGetPassCodeService;

import org.junit.Test;

import java.util.HashMap;
import java.util.List;

import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;

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
                .subscribe(new Consumer<List<NumberDataBean>>() {
                    @Override
                    public void accept(List<NumberDataBean> numberDataBeans) {
                        System.out.println("start");
                        for (NumberDataBean bean : numberDataBeans) {
                            System.out.println(bean.getStart_station_name());
                        }
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) {
                        System.err.println(throwable.getLocalizedMessage());
                    }
                }, new Action() {
                    @Override
                    public void run() {
                        System.out.println("finish-------------");
                    }
                });
    }
}
