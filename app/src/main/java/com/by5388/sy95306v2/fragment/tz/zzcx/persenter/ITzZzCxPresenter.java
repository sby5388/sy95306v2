package com.by5388.sy95306v2.fragment.tz.zzcx.persenter;

/**
 * @author by5388  on 2018/8/20.
 */
public interface ITzZzCxPresenter {
    /**
     * 关闭时，取消订阅关系，防止内存泄露
     */
    void unSubscribe();

    /**
     * 更新验证码图片
     */
    void refreshPassCodeBitmap();

    /**
     * 校验验证码
     *
     * @param passCode 验证码
     */
    void checkPassCode(String passCode);

    /**
     * 中转查询，获取列车信息
     *
     * @param date        日期
     * @param fromStation 出发站名
     * @param toStation   到达站名
     * @param randCode    验证码
     */
    void getTrainList(String date, String fromStation, String toStation, String randCode);



}
