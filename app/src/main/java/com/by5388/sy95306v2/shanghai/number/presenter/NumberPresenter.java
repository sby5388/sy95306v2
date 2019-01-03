package com.by5388.sy95306v2.shanghai.number.presenter;

import com.by5388.sy95306v2.shanghai.bean.InfoBeanTrainNo;
import com.by5388.sy95306v2.shanghai.bean.ShanghaiTrainNumber;
import com.by5388.sy95306v2.shanghai.number.model.INumberModel;
import com.by5388.sy95306v2.shanghai.number.model.NumberModel;
import com.by5388.sy95306v2.shanghai.number.view.INumberView;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * @author by5388  on 2018/8/9.
 */

public class NumberPresenter implements INumberPresenter {

    private final INumberModel model;
    private final INumberView view;
    private final Consumer<List<ShanghaiTrainNumber>> consumer;
    private final Consumer<Throwable> throwableConsumer;
    private Disposable disposable;

    public NumberPresenter(INumberView view) {
        this.view = view;
        this.model = new NumberModel();
        consumer = numbers -> {
            if (null == numbers || numbers.isEmpty()) {
                view.showError("未查到相关信息");
            } else {
                view.updateList(numbers);
            }
            view.finishQuery();
        };
        throwableConsumer = throwable -> view.showError(throwable.getMessage());
    }

    @Override
    public void search(String trainCode, String trainDate) {
        view.startQuery();
        disposable = model.getTrainNumber(new InfoBeanTrainNo(trainCode, trainDate))
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
