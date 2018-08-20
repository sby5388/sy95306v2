package com.by5388.sy95306v2.bean.shanghai;

/**
 * @author by5388
 * @date 2018/8/8 16:28
 */
public class QueryMethod<T> {

    public QueryMethod(T t) {
        this.info = t;
        this.user = new UserBean();
    }


    /**
     * T 1:车次查询
     *
     * @see InfoBeanTrainNo
     * info : {"type":"1","trainCode":"G2","trainDate":"2018-08-08"}
     * <p>
     * T 2:站站查询
     * @see InfoBeanP2P
     * info:{type:"2",trainDate:"2018-08-08",fromStation:"上海",toStation:"徐州"}
     * T 3;延迟查询
     * @see InfoBeanDelay
     *
     * <p>
     * 用户信息：固定值，使用构造方法即可
     * @see UserBean
     * user : {"mac":"","version":"","phone":"","device":"","system":"1"}
     */

    private T info;
    private UserBean user;

    public T getInfo() {
        return info;
    }

    public void setInfo(T info) {
        this.info = info;
    }

    public UserBean getUser() {
        return user;
    }

    public void setUser(UserBean user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "{" +
                "info=" + info +
                ", user=" + user +
                '}';
    }
}
