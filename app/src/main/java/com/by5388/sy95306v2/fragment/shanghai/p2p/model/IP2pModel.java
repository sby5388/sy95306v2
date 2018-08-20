package com.by5388.sy95306v2.fragment.shanghai.p2p.model;

import com.by5388.sy95306v2.bean.shanghai.InfoBeanP2P;
import com.by5388.sy95306v2.bean.shanghai.ShanghaiTrainP2P;

import java.util.List;

import io.reactivex.Observable;

/**
 * @author by5388  on 2018/8/10.
 */

public interface IP2pModel {

    /**
     * p2p
     *
     * @param info 查询信息
     * @return 车次列表
     */
    Observable<List<ShanghaiTrainP2P>> queryTrainP2P(InfoBeanP2P info);
}
