package com.by5388.sy95306v2.tz;

import com.by5388.sy95306v2.tiezong.api.pass.code.GetPassCodeImpl;
import com.by5388.sy95306v2.tiezong.api.pass.code.IGetPassCodeService;
import com.by5388.sy95306v2.tiezong.bean.TzResult;
import com.by5388.sy95306v2.tiezong.bean.temp.DataBean;
import com.by5388.sy95306v2.tiezong.bean.temp.DataBeanX;

import org.junit.Test;

import java.util.List;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * @author by5388  on 2019/1/6.
 */
public class TzStationAllTrainTest {
    @Test
    public void testTzStationAllTrain() {
        IGetPassCodeService service = new GetPassCodeImpl();
        final String date = "2019-01-26";
        final String stationName = "饶平";
        final String stationCode = "RVQ";
        service.getStationAllTrain(date, stationName, stationCode)
                .subscribe(new Observer<TzResult<DataBeanX>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(TzResult<DataBeanX> dataBeanXTzResult) {
                        if (true) {
                            showResult(dataBeanXTzResult, stationName);
                        }
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
//                .flatMap(new Function<TzResult<DataBeanX>, ObservableSource<DataSimpleBean>>() {
//                    @Override
//                    public ObservableSource<DataSimpleBean> apply(TzResult<DataBeanX> dataBeanXTzResult) {
//                        List<DataSimpleBean> dataBeanList = dataBeanXTzResult.getSimpleBeanList().getSimpleBeanList();
//                        for (DataSimpleBean dataBean : dataBeanList) {
//                            return Observable.just(dataBean);
//                        }
//                        return null;
//                    }
//                })
//                .filter(new Predicate<DataSimpleBean>() {
//                    @Override
//                    public boolean test(DataSimpleBean dataBean) {
//                        return stationName.equals(dataBean.getStationName());
//                    }
//                })
//                .subscribe(new Observer<DataSimpleBean>() {
//                    @Override
//                    public void onSubscribe(Disposable d) {
//
//                    }
//
//                    @Override
//                    public void onNext(DataSimpleBean dataBean) {
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

    private void showResult(TzResult<DataBeanX> dataBeanXTzResult, String stationName2) {
        if (dataBeanXTzResult == null || !dataBeanXTzResult.isStatus()) {
            System.err.println("empty");
            return;
        }
        DataBeanX dataBeanX = dataBeanXTzResult.getData();
        List<DataBean> dataBeansList = dataBeanX.getData();
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
