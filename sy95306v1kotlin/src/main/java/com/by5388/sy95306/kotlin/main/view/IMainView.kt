package com.by5388.sy95306.kotlin.main.view

import android.support.annotation.StringRes

/**
 * 主要是数据库检查-更新的部分
 * @author  by5388  on 2019/1/5.
 */
interface IMainView {
    /**
     * 显示更新对话框
     * @param allCount 总数
     */
    fun showUpdateDiaglog(allCount: Int)

    /**
     * 显示更新进度
     * @param progress 当前已更新的数量
     */
    fun updateProgress(progress: Int)

    /**
     * 通知更新
     */
    fun notifyUpdate()

    /**
     * 提示
     *
     * @param tip 提示
     */
    fun tip(@StringRes tip: Int)

    /**
     * 打开进度对话框（升级中）
     */
    fun showUpdating()

    /**
     * 升级完成
     */
    fun finishUpdate()
}