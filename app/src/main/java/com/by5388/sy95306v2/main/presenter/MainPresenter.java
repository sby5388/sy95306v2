package com.by5388.sy95306v2.main.presenter;

import android.util.Log;

import com.by5388.sy95306v2.exception.NetworkException;
import com.by5388.sy95306v2.main.model.IMainModel;
import com.by5388.sy95306v2.main.model.MainModel;
import com.by5388.sy95306v2.main.view.IMainView;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

/**
 * @author by5388  on 2019/1/2.
 */
public class MainPresenter implements IMainPresenter {
    private static final String TAG = MainPresenter.class.getSimpleName();

    private final IMainView view;
    private final IMainModel model;
    private final CompositeDisposable disposable;

    public MainPresenter(IMainView view) {
        this.view = view;
        this.model = new MainModel();
        this.disposable = new CompositeDisposable();
    }

    @Override
    public void checkUpdate() {
        view.onStartChecking();
        disposable.add(model.isNeedUpdate()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(isNeedUpdate -> {
                    if (isNeedUpdate) {
                        view.onNotifyUpdate();
                    } else {
                        view.onFinishChecked();
                    }
                }, throwable -> {
                    view.openNetWorkSetting();
                    view.onTip("请重试");
                })
        );

    }

    @Override
    public void startUpdate() {
        view.onShowUpdating();
        // TODO: 2019/11/26 对异常没有处理，导致闪退
        disposable.add(model.getStationCount()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(mInteger -> {
                    view.updateAllCount(mInteger);
                    clearData();
                }, throwable -> {
                    if (throwable instanceof NetworkException) {
                        Log.e(TAG, "startUpdate: ", throwable);
                        view.onTip(throwable.getLocalizedMessage());
                    }
                })
        );

    }

    private void clearData() {
        disposable.add(model.clearData()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(mInteger -> {
                    if (0 == mInteger) {
                        insertData();
                    }
                })
        );
    }

    private void insertData() {
        disposable.add(model.insertData()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(view::updateProgress, mThrowable -> view.onTip("异常"), () -> {
                    view.onFinishUpdate();
                    model.onFinishUpdate();
                })
        );
    }
}
