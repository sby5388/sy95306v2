package com.by5388.sy95306v2.other.step1.sy;

import com.by5388.sy95306v2.shenyang.list.model.ITrainListModel;
import com.by5388.sy95306v2.shenyang.list.model.TrainListModel;
import com.by5388.sy95306v2.shenyang.list.model.sort.BaseTrainNumberSort;
import com.by5388.sy95306v2.shenyang.bean.TrainNumber;

import org.junit.Test;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.internal.util.AppendOnlyLinkedArrayList;

import static com.by5388.sy95306v2.shenyang.list.model.sort.BaseTrainNumberSort.SORT_BY_START_TIME;

/**
 * @author by5388  on 2018/11/13.
 */
public class RxJavaSortTest {
    private final String fromStation = "CBQ";

    @Test
    public void testRxJavaSortTest() {
        // TODO: 2018/11/13 如何把一个Observable<List<T>> 变成多个的Observable<T>？
        int date = 20181228;


        BaseTrainNumberSort sort = BaseTrainNumberSort.getTrainNumberSort(true, SORT_BY_START_TIME);
        ITrainListModel model = new TrainListModel();
        String toStation = "IOQ";
        model.getTrainNumber(date, fromStation, toStation)
                .doOnNext(trainNumbers -> trainNumbers.sort(sort))
                .subscribe(this::show);
    }

    private void show(List<TrainNumber> trainNumbers) {
        if (null == trainNumbers) {
            return;
        }
         int count = 0;
        for (TrainNumber number : trainNumbers) {
            if (number != null) {
                Observable.just(number)
                        .filter((AppendOnlyLinkedArrayList.NonThrowingPredicate<TrainNumber>) number12 ->
                                        number12.isFirstStation()
//                                        ||number12.isLastStation()
                        )
                        //fromStation.equals(number12.getFST()) && (number12.getSTCODE().startsWith("D7") || number12.getSTCODE().startsWith("G6")))
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
