package com.by5388.sy95306v2.module.guangzhou.number.perserter;


import com.by5388.sy95306v2.module.guangzhou.bean.GzResultData;
import com.by5388.sy95306v2.module.guangzhou.bean.station.TrainsBean;
import com.by5388.sy95306v2.module.guangzhou.number.model.ITrainNumberModel;
import com.by5388.sy95306v2.module.guangzhou.number.model.TrainNumberModel;
import com.by5388.sy95306v2.module.guangzhou.number.view.ITrainNumberView;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * @author by5388  on 2018/8/2.
 */

public class TrainNumberPresenter implements ITrainNumberPresenter {

    private final ITrainNumberView view;
    private final ITrainNumberModel model;
    private final Consumer<GzResultData<List<TrainsBean>>> consumer;
    private final Consumer<Throwable> throwableConsumer;
    private Disposable disposable;

    public TrainNumberPresenter(ITrainNumberView view) {
        this.view = view;
        this.model = new TrainNumberModel();
        consumer = this::showResult;
        throwableConsumer = throwable -> {
            view.showError(throwable.getMessage());
            view.finishQuery();
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

    private void showResult(GzResultData<List<TrainsBean>> listResultData) {
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
