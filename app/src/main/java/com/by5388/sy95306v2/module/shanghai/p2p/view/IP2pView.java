package com.by5388.sy95306v2.module.shanghai.p2p.view;

import com.by5388.sy95306v2.base.IBaseView;
import com.by5388.sy95306v2.module.shanghai.bean.ShanghaiTrainP2P;

import java.util.List;

/**
 * @author by5388  on 2018/8/10.
 */

public interface IP2pView extends IBaseView {

    /**
     * 刷新数据
     *
     * @param trainP2PS 数据
     */
    void updateList(List<ShanghaiTrainP2P> trainP2PS);

}
