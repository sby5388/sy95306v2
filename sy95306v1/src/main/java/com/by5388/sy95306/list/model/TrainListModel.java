package com.by5388.sy95306.list.model;


import com.by5388.sy95306.bean.Station;
import com.by5388.sy95306.bean.TrainNumber;
import com.by5388.sy95306.database.DataBaseApi;
import com.by5388.sy95306.database.DataBaseTempApiImpl;
import com.by5388.sy95306.list.model.sort.BaseTrainNumberSort;
import com.by5388.sy95306.net.NetTools;
import com.by5388.sy95306.net.TrainNumberService;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import io.reactivex.Observable;
import retrofit2.Retrofit;

import static com.by5388.sy95306.list.model.ModelData.getSelectedType;


/**
 * @author by5388  on 2018/7/29.
 */

public class TrainListModel implements ITrainListModel {
    private final TrainNumberService trainNumberService;
    private final List<Integer> selected;
    private DataBaseApi dataBaseService;

    public TrainListModel() {
        Retrofit retrofit = NetTools.getRetrofit();
        trainNumberService = retrofit.create(TrainNumberService.class);
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
        // TODO: 2019/1/3 刷新采用新的方式
//        selected.clear();
//        List<FilterBean> beans = getFilterItems();
//        //高铁
//        if (beans.get(0).isSelected()) {
//            selected.add(0);
//        }
//        //动车
//        if (beans.get(1).isSelected()) {
//            selected.add(1);
//        }
//        //特快
//        if (beans.get(2).isSelected()) {
//            selected.add(2);
//            selected.add(3);
//        }
//        if (beans.get(3).isSelected()) {
//            selected.add(4);
//            selected.add(5);
//        }
    }

    @Override
    public String getStationNames(String fromStationCode, String toStationCode) {
        if (null == dataBaseService) {
            dataBaseService = DataBaseTempApiImpl.getInstance();
        }
        Station startStation = dataBaseService.selectStationByNameUpper(fromStationCode);
        Station endStation = dataBaseService.selectStationByNameUpper(toStationCode);
        if (null == startStation || null == endStation) {
            return "";
        }
        return startStation.getName() + "-" + endStation.getName();
    }
}
