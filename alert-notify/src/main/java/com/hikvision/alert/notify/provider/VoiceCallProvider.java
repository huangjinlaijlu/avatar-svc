package com.hikvision.alert.notify.provider;

public interface VoiceCallProvider {
    void call(String phone, String content);
}
