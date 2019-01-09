package com.by5388.sy95306.kotlin.main.view

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
     * @param currentCount 当前已更新的数量
     */
    fun updateProgress(currentCount: Int)

}