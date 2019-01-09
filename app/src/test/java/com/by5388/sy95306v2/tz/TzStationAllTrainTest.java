package com.by5388.sy95306v2.tz;

import com.by5388.sy95306v2.tiezong.api.pass.code.GetPassCodeImpl;
import com.by5388.sy95306v2.tiezong.api.pass.code.IGetPassCodeService;
import com.by5388.sy95306v2.tiezong.bean.TzResult;
import com.by5388.sy95306v2.tiezong.bean.temp.DataBean;
import com.by5388.sy95306v2.tiezong.bean.temp.DataBeanX;

import org.junit.Test;

import java.util.HashMap;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Function;
import io.reactivex.functions.Predicate;

/**
 * @author by5388  on 2019/1/6.
 */
public class TzStationAllTrainTest {
    @Test
    public void testTzStationAllTrain() {
        IGetPassCodeService service = new GetPassCodeImpl(new HashMap<>());
        String date = "2019-01-12";
        String stationName = "饶平";
        String stationCode = "RVQ";
        service.getStationAllTrain(date, stationName, stationCode)
                .subscribe(new Observer<TzResult<DataBeanX>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(TzResult<DataBeanX> dataBeanXTzResult) {
                        showResult(dataBeanXTzResult);
                    }

                    @Override
                    public void onError(Throwable e) {
                        System.err.println(e.getLocalizedMessage());
                    }

                    @Override
                    public void onComplete() {
                        System.out.println("加载完成");
                    }
                });

//        service.getStationAllTrain(date, stationName, stationCode)
//                .flatMap(new Function<TzResult<DataBeanX>, ObservableSource<DataBean>>() {
//                    @Override
//                    public ObservableSource<DataBean> apply(TzResult<DataBeanX> dataBeanXTzResult) {
//                        List<DataBean> dataBeanList = dataBeanXTzResult.getData().getData();
//                        for (DataBean dataBean : dataBeanList) {
//                            return Observable.just(dataBean);
//                        }
//                        return null;
//                    }
//                })
//                .filter(new Predicate<DataBean>() {
//                    @Override
//                    public boolean test(DataBean dataBean) {
//                        return stationName.equals(dataBean.getStationName());
//                    }
//                })
//                .subscribe(new Observer<DataBean>() {
//                    @Override
//                    public void onSubscribe(Disposable d) {
//
//                    }
//
//                    @Override
//                    public void onNext(DataBean dataBean) {
//                        if (dataBean == null) {
//                            return;
//                        }
//                        System.out.println(dataBean.getStationTrainCode());
//                    }
//
//                    @Override
//                    public void onError(Throwable e) {
//
//                    }
//
//                    @Override
//                    public void onComplete() {
//
//                    }
//                });
    }

    private void showResult(TzResult<DataBeanX> dataBeanXTzResult) {
        if (dataBeanXTzResult == null || !dataBeanXTzResult.isStatus()) {
            System.err.println("empty");
            return;
        }
        DataBeanX dataBeanX = dataBeanXTzResult.getData();
        for (String stationName : dataBeanX.getSameStations()) {
            System.out.println(stationName);
            for (DataBean dataBean : dataBeanX.getData()) {
                if (dataBean.getStationName().equals(stationName)) {
                    System.out.println("\t" + dataBean.getStationTrainCode());
                }
            }
        }

    }
}
