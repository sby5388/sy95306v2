package com.by5388.t12306.api.late.status;

import java.util.List;

/**
 * @author by5388  on 2019/5/5.
 */
public class LateStatusResult {

    /**
     * validateMessagesShowId : _validatorMessage
     * status : true
     * httpstatus : 200
     * data : {"message":"预计D7486次列车，广州南站到达时间为21:52","flag":false}
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
        /**
         * message : 预计D7486次列车，广州南站到达时间为21:52
         * flag : false
         */

        private String message;
        private boolean flag;

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }

        public boolean isFlag() {
            return flag;
        }

        public void setFlag(boolean flag) {
            this.flag = flag;
        }
    }

    public static class ValidateMessagesBean {
    }
}
