package com.by5388.sy95306v2.fragment.tz.combination.model;

import android.graphics.Bitmap;
import android.support.annotation.NonNull;

import com.by5388.sy95306v2.bean.Station;
import com.by5388.sy95306v2.bean.shenyang.TrainDetail;
import com.by5388.sy95306v2.bean.tz.TzResult;
import com.by5388.sy95306v2.bean.tz.yp.success.SuccessDataBean;
import com.by5388.sy95306v2.bean.tz.yp.success.TzDataBean;
import com.by5388.sy95306v2.database.DataBaseTempAction;
import com.by5388.sy95306v2.database.IShenYangStationDb;
import com.by5388.sy95306v2.fragment.tz.GetPassCodeImpl;
import com.by5388.sy95306v2.fragment.tz.IGetPassCodeService;
import com.by5388.sy95306v2.fragment.tz.api.cookie.ReceivedCookieInterceptor;
import com.by5388.sy95306v2.net.sy.SyNetTools;
import com.by5388.sy95306v2.net.sy.SyService;

import java.util.ArrayList;
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
public class CombinationModel implements ICombinationModel {
    private final IGetPassCodeService service;
    private final SyService syService;
    private final IShenYangStationDb db;
    private final Random random;
    private static final String COOKIE_12306 = ReceivedCookieInterceptor.COOKIE_12306;
    private final HashMap<String, List<Cookie>> cookieStore;

    public CombinationModel() {
        cookieStore = new HashMap<>();
        service = new GetPassCodeImpl(cookieStore);
        syService = new SyNetTools().getRetrofit().create(SyService.class);
        db = DataBaseTempAction.getInstance();
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
    public boolean isTrueStationName(String stationName) {
        Station station = db.selectStationByName(stationName.trim());
        return Station.EMPTY_ID == station.getId();
    }

    @Override
    public void clearCookie() {
        cookieStore.clear();
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
    public Observable<SuccessDataBean> getOnLyResult(String queryDate, String fromStationName, String toStationName, String randCode, String trainCode) {
        return getResult(queryDate, fromStationName, toStationName, randCode)
                .flatMap((Function<SuccessDataBean, ObservableSource<SuccessDataBean>>) successDataBean -> {
                    SuccessDataBean result = getSuccessDataBeanByTrainCode(successDataBean, trainCode);
                    return Observable.just(result);
                });
    }

    /**
     * 结果中 筛符合的数据
     *
     * @param successDataBean 请求结果
     * @param trainCode       车次
     * @return 真正结果
     */
    @NonNull
    private SuccessDataBean getSuccessDataBeanByTrainCode(SuccessDataBean successDataBean, String trainCode) {
        SuccessDataBean result = new SuccessDataBean();
        result.setFlag(successDataBean.isFlag());
        result.setIsThrough(successDataBean.getIsThrough());
        // FIXME: 2018/8/25 应当使用深拷贝
        List<TzDataBean> dataBeans = new ArrayList<>();
        for (TzDataBean data : successDataBean.getDatas()) {
            if (data.getStation_train_code().contains(trainCode)) {
                dataBeans.add(new TzDataBean(data));
            }
        }
        result.setDatas(dataBeans);
        return result;
    }


    @Override
    public Observable<List<TrainDetail>> getTrainByTrainCode(int date, String trainCode) {
        return syService.getTrainByTrainCode(date, trainCode);
    }
}

