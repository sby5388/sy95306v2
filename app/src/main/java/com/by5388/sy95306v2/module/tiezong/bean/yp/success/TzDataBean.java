package com.by5388.sy95306v2.module.tiezong.bean.yp.success;

import android.text.TextUtils;

import com.by5388.sy95306v2.bean.IRemainingTicket;

/**
 * @author by5388
 * @date 2018/8/17 15:12
 * 车次属性
 */
@SuppressWarnings("all")
public class TzDataBean implements IRemainingTicket, Cloneable {
    private static final String TAG = "TzDataBean";
    private final static String EMPTY = "--";
    private final static String RESULT_EMPTY = "";
    private final static String NO_NUMBER = "无";
    public TzDataBean() {

    }
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

    /**
     * train_no : 65000D753300
     * station_train_code : D7533
     * start_station_telecode : GGQ
     * start_station_name : 广州东
     * end_station_telecode : CBQ
     * end_station_name : 潮汕
     * from_station_telecode : GGQ
     * from_station_name : 广州东
     * to_station_telecode : CBQ
     * to_station_name : 潮汕
     * start_time : 11:30
     * arrive_time : 14:47
     * day_difference : 0
     * train_class_name : 动车
     * lishi : 03:17
     * canWebBuy : N
     * lishiValue : 197
     * yp_info : O017950000M024950000O017953000
     * exchange_train_flag : 0
     * control_train_day : 20301231
     * start_train_date : 20180817
     * seat_feature : O3M3W3
     * yp_ex : O0M0O0
     * train_seat_feature : 3
     * train_type_code : 3
     * start_province_code : 16
     * start_city_code : 1502
     * end_province_code : 16
     * end_city_code : 1515
     * seat_types : OMO
     * location_code : Q6
     * from_station_no : 01
     * to_station_no : 07
     * control_day : 29
     * sale_time : 1130
     * is_support_card : 0
     * controlled_train_flag : 0
     * controlled_train_message : 正常车次，不受控
     * rw_num : --软卧
     * srrb_num : -- 动卧
     * gg_num : --
     * gr_num : -- 高级软卧
     * qt_num : --
     * rz_num : --
     * tz_num : --
     * wz_num : 无
     * yb_num : --
     * yw_num : --
     * yz_num : --
     * ze_num : 无
     * zy_num : 无
     * swz_num : --
     */


    private String train_no;
    private String station_train_code;
    private String start_station_telecode;
    private String start_station_name;
    private String end_station_telecode;
    private String end_station_name;
    private String from_station_telecode;
    private String from_station_name;
    private String to_station_telecode;
    private String to_station_name;
    private String start_time;
    private String arrive_time;
    private String day_difference;
    private String train_class_name;
    private String lishi;
    private String canWebBuy;
    private String lishiValue;
    private String yp_info;
    private String exchange_train_flag;
    private String control_train_day;
    private String start_train_date;
    private String seat_feature;
    private String yp_ex;
    private String train_seat_feature;
    private String train_type_code;
    private String start_province_code;
    private String start_city_code;
    private String end_province_code;
    private String end_city_code;
    private String seat_types;
    private String location_code;
    private String from_station_no;
    private String to_station_no;
    private int control_day;
    private String sale_time;
    private String is_support_card;
    private String controlled_train_flag;
    private String controlled_train_message;
    private String rw_num;
    private String srrb_num;
    private String gg_num;
    private String gr_num;
    private String qt_num;
    private String rz_num;
    private String tz_num;
    private String wz_num;
    private String yb_num;
    private String yw_num;
    private String yz_num;
    private String ze_num;
    private String zy_num;
    private String swz_num;

    public String getTrain_no() {
        return train_no;
    }

    public void setTrain_no(String train_no) {
        this.train_no = train_no;
    }

    public String getStation_train_code() {
        return station_train_code;
    }

    public void setStation_train_code(String station_train_code) {
        this.station_train_code = station_train_code;
    }

    public String getStart_station_telecode() {
        return start_station_telecode;
    }

    public void setStart_station_telecode(String start_station_telecode) {
        this.start_station_telecode = start_station_telecode;
    }

    public String getStart_station_name() {
        return start_station_name;
    }

    public void setStart_station_name(String start_station_name) {
        this.start_station_name = start_station_name;
    }

    public String getEnd_station_telecode() {
        return end_station_telecode;
    }

    public void setEnd_station_telecode(String end_station_telecode) {
        this.end_station_telecode = end_station_telecode;
    }

    public String getEnd_station_name() {
        return end_station_name;
    }

    public void setEnd_station_name(String end_station_name) {
        this.end_station_name = end_station_name;
    }

    public String getFrom_station_telecode() {
        return from_station_telecode;
    }

    public void setFrom_station_telecode(String from_station_telecode) {
        this.from_station_telecode = from_station_telecode;
    }

    public String getFrom_station_name() {
        return from_station_name;
    }

    public void setFrom_station_name(String from_station_name) {
        this.from_station_name = from_station_name;
    }

    public String getTo_station_telecode() {
        return to_station_telecode;
    }

    public void setTo_station_telecode(String to_station_telecode) {
        this.to_station_telecode = to_station_telecode;
    }

    public String getTo_station_name() {
        return to_station_name;
    }

    public void setTo_station_name(String to_station_name) {
        this.to_station_name = to_station_name;
    }

    public String getStart_time() {
        return start_time;
    }

    public void setStart_time(String start_time) {
        this.start_time = start_time;
    }

    public String getArrive_time() {
        return arrive_time;
    }

    public void setArrive_time(String arrive_time) {
        this.arrive_time = arrive_time;
    }

    public String getDay_difference() {
        return day_difference;
    }

    public void setDay_difference(String day_difference) {
        this.day_difference = day_difference;
    }

    public String getTrain_class_name() {
        return train_class_name;
    }

    public void setTrain_class_name(String train_class_name) {
        this.train_class_name = train_class_name;
    }

    public String getLishi() {
        return lishi;
    }

    public void setLishi(String lishi) {
        this.lishi = lishi;
    }

    public String getCanWebBuy() {
        return canWebBuy;
    }

    public void setCanWebBuy(String canWebBuy) {
        this.canWebBuy = canWebBuy;
    }

    public String getLishiValue() {
        return lishiValue;
    }

    public void setLishiValue(String lishiValue) {
        this.lishiValue = lishiValue;
    }

    public String getYp_info() {
        return yp_info;
    }

    public void setYp_info(String yp_info) {
        this.yp_info = yp_info;
    }

    public String getExchange_train_flag() {
        return exchange_train_flag;
    }

    public void setExchange_train_flag(String exchange_train_flag) {
        this.exchange_train_flag = exchange_train_flag;
    }



    public String getControl_train_day() {
        return control_train_day;
    }

    public void setControl_train_day(String control_train_day) {
        this.control_train_day = control_train_day;
    }

    public String getStart_train_date() {
        return start_train_date;
    }

    public void setStart_train_date(String start_train_date) {
        this.start_train_date = start_train_date;
    }

    public String getSeat_feature() {
        return seat_feature;
    }

    public void setSeat_feature(String seat_feature) {
        this.seat_feature = seat_feature;
    }

    public String getYp_ex() {
        return yp_ex;
    }

    public void setYp_ex(String yp_ex) {
        this.yp_ex = yp_ex;
    }

    public String getTrain_seat_feature() {
        return train_seat_feature;
    }

    public void setTrain_seat_feature(String train_seat_feature) {
        this.train_seat_feature = train_seat_feature;
    }

    public String getTrain_type_code() {
        return train_type_code;
    }

    public void setTrain_type_code(String train_type_code) {
        this.train_type_code = train_type_code;
    }

    public String getStart_province_code() {
        return start_province_code;
    }

    public void setStart_province_code(String start_province_code) {
        this.start_province_code = start_province_code;
    }

    public String getStart_city_code() {
        return start_city_code;
    }

    public void setStart_city_code(String start_city_code) {
        this.start_city_code = start_city_code;
    }

    public String getEnd_province_code() {
        return end_province_code;
    }

    public void setEnd_province_code(String end_province_code) {
        this.end_province_code = end_province_code;
    }

    public String getEnd_city_code() {
        return end_city_code;
    }

    public void setEnd_city_code(String end_city_code) {
        this.end_city_code = end_city_code;
    }

    public String getSeat_types() {
        return seat_types;
    }

    public void setSeat_types(String seat_types) {
        this.seat_types = seat_types;
    }

    public String getLocation_code() {
        return location_code;
    }

    public void setLocation_code(String location_code) {
        this.location_code = location_code;
    }

    public String getFrom_station_no() {
        return from_station_no;
    }

    public void setFrom_station_no(String from_station_no) {
        this.from_station_no = from_station_no;
    }

    public String getTo_station_no() {
        return to_station_no;
    }

    public void setTo_station_no(String to_station_no) {
        this.to_station_no = to_station_no;
    }

    public int getControl_day() {
        return control_day;
    }

    public void setControl_day(int control_day) {
        this.control_day = control_day;
    }

    public String getSale_time() {
        return sale_time;
    }

    public void setSale_time(String sale_time) {
        this.sale_time = sale_time;
    }

    public String getIs_support_card() {
        return is_support_card;
    }

    public void setIs_support_card(String is_support_card) {
        this.is_support_card = is_support_card;
    }

    public String getControlled_train_flag() {
        return controlled_train_flag;
    }

    public void setControlled_train_flag(String controlled_train_flag) {
        this.controlled_train_flag = controlled_train_flag;
    }

    public String getControlled_train_message() {
        return controlled_train_message;
    }

    public void setControlled_train_message(String controlled_train_message) {
        this.controlled_train_message = controlled_train_message;
    }

    public String getRw_num() {
        return rw_num;
    }

    public void setRw_num(String rw_num) {
        this.rw_num = rw_num;
    }

    public String getSrrb_num() {
        return srrb_num;
    }

    public void setSrrb_num(String srrb_num) {
        this.srrb_num = srrb_num;
    }

    public String getGg_num() {
        return gg_num;
    }

    public void setGg_num(String gg_num) {
        this.gg_num = gg_num;
    }

    public String getGr_num() {
        return gr_num;
    }

    public void setGr_num(String gr_num) {
        this.gr_num = gr_num;
    }

    public String getQt_num() {
        return qt_num;
    }

    public void setQt_num(String qt_num) {
        this.qt_num = qt_num;
    }

    public String getRz_num() {
        return rz_num;
    }

    public void setRz_num(String rz_num) {
        this.rz_num = rz_num;
    }

    public String getTz_num() {
        return tz_num;
    }

    public void setTz_num(String tz_num) {
        this.tz_num = tz_num;
    }

    public String getWz_num() {
        return wz_num;
    }

    public void setWz_num(String wz_num) {
        this.wz_num = wz_num;
    }

    public String getYb_num() {
        return yb_num;
    }

    public void setYb_num(String yb_num) {
        this.yb_num = yb_num;
    }

    public String getYw_num() {
        return yw_num;
    }

    public void setYw_num(String yw_num) {
        this.yw_num = yw_num;
    }

    public String getYz_num() {
        return yz_num;
    }

    public void setYz_num(String yz_num) {
        this.yz_num = yz_num;
    }

    public String getZe_num() {
        return ze_num;
    }

    public void setZe_num(String ze_num) {
        this.ze_num = ze_num;
    }

    public String getZy_num() {
        return zy_num;
    }

    public void setZy_num(String zy_num) {
        this.zy_num = zy_num;
    }

    public String getSwz_num() {
        return swz_num;
    }

    public void setSwz_num(String swz_num) {
        this.swz_num = swz_num;
    }


    @Override
    protected Object clone() throws CloneNotSupportedException {
        TzDataBean o = (TzDataBean) super.clone();
        return o;
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    /**
     * TODO 应当实现克隆接口 Copy
     * {@link Cloneable}
     *
     * @param bean 复制
     */


    public TzDataBean(TzDataBean bean) {
        this.train_no = bean.train_no;
        this.station_train_code = bean.station_train_code;
        this.start_station_telecode = bean.start_station_telecode;
        this.start_station_name = bean.start_station_name;
        this.end_station_telecode = bean.end_station_telecode;
        this.end_station_name = bean.end_station_name;
        this.from_station_telecode = bean.from_station_telecode;
        this.from_station_name = bean.from_station_name;
        this.to_station_telecode = bean.to_station_telecode;
        this.to_station_name = bean.to_station_name;
        this.start_time = bean.start_time;
        this.arrive_time = bean.arrive_time;
        this.day_difference = bean.day_difference;
        this.train_class_name = bean.train_class_name;
        this.lishi = bean.lishi;
        this.canWebBuy = bean.canWebBuy;
        this.lishiValue = bean.lishiValue;
        this.yp_info = bean.yp_info;
        this.exchange_train_flag = bean.exchange_train_flag;
        this.control_train_day = bean.control_train_day;
        this.start_train_date = bean.start_train_date;
        this.seat_feature = bean.seat_feature;
        this.yp_ex = bean.yp_ex;
        this.train_seat_feature = bean.train_seat_feature;
        this.train_type_code = bean.train_type_code;
        this.start_province_code = bean.start_province_code;
        this.start_city_code = bean.start_city_code;
        this.end_province_code = bean.end_province_code;
        this.end_city_code = bean.end_city_code;
        this.seat_types = bean.seat_types;
        this.location_code = bean.location_code;
        this.from_station_no = bean.from_station_no;
        this.to_station_no = bean.to_station_no;
        this.control_day = bean.control_day;
        this.sale_time = bean.sale_time;
        this.is_support_card = bean.is_support_card;
        this.controlled_train_flag = bean.controlled_train_flag;
        this.controlled_train_message = bean.controlled_train_message;
        this.rw_num = bean.rw_num;
        this.srrb_num = bean.srrb_num;
        this.gg_num = bean.gg_num;
        this.gr_num = bean.gr_num;
        this.qt_num = bean.qt_num;
        this.rz_num = bean.rz_num;
        this.tz_num = bean.tz_num;
        this.wz_num = bean.wz_num;
        this.yb_num = bean.yb_num;
        this.yw_num = bean.yw_num;
        this.yz_num = bean.yz_num;
        this.ze_num = bean.ze_num;
        this.zy_num = bean.zy_num;
        this.swz_num = bean.swz_num;
    }
}