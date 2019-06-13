package com.by5388.sy95306v2.module.guangzhou.p2p.view;

import com.by5388.sy95306v2.base.IBaseView;
import com.by5388.sy95306v2.module.guangzhou.bean.station.DataBeanP2P;

/**
 * @author by5388  on 2018/8/10.
 */

public interface IGzP2pView extends IBaseView {
    /**
     * 更新数据
     *
     * @param dataBean 数据
     */
    void updateDate(DataBeanP2P dataBean);
}
