package com.by5388.sy95306v2.activity.sy.list.presenter;

import com.by5388.sy95306v2.activity.sy.list.model.ITrainListModel;
import com.by5388.sy95306v2.activity.sy.list.model.TrainListModel;
import com.by5388.sy95306v2.activity.sy.list.view.ITrainListView;
import com.by5388.sy95306v2.bean.shenyang.TrainNumber;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * @author by5388  on 2018/7/29.
 */

public class TrainListPresenter implements ITrainListPresenter {

    private final ITrainListView view;
    private final ITrainListModel model;

    private Disposable disposable;
    private final Consumer<List<TrainNumber>> consumer;
    private final Consumer<Throwable> throwableConsumer;
    /**
     * 全部的车次信息，未过滤
     */
    private List<TrainNumber> allTrainNumbers;

    public TrainListPresenter(ITrainListView view) {
        this.view = view;
        this.model = new TrainListModel();
        consumer = trainNumbers -> {
            //获取初始值.执行过滤+排序
            if (allTrainNumbers == null) {
                allTrainNumbers = trainNumbers;
                bindData(model.sortTrainNumber(trainNumbers, 0, true));
            } else {
                view.updateTrainList(trainNumbers);
                view.finishLoading();
            }
        };
        throwableConsumer = throwable -> view.showErrorMessage(throwable.getLocalizedMessage());

    }

    @Override
    public void getTrainList(int date, String fromStation, String toStation) {
        view.showLoading();
        bindData(model.getTrainNumber(date, fromStation, toStation));
    }

    @Override
    public void unSubscribe() {
        if (disposable != null && !disposable.isDisposed()) {
            disposable.dispose();
        }
    }

    @Override
    public void sortTrainList(int method, boolean isUp) {
        view.showLoading();
        bindData(model.sortTrainNumber(allTrainNumbers, method, isUp));
    }


    private void bindData(Observable<List<TrainNumber>> observable) {
        disposable = observable
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(consumer, throwableConsumer);
    }

    @Override
    public String getStationNames(String fromStationCode, String toStationCode) {
        return model.getStationNames(fromStationCode, toStationCode);
    }
}
