package com.by5388.sy95306v2.activity.sy.list.model;

import com.by5388.sy95306v2.bean.Station;
import com.by5388.sy95306v2.bean.shenyang.TrainNumber;
import com.by5388.sy95306v2.database.DataBaseTempAction;
import com.by5388.sy95306v2.database.IShenYangStationDb;
import com.by5388.sy95306v2.dialog.bean.FilterBean;
import com.by5388.sy95306v2.net.sy.SyNetTools;
import com.by5388.sy95306v2.net.sy.SyService;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import io.reactivex.Observable;
import retrofit2.Retrofit;

import static com.by5388.sy95306v2.activity.sy.list.model.ModelData.getSelectedType;
import static com.by5388.sy95306v2.dialog.bean.FilterData.getFilterItems;

/**
 * @author by5388  on 2018/7/29.
 */

public class TrainListModel implements ITrainListModel {
    private final SyService trainNumberService;
    private final TrainNumberSort sort;
    private final List<Integer> selected;
    private final IShenYangStationDb dataBaseService;

    public TrainListModel() {
        Retrofit retrofit = new SyNetTools().getRetrofit();
        trainNumberService = retrofit.create(SyService.class);
        sort = new TrainNumberSort();
        selected = new ArrayList<>();
        dataBaseService = DataBaseTempAction.getInstance();
    }

    @Override
    public Observable<List<TrainNumber>> getTrainNumber(int date, String fromStation, String toStation) {
        return trainNumberService.getTrainNumberByP2P(date, fromStation, toStation);
    }

    @Override
    public Observable<List<TrainNumber>> sortTrainNumber(List<TrainNumber> trainNumbers, int position, boolean isUp) {
        sort.setPosition(position).setUp(isUp);
        return Observable.create(emitter -> {
            if (!(emitter.isDisposed())) {
                //过滤
                List<TrainNumber> nextTrainNumbers = getFilterTrainNumber(trainNumbers);
                //排序
                Collections.sort(nextTrainNumbers, sort);
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


    /**
     * 排序
     */
    private class TrainNumberSort implements Comparator<TrainNumber> {
        boolean isUp;
        int position;


        TrainNumberSort() {
            this.isUp = true;
            this.position = 0;
        }

        TrainNumberSort setPosition(int position) {
            this.position = position;
            return this;
        }

        public TrainNumberSort setUp(boolean up) {
            this.isUp = up;
            return this;
        }

        @Override
        public int compare(TrainNumber o1, TrainNumber o2) {
            try {
                switch (position) {
                    case 0:
                        if (isUp) {
                            return o1.getStartTime() - o2.getStartTime();
                        } else {
                            return o2.getStartTime() - o1.getStartTime();
                        }
                    case 1:
                        if (isUp) {
                            return o1.getSpendTime() - o2.getSpendTime();
                        } else {
                            return o2.getSpendTime() - o1.getSpendTime();
                        }
                    case 2:
                        if (isUp) {
                            return o1.getArriveTime() - o2.getArriveTime();
                        } else {
                            return o2.getArriveTime() - o1.getArriveTime();
                        }
                    default:
                        break;
                }
            } catch (NumberFormatException e) {
                return 0;
            }
            return 0;
        }

    }

    @Override
    public String getStationNames(String fromStationCode, String toStationCode) {
        Station startStation = dataBaseService.selectStationByNameUpper(fromStationCode);
        Station endStation = dataBaseService.selectStationByNameUpper(toStationCode);
        if (null == startStation || null == endStation) {
            return "";
        }
        return startStation.getName() + "-" + endStation.getName();
    }
}
