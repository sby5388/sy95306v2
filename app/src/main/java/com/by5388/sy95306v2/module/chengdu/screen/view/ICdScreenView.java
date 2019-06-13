package com.by5388.sy95306v2.module.chengdu.screen.view;

import com.by5388.sy95306v2.base.IBaseView;
import com.by5388.sy95306v2.module.chengdu.bean.screen.ScreenStation;
import com.by5388.sy95306v2.module.chengdu.screen.CdScreenItem;

import java.util.List;

/**
 * @author by5388  on 2018/8/18.
 */
public interface ICdScreenView extends IBaseView {
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
