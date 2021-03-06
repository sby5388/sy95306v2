package com.by5388.sy95306v2.module.tiezong.combination.persenter;

import android.util.Log;

import com.by5388.sy95306v2.bean.IRemainingTicket;
import com.by5388.sy95306v2.module.shenyang.bean.TrainDetail;
import com.by5388.sy95306v2.module.tiezong.bean.yp.success.SuccessDataBean;
import com.by5388.sy95306v2.module.tiezong.bean.yp.success.TzDataBean;
import com.by5388.sy95306v2.module.tiezong.combination.model.CombinationModel;
import com.by5388.sy95306v2.module.tiezong.combination.model.ICombinationModel;
import com.by5388.sy95306v2.module.tiezong.combination.view.ICombinationView;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

/**
 * @author by5388  on 2018/8/20.
 */
public class CombinationPresenter implements ICombinationPresenter {
    private static final String TAG = "DRTicketPresenter";
    private final CompositeDisposable mDisposable;
    private final Consumer<Throwable> throwableConsumer;

    private final Consumer<SuccessDataBean> resultConsumer;
    private final Consumer<SuccessDataBean> addConsumer;

    private final ICombinationModel model;
    private final ICombinationView view;

    public CombinationPresenter(ICombinationView view) {
        this.view = view;
        this.model = new CombinationModel();
        this.throwableConsumer = throwable -> {
            view.showError(throwable.getLocalizedMessage());
            view.finishQuery();
        };

        this.resultConsumer = successDataBean -> {
            view.finishQuery();
            if (null == successDataBean) {
                view.showError("没有相关数据");
                return;
            }
            List<TzDataBean> beans = successDataBean.getDatas();
            if (null == beans || beans.isEmpty()) {
                return;
            }
            List<IRemainingTicket> yps = new ArrayList<>(beans);
            view.updateList(yps);
        };
        this.addConsumer = successDataBean -> {
            if (null == successDataBean) {
                return;
            }
            List<TzDataBean> beans = successDataBean.getDatas();
            if (null == beans || beans.isEmpty()) {
                return;
            }
            view.addIRemainingTicket(successDataBean.getDatas().get(0));
        };
        mDisposable = new CompositeDisposable();
    }

    @Override
    public void getTrainList(String date, String fromStation, String toStation) {
        if (model.isErrorStationName(fromStation)) {
            view.showError(fromStation + " 不正确");
            return;
        }
        if (model.isErrorStationName(toStation)) {
            view.showError(toStation + " 不正确");
            return;
        }
        view.startQuery();
        bindData(model.getResult(date, fromStation, toStation));
    }


    @Override
    public void getOnlyOneTrainList(String date, String fromStation, String toStation, String trainCode) {
        if (model.isErrorStationName(fromStation)) {
            view.showError(fromStation + " 不正确");
            return;
        }
        if (model.isErrorStationName(toStation)) {
            view.showError(toStation + " 不正确");
            return;
        }
        view.startQuery();

        bindData(model.getOnlyResult(date, fromStation, toStation, trainCode));
    }

    @Override
    public void getTrainListByEmptyToStation(String date, String fromStation, String trainCode) {
        if (model.isErrorStationName(fromStation)) {
            view.showError(fromStation + " 不正确");
            return;
        }
        view.startQuery();
        final String newDate = date.replaceAll("-", "");
        mDisposable.add(model.getTrainByTrainCode(Integer.parseInt(newDate), trainCode)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(trainDetails -> {
                    List<String> names = new ArrayList<>();
                    for (TrainDetail detail : trainDetails) {
                        names.add(detail.getSNAME());
                    }
                    int position = names.indexOf(fromStation);
                    if (position < 0) {
                        System.err.println("该站没有在该车次中");
                        return;
                    }
                    List<String> newNames = new ArrayList<>();
                    for (int i = position + 1; i < names.size(); i++) {
                        System.out.println(names.get(i));
                        newNames.add(names.get(i));
                    }
                    getToStationDetailData(date, fromStation, trainCode, newNames);
                }, throwableConsumer));
    }


    @Override
    public void getTrainListByEmptyFromStation(String date, String toStation, String trainCode) {
        if (model.isErrorStationName(toStation)) {
            view.showError(toStation + " 不正确");
            return;
        }
        view.startQuery();
        String newDate = date.replaceAll("-", "");
        Log.d(TAG, "getTrainListByEmptyFromStation: " + newDate);
        mDisposable.add(model.getTrainByTrainCode(Integer.parseInt(newDate), trainCode)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(trainDetails -> {
                    List<String> names = new ArrayList<>();
                    for (TrainDetail detail : trainDetails) {
                        names.add(detail.getSNAME());
                        Log.d(TAG, "accept: " + detail.getSNAME());
                    }
                    int position = names.indexOf(toStation);
                    Log.d(TAG, "accept: " + position);
                    if (position < 0 || position == 0) {
                        view.showError("该站没有在该车次中");
                        return;
                    }
                    List<String> newNames = new ArrayList<>();
                    for (int i = 0; i < position - 1; i++) {
                        newNames.add(names.get(i));
                    }
                    getFromStationDetailData(date, toStation, trainCode, newNames);
                }, throwableConsumer));
    }


    @Override
    public void getTrainListByEmptyTrainCode(String date, String fromStation, String toStation) {
        getTrainList(date, fromStation, toStation);
    }


    private void bindData(Observable<SuccessDataBean> observable) {
        mDisposable.add(observable
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(resultConsumer, throwableConsumer));
    }


    private void getToStationDetailData(String date, String fromStation, String trainCode, @NonNull List<String> names) {
        mDisposable.add(Observable.fromIterable(names)
                .flatMap((Function<String, ObservableSource<SuccessDataBean>>) toStation -> model.getOnlyResult(date, fromStation, toStation, trainCode))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(addConsumer, throwableConsumer));
    }

    private void getFromStationDetailData(String date, String toStation, String trainCode, @NonNull List<String> names) {
        mDisposable.add(Observable.fromIterable(names)
                .flatMap((Function<String, ObservableSource<SuccessDataBean>>) fromStation ->
                        model.getOnlyResult(date, fromStation, toStation, trainCode))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(addConsumer, throwableConsumer));
    }


    @Override
    public void unSubscribe() {
        if (mDisposable != null) {
            mDisposable.clear();
        }
    }

}
