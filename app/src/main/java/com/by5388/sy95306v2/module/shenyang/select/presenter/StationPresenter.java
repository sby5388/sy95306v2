package com.by5388.sy95306v2.module.shenyang.select.presenter;

import android.text.TextUtils;

import com.by5388.sy95306v2.module.shenyang.bean.Station;
import com.by5388.sy95306v2.module.shenyang.select.model.IStationModel;
import com.by5388.sy95306v2.module.shenyang.select.model.StationModel;
import com.by5388.sy95306v2.module.shenyang.select.view.IStationView;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * @author by5388  on 2018/7/28.
 */

public class StationPresenter implements IStationPresenter {

    private final IStationModel model;
    private final Consumer<List<Station>> consumer;
    private final Consumer<Throwable> throwableConsumer;
    private Disposable disposable;

    public StationPresenter(IStationView view) {
        this.model = new StationModel();
        consumer = stations -> {
            if (stations == null || stations.isEmpty()) {
                //view.showErrorMessage("名字异常");
                return;
            }
            view.setStations(stations);
        };
        // TODO: 2019/9/10 需要对异常的原因给以明示

        throwableConsumer = throwable -> view.showErrorMessage(throwable.getLocalizedMessage());

    }

    @Override
    public void getStation(String keyWord) {
        if (TextUtils.isEmpty(keyWord)) {
            return;
        }
        bindData(model.getStation(keyWord));
    }


    private void bindData(Observable<List<Station>> observable) {
        disposable = observable
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(consumer, throwableConsumer);

    }

    @Override
    public void getDefaultStation() {
        bindData(model.getDefaultStation());
    }

    @Override
    public void unSubscribe() {
        if (disposable != null && !disposable.isDisposed()) {
            disposable.dispose();
        }
    }
}
