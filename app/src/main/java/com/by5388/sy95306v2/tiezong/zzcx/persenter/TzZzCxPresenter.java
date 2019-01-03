package com.by5388.sy95306v2.tiezong.zzcx.persenter;

import android.graphics.Bitmap;

import com.by5388.sy95306v2.bean.IRemainingTicket;
import com.by5388.sy95306v2.tiezong.bean.yp.success.SuccessDataBean;
import com.by5388.sy95306v2.tiezong.bean.yp.success.TzDataBean;
import com.by5388.sy95306v2.tiezong.zzcx.model.ITzZzCxModel;
import com.by5388.sy95306v2.tiezong.zzcx.model.TzZzCxModel;
import com.by5388.sy95306v2.tiezong.zzcx.view.ITzZzCxView;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * @author by5388  on 2018/8/20.
 */
public class TzZzCxPresenter implements ITzZzCxPresenter {
    private Disposable codeDisposable, bitmapDisposable, listDisposable;
    private final Consumer<Throwable> throwableConsumer;

    private final Consumer<SuccessDataBean> resultConsumer;
    private final Consumer<Bitmap> bitmapConsumer;

    private final Consumer<Boolean> booleanConsumer;

    private final ITzZzCxModel model;
    private final ITzZzCxView view;
    /**
     * 错误统计，连续错误则清除cookie
     */
    private int errorCount = 0;
    /**
     * 最大错误数
     */
    private static final int MAX_ERROR_COUNT = 1;

    public TzZzCxPresenter(ITzZzCxView view) {
        this.view = view;
        this.model = new TzZzCxModel();
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
                    refreshPassCodeBitmap();
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
            List<IRemainingTicket> yps = new ArrayList<>(beans);
            view.updateList(yps);
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
        if (model.isErrorStationName(fromStation)) {
            view.showError(fromStation + " 不正确");
            return;
        }
        if (model.isErrorStationName(toStation)) {
            view.showError(toStation + " 不正确");
            return;
        }
        view.startQuery();
        listDisposable = model.getResult(date, fromStation, toStation, randCode)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(resultConsumer
                        , throwableConsumer
                );
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
