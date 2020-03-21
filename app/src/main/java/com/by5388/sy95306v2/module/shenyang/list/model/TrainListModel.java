package com.by5388.sy95306v2.module.shenyang.list.model;

import com.by5388.sy95306v2.database.DataBaseApiImpl;
import com.by5388.sy95306v2.database.IShenYangDbApi;
import com.by5388.sy95306v2.dialog.ITrainType;
import com.by5388.sy95306v2.dialog.TrainType;
import com.by5388.sy95306v2.module.shenyang.bean.Station;
import com.by5388.sy95306v2.module.shenyang.bean.TrainNumber;
import com.by5388.sy95306v2.module.shenyang.list.model.sort.BaseTrainNumberSort;
import com.by5388.sy95306v2.module.shenyang.net.api.SyNetTools;
import com.by5388.sy95306v2.module.shenyang.net.api.SyService;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import androidx.annotation.WorkerThread;
import io.reactivex.Observable;
import retrofit2.Retrofit;

import static com.by5388.sy95306v2.module.shenyang.list.model.ModelData.getSelectedType;

/**
 * @author by5388  on 2018/7/29.
 */

public class TrainListModel implements ITrainListModel {
    private final SyService mTrainNumberService;
    private final List<Integer> mIntegerList;
    private IShenYangDbApi mDataBaseService;

    public TrainListModel() {
        Retrofit retrofit = new SyNetTools().getRetrofit();
        mTrainNumberService = retrofit.create(SyService.class);
        mIntegerList = new ArrayList<>();
    }


    @Override
    public Observable<List<TrainNumber>> getTrainNumber(int date, String fromStation, String toStation) {
        return mTrainNumberService.getTrainNumberByP2P(date, fromStation, toStation);
    }

    @Override
    public Observable<List<TrainNumber>> sortTrainNumber(List<TrainNumber> trainNumbers, int position, boolean isUp) {
        // TODO: 2020/3/19 这里需要更新
        return Observable.create(emitter -> {
            if (!(emitter.isDisposed())) {
                final BaseTrainNumberSort sorter = BaseTrainNumberSort.getTrainNumberSort(isUp, position);
                //过滤
                List<TrainNumber> nextTrainNumbers = getFilterTrainNumber(trainNumbers);
                //排序
                Collections.sort(nextTrainNumbers, sorter);
                emitter.onNext(nextTrainNumbers);
                emitter.onComplete();
            }
        });
    }

    /**
     * 过滤
     *
     * @param trainNumbers 所有的车次
     * @return 过滤后的车次
     */
    @WorkerThread
    private List<TrainNumber> getFilterTrainNumber(List<TrainNumber> trainNumbers) {
        // TODO: 2020/3/19 过滤函数
        // FIXME: 2020/3/19 使用新的API
        refreshSelected();
        List<String> selectedType = getSelectedType(mIntegerList);
        List<TrainNumber> nextTrainNumbers = new ArrayList<>();
        for (TrainNumber trainNumber : trainNumbers) {
            String code = trainNumber.getTCCODE();
            if (selectedType.contains(code)) {
                nextTrainNumbers.add(trainNumber);
            }
        }
        return nextTrainNumbers;
    }

    private void refreshSelectedByNew() {
        final ITrainType trainType = TrainType.getInstance();
        final boolean[] checks = trainType.getChecks();
        if (checks[0]) {
            mIntegerList.add(0);
        }
        if (checks[1]) {
            mIntegerList.add(1);
        }
        if (checks[2]) {
            mIntegerList.add(2);
            mIntegerList.add(3);
        }
        if (checks[3]) {
            mIntegerList.add(4);
            mIntegerList.add(5);
        }
    }

    private void refreshSelected() {
        // TODO: 2020/3/19
        mIntegerList.clear();
        refreshSelectedByNew();
    }

    @Override
    public String getStationNames(String fromStationCode, String toStationCode) {
        if (null == mDataBaseService) {
            mDataBaseService = DataBaseApiImpl.getInstance();
        }
        Station startStation = mDataBaseService.selectStationByNameUpper(fromStationCode);
        Station endStation = mDataBaseService.selectStationByNameUpper(toStationCode);
        if (null == startStation || null == endStation) {
            return "";
        }
        return startStation.getName() + "-" + endStation.getName();
    }
}
