package com.by5388.sy95306.kotlin.main.model

import io.reactivex.Observable

/**
 * @author  by5388  on 2019/1/8.
 */
interface IMainModel {
    /**
     * 检查是否需要更新
     * -1:时 不需要更新
     * 否则更新
     */
    fun checkUpdate(): Observable<Boolean>

    /**
     * 已经更新的数量
     */
    fun insertStationList(): Observable<Int>

    /**
     * 检查网络状态
     */
    fun getNetWordStatus(): Observable<Int>

    /**
     * 车站总数量
     *
     * @return 总数量
     */
    fun getStationCount(): Observable<Int>

    /**
     * 清空数据库
     *
     * @return 0:清空完成
     */
    fun clearData(): Observable<Int>

    /**
     * 更新完成，更新版本号
     */
    fun finishUpdate()
}