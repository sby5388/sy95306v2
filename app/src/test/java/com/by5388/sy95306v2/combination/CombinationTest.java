package com.by5388.sy95306v2.combination;

import com.by5388.sy95306v2.module.shenyang.bean.TrainDetail;
import com.by5388.sy95306v2.module.shenyang.net.api.SyNetTools;
import com.by5388.sy95306v2.module.shenyang.net.api.SyService;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.functions.Consumer;

/**
 * @author by5388  on 2018/8/22.
 */
public class CombinationTest {

    private Observable<List<TrainDetail>> getObservable(final int date, final String trainCode) {
        SyService syService = new SyNetTools().getRetrofit().create(SyService.class);
        return syService.getTrainByTrainCode(date, trainCode);
    }

    @Test
    public void test() {
        final String fromStationName = "厦门北";
        final int date = 20191008;
        final String trainCode = "D2286";
        getObservable(date, trainCode)
                .subscribe(getSyConsumer(fromStationName), getThrowableConsumer());


    }


    private Consumer<Throwable> getThrowableConsumer() {
        return throwable -> System.err.println(throwable.getLocalizedMessage());
    }

    private Consumer<List<TrainDetail>> getSyConsumer(final String fromStationName) {
        return trainDetails -> {
            List<String> names = new ArrayList<>();
            for (TrainDetail detail : trainDetails) {
                names.add(detail.getSNAME());
                System.out.println(detail.getSNAME());
            }
            int position = names.indexOf(fromStationName);
            System.out.println(position);
            if (position < 0) {
                System.err.println("错误");
                throw new RuntimeException("名字不正确");
            }
            List<String> newNames = new ArrayList<>();
            for (int i = position + 1; i < names.size(); i++) {
                System.out.println(names.get(i));
                newNames.add(names.get(i));
            }
        };
    }

}

