package com.hikvision.alert.receiver.service;

import com.hikvision.alert.core.Alert;
import com.hikvision.alert.core.AlertIds;
import com.hikvision.alert.notify.NotificationService;
import com.hikvision.alert.notify.enums.NotifyChannel;
import com.hikvision.alert.notify.model.NotifyRequest;
import com.hikvision.alert.notify.model.NotifyResult;
import com.hikvision.alert.receiver.config.AlertRouteProperties;
import com.hikvision.alert.receiver.dedup.Deduplicator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class AlertDispatchService {

    private static final Logger log = LoggerFactory.getLogger(AlertDispatchService.class);

    private final NotificationService notificationService;
    private final AlertRouteProperties properties;
    private final Deduplicator deduplicator;

    public AlertDispatchService(NotificationService notificationService, AlertRouteProperties properties) {
        this.notificationService = notificationService;
        this.properties = properties;
        this.deduplicator = new Deduplicator(properties.getDedupSeconds());
    }

    public String receive(Alert alert) {
        if (alert == null) {
            return "empty alert";
        }
        if (alert.getFingerprint() == null || alert.getFingerprint().isEmpty()) {
            alert.setFingerprint(AlertIds.fingerprint(alert.getSource(), alert.getTitle(), alert.getLabels()));
        }
        if (deduplicator.isDuplicate(alert.getFingerprint())) {
            return "duplicate: " + alert.getFingerprint();
        }
        List<AlertRouteProperties.RouteRule> rules = properties.getRules();
        if (rules == null || rules.isEmpty()) {
            log.warn("no route rules configured, skip alert: {}", alert.getTitle());
            return "no route";
        }
        boolean matched = false;
        for (AlertRouteProperties.RouteRule rule : rules) {
            if (!match(rule, alert)) {
                continue;
            }
            matched = true;
            for (NotifyChannel channel : rule.getChannels()) {
                NotifyRequest req = NotifyRequest.builder()
                        .title(alert.getTitle())
                        .content(alert.getContent())
                        .phones(rule.getPhones())
                        .emails(rule.getEmails())
                        .build();
                NotifyResult result = notificationService.send(channel, req);
                log.info("dispatch alert [{}] to {} -> {}", alert.getTitle(), channel, result.getMessage());
            }
        }
        return matched ? "dispatched" : "no matched rule";
    }

    private boolean match(AlertRouteProperties.RouteRule rule, Alert alert) {
        return rule.getSeverity() == null
                || rule.getSeverity().isEmpty()
                || rule.getSeverity().equalsIgnoreCase(alert.getSeverity() == null ? "" : alert.getSeverity().name());
    }
}
