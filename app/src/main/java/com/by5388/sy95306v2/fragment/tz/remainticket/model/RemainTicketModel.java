package com.by5388.sy95306v2.fragment.tz.remainticket.model;

import android.support.annotation.NonNull;
import android.util.Log;

import com.by5388.sy95306v2.bean.IRemainingTicket;
import com.by5388.sy95306v2.bean.second.QueryParam;
import com.by5388.sy95306v2.bean.second.SecondRemainTicketData;
import com.by5388.sy95306v2.bean.second.SecondResult;
import com.by5388.sy95306v2.net.yupiao.YpNetTools;
import com.by5388.sy95306v2.net.yupiao.YpService;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.functions.Function;
import retrofit2.Retrofit;

/**
 * @author by5388  on 2018/8/13.
 */
public class RemainTicketModel implements IRemainTicketModel {
    private static final String TAG = "CdRemainTicketModel";
    private final YpService service;

    public RemainTicketModel() {
        Retrofit retrofit = new YpNetTools().getRetrofit();
        service = retrofit.create(YpService.class);
    }

    @Override
    public Observable<List<IRemainingTicket>> getRemainTicketData(@NonNull QueryParam param) {
        return service.getYpMessage(param.toString(), param.getDate())
                .flatMap((Function<SecondResult, ObservableSource<List<SecondRemainTicketData>>>) result -> {
                    if (null == result) {
                        Log.e(TAG, "apply: " + "empty");
                        return Observable.just(new ArrayList<>());
                    }
                    return Observable.just(getRemainTicketData(result.getData()));
                }).flatMap((Function<List<SecondRemainTicketData>, ObservableSource<List<IRemainingTicket>>>) tzYpData -> {
                    List<IRemainingTicket> list = new ArrayList<>(tzYpData);
                    return Observable.just(list);
                });
    }

    /**
     * 手动解析成相应的数据
     *
     * @param data 数据
     * @return 车次+余票信息
     */
    private List<SecondRemainTicketData> getRemainTicketData(String data) {
        //正确的数据中：有14个数据
        final int max = 14;
        List<SecondRemainTicketData> list = new ArrayList<>();
        String[] items = data.split(",;");
        for (String item : items) {
            String[] params = item.split(",");
            if (params.length != max) {
                continue;
            }
            list.add(new SecondRemainTicketData(params));
        }
        return list;
    }


}
