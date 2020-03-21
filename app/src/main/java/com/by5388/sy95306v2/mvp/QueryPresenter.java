package com.by5388.sy95306v2.mvp;

import android.content.Context;
import android.os.Handler;
import android.text.TextUtils;

import com.by5388.sy95306v2.App;
import com.by5388.sy95306v2.database.DataBaseApiImpl;
import com.by5388.sy95306v2.database.IShenYangDbApi;
import com.by5388.sy95306v2.module.shenyang.bean.Station;
import com.by5388.sy95306v2.t201906.QueryThread;
import com.by5388.sy95306v2.t201906.TrainResult;

import java.net.HttpURLConnection;
import java.util.List;

/**
 * 一个使用原生Handler + handlerThread 来实现网络请求的
 *
 * @author Administrator  on 2019/9/12.
 */
public class QueryPresenter extends Presenter<QueryPresenter.QueryUi> implements QueryThread.Callback {
    private QueryThread mIQueryThread;
    private IShenYangDbApi mApi;

    private QueryPresenter(Context context) {
        mApi = DataBaseApiImpl.getInstance();
        mIQueryThread = new QueryThread(new Handler(), this);
        mIQueryThread.start();

    }

    public static QueryPresenter getInstance() {
        return Singleton.INSTANCE;
    }

    @Override
    public void push(TrainResult result) {
        // TODO: 2019/9/16 对结果进一步的判断
        final QueryUi ui = getUi();
        if (result == null) {
            ui.onFailQuery("数据为空");
            return;
        }
        // TODO: 2019/9/16 需要对返回的状态码进一步处理，
        final int httpStatus = result.httpstatus;

        if (httpStatus != HttpURLConnection.HTTP_OK) {
            ui.onFailQuery("查询失败");
            return;
        }

        final List<TrainResult.DataBean> dataBeans = result.data;
        if (dataBeans != null) {
            getUi().onResult(dataBeans);
            ui.onEndQuery();
        }
    }

    public void queryTicketPrices(final String date, final String from, final String to) {
        // TODO: 2019/9/24 checkName
        final Station stationFrom = mApi.selectStationByName(from);
        if (stationFrom == null || TextUtils.isEmpty(stationFrom.getNameUpper())) {
            getUi().onFailQuery("错误名称 " + from);
            return;
        }
        final Station stationTo = mApi.selectStationByName(to);
        if (stationTo == null || TextUtils.isEmpty(stationTo.getNameUpper())) {
            getUi().onFailQuery("错误名称 " + to);
            return;
        }

        //type 1： all
        final String type = "1";
        getUi().onStartQuery();
        mIQueryThread.queryTicketPrices(date, stationFrom.getNameUpper(), stationTo.getNameUpper(), type);
    }

    public interface QueryUi extends Ui {
        void onStartQuery();

        void onEndQuery();

        void onFailQuery(String s);

        void onResult(List<TrainResult.DataBean> result);
    }

    private static final class Singleton {
        private static final QueryPresenter INSTANCE = new QueryPresenter(App.getInstance());
    }


}
