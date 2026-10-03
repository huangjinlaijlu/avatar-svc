package com.hikvision.alert.notify.provider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;




public class LogSmsProvider implements SmsProvider {
    private static final Logger log = LoggerFactory.getLogger(LogSmsProvider.class);

    @Override
    public void send(String phone, String content) {
        log.info("SMS to {}: {}", phone, content);
    }
}
