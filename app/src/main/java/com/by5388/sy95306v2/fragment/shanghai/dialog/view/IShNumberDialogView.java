package com.by5388.sy95306v2.fragment.shanghai.dialog.view;

import com.by5388.sy95306v2.fragment.shanghai.number.view.INumberView;

/**
 * @author by5388  on 2018/8/10.
 */

public interface IShNumberDialogView extends INumberView {
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
     * 搜索
     *
     * @param trainCode 车次
     * @param trainDate 日期
     * @return 自身
     */
    IShNumberDialogView search(String trainCode, String trainDate);

}
