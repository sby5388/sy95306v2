package com.by5388.sy95306v2.module.tiezong.zzcx.persenter;

import android.util.Log;

import com.by5388.sy95306v2.bean.IRemainingTicket;
import com.by5388.sy95306v2.module.tiezong.bean.yp.success.SuccessDataBean;
import com.by5388.sy95306v2.module.tiezong.bean.yp.success.TzDataBean;
import com.by5388.sy95306v2.module.tiezong.zzcx.model.ITzZzCxModel;
import com.by5388.sy95306v2.module.tiezong.zzcx.model.TzZzCxModel;
import com.by5388.sy95306v2.module.tiezong.zzcx.view.ITzZzCxView;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * @author by5388  on 2018/8/20.
 */
public class TzZzCxPresenter implements ITzZzCxPresenter {
    public static final String TAG = "TzZzCxPresenter";
    private Disposable listDisposable;
    private final Consumer<Throwable> throwableConsumer;

    private final Consumer<SuccessDataBean> resultConsumer;

    private final ITzZzCxModel model;
    private final ITzZzCxView view;

    public TzZzCxPresenter(ITzZzCxView view) {
        this.view = view;
        this.model = new TzZzCxModel();
        this.throwableConsumer = throwable -> {
            Log.e(TAG, "TzZzCxPresenter: ", throwable);
            view.showError(throwable.getLocalizedMessage());
            view.finishQuery();
        };

        this.resultConsumer = successDataBean -> {
            view.finishQuery();
            if (null == successDataBean) {
                view.showError("没有相关数据");
                return;
            }
            List<TzDataBean> beans = successDataBean.getDatas();
            if (null == beans || beans.isEmpty()) {
                return;
            }
            List<IRemainingTicket> yps = new ArrayList<>(beans);
            view.updateList(yps);
        };
    }

    @Override
    public void getTrainList(String date, String fromStation, String toStation) {
        if (model.isErrorStationName(fromStation)) {
            view.showError(fromStation + " 不正确");
            return;
        }
        if (model.isErrorStationName(toStation)) {
            view.showError(toStation + " 不正确");
            return;
        }
        view.startQuery();
        listDisposable = model.getResult(date, fromStation, toStation)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(resultConsumer
                        , throwableConsumer
                );
    }

    private void temp() {
        System.out.println("this  just is  a TAGl");
    }

    @Override
    public void unSubscribe() {
        if (listDisposable != null) {
            listDisposable.dispose();
        }
    }

}
