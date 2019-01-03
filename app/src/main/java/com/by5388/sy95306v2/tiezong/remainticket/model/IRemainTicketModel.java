package com.by5388.sy95306v2.tiezong.remainticket.model;

import android.support.annotation.NonNull;

import com.by5388.sy95306v2.bean.IRemainingTicket;
import com.by5388.sy95306v2.bean.second.QueryParam;

import java.util.List;

import io.reactivex.Observable;

/**
 * @author by5388  on 2018/8/13.
 */
public interface IRemainTicketModel {
    /**
     * 余票查询接口
     *
     * @param param 参数
     * @return 车次+余票信息
     */
    Observable<List<IRemainingTicket>> getRemainTicketData(@NonNull QueryParam param);
}
