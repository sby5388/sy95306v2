package com.by5388.sy95306v2.tiezong.bean;

import com.by5388.sy95306v2.tiezong.bean.check.PassCodeDataBean;
import com.by5388.sy95306v2.tiezong.bean.number.NumberListDataBean;
import com.by5388.sy95306v2.tiezong.bean.temp.DataBeanX;
import com.by5388.sy95306v2.tiezong.bean.yp.fail.FailDataBean;
import com.by5388.sy95306v2.tiezong.bean.yp.success.SuccessDataBean;

import java.util.List;

/**
 * @author by5388
 * @date 2018/8/17 15:05
 * 12306 相关数据请求的数据返回格式
 */
@SuppressWarnings("all")
public class TzResult<T> {

    /**
     * validateMessagesShowId : _validatorMessage
     * status : true
     * httpstatus : 200
     * messages : []
     * validateMessages : {}
     * data::T ,
     *
     * @see PassCodeDataBean 包括图片验证码校验、
     * @see SuccessDataBean 中转查询中的得到直达车次,为成功
     * @see FailDataBean 没有直达车次为失败的
     * @see NumberListDataBean 车次查询结果
     * @see DataBeanX 车站车次查询的结果
     */

    private String validateMessagesShowId;
    private boolean status;
    private int httpstatus;
    private T data;
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

    public T getData() {
        return data;
    }

    public void setData(T data) {
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

}