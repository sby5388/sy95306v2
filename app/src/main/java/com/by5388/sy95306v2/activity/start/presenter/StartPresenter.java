package com.by5388.sy95306v2.activity.start.presenter;

import android.util.Log;

import com.by5388.sy95306v2.activity.start.model.IStartModel;
import com.by5388.sy95306v2.activity.start.model.StartModel;
import com.by5388.sy95306v2.activity.start.view.IStartView;

import java.lang.reflect.Type;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * @author by5388  on 2018/7/30.
 */

public class StartPresenter implements IStartPresenter {
    private final static String TAG = "StartPresenter";
    private final IStartView view;
    private final IStartModel model;
    private final CompositeDisposable compositeDisposable;

    // FIXME: 2018/8/26 那如果有多个 Disposable 该怎么办呢,
    // RxJava中已经内置了一个容器 CompositeDisposable , 每当
    //我们得到一个 Disposable 时就调用 CompositeDisposable.add() 将它添加到容器中, 在退出的时
    //候, 调用 CompositeDisposable.clear() 即可切断所有的水管

    /**
     * 是否需要更新
     */
    private final Consumer<Boolean> updateConsumer;
    /**
     * 更新进度
     */
    private final Consumer<Integer> progressConsumer;
    /**
     * 清除数据
     */
    private final Consumer<Integer> clearDataConsumer;
    /**
     * 异常
     */
    private final Consumer<Throwable> throwableConsumer;

    public StartPresenter(IStartView view) {
        this.view = view;
        this.model = new StartModel();
        compositeDisposable = new CompositeDisposable();

        updateConsumer = aBoolean -> {
            Log.d(TAG, "是否需要更新数据库？ " + aBoolean);
            if (aBoolean) {
                view.showUpdateDialog();
            } else {
                view.toMainActivity();
            }
        };
        progressConsumer = view::updateProgress;
        throwableConsumer = throwable -> view.showErrorMessage(throwable.getLocalizedMessage());
        clearDataConsumer = integer -> {
            if (0 == integer) {
                view.insertData();
            } else {
                view.toMainActivity();
            }
        };

    }


    @Override
    public void checkStationListVersion() {
        compositeDisposable.add(
                model.isNeedUpdate()
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(updateConsumer, throwableConsumer));

    }

    @Override
    public void clearData() {
        compositeDisposable.add(
                model.clearData()
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(clearDataConsumer, throwableConsumer));
    }

    @Override
    public void insertData() {
        compositeDisposable.add(
                model.insertProgressBar()
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(progressConsumer, throwableConsumer));
    }

    @Override
    public void checkNetworkStatus() {
        boolean isConnected = model.getNetWordCanUse();
        if (!isConnected) {
            //网络不可用-->设置
            view.openNetWorkSetting();
        } else {
            //可用-->下一步
            view.checkNeedUpdate();
        }
    }

    @Override
    public void startUpdate() {
        compositeDisposable.add(
                model.getStationCount()
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(integer -> {
                            Log.d(TAG, "count:accept: " + integer);
                            view.updateAllCount(integer);
                            view.clearData();
                        }, throwableConsumer));

    }

    private void addDisposable(Observable<Type> observable, Consumer<Type> consumer) {
        compositeDisposable.add(
                observable
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(consumer, throwableConsumer));
    }

    @Override
    public void finishUpdate() {
        view.updateFinish();
        model.finishUpdate();

    }

    @Override
    public void unSubscribe() {
        compositeDisposable.clear();
    }
}
