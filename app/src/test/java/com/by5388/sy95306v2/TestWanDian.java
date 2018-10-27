package com.by5388.sy95306v2;

import com.by5388.sy95306v2.net.tz.TzQuery;

import org.junit.Test;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * @author by5388  on 2018/8/15.
 */
public class TestWanDian {
    @Test
    public void test() {
        String name = "饶平";
        String trainCode = "D7414";
        String date = "2018-10-27";
        TzQuery.queryLate(name, trainCode, 1, date)
                .subscribe(new Observer<String>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(String s) {
                        System.out.println(s.trim());
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
