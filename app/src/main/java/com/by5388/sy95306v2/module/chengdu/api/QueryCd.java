package com.by5388.sy95306v2.module.chengdu.api;

import android.text.TextUtils;
import android.util.Log;

import com.by5388.sy95306v2.module.chengdu.api.late.CdLateNetTools;
import com.by5388.sy95306v2.module.chengdu.api.late.CdLateService;
import com.by5388.sy95306v2.module.chengdu.api.screen.CdScreenNetTools;
import com.by5388.sy95306v2.module.chengdu.api.screen.CdScreenService;
import com.by5388.sy95306v2.module.chengdu.api.yp.CdYpNetTools;
import com.by5388.sy95306v2.module.chengdu.api.yp.CdYpService;
import com.by5388.sy95306v2.module.chengdu.bean.late.CdLateDetail;
import com.by5388.sy95306v2.module.chengdu.bean.late.CdLateResultTop;
import com.by5388.sy95306v2.module.chengdu.bean.late.CdLateStation;
import com.by5388.sy95306v2.module.chengdu.bean.late.CdTrainAllNodeBean;
import com.by5388.sy95306v2.module.chengdu.bean.screen.ScreenArriveDetail;
import com.by5388.sy95306v2.module.chengdu.bean.screen.ScreenLeaveDetail;
import com.by5388.sy95306v2.module.chengdu.bean.screen.ScreenStation;
import com.by5388.sy95306v2.module.chengdu.bean.yupiao.CdAllResultDataBean;
import com.by5388.sy95306v2.module.chengdu.bean.yupiao.CdRemainTicketDetailBean;
import com.by5388.sy95306v2.module.chengdu.bean.yupiao.CdYpTop;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.functions.Function;
import okhttp3.ResponseBody;

/**
 * 成都铁路局对外统一暴露接口
 *
 * @author by5388
 * @date 2018/8/16 11:12
 */
public class QueryCd implements ICdQuery {
    private static final String TAG = "QueryCd";
    private CdYpService yPService;
    private CdLateService lateService;
    private CdScreenService screenService;
    private final Gson gson = new Gson();
    private static final String[] LOGIN_PARAM = {"10.192.111.79", "hhs", "hhs"};
    private final String userMessage = gson.toJson(Arrays.asList(LOGIN_PARAM));
    private final String emptyJson = gson.toJson(new ArrayList<>());


    public QueryCd() {

    }

    /**
     * string->车站集合
     *
     * @param result 网络返回结果
     * @return 车站集合
     */
    private static List<CdLateStation> getCdStationList(final String result) {
        String from = result;
        from = from.replace("\"", "");
        from = from.replace("[[", "");
        from = from.replace("]]", "");
        String[] stations = from.split("],\\[");
        List<CdLateStation> cdLateStations = new ArrayList<>();
        for (String station : stations) {
            cdLateStations.add(getCdStation(station));
        }
        return cdLateStations;
    }

    /**
     * String->车站
     *
     * @param cd 参数
     * @return 车站
     */
    private static CdLateStation getCdStation(final String cd) {
        String[] params = cd.split(",");
        return CdLateStation.getStation(params);
    }


    @Override
    public Observable<List<CdRemainTicketDetailBean>> getCdRemainTicket(String fromStation, String toStation, String date) {
        if (null == yPService) {
            yPService = new CdYpNetTools().getRetrofit().create(CdYpService.class);
        }
        final String queryType = "QB";
        return yPService.queryYp(fromStation, toStation, date, queryType)
                .flatMap((Function<CdYpTop, ObservableSource<List<CdRemainTicketDetailBean>>>) cdYpTop -> {
                    if (null == cdYpTop) {
                        return Observable.just(new ArrayList<>());
                    }
                    CdAllResultDataBean dataBean = cdYpTop.getData();
                    if (null == dataBean) {
                        return Observable.just(new ArrayList<>());
                    }
                    List<CdRemainTicketDetailBean> list = dataBean.getDatas();
                    if (null == list || list.isEmpty()) {
                        return Observable.just(new ArrayList<>());
                    }
                    return Observable.just(list);
                });
    }

    private static void println(String tip) {
        Log.e(TAG, "println: " + tip);
    }

    @Override
    public Observable<List<CdLateStation>> getLate(String trainCode, String date) {
        if (null == lateService) {
            lateService = new CdLateNetTools().getRetrofit().create(CdLateService.class);
        }
        //纯数字的日期
        date = date.replace("-", "");
        final String typeCode = "ex_1015";
        final List<String> params = Arrays.asList(trainCode, date);
        return lateService
                .lateTimeCCStation(typeCode, gson.toJson(params), emptyJson, emptyJson, userMessage)
                .flatMap((Function<ResponseBody, ObservableSource<List<CdLateStation>>>) responseBody -> {
                    String result = responseBody.string().trim();
                    if (TextUtils.isEmpty(result)) {
                        return Observable.just(new ArrayList<>());
                    }
                    return Observable.just(getCdStationList(result));
                });
    }

    @Override
    public Observable<List<CdTrainAllNodeBean>> getLateDetail(String trainCode, String date, String stationName) {
        if (null == lateService) {
            lateService = new CdLateNetTools().getRetrofit().create(CdLateService.class);
        }
        final String typeCode = "ex_1009";
        date = replace(date);
        List<String> params = new ArrayList<>();
        // TODO: 2019/3/27 使用不可变的数据
//        List<String> params2 = Arrays.asList(date,trainCode,stationName);
        params.add(date);
        params.add(trainCode);
        params.add(stationName);
        return lateService
                .lateTimeCCDetail(typeCode, gson.toJson(params), emptyJson, emptyJson, userMessage)
                .flatMap((Function<CdLateResultTop, ObservableSource<List<CdTrainAllNodeBean>>>) top -> {
                    if (null == top || !top.isStatus()) {
                        println("empty");
                        return Observable.just(new ArrayList<>());
                    }
                    try {
                        CdLateDetail detail = gson.fromJson(top.getData(), CdLateDetail.class);
                        if (!detail.isStatus()) {
                            return Observable.just(new ArrayList<>());
                        }
                        return Observable.just(detail.getTrainAllNode());
                    } catch (Exception e) {
                        println(e.getLocalizedMessage());
                        return Observable.just(new ArrayList<>());
                    }
                });
    }

    @Override
    public Observable<List<ScreenStation>> getCdScreenStationList() {
        if (null == screenService) {
            screenService = new CdScreenNetTools().getRetrofit().create(CdScreenService.class);
        }
        final String typeCode = "C50102";
        return screenService.getScreenStationList(typeCode, userMessage, emptyJson, emptyJson, emptyJson);
    }

    /**
     * 候乘：code=C50101&login=["10.192.111.79","hhs","hhs"]&sql=["20180817","GIW"]&where=[]&order=[]
     *
     * @param stationCode 车站电报码
     * @param date        日期
     * @return 数据
     */

    @Override
    public Observable<List<ScreenLeaveDetail>> getLeaveDetail(String stationCode, String date) {
        if (null == screenService) {
            screenService = new CdScreenNetTools().getRetrofit().create(CdScreenService.class);
        }
        final String typeCode = "C50101";
        date = replace(date);
        List<String> params = Arrays.asList(date, stationCode);
        return screenService.getLeaveDetail(typeCode, userMessage, gson.toJson(params), emptyJson, emptyJson);
    }

    /**
     * 接站：code=C5054&login=["10.192.111.79","hhs","hhs"]&sql=["20180817","GIW"]&where=[]&order=[]
     *
     * @param stationCode 车站电报码
     * @param date        日期
     * @return 数据
     */
    @Override
    public Observable<List<ScreenArriveDetail>> getArriveDetail(String stationCode, String date) {
        if (null == screenService) {
            screenService = new CdScreenNetTools().getRetrofit().create(CdScreenService.class);
        }
        final String typeCode = "C5054";
        date = replace(date);
        List<String> params = Arrays.asList(date, stationCode);
        return screenService.getArriveDetail(typeCode, userMessage, gson.toJson(params), emptyJson, emptyJson);
    }

    private static String replace(String original) {
        return original.replace("[^0-9]", "").replace("-", "");
    }
}
