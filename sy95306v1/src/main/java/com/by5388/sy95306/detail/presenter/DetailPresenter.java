package com.by5388.sy95306.detail.presenter;


import com.by5388.sy95306.bean.TrainDetail;
import com.by5388.sy95306.detail.model.DetailModel;
import com.by5388.sy95306.detail.model.IDetailModel;
import com.by5388.sy95306.detail.view.IDetailView;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * @author by5388  on 2018/7/29.
 */

public class DetailPresenter implements IDetailPresenter {


    private final IDetailView view;
    private final IDetailModel model;
    private Disposable disposable;
    private final Consumer<List<TrainDetail>> consumer;
    private final Consumer<List<TrainDetail>> consumer2 = new Consumer<List<TrainDetail>>() {
        @Override
        public void accept(List<TrainDetail> mTrainDetails) throws Exception {

        }
    };
    private final Consumer<Throwable> throwableConsumer;

    public DetailPresenter(IDetailView view) {
        this.view = view;
        model = new DetailModel();
        consumer = trainDetails -> {
            view.setStations(trainDetails);
            String trainName = model.getTrainName(trainDetails);
            String code = model.getCodeName(trainDetails);
            view.updateTopView(trainName, code);
            view.finishLoading();
        };
        throwableConsumer = throwable -> view.showErrorMessage(throwable.getLocalizedMessage());
    }

    @Override
    public void getDetailData(int currentDate, String code) {
        view.showLoading();
        bindData(model.getTrainByTrainCode(currentDate, code));
    }

    private void bindData(Observable<List<TrainDetail>> observable) {
        disposable = observable
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(consumer, throwableConsumer);
    }

    @Override
    public void unSubscribe() {
        if (disposable != null && !disposable.isDisposed()) {
            disposable.dispose();
        }
    }
}
