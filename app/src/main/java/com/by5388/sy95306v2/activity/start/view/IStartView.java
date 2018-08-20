package com.by5388.sy95306v2.activity.start.view;

/**
 * @author by5388  on 2018/7/30.
 */

public interface IStartView {

    /**
     * 显示错误信息
     *
     * @param message 信息
     */
    void showErrorMessage(String message);

    /**
     * 更新完成
     */
    void updateFinish();

    /**
     * 提示用户更新的对话框
     */
    void showUpdateDialog();

    /**
     * 正在更新，更新进度
     *
     * @param allCount 总数
     */
    void updateAllCount(int allCount);

    /**
     * 已更新的进度
     *
     * @param progress 进度
     */
    void updateProgress(int progress);

    /**
     * 打开网络
     */
    void openNetWorkSetting();

    /**
     * 跳转
     */
    void toMainActivity();

    /**
     * 检查是否需要更新
     */
    void checkNeedUpdate();

    /**
     * 清除数据
     */
    void clearData();

    /**
     * 插入数据
     */
    void insertData();
}
