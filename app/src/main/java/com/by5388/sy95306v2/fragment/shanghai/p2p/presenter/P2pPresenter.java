package com.by5388.sy95306v2.fragment.shanghai.p2p.presenter;

import com.by5388.sy95306v2.bean.shanghai.InfoBeanP2P;
import com.by5388.sy95306v2.bean.shanghai.ShanghaiTrainP2P;
import com.by5388.sy95306v2.fragment.shanghai.p2p.model.IP2pModel;
import com.by5388.sy95306v2.fragment.shanghai.p2p.model.P2pModel;
import com.by5388.sy95306v2.fragment.shanghai.p2p.view.IP2pView;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * @author by5388  on 2018/8/10.
 */

public class P2pPresenter implements IP2pPresenter {
    private final IP2pModel model;
    private final IP2pView view;
    private final Consumer<List<ShanghaiTrainP2P>> consumer;
    private final Consumer<Throwable> throwableConsumer;
    private Disposable disposable;

    public P2pPresenter(IP2pView view) {
        this.view = view;
        this.model = new P2pModel();
        this.consumer = trainP2PS -> {
            if (null == trainP2PS || trainP2PS.isEmpty()) {
                view.showError("未查到相关信息");
            } else {
                view.updateList(trainP2PS);
            }
            view.finishQuery();
        };
        this.throwableConsumer = throwable -> {
            view.showError(throwable.getLocalizedMessage());
            view.finishQuery();
        };
    }

    @Override
    public void search(String fromStation, String toStation, String trainDate) {
        view.startQuery();
        disposable = model.queryTrainP2P(new InfoBeanP2P(fromStation, toStation, trainDate))
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
