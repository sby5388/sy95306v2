package com.by5388.sy95306v2.module.tiezong.api.version2;

import com.by5388.sy95306v2.module.tiezong.bean.ValidateMessagesBean;

import java.util.List;

/**
 * @author Administrator  on 2019/6/14.
 */
public class Temp {

    /**
     * validateMessagesShowId : _validatorMessage
     * status : true
     * httpstatus : 200
     * data : {"data":[{"arrive_day_str":"当日到达","station_name":"饶平","train_class_name":"动车","is_start":"Y","service_type":"1","end_station_name":"广州东","arrive_time":"----","start_station_name":"饶平","station_train_code":"D7515","arrive_day_diff":"0","start_time":"13:59","station_no":"01","wz_num":"--","running_time":"00:00"},{"arrive_day_str":"当日到达","arrive_time":"14:16","station_train_code":"D7515","station_name":"潮汕","arrive_day_diff":"0","OT":[],"start_time":"14:19","wz_num":"--","station_no":"02","running_time":"00:17"},{"arrive_day_str":"当日到达","arrive_time":"14:40","station_train_code":"D7515","station_name":"普宁","arrive_day_diff":"0","OT":[],"start_time":"14:42","wz_num":"--","station_no":"03","running_time":"00:41"},{"arrive_day_str":"当日到达","arrive_time":"15:17","station_train_code":"D7515","station_name":"汕尾","arrive_day_diff":"0","OT":[],"start_time":"15:19","wz_num":"--","station_no":"04","running_time":"01:18"},{"arrive_day_str":"当日到达","arrive_time":"16:01","station_train_code":"D7515","station_name":"深圳坪山","arrive_day_diff":"0","OT":[],"start_time":"16:03","wz_num":"--","station_no":"05","running_time":"02:02"},{"arrive_day_str":"当日到达","arrive_time":"16:54","station_train_code":"D7514","station_name":"东莞","arrive_day_diff":"0","OT":[],"start_time":"16:56","wz_num":"--","station_no":"06","running_time":"02:55"},{"arrive_day_str":"当日到达","arrive_time":"17:28","station_train_code":"D7514","station_name":"广州东","arrive_day_diff":"0","OT":[],"start_time":"17:28","wz_num":"--","station_no":"07","running_time":"03:29"}]}
     * messages : []
     * validateMessages : {}
     */

    private String validateMessagesShowId;
    private boolean status;
    private int httpstatus;
    private DataBeanX data;
    private ValidateMessagesBean validateMessages;
    private List<?> messages;

    public String getValidateMessagesShowId() {
        return validateMessagesShowId;
    }

    public void setValidateMessagesShowId(String validateMessagesShowId) {
        this.validateMessagesShowId = validateMessagesShowId;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public int getHttpstatus() {
        return httpstatus;
    }

    public void setHttpstatus(int httpstatus) {
        this.httpstatus = httpstatus;
    }

    public DataBeanX getData() {
        return data;
    }

    public void setData(DataBeanX data) {
        this.data = data;
    }

    public ValidateMessagesBean getValidateMessages() {
        return validateMessages;
    }

    public void setValidateMessages(ValidateMessagesBean validateMessages) {
        this.validateMessages = validateMessages;
    }

    public List<?> getMessages() {
        return messages;
    }

    public void setMessages(List<?> messages) {
        this.messages = messages;
    }

    public static class DataBeanX {
        private List<DataBean> data;

        public List<DataBean> getData() {
            return data;
        }

        public void setData(List<DataBean> data) {
            this.data = data;
        }

        public static class DataBean {
            /**
             * arrive_day_str : 当日到达
             * station_name : 饶平
             * train_class_name : 动车
             * is_start : Y
             * service_type : 1
             * end_station_name : 广州东
             * arrive_time : ----
             * start_station_name : 饶平
             * station_train_code : D7515
             * arrive_day_diff : 0
             * start_time : 13:59
             * station_no : 01
             * wz_num : --
             * running_time : 00:00
             * OT : []
             */

            private String arrive_day_str;
            private String station_name;
            private String train_class_name;

            public String getArrive_day_str() {
                return arrive_day_str;
            }

            public void setArrive_day_str(String arrive_day_str) {
                this.arrive_day_str = arrive_day_str;
            }

            public String getStation_name() {
                return station_name;
            }

            public void setStation_name(String station_name) {
                this.station_name = station_name;
            }

            public String getTrain_class_name() {
                return train_class_name;
            }

            public void setTrain_class_name(String train_class_name) {
                this.train_class_name = train_class_name;
            }
        }
    }
}
