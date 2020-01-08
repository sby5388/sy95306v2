package com.by5388.sy95306v2.main.presenter;

import android.util.Log;

import com.by5388.sy95306v2.database.IShenYangDbApi;
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

    private final IMainView mMainView;
    private final IMainModel mModel;
    private final CompositeDisposable mDisposable;

    public MainPresenter(IMainView view) {
        this.mMainView = view;
        this.mModel = new MainModel();
        this.mDisposable = new CompositeDisposable();
    }

    @Override
    public void checkUpdate() {
        mMainView.onStartChecking();
        mDisposable.add(mModel.isNeedUpdate()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(isNeedUpdate -> {
                    if (isNeedUpdate) {
                        mMainView.onNotifyUpdate();
                    } else {
                        mMainView.onFinishChecked();
                    }
                }, throwable -> {
                    mMainView.openNetWorkSetting();
                    mMainView.onTip("请重试");
                })
        );

    }

    @Override
    public void startUpdate() {
        mMainView.onShowUpdating();
        // TODO: 2019/11/26 对异常没有处理，导致闪退
        mDisposable.add(mModel.getStationCount()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(mInteger -> {
                    mMainView.updateAllCount(mInteger);
                    clearData();
                }, throwable -> {
                    if (throwable instanceof NetworkException) {
                        Log.e(TAG, "startUpdate: ", throwable);
                        mMainView.onTip(throwable.getLocalizedMessage());
                    }
                })
        );

    }

    private void clearData() {
        mDisposable.add(mModel.clearData()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(result -> {
                    if (IShenYangDbApi.ACTION_DELETE_SUCCESS == result) {
                        insertData();
                    }
                })
        );
    }

    private void insertData() {
        mDisposable.add(mModel.insertData()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(mMainView::updateProgress,
                        throwable -> mMainView.onTip("异常"),
                        () -> {
                            mMainView.onFinishUpdate();
                            mModel.onFinishUpdate();
                        })
        );
    }
}
