package com.by5388.sy95306v2.fragment.gz.p2p.view;

import com.by5388.sy95306v2.bean.guangzhou.station.DataBeanP2P;

/**
 * @author by5388  on 2018/8/10.
 */

public interface IGzP2pView {
    void startQuery();

    void finishQuery();

    void showError(String tip);

    void updateDate(DataBeanP2P dataBean);
}
