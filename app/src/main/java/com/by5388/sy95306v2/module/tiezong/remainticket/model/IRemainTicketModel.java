package com.by5388.sy95306v2.module.tiezong.remainticket.model;

import com.by5388.sy95306v2.bean.IRemainingTicket;
import com.by5388.sy95306v2.bean.thrid.QueryParam;

import java.util.List;

import androidx.annotation.NonNull;
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
