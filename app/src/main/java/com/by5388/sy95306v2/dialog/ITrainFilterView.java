package com.by5388.sy95306v2.dialog;

import java.util.List;

/**
 * @author by5388  on 2018/8/7.
 */

public interface ITrainFilterView {

    /**
     * 显示
     */
    void show();

    /**
     * 关闭
     */

    void dismiss();

    /**
     * 正在显示？
     *
     * @return 正在显示
     */
    boolean isShowing();

    /**
     * 获取勾选类型
     *
     * @return 获取勾选类型
     */
    List<Integer> getSelectedType();


    /**
     * 刷新
     *
     * @return 自身
     */
    ITrainFilterView refresh();
}
