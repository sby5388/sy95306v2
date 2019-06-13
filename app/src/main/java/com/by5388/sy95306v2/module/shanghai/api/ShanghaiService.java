package com.by5388.sy95306v2.module.shanghai.api;

import com.by5388.sy95306v2.module.shanghai.bean.InfoBeanDelay;
import com.by5388.sy95306v2.module.shanghai.bean.InfoBeanP2P;
import com.by5388.sy95306v2.module.shanghai.bean.InfoBeanTrainNo;
import com.by5388.sy95306v2.module.shanghai.bean.QueryMethod;
import com.by5388.sy95306v2.module.shanghai.bean.ShanghaiTrainDelay;
import com.by5388.sy95306v2.module.shanghai.bean.ShanghaiTrainNumber;
import com.by5388.sy95306v2.module.shanghai.bean.ShanghaiTrainP2P;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * @author by5388
 * @date 2018/8/8 16:22
 */
public interface ShanghaiService {

    //站站查询
    //{info:{type:"2",trainDate:"2018-08-08",fromStation:"上海",toStation:"徐州"}
    // ,user:{mac:"",version:"",phone:"",device:"",system:"1"}}
    //车次查询
    //{info:{type:"1",trainCode:"G2",trainDate:"2018-08-08"}
    // ,user:{mac:"",version:"",phone:"",device:"",system:"1"}}
    //晚点查询
    //    {info:{stationName:"济南西",trainCode:"G105"}
    // ,user:{mac:"",version:"",phone:"",device:"",system:"1"}}


    /**
     * 车次查询
     *
     * @param info
     * @return
     */
    @POST("queryTrainInfo")
    Observable<List<ShanghaiTrainNumber>> queryTrainNumber(@Body QueryMethod<InfoBeanTrainNo> info);

    /**
     * 站站查询
     *
     * @param info
     * @return
     */
    @POST("queryTrainInfo")
    Observable<List<ShanghaiTrainP2P>> queryTrainP2P(@Body QueryMethod<InfoBeanP2P> info);

    /**
     * 车次在上海管内站点的实际到达时间
     *
     * @param info
     * @return
     */
    @POST("queryTrainDelay")
    Observable<List<ShanghaiTrainDelay>> queryTrainDelay(@Body QueryMethod<InfoBeanDelay> info);
}
