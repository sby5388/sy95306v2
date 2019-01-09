package com.by5388.sy95306v2.main.presenter;

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
        view.startChecking();
        disposable.add(model.isNeedUpdate()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(isNeedUpdate -> {
                    if (isNeedUpdate) {
                        view.notifyUpdate();
                    } else {
                        view.finishChecked();
                    }
                },throwable -> {
                    view.tip("请重试");
                })
        );

    }

    @Override
    public void startUpdate() {
            //view.
    }
}
