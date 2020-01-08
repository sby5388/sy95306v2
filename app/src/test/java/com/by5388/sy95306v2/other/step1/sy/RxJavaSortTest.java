package com.by5388.sy95306v2.other.step1.sy;

import com.by5388.sy95306v2.module.shenyang.bean.TrainNumber;
import com.by5388.sy95306v2.module.shenyang.list.model.ITrainListModel;
import com.by5388.sy95306v2.module.shenyang.list.model.TrainListModel;
import com.by5388.sy95306v2.module.shenyang.list.model.sort.BaseTrainNumberSort;

import org.junit.Test;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Predicate;
import io.reactivex.internal.util.AppendOnlyLinkedArrayList;

import static com.by5388.sy95306v2.module.shenyang.list.model.sort.BaseTrainNumberSort.SORT_BY_START_TIME;

/**
 * @author by5388  on 2018/11/13.
 */
public class RxJavaSortTest {

    @Test
    public void testRxJavaSortTest() {
        // TODO: 2018/11/13 如何把一个Observable<List<T>> 变成多个的Observable<T>？
        int date = 20191202;
        String toStation = "IOQ";
        String fromStation = "CBQ";

        BaseTrainNumberSort sort = BaseTrainNumberSort.getTrainNumberSort(true, SORT_BY_START_TIME);
        ITrainListModel model = new TrainListModel();

        final Disposable subscribe = model.getTrainNumber(date, fromStation, toStation)
                .doOnNext(trainNumbers -> trainNumbers.sort(sort))
                .subscribe(this::show, throwable -> {
                    System.out.println(throwable.getLocalizedMessage());
                });
    }

    private void show(List<TrainNumber> trainNumbers) {
        if (null == trainNumbers) {
            return;
        }
        int count = 0;
        for (TrainNumber number : trainNumbers) {
            if (number != null) {
                Observable.just(number)
                        .filter((AppendOnlyLinkedArrayList.NonThrowingPredicate<TrainNumber>) TrainNumber::isFirstStation)
                        .filter(new Predicate<TrainNumber>() {
                            @Override
                            public boolean test(TrainNumber trainNumber) throws Exception {
                                return trainNumber.isFirstStation();
                            }
                        })
                        .subscribe(number1 -> {
                            //todo  输出总数
                            System.out.println("-------");
                            System.out.println("\t" + number1.getSTCODE());
                            System.out.println("\t" + number1.getFST() + "\t" + number1.getTST());
                            System.out.println("\t" + number1.getSTIME() + "\t" + number1.getATIME());
                        });
            }
        }
    }
}
