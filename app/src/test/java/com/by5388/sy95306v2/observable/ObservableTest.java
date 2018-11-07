package com.by5388.sy95306v2.observable;

import org.junit.Test;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.functions.Function;

/**
 * @author by5388  on 2018/7/28.
 */

public class ObservableTest {
    @Test
    public void test() {
        Observable<List<String>> observable = Observable.fromArray(StaticData.number)
                .map(integer -> {
                    String string = String.valueOf(integer);
                    StaticData.names.add(string);
                    return string;
                })
                .flatMap((Function<String, ObservableSource<List<String>>>) s -> Observable.just(StaticData.names));
        observable.subscribe(strings -> {
            for (String s : strings) {
                System.out.println(s);
            }
        });
//        assertEquals(4, 2 + 2);

    }


}
