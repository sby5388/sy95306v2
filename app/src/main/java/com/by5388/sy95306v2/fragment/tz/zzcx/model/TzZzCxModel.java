package com.by5388.sy95306v2.fragment.tz.zzcx.model;

import android.graphics.Bitmap;

import com.by5388.sy95306v2.bean.Station;
import com.by5388.sy95306v2.bean.tz.TzResult;
import com.by5388.sy95306v2.bean.tz.yp.success.SuccessDataBean;
import com.by5388.sy95306v2.database.DataBaseTempAction;
import com.by5388.sy95306v2.database.IShenYangStationDb;
import com.by5388.sy95306v2.fragment.tz.GetPassCodeImpl;
import com.by5388.sy95306v2.fragment.tz.IGetPassCodeService;
import com.by5388.sy95306v2.fragment.tz.api.cookie.ReceivedCookieInterceptor;
import com.by5388.sy95306v2.setting.ISettingSharedPreferences;
import com.by5388.sy95306v2.setting.SettingSharedPreferences;

import java.util.HashMap;
import java.util.List;
import java.util.Random;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.functions.Function;
import okhttp3.Cookie;

/**
 * @author by5388  on 2018/8/20.
 */
public class TzZzCxModel implements ITzZzCxModel {
    private final IGetPassCodeService service;
    private final IShenYangStationDb db;
    private final Random random;
    private static final String COOKIE_12306 = ReceivedCookieInterceptor.COOKIE_12306;
    private final HashMap<String, List<Cookie>> cookieStore;

    public TzZzCxModel() {
        cookieStore = new HashMap<>();
        service = new GetPassCodeImpl(cookieStore);
        db = DataBaseTempAction.getInstance();
        random = new Random();
        ISettingSharedPreferences sharedPreference = SettingSharedPreferences.getInstance();
    }

    @Override
    public Observable<Boolean> checkCode(String randCode) {
        //TODO presenter 层对数据长度进行校验
        return service.checkCode(randCode);
    }

    @Override
    public Observable<Bitmap> getPassCodeBitmap() {
        double value = random.nextDouble();
        return service.getBitmap(value);
    }

    @Override
    public boolean isTrueStationName(String stationName) {
        Station station = db.selectStationByName(stationName.trim());
        return Station.EMPTY_ID != station.getId();
    }

    @Override
    public Observable<SuccessDataBean> getResult(String queryDate, String fromStationName, String toStationName, String randCode) {
        String fromStationCode = db.selectStationByName(fromStationName).getNameUpper();
        String toStationCode = db.selectStationByName(toStationName).getNameUpper();
        return service.getZzCxData(queryDate, fromStationCode,
                toStationCode, fromStationName, toStationName, randCode)
                .flatMap((Function<TzResult<SuccessDataBean>, ObservableSource<SuccessDataBean>>) successDataBeanTzResult -> Observable.just(successDataBeanTzResult.getData()));

    }

    @Override
    public void clearCookie() {
        //sharedPreference.remove(COOKIE_12306);
        cookieStore.clear();
    }
}

