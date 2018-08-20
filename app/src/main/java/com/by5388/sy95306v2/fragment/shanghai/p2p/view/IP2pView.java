package com.by5388.sy95306v2.fragment.shanghai.p2p.view;

import com.by5388.sy95306v2.bean.shanghai.ShanghaiTrainP2P;

import java.util.List;

/**
 * @author by5388  on 2018/8/10.
 */

public interface IP2pView {

    /**
     * 刷新数据
     *
     * @param trainP2PS 数据
     */
    void updateList(List<ShanghaiTrainP2P> trainP2PS);

    void startQuery();

    void finishQuery();

    void showError(String tip);
}
