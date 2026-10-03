package com.hikvision.alert.notify.provider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;



import java.util.List;


public class LogEmailProvider implements EmailProvider {
    private static final Logger log = LoggerFactory.getLogger(LogEmailProvider.class);

    @Override
    public void send(String title, String content, List<String> emails) {
        log.info("EMAIL to {}: {} - {}", emails, title, content);
    }
}
