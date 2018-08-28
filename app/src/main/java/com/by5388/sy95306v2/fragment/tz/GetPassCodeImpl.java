package com.by5388.sy95306v2.fragment.tz;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.annotation.NonNull;
import android.text.TextUtils;

import com.by5388.sy95306v2.bean.tz.TzResult;
import com.by5388.sy95306v2.bean.tz.check.PassCodeDataBean;
import com.by5388.sy95306v2.bean.tz.number.NumberDataBean;
import com.by5388.sy95306v2.bean.tz.number.NumberListDataBean;
import com.by5388.sy95306v2.bean.tz.yp.success.SuccessDataBean;
import com.by5388.sy95306v2.fragment.tz.api.IPassCodeService;
import com.by5388.sy95306v2.fragment.tz.api.PassCodeNetTools;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.functions.Function;
import okhttp3.Cookie;
import okhttp3.ResponseBody;

/**
 * @author by5388  on 2018/8/17.
 */
public class GetPassCodeImpl implements IGetPassCodeService {
    private final IPassCodeService service;

    public GetPassCodeImpl(@NonNull HashMap<String, List<Cookie>> cookieStore) {
        service = new PassCodeNetTools(cookieStore).getRetrofit().create(IPassCodeService.class);
    }


    @Override
    public Observable<Bitmap> getBitmap(double value) {
        final String module = "other";
        final String rand = "sjrand";
        return service.getNewPassCode(module, rand, value)
                .flatMap((Function<ResponseBody, ObservableSource<Bitmap>>) responseBody -> {
                    byte[] data = responseBody.bytes();
                    return Observable.just(BitmapFactory.decodeByteArray(data, 0, data.length));
                });

    }


    @Override
    public Observable<Boolean> checkCode(String randCode) {
        final String rand = "sjrand";
        final int codeLength = 4;

        if (TextUtils.isEmpty(randCode) || codeLength != randCode.length()) {
            return Observable.just(false);
        }
        return service.checkRandCodeAnsyn(rand, randCode)
                .flatMap((Function<TzResult<PassCodeDataBean>, ObservableSource<Boolean>>) result -> {
                    if (null == result || null == result.getData()) {
                        return Observable.just(false);
                    }
                    PassCodeDataBean bean = result.getData();
                    return Observable.just(bean.isRight());
                });
    }


    @Override
    public Observable<TzResult<SuccessDataBean>> getZzCxData(String queryDate, String fromStationCode, String toStationCode, String fromStationName, String toStationName, String randCode) {
        final String changeStationText = "";
        return service.getZzCxData(queryDate, fromStationCode, toStationCode, fromStationName, toStationName, randCode, changeStationText);
    }

    @Override
    public Observable<List<NumberDataBean>> getListNumberDataBean(String trainNo, String fromStationCOde, String toStationCOde, String date) {
        return service.getNumberList(trainNo, fromStationCOde, toStationCOde, date)
                .flatMap(new Function<TzResult<NumberListDataBean>, ObservableSource<List<NumberDataBean>>>() {
                    @Override
                    public ObservableSource<List<NumberDataBean>> apply(TzResult<NumberListDataBean> result) {
                        List<NumberDataBean> list =new ArrayList<>();
                        System.out.println(1);
                        if (null == result || !result.isStatus()) {
                          return   Observable.just(list);
                        }
                        System.out.println(2);
                        NumberListDataBean bean = result.getData();
                        if (null == bean) {
                            return   Observable.just(list);
                        }
                        System.out.println(3);
                        List<NumberDataBean> listA = bean.getData();
                        if (null == listA || listA.isEmpty()) {
                            return   Observable.just(list);
                        }
                        System.out.println(4);
                        return   Observable.just(listA);
                    }
                });

    }
}
