package com.by5388.sy95306v2.fragment.gz.trainNumber.perserter;


import com.by5388.sy95306v2.bean.guangzhou.ResultData;
import com.by5388.sy95306v2.bean.guangzhou.station.TrainsBean;
import com.by5388.sy95306v2.fragment.gz.trainNumber.model.ITrainNumberModel;
import com.by5388.sy95306v2.fragment.gz.trainNumber.model.TrainNumberModel;
import com.by5388.sy95306v2.fragment.gz.trainNumber.view.ITrainNumberView;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * @author by5388  on 2018/8/2.
 */

public class TrainNumberPresenter implements ITrainNumberPresenter {

    private ITrainNumberView view;
    private ITrainNumberModel model;
    private Consumer<ResultData<List<TrainsBean>>> consumer;
    private Consumer<Throwable> throwableConsumer;
    private Disposable disposable;

    public TrainNumberPresenter(ITrainNumberView view) {
        this.view = view;
        this.model = new TrainNumberModel();
        consumer = new Consumer<ResultData<List<TrainsBean>>>() {
            @Override
            public void accept(ResultData<List<TrainsBean>> listResultData) {
                showResult(listResultData);
            }
        };
        throwableConsumer = new Consumer<Throwable>() {
            @Override
            public void accept(Throwable throwable) {
                view.showError(throwable.getMessage());
                view.finishQuery();
            }
        };
    }

    @Override
    public void search(String trainNo, String date) {
        view.startQuery();
        disposable = model.getTrainByNo(trainNo, date)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(consumer, throwableConsumer);
    }

    private void showResult(ResultData<List<TrainsBean>> listResultData) {
        List<TrainsBean> list = listResultData.getData();
        if (list == null || list.isEmpty()) {
            view.showError("查无信息");
            view.finishQuery();
            return;
        }
        showResult(list);
    }

    private void showResult(List<TrainsBean> trainsBeans) {
        view.updateDate(trainsBeans);
        view.finishQuery();
    }

    @Override
    public void unSubscribe() {
        if (disposable != null && !disposable.isDisposed()) {
            disposable.dispose();
        }
    }
}
