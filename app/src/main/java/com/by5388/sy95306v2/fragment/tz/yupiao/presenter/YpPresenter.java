package com.by5388.sy95306v2.fragment.tz.yupiao.presenter;

import com.by5388.sy95306v2.bean.IYp;
import com.by5388.sy95306v2.bean.tzyp.QueryParam;
import com.by5388.sy95306v2.bean.tzyp.TzYpData;
import com.by5388.sy95306v2.fragment.tz.yupiao.model.IYpModel;
import com.by5388.sy95306v2.fragment.tz.yupiao.model.YpModel;
import com.by5388.sy95306v2.fragment.tz.yupiao.view.IYpView;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * @author by5388  on 2018/8/13.
 */
public class YpPresenter implements IYpPresenter {
    private final IYpModel model;
    private final IYpView view;
    private Disposable disposable;
    private final Consumer<List<IYp>> consumer;
    private final Consumer<Throwable> throwableConsumer;

    public YpPresenter(IYpView view) {
        this.view = view;
        this.model = new YpModel();
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
    public void getYpMessage(String fromStation, String toStation, String code, String trainDate) {
        view.startQuery();
        disposable = model.getYuPiaoData(new QueryParam(fromStation, toStation, code, trainDate))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(consumer, throwableConsumer);
    }

    @Override
    public void unSubscribe() {
        if (null != disposable) {
            disposable.dispose();
        }
    }
}
