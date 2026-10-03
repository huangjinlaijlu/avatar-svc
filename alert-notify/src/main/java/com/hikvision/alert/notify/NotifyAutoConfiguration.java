package com.hikvision.alert.notify;

import com.hikvision.alert.notify.notifier.*;
import com.hikvision.alert.notify.provider.*;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;
import java.util.List;

@Configuration
@EnableConfigurationProperties(NotifyProperties.class)
public class NotifyAutoConfiguration {

    @Bean
    public DingTalkWebhookNotifier dingTalkWebhookNotifier(NotifyProperties props) {
        return new DingTalkWebhookNotifier(props.getDingtalk());
    }

    @Bean
    public WeComWebhookNotifier weComWebhookNotifier(NotifyProperties props) {
        return new WeComWebhookNotifier(props.getWecom());
    }

    @Bean
    public FeishuWebhookNotifier feishuWebhookNotifier(NotifyProperties props) {
        return new FeishuWebhookNotifier(props.getFeishu());
    }

    @Bean
    public EmailProvider emailProvider(NotifyProperties props) {
        return props.getHttpEmail() != null && props.getHttpEmail().getUrl() != null
                ? new HttpEmailProvider(props.getHttpEmail()) : new LogEmailProvider();
    }

    @Bean
    public EmailNotifier emailNotifier(EmailProvider provider) {
        return new EmailNotifier(provider);
    }

    @Bean
    public SmsProvider smsProvider(NotifyProperties props) {
        return props.getHttpSms() != null && props.getHttpSms().getUrl() != null
                ? new HttpSmsProvider(props.getHttpSms()) : new LogSmsProvider();
    }

    @Bean
    public VoiceCallProvider voiceCallProvider(NotifyProperties props) {
        return props.getHttpVoice() != null && props.getHttpVoice().getUrl() != null
                ? new HttpVoiceCallProvider(props.getHttpVoice()) : new LogVoiceCallProvider();
    }

    @Bean
    public SmsNotifier smsNotifier(SmsProvider provider) {
        return new SmsNotifier(provider);
    }

    @Bean
    public VoiceCallNotifier voiceCallNotifier(VoiceCallProvider provider) {
        return new VoiceCallNotifier(provider);
    }

    @Bean
    public NotificationService notificationService(DingTalkWebhookNotifier d, WeComWebhookNotifier w,
                                                   FeishuWebhookNotifier f, EmailNotifier e,
                                                   SmsNotifier s, VoiceCallNotifier v) {
        List<Notifier> list = Arrays.<Notifier>asList(d, w, f, e, s, v);
        return new NotificationService(list);
    }
}
