package com.by5388.sy95306v2.fragment.gz.p2p.presenter;

import com.by5388.sy95306v2.bean.guangzhou.ResultData;
import com.by5388.sy95306v2.bean.guangzhou.station.DataBeanP2P;
import com.by5388.sy95306v2.fragment.gz.p2p.model.GzP2pModel;
import com.by5388.sy95306v2.fragment.gz.p2p.model.IGzP2pModel;
import com.by5388.sy95306v2.fragment.gz.p2p.view.IGzP2pView;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * @author by5388  on 2018/8/10.
 */

public class GzP2pPresenter implements IGzP2pPresenter {

    private IGzP2pView view;
    private IGzP2pModel model;
    private Disposable disposable;
    private Consumer<ResultData<DataBeanP2P>> consumer;
    private Consumer<Throwable> throwableConsumer;


    public GzP2pPresenter(IGzP2pView view) {
        this.view = view;
        this.model = new GzP2pModel();
        this.consumer = new Consumer<ResultData<DataBeanP2P>>() {
            @Override
            public void accept(ResultData<DataBeanP2P> dataBeanP2PResultData) {
                DataBeanP2P dataBean = dataBeanP2PResultData.getData();
                if (null != dataBean) {
                    view.updateDate(dataBean);
                } else {
                    view.showError("没有相关的车次");
                }
                view.finishQuery();
            }
        };
        this.throwableConsumer = new Consumer<Throwable>() {
            @Override
            public void accept(Throwable throwable) {
                view.showError(throwable.getLocalizedMessage());
            }
        };

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
