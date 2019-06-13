package com.by5388.sy95306v2.module.chengdu.screen.persenter;

import com.by5388.sy95306v2.module.chengdu.bean.screen.ScreenStation;
import com.by5388.sy95306v2.module.chengdu.screen.CdScreenItem;
import com.by5388.sy95306v2.module.chengdu.screen.model.CdScreenModel;
import com.by5388.sy95306v2.module.chengdu.screen.model.ICdScreenModel;
import com.by5388.sy95306v2.module.chengdu.screen.view.ICdScreenView;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * @author by5388  on 2018/8/18.
 */
public class CdScreenPresenter implements ICdScreenPresenter {
    private final ICdScreenModel model;
    private final ICdScreenView view;

    private final Consumer<Throwable> throwableConsumer;
    private final Consumer<List<CdScreenItem>> itemConsumer;
    private final Consumer<List<ScreenStation>> stationConsumer;

    private Disposable disposableItem, disposableStation;

    public CdScreenPresenter(ICdScreenView view) {
        this.view = view;
        this.model = new CdScreenModel();
        this.throwableConsumer = throwable -> view.showError(throwable.getLocalizedMessage());
        this.itemConsumer = screenItems -> {
            if (null == screenItems || screenItems.isEmpty()) {
                view.showError("未找到相关数据");
            } else {
                view.updateCdScreenData(screenItems);
            }
            view.finishQuery();
        };
        this.stationConsumer = screenStations -> {
            if (null == screenStations || screenStations.isEmpty()) {
                view.showError("未找到相关数据");
            } else {
                view.updateScreenStation(screenStations);
            }
            view.finishQuery();
        };
    }

    @Override
    public void getScreenItems(String stationCode, String date, int type) {
        view.startQuery();
        disposableItem = model.getScreenItem(type, stationCode, date)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(itemConsumer, throwableConsumer);
    }


    @Override
    public void getScreenStation() {
        disposableStation = model.getCdScreenStationList()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(stationConsumer, throwableConsumer);
    }

    @Override
    public void unSubscribe() {
        if (disposableItem != null && !disposableItem.isDisposed()) {
            disposableItem.dispose();
        }
        if (disposableStation != null && !disposableStation.isDisposed()) {
            disposableStation.dispose();
        }
    }
}
