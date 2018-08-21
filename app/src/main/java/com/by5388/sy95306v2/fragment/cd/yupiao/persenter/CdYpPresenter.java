package com.by5388.sy95306v2.fragment.cd.yupiao.persenter;

import com.by5388.sy95306v2.bean.IYp;
import com.by5388.sy95306v2.fragment.cd.yupiao.model.CdYpModel;
import com.by5388.sy95306v2.fragment.cd.yupiao.model.ICdYpModel;
import com.by5388.sy95306v2.fragment.cd.yupiao.view.ICdYpView;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * @author by5388  on 2018/8/18.
 */
public class CdYpPresenter implements ICdYpPresenter {

    private final ICdYpView view;
    private final ICdYpModel model;
    private Disposable disposable;
    private final Consumer<Throwable> throwableConsumer;
    private final Consumer<List<IYp>> consumer;

    public CdYpPresenter(ICdYpView view) {
        this.view = view;
        this.model = new CdYpModel();
        this.consumer = yuPiaoData -> {
            if (null == yuPiaoData || yuPiaoData.isEmpty()) {
                view.showError("没有相关数据");
            } else {
                view.updateDate(yuPiaoData);
            }
            view.finishQuery();
        };
        this.throwableConsumer = throwable -> view.showError(throwable.getLocalizedMessage());
    }

    @Override
    public void unSubscribe() {
        if (disposable != null) {
            disposable.dispose();
        }
    }

    @Override
    public void getYpMessage(String fromStation, String toStation, String date) {
        view.startQuery();
        disposable = model.getCdYp(fromStation, toStation, date)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(consumer, throwableConsumer);
    }
}
