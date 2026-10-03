package com.hikvision.alert.notify.provider;

import cn.hutool.core.util.StrUtil;
import cn.hutool.http.HttpRequest;
import cn.hutool.json.JSONObject;
import com.hikvision.alert.notify.NotifyProperties;

import java.nio.charset.StandardCharsets;

public class HttpVoiceCallProvider implements VoiceCallProvider {

    private final NotifyProperties.Http config;

    public HttpVoiceCallProvider(NotifyProperties.Http config) {
        this.config = config;
    }

    @Override
    public void call(String phone, String content) {
        if (StrUtil.isBlank(config.getUrl())) {
            throw new IllegalStateException("voice url not configured");
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
            throw new IllegalStateException("voice call failed: " + resp);
        }
    }
}
