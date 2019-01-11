package com.by5388.sy95306v2.main.view;

/**
 * 检查数据更新移动到主页面之后，减少启动页面的等待时间
 *
 * @author by5388  on 2019/1/2.
 */
public interface IMainView {
    /**
     * 通知更新
     */
    void notifyUpdate();

    /**
     * 开始检查
     */
    void startChecking();

    /**
     * 检查完成
     */
    void finishChecked();


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
     * 提示
     *
     * @param tip 提示
     */
    void tip(String tip);

    /**
     * 打开进度对话框（升级中）
     */
    void showUpdating();

    /**
     * 升级完成
     */
    void finishUpdate();
}
