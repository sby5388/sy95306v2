package com.by5388.sy95306v2.fragment.cd.screen.view;

import com.by5388.sy95306v2.bean.cd.screen.ScreenStation;
import com.by5388.sy95306v2.fragment.cd.screen.CdScreenItem;

import java.util.List;

/**
 * @author by5388  on 2018/8/18.
 */
public interface ICdScreenView {
    /**
     * 开始查询
     */
    void startQuery();

    /**
     * 结束查询
     */
    void finishQuery();

    /**
     * 错误提示
     *
     * @param tip 提示
     */
    void showError(String tip);

    /**
     * 更新数据
     *
     * @param screenItems 数据
     */

    void updateCdScreenData(List<CdScreenItem> screenItems);

    /**
     * 成都大屏幕中所有车站
     *
     * @param screenStations 车站
     */
    void updateScreenStation(List<ScreenStation> screenStations);
}
