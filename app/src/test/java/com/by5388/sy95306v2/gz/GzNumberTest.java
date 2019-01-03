package com.by5388.sy95306v2.gz;

import android.support.annotation.NonNull;

import com.by5388.sy95306v2.guangzhou.bean.GzResultData;
import com.by5388.sy95306v2.guangzhou.bean.StationInfoBean;
import com.by5388.sy95306v2.guangzhou.bean.station.TrainsBean;
import com.by5388.sy95306v2.guangzhou.number.model.ITrainNumberModel;
import com.by5388.sy95306v2.guangzhou.number.model.TrainNumberModel;

import org.junit.Test;

import java.util.Formatter;
import java.util.List;

import io.reactivex.disposables.Disposable;

/**
 * @author by5388  on 2018/11/9.
 */
public class GzNumberTest {
    private final String date = "2018-12-05";
    private static final Formatter formatter = new Formatter(System.out);

    @Test
    public void testNumber() {
        ITrainNumberModel model = new TrainNumberModel();
        String trainNo = "D741";
        Disposable disposable = model.getTrainByNo(trainNo, date)
                .subscribe(this::showResult, throwable -> System.err.println(throwable.getLocalizedMessage()));
    }

    private void showResult(GzResultData<List<TrainsBean>> listGzResultData) {
        if (listGzResultData == null) {
            return;
        }
        List<TrainsBean> beans = listGzResultData.getData();
        if (null == beans) {
            return;
        }
        for (TrainsBean bean : beans) {
            show(bean);
        }
    }

     public static void  show(@NonNull TrainsBean bean) {
        System.out.println("-----------------");
        System.out.println(bean.getTrainNo());
        System.out.println(bean.getSfStation() + " -- " + bean.getZdStation());
        System.out.println(bean.getSfTime() + " -- " + bean.getZdTime());
        for (StationInfoBean infoBean : bean.getStationInfos()) {
            formatter.format("\t%-12s %-8s %-8s\n", infoBean.getStationName(), infoBean.getArrTime(), infoBean.getGoTime());
            //System.out.println("\t" + infoBean.getStationName() + "\t\t\t" + infoBean.getArrTime() + "\t" + infoBean.getGoTime());
        }
    }

}
