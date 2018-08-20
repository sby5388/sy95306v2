package com.by5388.sy95306v2.fragment.tz.api;

import android.graphics.Bitmap;

import io.reactivex.Observable;


/**
 * @author by5388  on 2018/8/17.
 */
public interface IGetPassCodeService {
    /**
     * 获取图片
     *
     * @param value 随机数
     * @return 图片
     */
    Observable<Bitmap> getBitmap(double value);


}
