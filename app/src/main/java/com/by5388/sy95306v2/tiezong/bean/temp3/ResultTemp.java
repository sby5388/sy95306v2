package com.by5388.sy95306v2.tiezong.bean.temp3;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * @author admin  on 2019/1/24.
 */
public class ResultTemp {

    /**
     * validateMessagesShowId : _validatorMessage
     * status : true
     * httpstatus : 200
     * data : {"19":["乐清","UPH"],"17":["温州南","VRH"],"18":["永嘉","URH"],"15":["宁德","NES"],"16":["福安","FAS"],"13":["福州南","FYS"],"14":["连江","LKS"],"11":["莆田","PTS"],"12":["福清","FQS"],"21":["宁海","NHH"],"20":["温岭","VHH"],"08":["漳州","ZUS"],"09":["厦门北","XKS"],"04":["汕尾","OGQ"],"22":["宁波","NGH"],"05":["潮阳","CNQ"],"23":["余姚北","CTH"],"06":["潮汕","CBQ"],"24":["绍兴东","SSH"],"07":["诏安","ZDS"],"25":["绍兴北","SLH"],"26":["杭州东","HGH"],"27":["余杭","EVH"],"01":["深圳北","IOQ"],"28":["嘉兴南","EPH"],"02":["深圳坪山","IFQ"],"29":["上海虹桥","AOH"],"03":["惠州南","KNQ"],"10":["泉州","QYS"]}
     * messages : []
     * validateMessages : {}
     */

    private String validateMessagesShowId;
    private boolean status;
    private int httpstatus;
    private DataBean data;
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

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
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

    public static class DataBean {
        @SerializedName("01")
        private List<String> _$01;

        public List<String> get_$01() {
            return _$01;
        }

        public void set_$01(List<String> _$01) {
            this._$01 = _$01;
        }
    }

    public static class ValidateMessagesBean {
    }
}
