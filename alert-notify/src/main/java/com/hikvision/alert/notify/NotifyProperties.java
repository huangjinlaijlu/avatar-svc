package com.hikvision.alert.notify;

import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.HashMap;
import java.util.Map;

@ConfigurationProperties(prefix = "alert.notify")
public class NotifyProperties {

    private Webhook dingtalk = new Webhook();
    private Webhook wecom = new Webhook();
    private Webhook feishu = new Webhook();
    private Http httpEmail = new Http();
    private Http httpSms = new Http();
    private Http httpVoice = new Http();

    public Webhook getDingtalk() {
        return dingtalk;
    }

    public void setDingtalk(Webhook dingtalk) {
        this.dingtalk = dingtalk;
    }

    public Webhook getWecom() {
        return wecom;
    }

    public void setWecom(Webhook wecom) {
        this.wecom = wecom;
    }

    public Webhook getFeishu() {
        return feishu;
    }

    public void setFeishu(Webhook feishu) {
        this.feishu = feishu;
    }

    public Http getHttpEmail() {
        return httpEmail;
    }

    public void setHttpEmail(Http httpEmail) {
        this.httpEmail = httpEmail;
    }

    public Http getHttpSms() {
        return httpSms;
    }

    public void setHttpSms(Http httpSms) {
        this.httpSms = httpSms;
    }

    public Http getHttpVoice() {
        return httpVoice;
    }

    public void setHttpVoice(Http httpVoice) {
        this.httpVoice = httpVoice;
    }

    public static class Webhook {
        private String url;
        private String secret;

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getSecret() {
            return secret;
        }

        public void setSecret(String secret) {
            this.secret = secret;
        }
    }

    public static class Http {
        private String url;
        private String method = "POST";
        private Map<String, String> headers = new HashMap<>();

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getMethod() {
            return method;
        }

        public void setMethod(String method) {
            this.method = method;
        }

        public Map<String, String> getHeaders() {
            return headers;
        }

        public void setHeaders(Map<String, String> headers) {
            this.headers = headers;
        }
    }
}
