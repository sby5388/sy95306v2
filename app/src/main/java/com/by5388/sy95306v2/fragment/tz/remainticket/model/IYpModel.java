package com.by5388.sy95306v2.fragment.tz.remainticket.model;

import android.support.annotation.NonNull;

import com.by5388.sy95306v2.bean.IRemainingTicket;
import com.by5388.sy95306v2.bean.tzyp.QueryParam;

import java.util.List;

import io.reactivex.Observable;

/**
 * @author by5388  on 2018/8/13.
 */
public interface IYpModel {
    /**
     * 余票查询接口
     *
     * @param param 参数
     * @return 车次+余票信息
     */
    Observable<List<IRemainingTicket>> getYuPiaoData(@NonNull QueryParam param);
}
