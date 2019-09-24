package com.by5388.sy95306v2.common;

import com.by5388.sy95306v2.database.IChengDuDbApi;
import com.by5388.sy95306v2.database.IShenYangDbApi;
import com.by5388.sy95306v2.module.chengdu.bean.screen.ScreenStation;
import com.by5388.sy95306v2.module.shenyang.bean.Station;

import java.util.List;

import io.reactivex.Observable;

/**
 * @author Administrator  on 2019/9/19.
 */
public class TestApi<T> implements IShenYangDbApi {
    private CommonDataBase mDataBase;

    public TestApi() {
        mDataBase = CommonDataBase.getInstance();
    }

    @Override
    public int deleteAllStation() {
        return 0;
    }

    @Override
    public Observable<Integer> insertStationList(List<Station> stations) {
        return null;
    }

    @Override
    public Station selectStationByNameUpper(String nameUpper) {
        return null;
    }

    @Override
    public Station selectStationByName(String stationName) {
        return null;
    }

    @Override
    public List<Station> selectStationList(String key) {
        return null;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    private T getData() {
        throw new RuntimeException();
    }
}
