package com.by5388.sy95306v2.net.cd;

import android.support.annotation.Nullable;

import com.by5388.sy95306v2.bean.cd.late.CdLateDetail;
import com.by5388.sy95306v2.bean.cd.late.CdLateResultTop;
import com.by5388.sy95306v2.bean.cd.late.CdLateStation;
import com.by5388.sy95306v2.bean.cd.late.CdTrainAllNodeBean;
import com.by5388.sy95306v2.bean.cd.screen.ScreenArriveDetail;
import com.by5388.sy95306v2.bean.cd.screen.ScreenLeaveDetail;
import com.by5388.sy95306v2.bean.cd.screen.ScreenStation;
import com.by5388.sy95306v2.bean.cd.yupiao.CdAllResultDataBean;
import com.by5388.sy95306v2.bean.cd.yupiao.CdYpDetailBean;
import com.by5388.sy95306v2.bean.cd.yupiao.CdYpTop;
import com.by5388.sy95306v2.net.cd.late.CdLateNetTools;
import com.by5388.sy95306v2.net.cd.late.CdLateService;
import com.by5388.sy95306v2.net.cd.screen.CdScreenNetTools;
import com.by5388.sy95306v2.net.cd.screen.CdScreenService;
import com.by5388.sy95306v2.net.cd.yp.CdYpNetTools;
import com.by5388.sy95306v2.net.cd.yp.CdYpService;
import com.google.gson.Gson;

import java.io.IOException;
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
    private CdYpService yPService;
    private CdLateService lateService;
    private CdScreenService screenService;
    private Gson gson = new Gson();
    private static final String[] LOGIN_PARAM = {"10.192.111.79", "hhs", "hhs"};
    private final String userMessage = gson.toJson(Arrays.asList(LOGIN_PARAM));
    private final String emptyJson = gson.toJson(new ArrayList<>());
    /**
     * 8个参数
     */
    private static final int PARAM_CD_STATION = 8;


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
    @Nullable
    private static CdLateStation getCdStation(final String cd) {
        String[] params = cd.split(",");
        if (PARAM_CD_STATION == params.length) {
            return new CdLateStation(params);
        }
        return null;
    }


    @Override
    public Observable<List<CdYpDetailBean>> getCdYp(String fromStation, String toStation, String date) {
        if (null == yPService) {
            yPService = new CdYpNetTools().getRetrofit().create(CdYpService.class);
        }
        final String queryType = "QB";
        return yPService.queryYp(fromStation, toStation, date, queryType)
                .flatMap(new Function<CdYpTop, ObservableSource<List<CdYpDetailBean>>>() {
                    @Override
                    public ObservableSource<List<CdYpDetailBean>> apply(CdYpTop cdYpTop) {
                        if (null == cdYpTop) {
                            System.err.println("cdYpTop");
                            return Observable.just(new ArrayList<>());
                        }
                        CdAllResultDataBean dataBean = cdYpTop.getData();
                        if (null == dataBean) {
                            System.err.println("dataBean");
                            return Observable.just(new ArrayList<>());
                        }
                        List<CdYpDetailBean> list = dataBean.getDatas();
                        if (null == list || list.isEmpty()) {
                            System.err.println("list");
                            return Observable.just(new ArrayList<>());
                        }
                        return Observable.just(list);
                    }
                });
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
                .flatMap(new Function<ResponseBody, ObservableSource<List<CdLateStation>>>() {
                    @Override
                    public ObservableSource<List<CdLateStation>> apply(ResponseBody responseBody) throws IOException {
                        String result = responseBody.string().trim();
                        //TODO 使用TextUtils.isEmpty()
                        if (result.length() == 0) {
                            return Observable.just(new ArrayList<>());
                        }
                        return Observable.just(getCdStationList(result));
                    }
                });

    }

    @Override
    public Observable<List<CdTrainAllNodeBean>> getLateDetail(String trainCode, String date, String stationName) {
        if (null == lateService) {
            lateService = new CdLateNetTools().getRetrofit().create(CdLateService.class);
        }
        final String typeCode = "ex_1009";
        date = date.replace("[^0-9]", "");
        date = date.replace("-", "");
        List<String> params = new ArrayList<>();
        params.add(date);
        params.add(trainCode);
        params.add(stationName);
        return lateService
                .lateTimeCCDetail(typeCode, gson.toJson(params), emptyJson, emptyJson, userMessage)
                .flatMap(new Function<CdLateResultTop, ObservableSource<List<CdTrainAllNodeBean>>>() {
                    @Override
                    public ObservableSource<List<CdTrainAllNodeBean>> apply(CdLateResultTop top) throws Exception {
                        if (null == top || !top.isStatus()) {
                            //TODO loge
                            return Observable.just(new ArrayList<>());
                        }
                        try {
                            CdLateDetail detail = gson.fromJson(top.getData(), CdLateDetail.class);
                            if (!detail.isStatus()) {
                                return Observable.just(new ArrayList<>());
                            }
                            return Observable.just(detail.getTrainAllNode());
                        } catch (ClassCastException e) {
                            return Observable.just(new ArrayList<>());
                        } catch (Exception e) {
                            System.err.println(e.getLocalizedMessage());
                            return Observable.just(new ArrayList<>());
                        }
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

    //候乘：code=C50101&login=["10.192.111.79","hhs","hhs"]&sql=["20180817","GIW"]&where=[]&order=[]
    //接站：code=C5054&login=["10.192.111.79","hhs","hhs"]&sql=["20180817","GIW"]&where=[]&order=[]
    @Override
    public Observable<List<ScreenLeaveDetail>> getLeaveDetail(String stationCode, String date) {
        if (null == screenService) {
            screenService = new CdScreenNetTools().getRetrofit().create(CdScreenService.class);
        }
        final String typeCode = "C50101";
        date = date.replace("[^0-9]", "");
        date = date.replace("[^0-9]", "");
        List<String> params = Arrays.asList(date, stationCode);
        return screenService.getLeaveDetail(typeCode, userMessage, gson.toJson(params), emptyJson, emptyJson);
    }

    @Override
    public Observable<List<ScreenArriveDetail>> getArriveDetail(String stationCode, String date) {
        if (null == screenService) {
            screenService = new CdScreenNetTools().getRetrofit().create(CdScreenService.class);
        }
        final String typeCode = "C5054";
        date = date.replace("[^0-9]", "");
        List<String> params = Arrays.asList(date, stationCode);
        return screenService.getArriveDetail(typeCode, userMessage, gson.toJson(params), emptyJson, emptyJson);
    }
}
