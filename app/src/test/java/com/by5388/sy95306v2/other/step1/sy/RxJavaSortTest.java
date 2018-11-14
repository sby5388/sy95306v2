package com.by5388.sy95306v2.other.step1.sy;

import com.by5388.sy95306v2.activity.sy.list.model.ITrainListModel;
import com.by5388.sy95306v2.activity.sy.list.model.TrainListModel;
import com.by5388.sy95306v2.activity.sy.list.model.sort.BaseTrainNumberSort;
import com.by5388.sy95306v2.bean.shenyang.TrainNumber;

import org.junit.Test;

import java.util.List;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

import static com.by5388.sy95306v2.activity.sy.list.model.sort.BaseTrainNumberSort.SORT_BY_START_TIME;

/**
 * @author by5388  on 2018/11/13.
 */
public class RxJavaSortTest {
    @Test
    public void testRxJavaSortTest() {
        // TODO: 2018/11/13 如何把一个Observable<List<T>> 变成多个的Observable<T>？
        int date = 20190109;
        String fromStation = "RVQ";
        String toStation = "GGQ";

        BaseTrainNumberSort sort = BaseTrainNumberSort.getTrainNumberSort(true, SORT_BY_START_TIME);
        ITrainListModel model = new TrainListModel();
        model.getTrainNumber(date, fromStation, toStation)
                .doOnNext(trainNumbers -> trainNumbers.sort(sort))
                .subscribe(new Observer<List<TrainNumber>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(List<TrainNumber> trainNumbers) {
                        show(trainNumbers);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    private void show(List<TrainNumber> trainNumbers) {
        if (null == trainNumbers) {
            return;
        }
        for (TrainNumber number : trainNumbers) {
            System.out.println("-------");
            System.out.println("\t" + number.getSTCODE());
            System.out.println("\t" + number.getFST() + "\t" + number.getTST());
            System.out.println("\t" + number.getSTIME() + "\t" + number.getATIME());
        }
    }
}
