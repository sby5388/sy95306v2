package com.by5388.sy95306v2.fragment.tz.remainticket.presenter;

import com.by5388.sy95306v2.bean.IRemainingTicket;
import com.by5388.sy95306v2.bean.second.QueryParam;
import com.by5388.sy95306v2.fragment.tz.remainticket.model.IRemainTicketModel;
import com.by5388.sy95306v2.fragment.tz.remainticket.model.RemainTicketModel;
import com.by5388.sy95306v2.fragment.tz.remainticket.view.IRemainTicketView;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * @author by5388  on 2018/8/13.
 */
public class RemainTicketPresenter implements IRemainTicketPresenter {
    private final IRemainTicketModel model;
    private final IRemainTicketView view;
    private Disposable disposable;
    private final Consumer<List<IRemainingTicket>> consumer;
    private final Consumer<Throwable> throwableConsumer;

    public RemainTicketPresenter(IRemainTicketView view) {
        this.view = view;
        this.model = new RemainTicketModel();
        this.consumer = remainTicketData -> {
            if (null == remainTicketData || remainTicketData.isEmpty()) {
                view.showError("没有相关数据");
            } else {
                view.updateDate(remainTicketData);
            }
            view.finishQuery();
        };
        this.throwableConsumer = throwable -> view.showError(throwable.getLocalizedMessage());

    }

    @Override
    public void getRemainTicketMessage(String fromStation, String toStation, String code, String trainDate) {
        view.startQuery();
        disposable = model.getRemainTicketData(new QueryParam(fromStation, toStation, code, trainDate))
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
