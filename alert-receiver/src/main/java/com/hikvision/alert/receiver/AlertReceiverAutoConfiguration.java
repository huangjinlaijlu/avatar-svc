package com.hikvision.alert.receiver;

import com.hikvision.alert.receiver.adapter.PrometheusWebhookController;
import com.hikvision.alert.receiver.config.AlertRouteProperties;
import com.hikvision.alert.receiver.controller.AlertWebhookController;
import com.hikvision.alert.receiver.service.AlertDispatchService;
import com.hikvision.alert.notify.NotificationService;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties(AlertRouteProperties.class)
public class AlertReceiverAutoConfiguration {

    @Bean
    public AlertDispatchService alertDispatchService(NotificationService notificationService,
                                                     AlertRouteProperties properties) {
        return new AlertDispatchService(notificationService, properties);
    }

    @Bean
    public AlertWebhookController alertWebhookController(AlertDispatchService dispatchService) {
        return new AlertWebhookController(dispatchService);
    }

    @Bean
    public PrometheusWebhookController prometheusWebhookController(AlertDispatchService dispatchService) {
        return new PrometheusWebhookController(dispatchService);
    }
}
