package com.by5388.sy95306v2.fragment.cd.late.persenter;

import android.util.Log;

import com.by5388.sy95306v2.bean.cd.late.CdLateStation;
import com.by5388.sy95306v2.bean.cd.late.CdTrainAllNodeBean;
import com.by5388.sy95306v2.fragment.cd.late.model.CdLateModel;
import com.by5388.sy95306v2.fragment.cd.late.model.ICdLateModel;
import com.by5388.sy95306v2.fragment.cd.late.view.ICdLateView;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * @author by5388  on 2018/8/18.
 */
public class CdLatePresenter implements ICdLatePresenter {
    public static final String TAG = "CdLatePresenter";
    private final ICdLateModel model;
    private final ICdLateView view;

    private Disposable disposableStation, disposableDetail;
    private final Consumer<Throwable> throwableConsumer;
    private final Consumer<List<CdTrainAllNodeBean>> consumerDetail;
    private final Consumer<List<CdLateStation>> consumerStation;

    public CdLatePresenter(ICdLateView view) {
        this.view = view;
        this.model = new CdLateModel();
        this.throwableConsumer = throwable -> view.showError(throwable.getLocalizedMessage());
        this.consumerDetail = beans -> {
            if (null == beans || beans.isEmpty()) {
                view.showError("未找到相关数据");
            } else {
                view.updateDetailData(beans);
            }
            view.finishQuery();
        };
        this.consumerStation = stations -> {
            view.finishQuery();
            // TODO: 2018/8/18
            if (null == stations || stations.isEmpty()) {
                view.showError("请重试");
                return;
            }

            Set<String> dates = new HashSet<>();
            for (CdLateStation station : stations) {
                dates.add(station.getDateDistance());
            }
            if (1 == dates.size()) {
                view.getLateDetail();
                return;
            }
            List<String> result = new ArrayList<>();
            result.add("号从" + stations.get(0).getStationName() + "出发");
            result.add("号到达" + stations.get(stations.size() - 1).getStationName());
            view.showDialog(result);
        };
    }

    @Override
    public void unSubscribe() {
        if (disposableStation != null && !disposableStation.isDisposed()) {
            disposableStation.dispose();
        }
        if (disposableDetail != null && !disposableDetail.isDisposed()) {
            disposableDetail.dispose();
        }
    }

    @Override
    public void getLateStationList(String trainCode, String date) {
        view.startQuery();
        disposableStation = model
                .getLateStationList(trainCode, date)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(consumerStation, throwableConsumer);
    }

    @Override
    public void getLateDetail(String trainCode, String date, String stationName) {
        view.startQuery();
        disposableDetail = model
                .getLateDetail(trainCode, date, stationName)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(consumerDetail, throwableConsumer);

    }
}
