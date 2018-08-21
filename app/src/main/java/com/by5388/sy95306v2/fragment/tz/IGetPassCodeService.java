package com.by5388.sy95306v2.fragment.tz;

import android.graphics.Bitmap;

import com.by5388.sy95306v2.bean.tz.TzResult;
import com.by5388.sy95306v2.bean.tz.yp.success.SuccessDataBean;

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

    /**
     * 检验验证码
     *
     * @param randCode 验证码
     * @return 是否正确
     */
    Observable<Boolean> checkCode(String randCode);


    /**
     * 中转查询：实际是余票查询
     *
     * @param queryDate       日期，格式"2018-08-20"
     * @param fromStationCode 出发站，电报码
     * @param toStationCode   目的站，电报码
     * @param fromStationName 出发站
     * @param toStationName   目的站
     * @param randCode        验证码
     * @return 结果，可能是成功也可能是失败
     */
    Observable<TzResult<SuccessDataBean>> getZzCxData(
            String queryDate,
            String fromStationCode,
            String toStationCode,
            String fromStationName,
            String toStationName,
            String randCode
    );


}
