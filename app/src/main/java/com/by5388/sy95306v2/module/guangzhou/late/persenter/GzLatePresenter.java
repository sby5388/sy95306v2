package com.by5388.sy95306v2.module.guangzhou.late.persenter;

import com.by5388.sy95306v2.module.guangzhou.bean.late.GzLateDataBean;
import com.by5388.sy95306v2.module.guangzhou.late.model.GzLateModel;
import com.by5388.sy95306v2.module.guangzhou.late.model.IGzLateModel;
import com.by5388.sy95306v2.module.guangzhou.late.view.IGzLateView;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * @author by5388  on 2018/8/21.
 */
public class GzLatePresenter implements IGzLatePresenter {

    private final IGzLateModel model;
    private final IGzLateView view;
    private final Consumer<Throwable> throwableConsumer;
    private final Consumer<List<GzLateDataBean>> consumer;
    private List<GzLateDataBean> currentBeans;
    private Disposable disposable;

    public GzLatePresenter(IGzLateView view) {
        this.view = view;
        this.model = new GzLateModel();
        this.currentBeans = new ArrayList<>();
        this.throwableConsumer = throwable -> view.showError(throwable.getLocalizedMessage());
        this.consumer = gzLateDataBeans -> {
            view.finishQuery();
            if (null == gzLateDataBeans || gzLateDataBeans.isEmpty()) {
                view.showError("没有相关数据");
                return;
            }
            if (gzLateDataBeans.size() > 1) {
                List<String> trainList = new ArrayList<>();
                for (GzLateDataBean bean : gzLateDataBeans) {
                    trainList.add(bean.getTrainNo() + ":" + bean.getSfStation() + "-" + bean.getZdStation());
                }
                view.showDialog(trainList);
                return;
            }
            getLateDetail(0);
        };
    }

    private void setCurrentBeans(List<GzLateDataBean> currentBeans) {
        this.currentBeans = currentBeans;
    }

    @Override
    public void unSubscribe() {
        if (disposable != null) {
            disposable.dispose();
        }
    }

    @Override
    public void getLateDetail(String trainCode) {
        view.startQuery();
        disposable = model.getGzLateStationInfoBeans(trainCode)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnNext(this::setCurrentBeans)
                .subscribe(consumer, throwableConsumer);

    }

    @Override
    public void getLateDetail(int position) {
        if (currentBeans.size() <= position) {
            return;
        }
        GzLateDataBean bean = currentBeans.get(position);
        if (null == bean) {
            return;
        }
        view.setTitle(bean.getTrainNo(), bean.getSfStation() + "-" + bean.getZdStation());
        view.updateData(bean.getStationInfos());
    }
}
