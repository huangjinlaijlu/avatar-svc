package com.hikvision.alert.receiver.config;

import com.hikvision.alert.notify.enums.NotifyChannel;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.ArrayList;
import java.util.List;

@ConfigurationProperties(prefix = "alert.receiver")
public class AlertRouteProperties {

    private long dedupSeconds = 60;
    private List<RouteRule> rules = new ArrayList<>();

    public long getDedupSeconds() {
        return dedupSeconds;
    }

    public void setDedupSeconds(long dedupSeconds) {
        this.dedupSeconds = dedupSeconds;
    }

    public List<RouteRule> getRules() {
        return rules;
    }

    public void setRules(List<RouteRule> rules) {
        this.rules = rules;
    }

    public static class RouteRule {
        private String severity;
        private List<NotifyChannel> channels = new ArrayList<>();
        private List<String> phones = new ArrayList<>();
        private List<String> emails = new ArrayList<>();

        public String getSeverity() {
            return severity;
        }

        public void setSeverity(String severity) {
            this.severity = severity;
        }

        public List<NotifyChannel> getChannels() {
            return channels;
        }

        public void setChannels(List<NotifyChannel> channels) {
            this.channels = channels;
        }

        public List<String> getPhones() {
            return phones;
        }

        public void setPhones(List<String> phones) {
            this.phones = phones;
        }

        public List<String> getEmails() {
            return emails;
        }

        public void setEmails(List<String> emails) {
            this.emails = emails;
        }
    }
}
