package com.hikvision.alert.notify.provider;

public interface EmailProvider {
    void send(String title, String content, java.util.List<String> emails);
}
