package com.hikvision.alert.notify.provider;

public interface SmsProvider {
    void send(String phone, String content);
}
