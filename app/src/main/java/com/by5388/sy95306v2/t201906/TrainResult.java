package com.by5388.sy95306v2.t201906;

import android.text.TextUtils;

import com.by5388.sy95306v2.bean.IRemainingTicket;

import java.util.List;

/**
 * @author Administrator  on 2019/9/12.
 */
public class TrainResult {

    /**
     * validateMessagesShowId : _validatorMessage
     * status : true
     * httpstatus : 200
     * data : [{"queryLeftNewDTO":{"train_no":"6j000G634302","station_train_code":"G6343","start_station_telecode":"CBQ","start_station_name":"潮汕","end_station_telecode":"IZQ","end_station_name":"广州南","from_station_telecode":"CBQ","from_station_name":"潮汕","to_station_telecode":"IZQ","to_station_name":"广州南","start_time":"06:08","arrive_time":"08:45","day_difference":"0","train_class_name":"","lishi":"02:37","is_support_card":"1","control_train_day":"20301231","start_train_date":"20190915","seat_feature":"O3M393","yp_ex":"O0M090","train_seat_feature":"3","controlled_train_flag":"0","controlled_train_message":"正常车次，不受控","from_station_no":"01","to_station_no":"05","seat_types":"OM9","gg_num":"-1","gr_num":"-1","rw_num":"-1","rz_num":"-1","tz_num":"-1","wz_num":"-1","yb_num":"-1","yw_num":"-1","yz_num":"-1","ze_num":"有","zy_num":"有","swz_num":"有","srrb_num":"-1","rw_price":"--","srrb_price":"--","yw_price":"--","gr_price":"--","yz_price":"--","rz_price":"--","zy_price":"02705","ze_price":"01815","tz_price":"--","gg_price":"--","yb_price":"--","bxyw_num":"-1","bxyw_price":"--","swz_price":"05195","hbyz_num":"-1","hbyz_price":"--","hbyw_num":"-1","hbyw_price":"--","bxrz_num":"-1","bxrz_price":"--","tdrz_num":"-1","tdrz_price":"--","errb_num":"-1","errb_price":"--","yrrb_num":"-1","yrrb_price":"--","ydsr_num":"-1","ydsr_price":"--","edsr_num":"-1","edsr_price":"--","hbrz_num":"-1","hbrz_price":"--","hbrw_num":"-1","hbrw_price":"--","ydrz_num":"-1","ydrz_price":"--","edrz_num":"-1","edrz_price":"--","wz_price":"--"},"buttonTextInfo":""},{"queryLeftNewDTO":{"train_no":"6j000G634705","station_train_code":"G6347","start_station_telecode":"CBQ","start_station_name":"潮汕","end_station_telecode":"IZQ","end_station_name":"广州南","from_station_telecode":"CBQ","from_station_name":"潮汕","to_station_telecode":"IZQ","to_station_name":"广州南","start_time":"06:55","arrive_time":"09:50","day_difference":"0","train_class_name":"","lishi":"02:55","is_support_card":"1","control_train_day":"20301231","start_train_date":"20190915","seat_feature":"O3M393","yp_ex":"O0M090","train_seat_feature":"3","controlled_train_flag":"0","controlled_train_message":"正常车次，不受控","from_station_no":"01","to_station_no":"09","seat_types":"OM9","gg_num":"-1","gr_num":"-1","rw_num":"-1","rz_num":"-1","tz_num":"-1","wz_num":"-1","yb_num":"-1","yw_num":"-1","yz_num":"-1","ze_num":"有","zy_num":"有","swz_num":"有","srrb_num":"-1","rw_price":"--","srrb_price":"--","yw_price":"--","gr_price":"--","yz_price":"--","rz_price":"--","zy_price":"02705","ze_price":"01815","tz_price":"--","gg_price":"--","yb_price":"--","bxyw_num":"-1","bxyw_price":"--","swz_price":"05195","hbyz_num":"-1","hbyz_price":"--","hbyw_num":"-1","hbyw_price":"--","bxrz_num":"-1","bxrz_price":"--","tdrz_num":"-1","tdrz_price":"--","errb_num":"-1","errb_price":"--","yrrb_num":"-1","yrrb_price":"--","ydsr_num":"-1","ydsr_price":"--","edsr_num":"-1","edsr_price":"--","hbrz_num":"-1","hbrz_price":"--","hbrw_num":"-1","hbrw_price":"--","ydrz_num":"-1","ydrz_price":"--","edrz_num":"-1","edrz_price":"--","wz_price":"--"},"buttonTextInfo":""},{"queryLeftNewDTO":{"train_no":"67000D753004","station_train_code":"D7531","start_station_telecode":"OTQ","start_station_name":"汕头","end_station_telecode":"GGQ","end_station_name":"广州东","from_station_telecode":"CBQ","from_station_name":"潮汕","to_station_telecode":"GGQ","to_station_name":"广州东","start_time":"07:56","arrive_time":"10:59","day_difference":"0","train_class_name":"动车","lishi":"03:03","is_support_card":"1","control_train_day":"20301231","start_train_date":"20190915","seat_feature":"O3M3W3","yp_ex":"O0M0O0","train_seat_feature":"3","controlled_train_flag":"0","controlled_train_message":"正常车次，不受控","from_station_no":"02","to_station_no":"06","seat_types":"OMO","gg_num":"-1","gr_num":"-1","rw_num":"-1","rz_num":"-1","tz_num":"-1","wz_num":"有","yb_num":"-1","yw_num":"-1","yz_num":"-1","ze_num":"有","zy_num":"有","swz_num":"-1","srrb_num":"-1","rw_price":"--","srrb_price":"--","yw_price":"--","gr_price":"--","wz_seat_type_code":"O","yz_price":"--","rz_price":"--","zy_price":"02495","ze_price":"01795","tz_price":"--","gg_price":"--","yb_price":"--","bxyw_num":"-1","bxyw_price":"--","swz_price":"--","hbyz_num":"-1","hbyz_price":"--","hbyw_num":"-1","hbyw_price":"--","bxrz_num":"-1","bxrz_price":"--","tdrz_num":"-1","tdrz_price":"--","errb_num":"-1","errb_price":"--","yrrb_num":"-1","yrrb_price":"--","ydsr_num":"-1","ydsr_price":"--","edsr_num":"-1","edsr_price":"--","hbrz_num":"-1","hbrz_price":"--","hbrw_num":"-1","hbrw_price":"--","ydrz_num":"-1","ydrz_price":"--","edrz_num":"-1","edrz_price":"--","wz_price":"01795"},"buttonTextInfo":""},{"queryLeftNewDTO":{"train_no":"6j000D754300","station_train_code":"D7543","start_station_telecode":"CBQ","start_station_name":"潮汕","end_station_telecode":"GGQ","end_station_name":"广州东","from_station_telecode":"CBQ","from_station_name":"潮汕","to_station_telecode":"GGQ","to_station_name":"广州东","start_time":"08:31","arrive_time":"11:46","day_difference":"0","train_class_name":"动车","lishi":"03:15","is_support_card":"1","control_train_day":"20301231","start_train_date":"20190915","seat_feature":"O3M3W3","yp_ex":"O0M0O0","train_seat_feature":"3","controlled_train_flag":"0","controlled_train_message":"正常车次，不受控","from_station_no":"01","to_station_no":"07","seat_types":"OMO","gg_num":"-1","gr_num":"-1","rw_num":"-1","rz_num":"-1","tz_num":"-1","wz_num":"有","yb_num":"-1","yw_num":"-1","yz_num":"-1","ze_num":"有","zy_num":"有","swz_num":"-1","srrb_num":"-1","rw_price":"--","srrb_price":"--","yw_price":"--","gr_price":"--","wz_seat_type_code":"O","yz_price":"--","rz_price":"--","zy_price":"02495","ze_price":"01795","tz_price":"--","gg_price":"--","yb_price":"--","bxyw_num":"-1","bxyw_price":"--","swz_price":"--","hbyz_num":"-1","hbyz_price":"--","hbyw_num":"-1","hbyw_price":"--","bxrz_num":"-1","bxrz_price":"--","tdrz_num":"-1","tdrz_price":"--","errb_num":"-1","errb_price":"--","yrrb_num":"-1","yrrb_price":"--","ydsr_num":"-1","ydsr_price":"--","edsr_num":"-1","edsr_price":"--","hbrz_num":"-1","hbrz_price":"--","hbrw_num":"-1","hbrw_price":"--","ydrz_num":"-1","ydrz_price":"--","edrz_num":"-1","edrz_price":"--","wz_price":"01795"},"buttonTextInfo":""},{"queryLeftNewDTO":{"train_no":"58000D238305","station_train_code":"D2383","start_station_telecode":"XMS","start_station_name":"厦门","end_station_telecode":"GGQ","end_station_name":"广州东","from_station_telecode":"CBQ","from_station_name":"潮汕","to_station_telecode":"GGQ","to_station_name":"广州东","start_time":"08:59","arrive_time":"12:05","day_difference":"0","train_class_name":"动车","lishi":"03:06","is_support_card":"1","control_train_day":"20301231","start_train_date":"20190915","seat_feature":"O3W3M3","yp_ex":"O0O0M0","train_seat_feature":"3","controlled_train_flag":"0","controlled_train_message":"正常车次，不受控","from_station_no":"04","to_station_no":"09","seat_types":"OOM","gg_num":"-1","gr_num":"-1","rw_num":"-1","rz_num":"-1","tz_num":"-1","wz_num":"有","yb_num":"-1","yw_num":"-1","yz_num":"-1","ze_num":"有","zy_num":"有","swz_num":"-1","srrb_num":"-1","rw_price":"--","srrb_price":"--","yw_price":"--","gr_price":"--","wz_seat_type_code":"O","yz_price":"--","rz_price":"--","zy_price":"02495","ze_price":"01795","tz_price":"--","gg_price":"--","yb_price":"--","bxyw_num":"-1","bxyw_price":"--","swz_price":"--","hbyz_num":"-1","hbyz_price":"--","hbyw_num":"-1","hbyw_price":"--","bxrz_num":"-1","bxrz_price":"--","tdrz_num":"-1","tdrz_price":"--","errb_num":"-1","errb_price":"--","yrrb_num":"-1","yrrb_price":"--","ydsr_num":"-1","ydsr_price":"--","edsr_num":"-1","edsr_price":"--","hbrz_num":"-1","hbrz_price":"--","hbrw_num":"-1","hbrw_price":"--","ydrz_num":"-1","ydrz_price":"--","edrz_num":"-1","edrz_price":"--","wz_price":"01795"},"buttonTextInfo":""},{"queryLeftNewDTO":{"train_no":"6j000G631909","station_train_code":"G6319","start_station_telecode":"CBQ","start_station_name":"潮汕","end_station_telecode":"IZQ","end_station_name":"广州南","from_station_telecode":"CBQ","from_station_name":"潮汕","to_station_telecode":"IZQ","to_station_name":"广州南","start_time":"10:22","arrive_time":"13:10","day_difference":"0","train_class_name":"","lishi":"02:48","is_support_card":"1","control_train_day":"20301231","start_train_date":"20190915","seat_feature":"O3M393","yp_ex":"O0M090","train_seat_feature":"3","controlled_train_flag":"0","controlled_train_message":"正常车次，不受控","from_station_no":"01","to_station_no":"06","seat_types":"OM9","gg_num":"-1","gr_num":"-1","rw_num":"-1","rz_num":"-1","tz_num":"-1","wz_num":"-1","yb_num":"-1","yw_num":"-1","yz_num":"-1","ze_num":"有","zy_num":"有","swz_num":"有","srrb_num":"-1","rw_price":"--","srrb_price":"--","yw_price":"--","gr_price":"--","yz_price":"--","rz_price":"--","zy_price":"02705","ze_price":"01815","tz_price":"--","gg_price":"--","yb_price":"--","bxyw_num":"-1","bxyw_price":"--","swz_price":"05195","hbyz_num":"-1","hbyz_price":"--","hbyw_num":"-1","hbyw_price":"--","bxrz_num":"-1","bxrz_price":"--","tdrz_num":"-1","tdrz_price":"--","errb_num":"-1","errb_price":"--","yrrb_num":"-1","yrrb_price":"--","ydsr_num":"-1","ydsr_price":"--","edsr_num":"-1","edsr_price":"--","hbrz_num":"-1","hbrz_price":"--","hbrw_num":"-1","hbrw_price":"--","ydrz_num":"-1","ydrz_price":"--","edrz_num":"-1","edrz_price":"--","wz_price":"--"},"buttonTextInfo":""},{"queryLeftNewDTO":{"train_no":"67000D751001","station_train_code":"D7511","start_station_telecode":"OTQ","start_station_name":"汕头","end_station_telecode":"GGQ","end_station_name":"广州东","from_station_telecode":"CBQ","from_station_name":"潮汕","to_station_telecode":"GGQ","to_station_name":"广州东","start_time":"10:55","arrive_time":"14:09","day_difference":"0","train_class_name":"动车","lishi":"03:14","is_support_card":"1","control_train_day":"20301231","start_train_date":"20190915","seat_feature":"O3W3M3","yp_ex":"O0O0M0","train_seat_feature":"3","controlled_train_flag":"0","controlled_train_message":"正常车次，不受控","from_station_no":"02","to_station_no":"07","seat_types":"OOM","gg_num":"-1","gr_num":"-1","rw_num":"-1","rz_num":"-1","tz_num":"-1","wz_num":"有","yb_num":"-1","yw_num":"-1","yz_num":"-1","ze_num":"有","zy_num":"有","swz_num":"-1","srrb_num":"-1","rw_price":"--","srrb_price":"--","yw_price":"--","gr_price":"--","wz_seat_type_code":"O","yz_price":"--","rz_price":"--","zy_price":"02495","ze_price":"01795","tz_price":"--","gg_price":"--","yb_price":"--","bxyw_num":"-1","bxyw_price":"--","swz_price":"--","hbyz_num":"-1","hbyz_price":"--","hbyw_num":"-1","hbyw_price":"--","bxrz_num":"-1","bxrz_price":"--","tdrz_num":"-1","tdrz_price":"--","errb_num":"-1","errb_price":"--","yrrb_num":"-1","yrrb_price":"--","ydsr_num":"-1","ydsr_price":"--","edsr_num":"-1","edsr_price":"--","hbrz_num":"-1","hbrz_price":"--","hbrw_num":"-1","hbrw_price":"--","ydrz_num":"-1","ydrz_price":"--","edrz_num":"-1","edrz_price":"--","wz_price":"01795"},"buttonTextInfo":""},{"queryLeftNewDTO":{"train_no":"6j000G630307","station_train_code":"G6303","start_station_telecode":"CBQ","start_station_name":"潮汕","end_station_telecode":"IZQ","end_station_name":"广州南","from_station_telecode":"CBQ","from_station_name":"潮汕","to_station_telecode":"IZQ","to_station_name":"广州南","start_time":"11:05","arrive_time":"13:59","day_difference":"0","train_class_name":"","lishi":"02:54","is_support_card":"1","control_train_day":"20301231","start_train_date":"20190915","seat_feature":"O3M393","yp_ex":"O0M090","train_seat_feature":"3","controlled_train_flag":"0","controlled_train_message":"正常车次，不受控","from_station_no":"01","to_station_no":"07","seat_types":"OM9","gg_num":"-1","gr_num":"-1","rw_num":"-1","rz_num":"-1","tz_num":"-1","wz_num":"-1","yb_num":"-1","yw_num":"-1","yz_num":"-1","ze_num":"有","zy_num":"有","swz_num":"有","srrb_num":"-1","rw_price":"--","srrb_price":"--","yw_price":"--","gr_price":"--","yz_price":"--","rz_price":"--","zy_price":"02705","ze_price":"01815","tz_price":"--","gg_price":"--","yb_price":"--","bxyw_num":"-1","bxyw_price":"--","swz_price":"05195","hbyz_num":"-1","hbyz_price":"--","hbyw_num":"-1","hbyw_price":"--","bxrz_num":"-1","bxrz_price":"--","tdrz_num":"-1","tdrz_price":"--","errb_num":"-1","errb_price":"--","yrrb_num":"-1","yrrb_price":"--","ydsr_num":"-1","ydsr_price":"--","edsr_num":"-1","edsr_price":"--","hbrz_num":"-1","hbrz_price":"--","hbrw_num":"-1","hbrw_price":"--","ydrz_num":"-1","ydrz_price":"--","edrz_num":"-1","edrz_price":"--","wz_price":"--"},"buttonTextInfo":""},{"queryLeftNewDTO":{"train_no":"58000G160903","station_train_code":"G1609","start_station_telecode":"FZS","start_station_name":"福州","end_station_telecode":"IZQ","end_station_name":"广州南","from_station_telecode":"CBQ","from_station_name":"潮汕","to_station_telecode":"IZQ","to_station_name":"广州南","start_time":"11:27","arrive_time":"14:18","day_difference":"0","train_class_name":"","lishi":"02:51","is_support_card":"1","control_train_day":"20301231","start_train_date":"20190915","seat_feature":"O3M393","yp_ex":"O0M090","train_seat_feature":"3","controlled_train_flag":"0","controlled_train_message":"正常车次，不受控","from_station_no":"06","to_station_no":"12","seat_types":"OM9","gg_num":"-1","gr_num":"-1","rw_num":"-1","rz_num":"-1","tz_num":"-1","wz_num":"-1","yb_num":"-1","yw_num":"-1","yz_num":"-1","ze_num":"有","zy_num":"有","swz_num":"有","srrb_num":"-1","rw_price":"--","srrb_price":"--","yw_price":"--","gr_price":"--","yz_price":"--","rz_price":"--","zy_price":"02705","ze_price":"01815","tz_price":"--","gg_price":"--","yb_price":"--","bxyw_num":"-1","bxyw_price":"--","swz_price":"05195","hbyz_num":"-1","hbyz_price":"--","hbyw_num":"-1","hbyw_price":"--","bxrz_num":"-1","bxrz_price":"--","tdrz_num":"-1","tdrz_price":"--","errb_num":"-1","errb_price":"--","yrrb_num":"-1","yrrb_price":"--","ydsr_num":"-1","ydsr_price":"--","edsr_num":"-1","edsr_price":"--","hbrz_num":"-1","hbrz_price":"--","hbrw_num":"-1","hbrw_price":"--","ydrz_num":"-1","ydrz_price":"--","edrz_num":"-1","edrz_price":"--","wz_price":"--"},"buttonTextInfo":""},{"queryLeftNewDTO":{"train_no":"67000D751800","station_train_code":"D7519","start_station_telecode":"OTQ","start_station_name":"汕头","end_station_telecode":"GGQ","end_station_name":"广州东","from_station_telecode":"CBQ","from_station_name":"潮汕","to_station_telecode":"GGQ","to_station_name":"广州东","start_time":"12:02","arrive_time":"15:09","day_difference":"0","train_class_name":"动车","lishi":"03:07","is_support_card":"1","control_train_day":"20301231","start_train_date":"20190915","seat_feature":"O3W3M3","yp_ex":"O0O0M0","train_seat_feature":"3","controlled_train_flag":"0","controlled_train_message":"正常车次，不受控","from_station_no":"02","to_station_no":"07","seat_types":"OOM","gg_num":"-1","gr_num":"-1","rw_num":"-1","rz_num":"-1","tz_num":"-1","wz_num":"有","yb_num":"-1","yw_num":"-1","yz_num":"-1","ze_num":"有","zy_num":"有","swz_num":"-1","srrb_num":"-1","rw_price":"--","srrb_price":"--","yw_price":"--","gr_price":"--","wz_seat_type_code":"O","yz_price":"--","rz_price":"--","zy_price":"02495","ze_price":"01795","tz_price":"--","gg_price":"--","yb_price":"--","bxyw_num":"-1","bxyw_price":"--","swz_price":"--","hbyz_num":"-1","hbyz_price":"--","hbyw_num":"-1","hbyw_price":"--","bxrz_num":"-1","bxrz_price":"--","tdrz_num":"-1","tdrz_price":"--","errb_num":"-1","errb_price":"--","yrrb_num":"-1","yrrb_price":"--","ydsr_num":"-1","ydsr_price":"--","edsr_num":"-1","edsr_price":"--","hbrz_num":"-1","hbrz_price":"--","hbrw_num":"-1","hbrw_price":"--","ydrz_num":"-1","ydrz_price":"--","edrz_num":"-1","edrz_price":"--","wz_price":"01795"},"buttonTextInfo":""},{"queryLeftNewDTO":{"train_no":"6j000G630709","station_train_code":"G6307","start_station_telecode":"CBQ","start_station_name":"潮汕","end_station_telecode":"IZQ","end_station_name":"广州南","from_station_telecode":"CBQ","from_station_name":"潮汕","to_station_telecode":"IZQ","to_station_name":"广州南","start_time":"12:20","arrive_time":"15:10","day_difference":"0","train_class_name":"","lishi":"02:50","is_support_card":"1","control_train_day":"20301231","start_train_date":"20190915","seat_feature":"O3M393","yp_ex":"O0M090","train_seat_feature":"3","controlled_train_flag":"0","controlled_train_message":"正常车次，不受控","from_station_no":"01","to_station_no":"07","seat_types":"OM9","gg_num":"-1","gr_num":"-1","rw_num":"-1","rz_num":"-1","tz_num":"-1","wz_num":"-1","yb_num":"-1","yw_num":"-1","yz_num":"-1","ze_num":"有","zy_num":"有","swz_num":"有","srrb_num":"-1","rw_price":"--","srrb_price":"--","yw_price":"--","gr_price":"--","yz_price":"--","rz_price":"--","zy_price":"02705","ze_price":"01815","tz_price":"--","gg_price":"--","yb_price":"--","bxyw_num":"-1","bxyw_price":"--","swz_price":"05195","hbyz_num":"-1","hbyz_price":"--","hbyw_num":"-1","hbyw_price":"--","bxrz_num":"-1","bxrz_price":"--","tdrz_num":"-1","tdrz_price":"--","errb_num":"-1","errb_price":"--","yrrb_num":"-1","yrrb_price":"--","ydsr_num":"-1","ydsr_price":"--","edsr_num":"-1","edsr_price":"--","hbrz_num":"-1","hbrz_price":"--","hbrw_num":"-1","hbrw_price":"--","ydrz_num":"-1","ydrz_price":"--","edrz_num":"-1","edrz_price":"--","wz_price":"--"},"buttonTextInfo":""},{"queryLeftNewDTO":{"train_no":"6j000D750308","station_train_code":"D7503","start_station_telecode":"CBQ","start_station_name":"潮汕","end_station_telecode":"GGQ","end_station_name":"广州东","from_station_telecode":"CBQ","from_station_name":"潮汕","to_station_telecode":"GGQ","to_station_name":"广州东","start_time":"13:04","arrive_time":"16:19","day_difference":"0","train_class_name":"动车","lishi":"03:15","is_support_card":"1","control_train_day":"20301231","start_train_date":"20190915","seat_feature":"O3M3W3","yp_ex":"O0M0O0","train_seat_feature":"3","controlled_train_flag":"0","controlled_train_message":"正常车次，不受控","from_station_no":"01","to_station_no":"07","seat_types":"OMO","gg_num":"-1","gr_num":"-1","rw_num":"-1","rz_num":"-1","tz_num":"-1","wz_num":"有","yb_num":"-1","yw_num":"-1","yz_num":"-1","ze_num":"有","zy_num":"有","swz_num":"-1","srrb_num":"-1","rw_price":"--","srrb_price":"--","yw_price":"--","gr_price":"--","wz_seat_type_code":"O","yz_price":"--","rz_price":"--","zy_price":"02495","ze_price":"01795","tz_price":"--","gg_price":"--","yb_price":"--","bxyw_num":"-1","bxyw_price":"--","swz_price":"--","hbyz_num":"-1","hbyz_price":"--","hbyw_num":"-1","hbyw_price":"--","bxrz_num":"-1","bxrz_price":"--","tdrz_num":"-1","tdrz_price":"--","errb_num":"-1","errb_price":"--","yrrb_num":"-1","yrrb_price":"--","ydsr_num":"-1","ydsr_price":"--","edsr_num":"-1","edsr_price":"--","hbrz_num":"-1","hbrz_price":"--","hbrw_num":"-1","hbrw_price":"--","ydrz_num":"-1","ydrz_price":"--","edrz_num":"-1","edrz_price":"--","wz_price":"01795"},"buttonTextInfo":""},{"queryLeftNewDTO":{"train_no":"6j000G63270A","station_train_code":"G6327","start_station_telecode":"CBQ","start_station_name":"潮汕","end_station_telecode":"IZQ","end_station_name":"广州南","from_station_telecode":"CBQ","from_station_name":"潮汕","to_station_telecode":"IZQ","to_station_name":"广州南","start_time":"13:41","arrive_time":"16:28","day_difference":"0","train_class_name":"","lishi":"02:47","is_support_card":"1","control_train_day":"20301231","start_train_date":"20190915","seat_feature":"O3M393","yp_ex":"O0M090","train_seat_feature":"3","controlled_train_flag":"0","controlled_train_message":"正常车次，不受控","from_station_no":"01","to_station_no":"06","seat_types":"OM9","gg_num":"-1","gr_num":"-1","rw_num":"-1","rz_num":"-1","tz_num":"-1","wz_num":"-1","yb_num":"-1","yw_num":"-1","yz_num":"-1","ze_num":"有","zy_num":"有","swz_num":"有","srrb_num":"-1","rw_price":"--","srrb_price":"--","yw_price":"--","gr_price":"--","yz_price":"--","rz_price":"--","zy_price":"02705","ze_price":"01815","tz_price":"--","gg_price":"--","yb_price":"--","bxyw_num":"-1","bxyw_price":"--","swz_price":"05195","hbyz_num":"-1","hbyz_price":"--","hbyw_num":"-1","hbyw_price":"--","bxrz_num":"-1","bxrz_price":"--","tdrz_num":"-1","tdrz_price":"--","errb_num":"-1","errb_price":"--","yrrb_num":"-1","yrrb_price":"--","ydsr_num":"-1","ydsr_price":"--","edsr_num":"-1","edsr_price":"--","hbrz_num":"-1","hbrz_price":"--","hbrw_num":"-1","hbrw_price":"--","ydrz_num":"-1","ydrz_price":"--","edrz_num":"-1","edrz_price":"--","wz_price":"--"},"buttonTextInfo":""},{"queryLeftNewDTO":{"train_no":"6j000D751520","station_train_code":"D7515","start_station_telecode":"RVQ","start_station_name":"饶平","end_station_telecode":"GGQ","end_station_name":"广州东","from_station_telecode":"RVQ","from_station_name":"饶平","to_station_telecode":"GGQ","to_station_name":"广州东","start_time":"13:59","arrive_time":"17:28","day_difference":"0","train_class_name":"动车","lishi":"03:29","is_support_card":"1","control_train_day":"20301231","start_train_date":"20190915","seat_feature":"O3W3M3","yp_ex":"O0O0M0","train_seat_feature":"3","controlled_train_flag":"0","controlled_train_message":"正常车次，不受控","from_station_no":"01","to_station_no":"07","seat_types":"OOM","gg_num":"-1","gr_num":"-1","rw_num":"-1","rz_num":"-1","tz_num":"-1","wz_num":"有","yb_num":"-1","yw_num":"-1","yz_num":"-1","ze_num":"有","zy_num":"有","swz_num":"-1","srrb_num":"-1","rw_price":"--","srrb_price":"--","yw_price":"--","gr_price":"--","wz_seat_type_code":"O","yz_price":"--","rz_price":"--","zy_price":"02735","ze_price":"01935","tz_price":"--","gg_price":"--","yb_price":"--","bxyw_num":"-1","bxyw_price":"--","swz_price":"--","hbyz_num":"-1","hbyz_price":"--","hbyw_num":"-1","hbyw_price":"--","bxrz_num":"-1","bxrz_price":"--","tdrz_num":"-1","tdrz_price":"--","errb_num":"-1","errb_price":"--","yrrb_num":"-1","yrrb_price":"--","ydsr_num":"-1","ydsr_price":"--","edsr_num":"-1","edsr_price":"--","hbrz_num":"-1","hbrz_price":"--","hbrw_num":"-1","hbrw_price":"--","ydrz_num":"-1","ydrz_price":"--","edrz_num":"-1","edrz_price":"--","wz_price":"01935"},"buttonTextInfo":""},{"queryLeftNewDTO":{"train_no":"67000D753801","station_train_code":"D7539","start_station_telecode":"OTQ","start_station_name":"汕头","end_station_telecode":"GGQ","end_station_name":"广州东","from_station_telecode":"CBQ","from_station_name":"潮汕","to_station_telecode":"GGQ","to_station_name":"广州东","start_time":"14:58","arrive_time":"18:21","day_difference":"0","train_class_name":"动车","lishi":"03:23","is_support_card":"1","control_train_day":"20301231","start_train_date":"20190915","seat_feature":"O3W3M3","yp_ex":"O0O0M0","train_seat_feature":"3","controlled_train_flag":"0","controlled_train_message":"正常车次，不受控","from_station_no":"02","to_station_no":"09","seat_types":"OOM","gg_num":"-1","gr_num":"-1","rw_num":"-1","rz_num":"-1","tz_num":"-1","wz_num":"有","yb_num":"-1","yw_num":"-1","yz_num":"-1","ze_num":"有","zy_num":"有","swz_num":"-1","srrb_num":"-1","rw_price":"--","srrb_price":"--","yw_price":"--","gr_price":"--","wz_seat_type_code":"O","yz_price":"--","rz_price":"--","zy_price":"02495","ze_price":"01795","tz_price":"--","gg_price":"--","yb_price":"--","bxyw_num":"-1","bxyw_price":"--","swz_price":"--","hbyz_num":"-1","hbyz_price":"--","hbyw_num":"-1","hbyw_price":"--","bxrz_num":"-1","bxrz_price":"--","tdrz_num":"-1","tdrz_price":"--","errb_num":"-1","errb_price":"--","yrrb_num":"-1","yrrb_price":"--","ydsr_num":"-1","ydsr_price":"--","edsr_num":"-1","edsr_price":"--","hbrz_num":"-1","hbrz_price":"--","hbrw_num":"-1","hbrw_price":"--","ydrz_num":"-1","ydrz_price":"--","edrz_num":"-1","edrz_price":"--","wz_price":"01795"},"buttonTextInfo":""},{"queryLeftNewDTO":{"train_no":"6j000G633909","station_train_code":"G6339","start_station_telecode":"CBQ","start_station_name":"潮汕","end_station_telecode":"ZHQ","end_station_name":"珠海","from_station_telecode":"CBQ","from_station_name":"潮汕","to_station_telecode":"IZQ","to_station_name":"广州南","start_time":"16:16","arrive_time":"19:12","day_difference":"0","train_class_name":"","lishi":"02:56","is_support_card":"1","control_train_day":"20301231","start_train_date":"20190915","seat_feature":"O3M393","yp_ex":"O0M090","train_seat_feature":"3","controlled_train_flag":"0","controlled_train_message":"正常车次，不受控","from_station_no":"01","to_station_no":"07","seat_types":"OM9","gg_num":"-1","gr_num":"-1","rw_num":"-1","rz_num":"-1","tz_num":"-1","wz_num":"-1","yb_num":"-1","yw_num":"-1","yz_num":"-1","ze_num":"有","zy_num":"有","swz_num":"有","srrb_num":"-1","rw_price":"--","srrb_price":"--","yw_price":"--","gr_price":"--","yz_price":"--","rz_price":"--","zy_price":"02705","ze_price":"01815","tz_price":"--","gg_price":"--","yb_price":"--","bxyw_num":"-1","bxyw_price":"--","swz_price":"05195","hbyz_num":"-1","hbyz_price":"--","hbyw_num":"-1","hbyw_price":"--","bxrz_num":"-1","bxrz_price":"--","tdrz_num":"-1","tdrz_price":"--","errb_num":"-1","errb_price":"--","yrrb_num":"-1","yrrb_price":"--","ydsr_num":"-1","ydsr_price":"--","edsr_num":"-1","edsr_price":"--","hbrz_num":"-1","hbrz_price":"--","hbrw_num":"-1","hbrw_price":"--","ydrz_num":"-1","ydrz_price":"--","edrz_num":"-1","edrz_price":"--","wz_price":"--"},"buttonTextInfo":""},{"queryLeftNewDTO":{"train_no":"6j000G63230B","station_train_code":"G6323","start_station_telecode":"CBQ","start_station_name":"潮汕","end_station_telecode":"IZQ","end_station_name":"广州南","from_station_telecode":"CBQ","from_station_name":"潮汕","to_station_telecode":"IZQ","to_station_name":"广州南","start_time":"17:00","arrive_time":"20:05","day_difference":"0","train_class_name":"","lishi":"03:05","is_support_card":"1","control_train_day":"20301231","start_train_date":"20190915","seat_feature":"O3M393","yp_ex":"O0M090","train_seat_feature":"3","controlled_train_flag":"0","controlled_train_message":"正常车次，不受控","from_station_no":"01","to_station_no":"08","seat_types":"OM9","gg_num":"-1","gr_num":"-1","rw_num":"-1","rz_num":"-1","tz_num":"-1","wz_num":"-1","yb_num":"-1","yw_num":"-1","yz_num":"-1","ze_num":"有","zy_num":"有","swz_num":"有","srrb_num":"-1","rw_price":"--","srrb_price":"--","yw_price":"--","gr_price":"--","yz_price":"--","rz_price":"--","zy_price":"02705","ze_price":"01815","tz_price":"--","gg_price":"--","yb_price":"--","bxyw_num":"-1","bxyw_price":"--","swz_price":"05195","hbyz_num":"-1","hbyz_price":"--","hbyw_num":"-1","hbyw_price":"--","bxrz_num":"-1","bxrz_price":"--","tdrz_num":"-1","tdrz_price":"--","errb_num":"-1","errb_price":"--","yrrb_num":"-1","yrrb_price":"--","ydsr_num":"-1","ydsr_price":"--","edsr_num":"-1","edsr_price":"--","hbrz_num":"-1","hbrz_price":"--","hbrw_num":"-1","hbrw_price":"--","ydrz_num":"-1","ydrz_price":"--","edrz_num":"-1","edrz_price":"--","wz_price":"--"},"buttonTextInfo":""},{"queryLeftNewDTO":{"train_no":"6j000G63150A","station_train_code":"G6315","start_station_telecode":"CBQ","start_station_name":"潮汕","end_station_telecode":"IZQ","end_station_name":"广州南","from_station_telecode":"CBQ","from_station_name":"潮汕","to_station_telecode":"IZQ","to_station_name":"广州南","start_time":"18:08","arrive_time":"20:57","day_difference":"0","train_class_name":"","lishi":"02:49","is_support_card":"1","control_train_day":"20301231","start_train_date":"20190915","seat_feature":"O3M393","yp_ex":"O0M090","train_seat_feature":"3","controlled_train_flag":"0","controlled_train_message":"正常车次，不受控","from_station_no":"01","to_station_no":"07","seat_types":"OM9","gg_num":"-1","gr_num":"-1","rw_num":"-1","rz_num":"-1","tz_num":"-1","wz_num":"-1","yb_num":"-1","yw_num":"-1","yz_num":"-1","ze_num":"有","zy_num":"有","swz_num":"有","srrb_num":"-1","rw_price":"--","srrb_price":"--","yw_price":"--","gr_price":"--","yz_price":"--","rz_price":"--","zy_price":"02705","ze_price":"01815","tz_price":"--","gg_price":"--","yb_price":"--","bxyw_num":"-1","bxyw_price":"--","swz_price":"05195","hbyz_num":"-1","hbyz_price":"--","hbyw_num":"-1","hbyw_price":"--","bxrz_num":"-1","bxrz_price":"--","tdrz_num":"-1","tdrz_price":"--","errb_num":"-1","errb_price":"--","yrrb_num":"-1","yrrb_price":"--","ydsr_num":"-1","ydsr_price":"--","edsr_num":"-1","edsr_price":"--","hbrz_num":"-1","hbrz_price":"--","hbrw_num":"-1","hbrw_price":"--","ydrz_num":"-1","ydrz_price":"--","edrz_num":"-1","edrz_price":"--","wz_price":"--"},"buttonTextInfo":""},{"queryLeftNewDTO":{"train_no":"67000D752600","station_train_code":"D7527","start_station_telecode":"OTQ","start_station_name":"汕头","end_station_telecode":"GGQ","end_station_name":"广州东","from_station_telecode":"CBQ","from_station_name":"潮汕","to_station_telecode":"GGQ","to_station_name":"广州东","start_time":"19:03","arrive_time":"22:09","day_difference":"0","train_class_name":"动车","lishi":"03:06","is_support_card":"1","control_train_day":"20301231","start_train_date":"20190915","seat_feature":"O3W3M3","yp_ex":"O0M0O0","train_seat_feature":"3","controlled_train_flag":"0","controlled_train_message":"正常车次，不受控","from_station_no":"02","to_station_no":"06","seat_types":"OMO","gg_num":"-1","gr_num":"-1","rw_num":"-1","rz_num":"-1","tz_num":"-1","wz_num":"有","yb_num":"-1","yw_num":"-1","yz_num":"-1","ze_num":"有","zy_num":"有","swz_num":"-1","srrb_num":"-1","rw_price":"--","srrb_price":"--","yw_price":"--","gr_price":"--","wz_seat_type_code":"O","yz_price":"--","rz_price":"--","zy_price":"02495","ze_price":"01795","tz_price":"--","gg_price":"--","yb_price":"--","bxyw_num":"-1","bxyw_price":"--","swz_price":"--","hbyz_num":"-1","hbyz_price":"--","hbyw_num":"-1","hbyw_price":"--","bxrz_num":"-1","bxrz_price":"--","tdrz_num":"-1","tdrz_price":"--","errb_num":"-1","errb_price":"--","yrrb_num":"-1","yrrb_price":"--","ydsr_num":"-1","ydsr_price":"--","edsr_num":"-1","edsr_price":"--","hbrz_num":"-1","hbrz_price":"--","hbrw_num":"-1","hbrw_price":"--","ydrz_num":"-1","ydrz_price":"--","edrz_num":"-1","edrz_price":"--","wz_price":"01795"},"buttonTextInfo":""},{"queryLeftNewDTO":{"train_no":"6j000G63110D","station_train_code":"G6311","start_station_telecode":"CBQ","start_station_name":"潮汕","end_station_telecode":"IZQ","end_station_name":"广州南","from_station_telecode":"CBQ","from_station_name":"潮汕","to_station_telecode":"IZQ","to_station_name":"广州南","start_time":"19:13","arrive_time":"22:04","day_difference":"0","train_class_name":"","lishi":"02:51","is_support_card":"1","control_train_day":"20301231","start_train_date":"20190915","seat_feature":"O3M393","yp_ex":"O0M090","train_seat_feature":"3","controlled_train_flag":"0","controlled_train_message":"正常车次，不受控","from_station_no":"01","to_station_no":"07","seat_types":"OM9","gg_num":"-1","gr_num":"-1","rw_num":"-1","rz_num":"-1","tz_num":"-1","wz_num":"-1","yb_num":"-1","yw_num":"-1","yz_num":"-1","ze_num":"有","zy_num":"有","swz_num":"有","srrb_num":"-1","rw_price":"--","srrb_price":"--","yw_price":"--","gr_price":"--","yz_price":"--","rz_price":"--","zy_price":"02705","ze_price":"01815","tz_price":"--","gg_price":"--","yb_price":"--","bxyw_num":"-1","bxyw_price":"--","swz_price":"05195","hbyz_num":"-1","hbyz_price":"--","hbyw_num":"-1","hbyw_price":"--","bxrz_num":"-1","bxrz_price":"--","tdrz_num":"-1","tdrz_price":"--","errb_num":"-1","errb_price":"--","yrrb_num":"-1","yrrb_price":"--","ydsr_num":"-1","ydsr_price":"--","edsr_num":"-1","edsr_price":"--","hbrz_num":"-1","hbrz_price":"--","hbrw_num":"-1","hbrw_price":"--","ydrz_num":"-1","ydrz_price":"--","edrz_num":"-1","edrz_price":"--","wz_price":"--"},"buttonTextInfo":""},{"queryLeftNewDTO":{"train_no":"6j000D750707","station_train_code":"D7507","start_station_telecode":"CBQ","start_station_name":"潮汕","end_station_telecode":"GGQ","end_station_name":"广州东","from_station_telecode":"CBQ","from_station_name":"潮汕","to_station_telecode":"GGQ","to_station_name":"广州东","start_time":"20:15","arrive_time":"23:27","day_difference":"0","train_class_name":"动车","lishi":"03:12","is_support_card":"1","control_train_day":"20301231","start_train_date":"20190915","seat_feature":"O3W3M3","yp_ex":"O0M0O0","train_seat_feature":"3","controlled_train_flag":"0","controlled_train_message":"正常车次，不受控","from_station_no":"01","to_station_no":"07","seat_types":"OMO","gg_num":"-1","gr_num":"-1","rw_num":"-1","rz_num":"-1","tz_num":"-1","wz_num":"有","yb_num":"-1","yw_num":"-1","yz_num":"-1","ze_num":"有","zy_num":"有","swz_num":"-1","srrb_num":"-1","rw_price":"--","srrb_price":"--","yw_price":"--","gr_price":"--","wz_seat_type_code":"O","yz_price":"--","rz_price":"--","zy_price":"02495","ze_price":"01795","tz_price":"--","gg_price":"--","yb_price":"--","bxyw_num":"-1","bxyw_price":"--","swz_price":"--","hbyz_num":"-1","hbyz_price":"--","hbyw_num":"-1","hbyw_price":"--","bxrz_num":"-1","bxrz_price":"--","tdrz_num":"-1","tdrz_price":"--","errb_num":"-1","errb_price":"--","yrrb_num":"-1","yrrb_price":"--","ydsr_num":"-1","ydsr_price":"--","edsr_num":"-1","edsr_price":"--","hbrz_num":"-1","hbrz_price":"--","hbrw_num":"-1","hbrw_price":"--","ydrz_num":"-1","ydrz_price":"--","edrz_num":"-1","edrz_price":"--","wz_price":"01795"},"buttonTextInfo":""},{"queryLeftNewDTO":{"train_no":"6j000G633107","station_train_code":"G6331","start_station_telecode":"CBQ","start_station_name":"潮汕","end_station_telecode":"IZQ","end_station_name":"广州南","from_station_telecode":"CBQ","from_station_name":"潮汕","to_station_telecode":"IZQ","to_station_name":"广州南","start_time":"20:47","arrive_time":"23:29","day_difference":"0","train_class_name":"","lishi":"02:42","is_support_card":"1","control_train_day":"20301231","start_train_date":"20190915","seat_feature":"O3M393","yp_ex":"O0M090","train_seat_feature":"3","controlled_train_flag":"0","controlled_train_message":"正常车次，不受控","from_station_no":"01","to_station_no":"05","seat_types":"OM9","gg_num":"-1","gr_num":"-1","rw_num":"-1","rz_num":"-1","tz_num":"-1","wz_num":"-1","yb_num":"-1","yw_num":"-1","yz_num":"-1","ze_num":"有","zy_num":"有","swz_num":"有","srrb_num":"-1","rw_price":"--","srrb_price":"--","yw_price":"--","gr_price":"--","yz_price":"--","rz_price":"--","zy_price":"02705","ze_price":"01815","tz_price":"--","gg_price":"--","yb_price":"--","bxyw_num":"-1","bxyw_price":"--","swz_price":"05195","hbyz_num":"-1","hbyz_price":"--","hbyw_num":"-1","hbyw_price":"--","bxrz_num":"-1","bxrz_price":"--","tdrz_num":"-1","tdrz_price":"--","errb_num":"-1","errb_price":"--","yrrb_num":"-1","yrrb_price":"--","ydsr_num":"-1","ydsr_price":"--","edsr_num":"-1","edsr_price":"--","hbrz_num":"-1","hbrz_price":"--","hbrw_num":"-1","hbrw_price":"--","ydrz_num":"-1","ydrz_price":"--","edrz_num":"-1","edrz_price":"--","wz_price":"--"},"buttonTextInfo":""}]
     * messages : []
     * validateMessages : {}
     */

    public String validateMessagesShowId;
    public boolean status;
    public int httpstatus;
    public ValidateMessagesBean validateMessages;
    public List<DataBean> data;
    public List<?> messages;

    public static class ValidateMessagesBean {
    }

    public static class DataBean implements IRemainingTicket {
        /**
         * queryLeftNewDTO : {"train_no":"6j000G634302","station_train_code":"G6343","start_station_telecode":"CBQ","start_station_name":"潮汕","end_station_telecode":"IZQ","end_station_name":"广州南","from_station_telecode":"CBQ","from_station_name":"潮汕","to_station_telecode":"IZQ","to_station_name":"广州南","start_time":"06:08","arrive_time":"08:45","day_difference":"0","train_class_name":"","lishi":"02:37","is_support_card":"1","control_train_day":"20301231","start_train_date":"20190915","seat_feature":"O3M393","yp_ex":"O0M090","train_seat_feature":"3","controlled_train_flag":"0","controlled_train_message":"正常车次，不受控","from_station_no":"01","to_station_no":"05","seat_types":"OM9","gg_num":"-1","gr_num":"-1","rw_num":"-1","rz_num":"-1","tz_num":"-1","wz_num":"-1","yb_num":"-1","yw_num":"-1","yz_num":"-1","ze_num":"有","zy_num":"有","swz_num":"有","srrb_num":"-1","rw_price":"--","srrb_price":"--","yw_price":"--","gr_price":"--","yz_price":"--","rz_price":"--","zy_price":"02705","ze_price":"01815","tz_price":"--","gg_price":"--","yb_price":"--","bxyw_num":"-1","bxyw_price":"--","swz_price":"05195","hbyz_num":"-1","hbyz_price":"--","hbyw_num":"-1","hbyw_price":"--","bxrz_num":"-1","bxrz_price":"--","tdrz_num":"-1","tdrz_price":"--","errb_num":"-1","errb_price":"--","yrrb_num":"-1","yrrb_price":"--","ydsr_num":"-1","ydsr_price":"--","edsr_num":"-1","edsr_price":"--","hbrz_num":"-1","hbrz_price":"--","hbrw_num":"-1","hbrw_price":"--","ydrz_num":"-1","ydrz_price":"--","edrz_num":"-1","edrz_price":"--","wz_price":"--"}
         * buttonTextInfo :
         */

        public QueryLeftNewDTOBean queryLeftNewDTO;
        public String buttonTextInfo;

        private final static String EMPTY = "--";
        private final static String RESULT_EMPTY = "";
        private final static String NO_NUMBER = "无";

        @Override
        public String getCode() {
            return queryLeftNewDTO.getCode();
        }

        @Override
        public String getFromStation() {
            return queryLeftNewDTO.getFromStation();
        }

        @Override
        public String getToStation() {
            return queryLeftNewDTO.getToStation();
        }

        @Override
        public String getStartTime() {
            return queryLeftNewDTO.getStartTime();
        }

        @Override
        public String getEndTime() {
            return queryLeftNewDTO.getEndTime();
        }

        @Override
        public String getSpeedTime() {
            return queryLeftNewDTO.getSpeedTime();
        }

        @Override
        public String getRw() {
            return queryLeftNewDTO.getRw();
        }

        @Override
        public String getYw() {
            return queryLeftNewDTO.getYw();
        }

        @Override
        public String getRz() {
            return queryLeftNewDTO.getRw();
        }

        @Override
        public String getYz() {
            return queryLeftNewDTO.getYz();
        }

        @Override
        public String getQt() {
            return queryLeftNewDTO.getQt();
        }

        @Override
        public String getWz() {
            return queryLeftNewDTO.getWz();
        }

        public static class QueryLeftNewDTOBean implements IRemainingTicket {

            @Override
            public String getCode() {
                return station_train_code;
            }

            @Override
            public String getFromStation() {
                return from_station_name;
            }

            @Override
            public String getToStation() {
                return to_station_name;
            }

            @Override
            public String getStartTime() {
                return start_time;
            }

            @Override
            public String getEndTime() {
                return arrive_time;
            }

            @Override
            public String getSpeedTime() {
                return lishi;
            }

            @Override
            public String getRw() {
                if (EMPTY.equals(rw_num) || NO_NUMBER.equals(rw_num)) {
                    return RESULT_EMPTY;
                }
                return rw_num;
            }

            @Override
            public String getYw() {
                if (EMPTY.equals(yw_num) || NO_NUMBER.equals(yw_num)) {
                    return RESULT_EMPTY;
                }
                return yw_num;
            }

            @Override
            public String getRz() {
                String result = zy_num + rz_num;
                return result.replaceAll("[^0-9]", "");
            }

            @Override
            public String getYz() {
                String result = ze_num + yz_num;
                return result.replaceAll("[^0-9]", "");
            }

            @Override
            public String getQt() {
                StringBuilder builder = new StringBuilder();
                //其他：商务座：swz
                //特等座：tz_num

                if (NO_NUMBER.equals(swz_num) || EMPTY.equals(swz_num)) {
                    nothing();
                } else {
                    builder.append("商务座:");
                    builder.append(swz_num);
                }
                if (NO_NUMBER.equals(tz_num) || EMPTY.equals(tz_num)) {
                    nothing();
                } else {
                    if (!TextUtils.isEmpty(builder)) {
                        builder.append(";");
                    }
                    builder.append("特等座:");
                    builder.append(tz_num);
                }
                return builder.toString();
            }

            private void nothing() {

            }

            @Override
            public String getWz() {
                if (EMPTY.equals(wz_num) || NO_NUMBER.equals(wz_num)) {
                    return RESULT_EMPTY;
                }
                return wz_num;
            }


            @Override
            public String toString() {
                return "{" +
                        "'" + station_train_code + '\'' +
                        ", '" + start_station_name + '\'' +
                        ", '" + end_station_name + '\'' +
                        '}';
            }

            /**
             * train_no : 6j000G634302
             * station_train_code : G6343
             * start_station_telecode : CBQ
             * start_station_name : 潮汕
             * end_station_telecode : IZQ
             * end_station_name : 广州南
             * from_station_telecode : CBQ
             * from_station_name : 潮汕
             * to_station_telecode : IZQ
             * to_station_name : 广州南
             * start_time : 06:08
             * arrive_time : 08:45
             * day_difference : 0
             * train_class_name :
             * lishi : 02:37
             * is_support_card : 1
             * control_train_day : 20301231
             * start_train_date : 20190915
             * seat_feature : O3M393
             * yp_ex : O0M090
             * train_seat_feature : 3
             * controlled_train_flag : 0
             * controlled_train_message : 正常车次，不受控
             * from_station_no : 01
             * to_station_no : 05
             * seat_types : OM9
             * gg_num : -1
             * gr_num : -1
             * rw_num : -1
             * rz_num : -1
             * tz_num : -1
             * wz_num : -1
             * yb_num : -1
             * yw_num : -1
             * yz_num : -1
             * ze_num : 有
             * zy_num : 有
             * swz_num : 有
             * srrb_num : -1
             * rw_price : --
             * srrb_price : --
             * yw_price : --
             * gr_price : --
             * yz_price : --
             * rz_price : --
             * zy_price : 02705
             * ze_price : 01815
             * tz_price : --
             * gg_price : --
             * yb_price : --
             * bxyw_num : -1
             * bxyw_price : --
             * swz_price : 05195
             * hbyz_num : -1
             * hbyz_price : --
             * hbyw_num : -1
             * hbyw_price : --
             * bxrz_num : -1
             * bxrz_price : --
             * tdrz_num : -1
             * tdrz_price : --
             * errb_num : -1
             * errb_price : --
             * yrrb_num : -1
             * yrrb_price : --
             * ydsr_num : -1
             * ydsr_price : --
             * edsr_num : -1
             * edsr_price : --
             * hbrz_num : -1
             * hbrz_price : --
             * hbrw_num : -1
             * hbrw_price : --
             * ydrz_num : -1
             * ydrz_price : --
             * edrz_num : -1
             * edrz_price : --
             * wz_price : --
             */

            public String train_no;
            public String station_train_code;
            public String start_station_telecode;
            public String start_station_name;
            public String end_station_telecode;
            public String end_station_name;
            public String from_station_telecode;
            public String from_station_name;
            public String to_station_telecode;
            public String to_station_name;
            public String start_time;
            public String arrive_time;
            public String day_difference;
            public String train_class_name;
            public String lishi;
            public String is_support_card;
            public String controlled_train_flag;
            public String controlled_train_message;
            public String from_station_no;
            public String to_station_no;
            public String seat_types;
            public String gg_num;
            public String gr_num;
            public String rw_num;
            public String rz_num;
            public String tz_num;
            public String wz_num;
            public String yb_num;
            public String yw_num;
            public String yz_num;
            public String ze_num;
            public String zy_num;
            public String swz_num;
            public String srrb_num;
            public String rw_price;
            public String srrb_price;
            public String yw_price;
            public String gr_price;
            public String yz_price;
            public String rz_price;
            public String zy_price;
            public String ze_price;
            public String tz_price;
            public String gg_price;
            public String yb_price;
            public String bxyw_num;
            public String bxyw_price;
            public String swz_price;
            public String hbyz_num;
            public String hbyz_price;
            public String hbyw_num;
            public String hbyw_price;
            public String bxrz_num;
            public String bxrz_price;
            public String tdrz_num;
            public String tdrz_price;
            public String errb_num;
            public String errb_price;
            public String yrrb_num;
            public String yrrb_price;
            public String ydsr_num;
            public String ydsr_price;
            public String edsr_num;
            public String edsr_price;
            public String hbrz_num;
            public String hbrz_price;
            public String hbrw_num;
            public String hbrw_price;
            public String ydrz_num;
            public String ydrz_price;
            public String edrz_num;
            public String edrz_price;
            public String wz_price;
            public String control_train_day;
            public String start_train_date;
            public String seat_feature;
            public String yp_ex;
            public String train_seat_feature;
        }
    }
}
