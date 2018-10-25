package com.by5388.sy95306v2;

import com.by5388.sy95306v2.bean.tz.TzResult;
import com.by5388.sy95306v2.bean.tz.yp.success.SuccessDataBean;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author by5388  on 2018/8/28.
 */
public class GsonTest<T> {
    @Test
    public void test() {
        String result = "{\"validateMessagesShowId\":\"_validatorMessage\",\"status\":true,\"httpstatus\":200,\"data\":{\"datas\":[{\"train_no\":\"6i000D312606\",\"station_train_code\":\"D3126\",\"start_station_telecode\":\"IOQ\",\"start_station_name\":\"深圳北\",\"end_station_telecode\":\"NJH\",\"end_station_name\":\"南京\",\"from_station_telecode\":\"IOQ\",\"from_station_name\":\"深圳北\",\"to_station_telecode\":\"AOH\",\"to_station_name\":\"上海虹桥\",\"start_time\":\"07:00\",\"arrive_time\":\"18:43\",\"day_difference\":\"0\",\"train_class_name\":\"动车\",\"lishi\":\"11:43\",\"canWebBuy\":\"Y\",\"lishiValue\":\"703\",\"yp_info\":\"O056800288M090900010O056803157\",\"exchange_train_flag\":\"0\",\"control_train_day\":\"20301231\",\"start_train_date\":\"20180830\",\"seat_feature\":\"O3M3W3\",\"yp_ex\":\"O0M0O0\",\"train_seat_feature\":\"3\",\"train_type_code\":\"2\",\"start_province_code\":\"16\",\"start_city_code\":\"1505\",\"end_province_code\":\"07\",\"end_city_code\":\"0705\",\"seat_types\":\"OMO\",\"location_code\":\"Q6\",\"from_station_no\":\"01\",\"to_station_no\":\"28\",\"control_day\":29,\"sale_time\":\"0900\",\"is_support_card\":\"1\",\"controlled_train_flag\":\"0\",\"controlled_train_message\":\"正常车次，不受控\",\"rw_num\":\"--\",\"srrb_num\":\"--\",\"gg_num\":\"--\",\"gr_num\":\"--\",\"qt_num\":\"--\",\"rz_num\":\"--\",\"tz_num\":\"--\",\"wz_num\":\"157\",\"yb_num\":\"--\",\"yw_num\":\"--\",\"yz_num\":\"--\",\"ze_num\":\"288\",\"zy_num\":\"10\",\"swz_num\":\"--\"},{\"train_no\":\"6i000D31080B\",\"station_train_code\":\"D3108\",\"start_station_telecode\":\"IOQ\",\"start_station_name\":\"深圳北\",\"end_station_telecode\":\"AOH\",\"end_station_name\":\"上海虹桥\",\"from_station_telecode\":\"IOQ\",\"from_station_name\":\"深圳北\",\"to_station_telecode\":\"AOH\",\"to_station_name\":\"上海虹桥\",\"start_time\":\"08:22\",\"arrive_time\":\"19:59\",\"day_difference\":\"0\",\"train_class_name\":\"动车\",\"lishi\":\"11:37\",\"canWebBuy\":\"Y\",\"lishiValue\":\"697\",\"yp_info\":\"O056800706O056803158M090900124\",\"exchange_train_flag\":\"0\",\"control_train_day\":\"20301231\",\"start_train_date\":\"20180830\",\"seat_feature\":\"O3W3M3\",\"yp_ex\":\"O0O0M0\",\"train_seat_feature\":\"3\",\"train_type_code\":\"2\",\"start_province_code\":\"16\",\"start_city_code\":\"1505\",\"end_province_code\":\"33\",\"end_city_code\":\"0712\",\"seat_types\":\"OOM\",\"location_code\":\"Q9\",\"from_station_no\":\"01\",\"to_station_no\":\"26\",\"control_day\":29,\"sale_time\":\"0900\",\"is_support_card\":\"1\",\"controlled_train_flag\":\"0\",\"controlled_train_message\":\"正常车次，不受控\",\"rw_num\":\"--\",\"srrb_num\":\"--\",\"gg_num\":\"--\",\"gr_num\":\"--\",\"qt_num\":\"--\",\"rz_num\":\"--\",\"tz_num\":\"--\",\"wz_num\":\"158\",\"yb_num\":\"--\",\"yw_num\":\"--\",\"yz_num\":\"--\",\"ze_num\":\"706\",\"zy_num\":\"124\",\"swz_num\":\"--\"},{\"train_no\":\"6i000D22840A\",\"station_train_code\":\"D2284\",\"start_station_telecode\":\"IOQ\",\"start_station_name\":\"深圳北\",\"end_station_telecode\":\"AOH\",\"end_station_name\":\"上海虹桥\",\"from_station_telecode\":\"IOQ\",\"from_station_name\":\"深圳北\",\"to_station_telecode\":\"AOH\",\"to_station_name\":\"上海虹桥\",\"start_time\":\"08:46\",\"arrive_time\":\"20:44\",\"day_difference\":\"0\",\"train_class_name\":\"动车\",\"lishi\":\"11:58\",\"canWebBuy\":\"Y\",\"lishiValue\":\"718\",\"yp_info\":\"O056800524O056803158M090900060\",\"exchange_train_flag\":\"0\",\"control_train_day\":\"20301231\",\"start_train_date\":\"20180830\",\"seat_feature\":\"O3W3M3\",\"yp_ex\":\"O0O0M0\",\"train_seat_feature\":\"3\",\"train_type_code\":\"2\",\"start_province_code\":\"16\",\"start_city_code\":\"1505\",\"end_province_code\":\"33\",\"end_city_code\":\"0712\",\"seat_types\":\"OOM\",\"location_code\":\"Q9\",\"from_station_no\":\"01\",\"to_station_no\":\"27\",\"control_day\":29,\"sale_time\":\"0900\",\"is_support_card\":\"1\",\"controlled_train_flag\":\"0\",\"controlled_train_message\":\"正常车次，不受控\",\"rw_num\":\"--\",\"srrb_num\":\"--\",\"gg_num\":\"--\",\"gr_num\":\"--\",\"qt_num\":\"--\",\"rz_num\":\"--\",\"tz_num\":\"--\",\"wz_num\":\"158\",\"yb_num\":\"--\",\"yw_num\":\"--\",\"yz_num\":\"--\",\"ze_num\":\"524\",\"zy_num\":\"60\",\"swz_num\":\"--\"},{\"train_no\":\"6i000D22820A\",\"station_train_code\":\"D2282\",\"start_station_telecode\":\"IOQ\",\"start_station_name\":\"深圳北\",\"end_station_telecode\":\"NJH\",\"end_station_name\":\"南京\",\"from_station_telecode\":\"IOQ\",\"from_station_name\":\"深圳北\",\"to_station_telecode\":\"AOH\",\"to_station_name\":\"上海虹桥\",\"start_time\":\"09:18\",\"arrive_time\":\"21:20\",\"day_difference\":\"0\",\"train_class_name\":\"动车\",\"lishi\":\"12:02\",\"canWebBuy\":\"Y\",\"lishiValue\":\"722\",\"yp_info\":\"O056800615O056803159M090900024\",\"exchange_train_flag\":\"0\",\"control_train_day\":\"20301231\",\"start_train_date\":\"20180830\",\"seat_feature\":\"O3W3M3\",\"yp_ex\":\"O0O0M0\",\"train_seat_feature\":\"3\",\"train_type_code\":\"2\",\"start_province_code\":\"16\",\"start_city_code\":\"1505\",\"end_province_code\":\"07\",\"end_city_code\":\"0705\",\"seat_types\":\"OOM\",\"location_code\":\"Q7\",\"from_station_no\":\"01\",\"to_station_no\":\"28\",\"control_day\":29,\"sale_time\":\"0900\",\"is_support_card\":\"1\",\"controlled_train_flag\":\"0\",\"controlled_train_message\":\"正常车次，不受控\",\"rw_num\":\"--\",\"srrb_num\":\"--\",\"gg_num\":\"--\",\"gr_num\":\"--\",\"qt_num\":\"--\",\"rz_num\":\"--\",\"tz_num\":\"--\",\"wz_num\":\"159\",\"yb_num\":\"--\",\"yw_num\":\"--\",\"yz_num\":\"--\",\"ze_num\":\"615\",\"zy_num\":\"24\",\"swz_num\":\"--\"},{\"train_no\":\"6i000D22860A\",\"station_train_code\":\"D2286\",\"start_station_telecode\":\"IOQ\",\"start_station_name\":\"深圳北\",\"end_station_telecode\":\"AOH\",\"end_station_name\":\"上海虹桥\",\"from_station_telecode\":\"IOQ\",\"from_station_name\":\"深圳北\",\"to_station_telecode\":\"AOH\",\"to_station_name\":\"上海虹桥\",\"start_time\":\"09:54\",\"arrive_time\":\"22:12\",\"day_difference\":\"0\",\"train_class_name\":\"动车\",\"lishi\":\"12:18\",\"canWebBuy\":\"Y\",\"lishiValue\":\"738\",\"yp_info\":\"O056800198M090900010O056803138\",\"exchange_train_flag\":\"0\",\"control_train_day\":\"20301231\",\"start_train_date\":\"20180830\",\"seat_feature\":\"O3M3W3\",\"yp_ex\":\"O0M0O0\",\"train_seat_feature\":\"3\",\"train_type_code\":\"2\",\"start_province_code\":\"16\",\"start_city_code\":\"1505\",\"end_province_code\":\"33\",\"end_city_code\":\"0712\",\"seat_types\":\"OMO\",\"location_code\":\"Q6\",\"from_station_no\":\"01\",\"to_station_no\":\"30\",\"control_day\":29,\"sale_time\":\"0900\",\"is_support_card\":\"1\",\"controlled_train_flag\":\"0\",\"controlled_train_message\":\"正常车次，不受控\",\"rw_num\":\"--\",\"srrb_num\":\"--\",\"gg_num\":\"--\",\"gr_num\":\"--\",\"qt_num\":\"--\",\"rz_num\":\"--\",\"tz_num\":\"--\",\"wz_num\":\"138\",\"yb_num\":\"--\",\"yw_num\":\"--\",\"yz_num\":\"--\",\"ze_num\":\"198\",\"zy_num\":\"10\",\"swz_num\":\"--\"},{\"train_no\":\"6i000D22880B\",\"station_train_code\":\"D2288\",\"start_station_telecode\":\"IOQ\",\"start_station_name\":\"深圳北\",\"end_station_telecode\":\"AOH\",\"end_station_name\":\"上海虹桥\",\"from_station_telecode\":\"IOQ\",\"from_station_name\":\"深圳北\",\"to_station_telecode\":\"AOH\",\"to_station_name\":\"上海虹桥\",\"start_time\":\"10:42\",\"arrive_time\":\"22:35\",\"day_difference\":\"0\",\"train_class_name\":\"动车\",\"lishi\":\"11:53\",\"canWebBuy\":\"Y\",\"lishiValue\":\"713\",\"yp_info\":\"O056800473O056803116M090900063\",\"exchange_train_flag\":\"0\",\"control_train_day\":\"20301231\",\"start_train_date\":\"20180830\",\"seat_feature\":\"O3W3M3\",\"yp_ex\":\"O0O0M0\",\"train_seat_feature\":\"3\",\"train_type_code\":\"2\",\"start_province_code\":\"16\",\"start_city_code\":\"1505\",\"end_province_code\":\"33\",\"end_city_code\":\"0712\",\"seat_types\":\"OOM\",\"location_code\":\"Q9\",\"from_station_no\":\"01\",\"to_station_no\":\"29\",\"control_day\":29,\"sale_time\":\"0900\",\"is_support_card\":\"1\",\"controlled_train_flag\":\"0\",\"controlled_train_message\":\"正常车次，不受控\",\"rw_num\":\"--\",\"srrb_num\":\"--\",\"gg_num\":\"--\",\"gr_num\":\"--\",\"qt_num\":\"--\",\"rz_num\":\"--\",\"tz_num\":\"--\",\"wz_num\":\"116\",\"yb_num\":\"--\",\"yw_num\":\"--\",\"yz_num\":\"--\",\"ze_num\":\"473\",\"zy_num\":\"63\",\"swz_num\":\"--\"},{\"train_no\":\"650000T2120H\",\"station_train_code\":\"T212\",\"start_station_telecode\":\"SZQ\",\"start_station_name\":\"深圳\",\"end_station_telecode\":\"SNH\",\"end_station_name\":\"上海南\",\"from_station_telecode\":\"SZQ\",\"from_station_name\":\"深圳\",\"to_station_telecode\":\"SNH\",\"to_station_name\":\"上海南\",\"start_time\":\"13:08\",\"arrive_time\":\"07:58\",\"day_difference\":\"1\",\"train_class_name\":\"\",\"lishi\":\"18:50\",\"canWebBuy\":\"Y\",\"lishiValue\":\"1130\",\"yp_info\":\"1023403236406130000210234001643039100159\",\"exchange_train_flag\":\"0\",\"control_train_day\":\"20301231\",\"start_train_date\":\"20180830\",\"seat_feature\":\"W3431333\",\"yp_ex\":\"10401030\",\"train_seat_feature\":\"3\",\"train_type_code\":\"2\",\"start_province_code\":\"16\",\"start_city_code\":\"1505\",\"end_province_code\":\"33\",\"end_city_code\":\"0712\",\"seat_types\":\"1413\",\"location_code\":\"Q7\",\"from_station_no\":\"01\",\"to_station_no\":\"16\",\"control_day\":29,\"sale_time\":\"0930\",\"is_support_card\":\"0\",\"controlled_train_flag\":\"0\",\"controlled_train_message\":\"正常车次，不受控\",\"rw_num\":\"2\",\"srrb_num\":\"--\",\"gg_num\":\"--\",\"gr_num\":\"--\",\"qt_num\":\"--\",\"rz_num\":\"--\",\"tz_num\":\"--\",\"wz_num\":\"236\",\"yb_num\":\"--\",\"yw_num\":\"159\",\"yz_num\":\"164\",\"ze_num\":\"--\",\"zy_num\":\"--\",\"swz_num\":\"--\"},{\"train_no\":\"650000T1020C\",\"station_train_code\":\"T102\",\"start_station_telecode\":\"SZQ\",\"start_station_name\":\"深圳\",\"end_station_telecode\":\"SNH\",\"end_station_name\":\"上海南\",\"from_station_telecode\":\"SZQ\",\"from_station_name\":\"深圳\",\"to_station_telecode\":\"SNH\",\"to_station_name\":\"上海南\",\"start_time\":\"16:02\",\"arrive_time\":\"11:06\",\"day_difference\":\"1\",\"train_class_name\":\"\",\"lishi\":\"19:04\",\"canWebBuy\":\"Y\",\"lishiValue\":\"1144\",\"yp_info\":\"1023403290406130000410234000943039100000\",\"exchange_train_flag\":\"0\",\"control_train_day\":\"20301231\",\"start_train_date\":\"20180830\",\"seat_feature\":\"W3431333\",\"yp_ex\":\"10401030\",\"train_seat_feature\":\"3\",\"train_type_code\":\"2\",\"start_province_code\":\"16\",\"start_city_code\":\"1505\",\"end_province_code\":\"33\",\"end_city_code\":\"0712\",\"seat_types\":\"1413\",\"location_code\":\"Q6\",\"from_station_no\":\"01\",\"to_station_no\":\"17\",\"control_day\":29,\"sale_time\":\"0930\",\"is_support_card\":\"0\",\"controlled_train_flag\":\"0\",\"controlled_train_message\":\"正常车次，不受控\",\"rw_num\":\"4\",\"srrb_num\":\"--\",\"gg_num\":\"--\",\"gr_num\":\"--\",\"qt_num\":\"--\",\"rz_num\":\"--\",\"tz_num\":\"--\",\"wz_num\":\"290\",\"yb_num\":\"--\",\"yw_num\":\"无\",\"yz_num\":\"94\",\"ze_num\":\"--\",\"zy_num\":\"--\",\"swz_num\":\"--\"}],\"flag\":true,\"isThrough\":\"Y\"},\"messages\":[],\"validateMessages\":{}}";
        Gson gson = new Gson();
        TzResult<SuccessDataBean> tzResult = gson.fromJson(result, TzResult.class);
        System.out.println(tzResult.isStatus());
        System.out.println(tzResult.getData());

        SuccessDataBean dataBean = tzResult.getData();
        System.out.println(dataBean.isFlag());
    }




}