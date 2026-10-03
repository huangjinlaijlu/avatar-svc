package com.hikvision.alert.notify.provider;

import cn.hutool.core.util.StrUtil;
import cn.hutool.http.HttpRequest;
import cn.hutool.json.JSONObject;
import com.hikvision.alert.notify.NotifyProperties;

import java.nio.charset.StandardCharsets;

public class HttpSmsProvider implements SmsProvider {

    private final NotifyProperties.Http config;

    public HttpSmsProvider(NotifyProperties.Http config) {
        this.config = config;
    }

    @Override
    public void send(String phone, String content) {
        if (StrUtil.isBlank(config.getUrl())) {
            throw new IllegalStateException("sms url not configured");
        }
        JSONObject body = new JSONObject();
        body.put("phone", phone);
        body.put("content", content);
        HttpRequest req = HttpRequest.post(config.getUrl());
        if (config.getHeaders() != null) {
            config.getHeaders().forEach(req::header);
        }
        String resp = req.body(body.toString(), StandardCharsets.UTF_8.name()).timeout(5000).execute().body();
        if (resp == null || resp.toLowerCase().contains("\"code\":500")) {
            throw new IllegalStateException("sms send failed: " + resp);
        }
    }
}
