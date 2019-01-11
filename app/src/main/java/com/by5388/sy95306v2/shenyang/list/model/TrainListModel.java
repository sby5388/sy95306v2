package com.by5388.sy95306v2.shenyang.list.model;

import com.by5388.sy95306v2.database.DataBaseApiImpl;
import com.by5388.sy95306v2.shenyang.list.model.sort.BaseTrainNumberSort;
import com.by5388.sy95306v2.shenyang.bean.Station;
import com.by5388.sy95306v2.shenyang.bean.TrainNumber;
import com.by5388.sy95306v2.database.IShenYangDbApi;
import com.by5388.sy95306v2.dialog.bean.FilterBean;
import com.by5388.sy95306v2.shenyang.net.api.SyNetTools;
import com.by5388.sy95306v2.shenyang.net.api.SyService;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import io.reactivex.Observable;
import retrofit2.Retrofit;

import static com.by5388.sy95306v2.shenyang.list.model.ModelData.getSelectedType;
import static com.by5388.sy95306v2.dialog.bean.FilterData.getFilterItems;

/**
 * @author by5388  on 2018/7/29.
 */

public class TrainListModel implements ITrainListModel {
    private final SyService trainNumberService;
    private final List<Integer> selected;
    private IShenYangDbApi dataBaseService;

    public TrainListModel() {
        Retrofit retrofit = new SyNetTools().getRetrofit();
        trainNumberService = retrofit.create(SyService.class);
        selected = new ArrayList<>();
    }


    @Override
    public Observable<List<TrainNumber>> getTrainNumber(int date, String fromStation, String toStation) {
        return trainNumberService.getTrainNumberByP2P(date, fromStation, toStation);
    }

    @Override
    public Observable<List<TrainNumber>> sortTrainNumber(List<TrainNumber> trainNumbers, int position, boolean isUp) {
        BaseTrainNumberSort sorter = BaseTrainNumberSort.getTrainNumberSort(isUp, position);
        return Observable.create(emitter -> {
            if (!(emitter.isDisposed())) {
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
    private List<TrainNumber> getFilterTrainNumber(List<TrainNumber> trainNumbers) {
        refreshSelected();
        List<String> selectedType = getSelectedType(selected);
        List<TrainNumber> nextTrainNumbers = new ArrayList<>();
        for (TrainNumber trainNumber : trainNumbers) {
            String code = trainNumber.getTCCODE();
            if (selectedType.contains(code)) {
                nextTrainNumbers.add(trainNumber);
            }
        }
        return nextTrainNumbers;
    }


    private void refreshSelected() {
        selected.clear();
        List<FilterBean> beans = getFilterItems();
        //高铁
        if (beans.get(0).isSelected()) {
            selected.add(0);
        }
        //动车
        if (beans.get(1).isSelected()) {
            selected.add(1);
        }
        //特快
        if (beans.get(2).isSelected()) {
            selected.add(2);
            selected.add(3);
        }
        if (beans.get(3).isSelected()) {
            selected.add(4);
            selected.add(5);
        }
    }

    @Override
    public String getStationNames(String fromStationCode, String toStationCode) {
        if (null == dataBaseService) {
            dataBaseService = DataBaseApiImpl.getInstance();
        }
        Station startStation = dataBaseService.selectStationByNameUpper(fromStationCode);
        Station endStation = dataBaseService.selectStationByNameUpper(toStationCode);
        if (null == startStation || null == endStation) {
            return "";
        }
        return startStation.getName() + "-" + endStation.getName();
    }
}
