package com.by5388.sy95306v2.combination;

import android.support.annotation.NonNull;

import com.by5388.sy95306v2.bean.shenyang.TrainDetail;
import com.by5388.sy95306v2.net.sy.SyNetTools;
import com.by5388.sy95306v2.net.sy.SyService;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;

/**
 * @author by5388  on 2018/8/22.
 */
public class CombinationTest {
    static int count = 0;

    private Observable<List<TrainDetail>> getObservable(int date, String trainCode) {
        SyService syService = new SyNetTools().getRetrofit().create(SyService.class);
        return syService.getTrainByTrainCode(date, trainCode);
    }

    @Test
    public void test() {
        String fromStationName = "厦门北";
        int date = 20180830;
        String trainCode = "D2286";
        getObservable(date, trainCode)
                .subscribe(getSyConsumer(fromStationName), getThrowableConsumer());


    }


    private Consumer<Throwable> getThrowableConsumer() {
        return new Consumer<Throwable>() {
            @Override
            public void accept(Throwable throwable) {
                System.err.println(throwable.getLocalizedMessage());
            }
        };
    }

    private Consumer<List<TrainDetail>> getSyConsumer(String fromStationName) {
        return new Consumer<List<TrainDetail>>() {
            @Override
            public void accept(List<TrainDetail> trainDetails) {
                List<String> names = new ArrayList<>();
                for (TrainDetail detail : trainDetails) {
                    names.add(detail.getSNAME());
                    System.out.println(detail.getSNAME());
                }
                int position = names.indexOf(fromStationName);
                System.out.println(position);
                if (position < 0) {
                    System.err.println("错误");
                    return;
                }
                List<String> newNames = new ArrayList<>();
                for (int i = position + 1; i < names.size(); i++) {
                    System.out.println(names.get(i));
                    newNames.add(names.get(i));
                }
                getDetailData(newNames);
            }
        };
    }

    class Person {
        String name;
        int number;

        Person(String name) {
            count++;
            this.name = name;
            this.number = count;
        }

        @Override
        public String toString() {
            return "Person{" +
                    "name='" + name + '\'' +
                    ", number=" + number +
                    '}';
        }
    }

    private void getDetailData(@NonNull List<String> names) {
        Observable.fromIterable(names)
                .flatMap(new Function<String, ObservableSource<Person>>() {
                    @Override
                    public ObservableSource<Person> apply(String s) {
                        return Observable.just(new Person(s));
                    }
                }).subscribe(new Consumer<Person>() {
            @Override
            public void accept(Person person) {
                System.err.println(person);
            }
        });
    }

}

