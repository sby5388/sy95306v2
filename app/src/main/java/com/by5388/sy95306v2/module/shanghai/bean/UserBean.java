package com.by5388.sy95306v2.module.shanghai.bean;

/**
 * @author by5388
 * @date 2018/8/8 16:30
 */
public class UserBean {
    UserBean() {
        this.mac = "";
        this.version = "";
        this.phone = "";
        this.device = "";
        this.system = "1";
    }

    /**
     * mac :
     * version :
     * phone :
     * device :
     * system : 1
     */


    private String mac;
    private String version;
    private String phone;
    private String device;
    private String system;

    public String getMac() {
        return mac;
    }

    public void setMac(String mac) {
        this.mac = mac;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getDevice() {
        return device;
    }

    public void setDevice(String device) {
        this.device = device;
    }

    public String getSystem() {
        return system;
    }

    public void setSystem(String system) {
        this.system = system;
    }
}
