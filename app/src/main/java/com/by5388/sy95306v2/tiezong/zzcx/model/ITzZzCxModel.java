package com.by5388.sy95306v2.tiezong.zzcx.model;

import android.graphics.Bitmap;

import com.by5388.sy95306v2.tiezong.bean.yp.success.SuccessDataBean;

import io.reactivex.Observable;

/**
 * @author by5388  on 2018/8/20.
 */
public interface ITzZzCxModel {
    /**
     * 检验验证码
     *
     * @param randCode 验证码
     * @return 是否正确
     */
    Observable<Boolean> checkCode(String randCode);

    /**
     * 获取图片
     *
     * @return 图片
     */
    Observable<Bitmap> getPassCodeBitmap();

    /**
     * 站名是否正确：存在数据库中
     *
     * @param stationName 站名
     * @return 正确与否
     */
    boolean isErrorStationName(String stationName);

    /**
     * 中转查询
     *
     * @param queryDate       查询的日期
     * @param fromStationName 出发站
     * @param toStationName   目的站
     * @param randCode        验证码
     * @return 结果
     */
    Observable<SuccessDataBean> getResult(String queryDate, String fromStationName,
                                          String toStationName, String randCode);

    /**
     * 清除cookie
     */
    void clearCookie();
}
