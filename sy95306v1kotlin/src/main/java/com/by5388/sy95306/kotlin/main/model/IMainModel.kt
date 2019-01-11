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
    fun updateProgress(): Observable<Int>

    /**
     * 检查网络状态
     */
    fun getNetWordStatus(): Observable<Int>
}