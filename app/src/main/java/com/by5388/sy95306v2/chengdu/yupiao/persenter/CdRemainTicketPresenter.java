package com.by5388.sy95306v2.chengdu.yupiao.persenter;

import com.by5388.sy95306v2.bean.IRemainingTicket;
import com.by5388.sy95306v2.chengdu.yupiao.model.CdRemainTicketModel;
import com.by5388.sy95306v2.chengdu.yupiao.model.ICdRemainTicketModel;
import com.by5388.sy95306v2.chengdu.yupiao.view.ICdRemainTicketView;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * @author by5388  on 2018/8/18.
 */
public class CdRemainTicketPresenter implements ICdRemainTicketPresenter {

    private final ICdRemainTicketView view;
    private final ICdRemainTicketModel model;
    private Disposable disposable;
    private final Consumer<Throwable> throwableConsumer;
    private final Consumer<List<IRemainingTicket>> consumer;

    public CdRemainTicketPresenter(ICdRemainTicketView view) {
        this.view = view;
        this.model = new CdRemainTicketModel();
        this.consumer = remainingTickets -> {
            if (null == remainingTickets || remainingTickets.isEmpty()) {
                view.showError("没有相关数据");
            } else {
                view.updateDate(remainingTickets);
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
    public void getRemainTicketMessage(String fromStation, String toStation, String date) {
        view.startQuery();
        disposable = model.getCdYp(fromStation, toStation, date)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(consumer, throwableConsumer);
    }
}
