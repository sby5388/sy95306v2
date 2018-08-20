package com.by5388.sy95306v2.fragment.tz.api;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.functions.Function;
import okhttp3.ResponseBody;

/**
 * @author by5388  on 2018/8/17.
 */
public class GetPassCode implements IGetPassCodeService {


    private PassCodeService service;

    public GetPassCode() {
        service = PassCodeNetTools.getRetrofit().create(PassCodeService.class);
    }


    @Override
    public Observable<Bitmap> getBitmap(double value) {
        if (null == service) {
            service = PassCodeNetTools.getRetrofit().create(PassCodeService.class);
        }
        final String module = "other";
        final String rand = "sjrand";
        return service.getNewPassCode(module, rand, value)
                .flatMap(new Function<ResponseBody, ObservableSource<Bitmap>>() {
                    @Override
                    public ObservableSource<Bitmap> apply(ResponseBody responseBody) throws Exception {
                        byte[] data = responseBody.bytes();
                        //TODO
//                        if (null == data || data.length == 0) {
//                            return Observable.just(null);
//                        }
                        return Observable.just(BitmapFactory.decodeByteArray(data, 0, data.length));

                    }
                });

    }
}
