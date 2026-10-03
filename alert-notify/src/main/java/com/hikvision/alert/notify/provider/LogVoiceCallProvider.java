package com.hikvision.alert.notify.provider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;




public class LogVoiceCallProvider implements VoiceCallProvider {
    private static final Logger log = LoggerFactory.getLogger(LogVoiceCallProvider.class);

    @Override
    public void call(String phone, String content) {
        log.info("VOICE call to {}: {}", phone, content);
    }
}
