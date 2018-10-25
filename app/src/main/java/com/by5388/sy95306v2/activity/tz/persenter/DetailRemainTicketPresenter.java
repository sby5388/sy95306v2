package com.by5388.sy95306v2.activity.tz.persenter;

import android.graphics.Bitmap;
import android.support.annotation.NonNull;
import android.util.Log;

import com.by5388.sy95306v2.activity.tz.model.DetailRemainTicketModel;
import com.by5388.sy95306v2.activity.tz.model.IDetailRemainTicketModel;
import com.by5388.sy95306v2.activity.tz.view.IDetailRemainTicketView;
import com.by5388.sy95306v2.bean.shenyang.TrainDetail;
import com.by5388.sy95306v2.bean.tz.yp.success.SuccessDataBean;
import com.by5388.sy95306v2.bean.tz.yp.success.TzDataBean;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

/**
 * @author by5388  on 2018/8/20.
 */
public class DetailRemainTicketPresenter implements IDetailRemainTicketPresenter {
    private static final String TAG = "DetailRemainTicket";
    private Disposable codeDisposable, bitmapDisposable, listDisposable;
    private final Consumer<Throwable> throwableConsumer;

    private final Consumer<SuccessDataBean> resultConsumer;
    private final Consumer<Bitmap> bitmapConsumer;

    private final Consumer<Boolean> booleanConsumer;
    private final Consumer<SuccessDataBean> addConsumer;

    private final IDetailRemainTicketModel model;
    private final IDetailRemainTicketView view;
    /**
     * 错误统计，连续错误则清除cookie
     */
    private int errorCount = 0;
    /**
     * 最大错误数
     */
    private static final int MAX_ERROR_COUNT = 1;

    public DetailRemainTicketPresenter(IDetailRemainTicketView view) {
        this.view = view;
        this.model = new DetailRemainTicketModel();
        this.throwableConsumer = throwable -> {
            view.showError(throwable.getLocalizedMessage());
            view.finishQuery();
        };
        this.bitmapConsumer = bitmap -> {
            view.finishQuery();
            if (null == bitmap) {
                return;
            }
            view.updateCheckCodeBitmap(bitmap);
        };
        this.booleanConsumer = aBoolean -> {
            if (!aBoolean) {
                errorCount++;
                if (errorCount > MAX_ERROR_COUNT) {
                    model.clearCookie();
                    errorCount = 0;
                }
            }
            view.checkPassCode(aBoolean);
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
            view.updateList(beans);
        };
        this.addConsumer = new Consumer<SuccessDataBean>() {
            @Override
            public void accept(SuccessDataBean successDataBean) {
                if (null == successDataBean) {
                    return;
                }
                List<TzDataBean> beans = successDataBean.getDatas();
                if (null == beans || beans.isEmpty()) {
                    return;
                }
                view.addRemainingTicket(successDataBean.getDatas().get(0));
            }
        };
    }


    @Override
    public void refreshPassCodeBitmap() {
        view.clearPassCode();
        view.startQuery();
        bitmapDisposable = model.getPassCodeBitmap()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(bitmapConsumer
                        , throwableConsumer
                );
    }

    @Override
    public void checkPassCode(String passCode) {
        final int codeLength = 4;
        if (codeLength != passCode.length()) {
            view.showError("格式不对");
            return;
        }
        view.startQuery();
        codeDisposable = model.checkCode(passCode)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(booleanConsumer
                        , throwableConsumer
                );
    }

    @Override
    public void getTrainList(String date, String fromStation, String toStation, String randCode) {
        if (!model.isTrueStationName(fromStation)) {
            view.showError(fromStation + " 不正确");
            return;
        }
        if (!model.isTrueStationName(toStation)) {
            view.showError(toStation + " 不正确");
            return;
        }
        view.startQuery();
        bindData(model.getResult(date, fromStation, toStation, randCode));
    }


    @Override
    public void getOnlyOneTrainList(String date, String fromStation, String toStation, String randCode, String trainCode) {
        if (!model.isTrueStationName(fromStation)) {
            view.showError(fromStation + " 不正确");
            return;
        }
        if (!model.isTrueStationName(toStation)) {
            view.showError(toStation + " 不正确");
            return;
        }
        view.startQuery();

        bindData(model.getOnLyResult(date, fromStation, toStation, randCode, trainCode));
    }

    @Override
    public void getTrainListByEmptyToStation(String date, String fromStation, String randCode, String trainCode) {
        if (!model.isTrueStationName(fromStation)) {
            view.showError(fromStation + " 不正确");
            return;
        }
        view.startQuery();
        String newDate = date.replaceAll("-", "");
        listDisposable = model.getTrainByTrainCode(Integer.parseInt(newDate), trainCode)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<List<TrainDetail>>() {
                    @Override
                    public void accept(List<TrainDetail> trainDetails) {
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
                        getToStationDetailData(date, fromStation, randCode, trainCode, newNames);
                    }
                }, throwableConsumer);
    }


    @Override
    public void getTrainListByEmptyFromStation(String date, String toStation, String randCode, String trainCode) {
        if (!model.isTrueStationName(toStation)) {
            view.showError(toStation + " 不正确");
            return;
        }
        view.startQuery();
        String newDate = date.replaceAll("-", "");
        Log.d(TAG, "getTrainListByEmptyFromStation: " + newDate);
        listDisposable = model.getTrainByTrainCode(Integer.parseInt(newDate), trainCode)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<List<TrainDetail>>() {
                    @Override
                    public void accept(List<TrainDetail> trainDetails) {
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
                        getFromStationDetailData(date, toStation, randCode, trainCode, newNames);
                    }
                }, throwableConsumer);
    }


    @Override
    public void getTrainListByEmptyTrainCode(String date, String fromStation, String toStation, String randCode) {
        getTrainList(date, fromStation, toStation, randCode);
    }


    private void bindData(Observable<SuccessDataBean> observable) {
        listDisposable = observable
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(resultConsumer
                        , throwableConsumer
                );
    }


    private void getToStationDetailData(String date, String fromStation, String randCode, String trainCode, @NonNull List<String> names) {
        listDisposable = Observable.fromIterable(names)
                .flatMap(new Function<String, ObservableSource<SuccessDataBean>>() {
                    @Override
                    public ObservableSource<SuccessDataBean> apply(String toStation) {
                        return model.getOnLyResult(date, fromStation, toStation, randCode, trainCode);
                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(addConsumer, throwableConsumer);
    }

    private void getFromStationDetailData(String date, String toStation, String randCode, String trainCode, @NonNull List<String> names) {
        listDisposable = Observable.fromIterable(names)
                .flatMap(new Function<String, ObservableSource<SuccessDataBean>>() {
                    @Override
                    public ObservableSource<SuccessDataBean> apply(String fromStation) {
                        return model.getOnLyResult(date, fromStation, toStation, randCode, trainCode);
                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(addConsumer, throwableConsumer);
    }


    @Override
    public void unSubscribe() {
        if (codeDisposable != null) {
            codeDisposable.dispose();
        }
        if (bitmapDisposable != null) {
            bitmapDisposable.dispose();
        }
        if (listDisposable != null) {
            listDisposable.dispose();
        }
        model.clearCookie();
    }

}
