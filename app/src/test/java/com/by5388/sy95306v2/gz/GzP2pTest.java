package com.by5388.sy95306v2.gz;

import android.support.annotation.NonNull;

import com.by5388.sy95306v2.module.guangzhou.bean.GzResultData;
import com.by5388.sy95306v2.module.guangzhou.bean.station.DataBeanP2P;
import com.by5388.sy95306v2.module.guangzhou.bean.station.StationsBean;
import com.by5388.sy95306v2.module.guangzhou.bean.station.TrainsBean;
import com.by5388.sy95306v2.module.guangzhou.p2p.model.GzP2pModel;
import com.by5388.sy95306v2.module.guangzhou.p2p.model.IGzP2pModel;

import org.junit.Test;

import java.util.List;

import io.reactivex.disposables.Disposable;

/**
 * @author by5388  on 2018/11/9.
 */
public class GzP2pTest {
    @Test
    public void gzP2pTest() {
        final String fromStation = "厦门";
        final String toStation = "饶平";
        final String date = "2018-11-10";

        IGzP2pModel model = new GzP2pModel();

        Disposable disposable = model.getTrainByStation(fromStation, toStation, date)
                .subscribe(dataBeanP2PGzResultData -> show(dataBeanP2PGzResultData), throwable -> System.err.println(throwable.getLocalizedMessage()));
    }

    private void show(GzResultData<DataBeanP2P> dataBeanP2PGzResultData) {
        if (null == dataBeanP2PGzResultData) {
            return;
        }
        DataBeanP2P dataBeanP2P = dataBeanP2PGzResultData.getData();
        if (dataBeanP2P == null) {
            return;
        }
        List<TrainsBean> trainsBeans = dataBeanP2P.getTrains();
        List<StationsBean> stationsBeans = dataBeanP2P.getStations();
        if (trainsBeans == null || stationsBeans == null) {
            return;
        }
        // TODO: 2018/11/10 排序
//        trainsBeans.sort(new Comparator<TrainsBean>() {
//            @Override
//            public int compare(TrainsBean bean1, TrainsBean bean2) {
//
//                return 1;
//            }
//        });


        for (TrainsBean bean : trainsBeans) {
            GzNumberTest.show(bean);
        }

        for (StationsBean stationsBean : stationsBeans) {
            show(stationsBean);
        }


    }

    private void show(@NonNull StationsBean bean) {
        System.out.println("---------");
        System.out.println(bean.getCc());
        System.out.println("\t" + bean.getSfStation() + "\t--\t" + bean.getZdStation());
        System.out.println("\t" + bean.getFromStation() + "\t" + bean.getToStation());
        System.out.println("\t" + bean.getLeaveTime() + "\t" + bean.getArriveTime());

    }
}
