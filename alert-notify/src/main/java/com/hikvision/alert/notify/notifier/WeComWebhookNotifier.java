package com.hikvision.alert.notify.notifier;

import cn.hutool.core.util.StrUtil;
import cn.hutool.http.HttpRequest;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.hikvision.alert.notify.NotifyProperties;
import com.hikvision.alert.notify.enums.NotifyChannel;
import com.hikvision.alert.notify.model.NotifyRequest;
import com.hikvision.alert.notify.model.NotifyResult;

import java.nio.charset.StandardCharsets;

public class WeComWebhookNotifier implements Notifier {

    private final NotifyProperties.Webhook config;

    public WeComWebhookNotifier(NotifyProperties.Webhook config) {
        this.config = config;
    }

    @Override
    public NotifyChannel channel() {
        return NotifyChannel.WECOM;
    }

    @Override
    public NotifyResult send(NotifyRequest request) {
        if (StrUtil.isBlank(config.getUrl())) {
            return NotifyResult.fail("wecom webhook not configured");
        }
        JSONObject body = new JSONObject();
        body.put("msgtype", "markdown");
        JSONObject markdown = new JSONObject();
        markdown.put("content", "### " + request.getTitle() + "\n\n" + request.getContent());
        body.put("markdown", markdown);
        try {
            String resp = HttpRequest.post(config.getUrl())
                    .body(body.toString(), StandardCharsets.UTF_8.name())
                    .timeout(5000).execute().body();
            JSONObject json = JSONUtil.parseObj(resp);
            return codeOf(json.get("errcode")) == 0 ? NotifyResult.ok() : NotifyResult.fail(resp);
        } catch (Exception e) {
            return NotifyResult.fail(e.getMessage());
        }
    }
    private static int codeOf(Object v) {
        return v instanceof Number ? ((Number) v).intValue() : -1;
    }
}
