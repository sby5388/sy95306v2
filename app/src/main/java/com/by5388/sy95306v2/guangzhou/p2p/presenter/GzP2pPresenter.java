package com.by5388.sy95306v2.guangzhou.p2p.presenter;

import com.by5388.sy95306v2.guangzhou.bean.GzResultData;
import com.by5388.sy95306v2.guangzhou.bean.station.DataBeanP2P;
import com.by5388.sy95306v2.guangzhou.p2p.model.GzP2pModel;
import com.by5388.sy95306v2.guangzhou.p2p.model.IGzP2pModel;
import com.by5388.sy95306v2.guangzhou.p2p.view.IGzP2pView;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * @author by5388  on 2018/8/10.
 */

public class GzP2pPresenter implements IGzP2pPresenter {

    private final IGzP2pView view;
    private final IGzP2pModel model;
    private Disposable disposable;
    private final Consumer<GzResultData<DataBeanP2P>> consumer;
    private final Consumer<Throwable> throwableConsumer;


    public GzP2pPresenter(IGzP2pView view) {
        this.view = view;
        this.model = new GzP2pModel();
        this.consumer = dataBeanP2PResultData -> {
            view.finishQuery();
            DataBeanP2P dataBean = dataBeanP2PResultData.getData();
            if (null == dataBean) {
                view.showError("没有相关的车次");
                return;
            }
            view.updateDate(dataBean);view.updateDate(dataBean);

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
    public void search(String fromStation, String toStation, String trainDate) {
        view.startQuery();
        disposable = model.getTrainByStation(fromStation, toStation, trainDate)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(consumer, throwableConsumer);
    }
}
