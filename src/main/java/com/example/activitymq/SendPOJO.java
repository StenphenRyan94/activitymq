package com.example.activitymq;

import org.springframework.stereotype.Component;

import java.io.Serializable;


@Component
public class SendPOJO implements Serializable {

    private static final long serialVersionUID = -658250125732806493L;

    String imel;
    String serialNumber;
    String userName;

    public String getImel() {
        return imel;
    }

    public void setImel(String imel) {
        this.imel = imel;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }
}
