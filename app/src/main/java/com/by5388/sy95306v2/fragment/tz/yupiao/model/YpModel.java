package com.by5388.sy95306v2.fragment.tz.yupiao.model;

import android.support.annotation.NonNull;
import android.util.Log;

import com.by5388.sy95306v2.bean.IYp;
import com.by5388.sy95306v2.bean.tzyp.QueryParam;
import com.by5388.sy95306v2.bean.tzyp.TzYpData;
import com.by5388.sy95306v2.bean.tzyp.YpResult;
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
public class YpModel implements IYpModel {
    public static final String TAG = "CdYpModel";
    private YpService service;

    public YpModel() {
        Retrofit retrofit = new YpNetTools().getRetrofit();
        service = retrofit.create(YpService.class);
    }

    @Override
    public Observable<List<IYp>> getYuPiaoData(@NonNull QueryParam param) {
        return service.getYpMessage(param.toString(), param.getDate())
                .flatMap(new Function<YpResult, ObservableSource<List<TzYpData>>>() {
                    @Override
                    public ObservableSource<List<TzYpData>> apply(YpResult result) throws Exception {
                        if (null == result) {
                            Log.e(TAG, "apply: " + "empty");
                            return Observable.just(new ArrayList<>());
                        }
                        return Observable.just(getYuPiaoData(result.getData()));
                    }
                }).flatMap(new Function<List<TzYpData>, ObservableSource<List<IYp>>>() {
                    @Override
                    public ObservableSource<List<IYp>> apply(List<TzYpData> tzYpData) throws Exception {
                        List<IYp> list = new ArrayList<>();
                        list.addAll(tzYpData);
                        return Observable.just(list);
                    }
                });
    }

    /**
     * 手动解析成相应的数据
     *
     * @param data 数据
     * @return 车次+余票信息
     */
    private List<TzYpData> getYuPiaoData(String data) {
        //正确的数据中：有14个数据
        final int max = 14;
        List<TzYpData> list = new ArrayList<>();
        String[] items = data.split(",;");
        for (String item : items) {
            String[] params = item.split(",");
            if (params.length != max) {
                continue;
            }
            list.add(new TzYpData(params));
        }
        return list;
    }


}
