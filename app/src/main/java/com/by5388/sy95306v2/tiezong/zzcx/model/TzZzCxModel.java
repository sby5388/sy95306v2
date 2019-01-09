package com.by5388.sy95306v2.tiezong.zzcx.model;

import android.graphics.Bitmap;
import android.text.TextUtils;

import com.by5388.sy95306v2.shenyang.bean.Station;
import com.by5388.sy95306v2.tiezong.bean.TzResult;
import com.by5388.sy95306v2.tiezong.bean.yp.success.SuccessDataBean;
import com.by5388.sy95306v2.database.DataBaseTempApi;
import com.by5388.sy95306v2.database.IShenYangStationDb;
import com.by5388.sy95306v2.tiezong.api.pass.code.GetPassCodeImpl;
import com.by5388.sy95306v2.tiezong.api.pass.code.IGetPassCodeService;
import com.by5388.sy95306v2.tiezong.api.cookie.ReceivedCookieInterceptor;
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
        db = DataBaseTempApi.getInstance();
        random = new Random();
    }

    @Override
    public Observable<Boolean> checkCode(String randCode) {
        return service.checkCode(randCode);
    }

    @Override
    public Observable<Bitmap> getPassCodeBitmap() {
        double value = random.nextDouble();
        return service.getBitmap(value);
    }

    @Override
    public boolean isErrorStationName(String stationName) {
        Station station = db.selectStationByName(stationName.trim());
        return TextUtils.isEmpty(station.getName());
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
        cookieStore.clear();
    }
}

