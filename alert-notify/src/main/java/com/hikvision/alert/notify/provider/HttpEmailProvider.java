package com.hikvision.alert.notify.provider;

import cn.hutool.core.util.StrUtil;
import cn.hutool.http.HttpRequest;
import cn.hutool.json.JSONObject;
import com.hikvision.alert.notify.NotifyProperties;

import java.nio.charset.StandardCharsets;
import java.util.List;

public class HttpEmailProvider implements EmailProvider {

    private final NotifyProperties.Http config;

    public HttpEmailProvider(NotifyProperties.Http config) {
        this.config = config;
    }

    @Override
    public void send(String title, String content, List<String> emails) {
        if (StrUtil.isBlank(config.getUrl())) {
            throw new IllegalStateException("email url not configured");
        }
        JSONObject body = new JSONObject();
        body.put("title", title);
        body.put("content", content);
        body.put("emails", emails);
        HttpRequest req = HttpRequest.post(config.getUrl());
        if (config.getHeaders() != null) {
            config.getHeaders().forEach(req::header);
        }
        String resp = req.body(body.toString(), StandardCharsets.UTF_8.name()).timeout(5000).execute().body();
        if (resp == null || resp.toLowerCase().contains("\"code\":500")) {
            throw new IllegalStateException("email send failed: " + resp);
        }
    }
}
