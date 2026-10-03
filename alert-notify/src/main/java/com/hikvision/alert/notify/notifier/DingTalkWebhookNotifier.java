package com.hikvision.alert.notify.notifier;

import cn.hutool.core.codec.Base64;
import cn.hutool.core.util.StrUtil;
import cn.hutool.core.util.URLUtil;
import cn.hutool.http.HttpRequest;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.hikvision.alert.notify.NotifyProperties;
import com.hikvision.alert.notify.enums.NotifyChannel;
import com.hikvision.alert.notify.model.NotifyRequest;
import com.hikvision.alert.notify.model.NotifyResult;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;

public class DingTalkWebhookNotifier implements Notifier {

    private final NotifyProperties.Webhook config;

    public DingTalkWebhookNotifier(NotifyProperties.Webhook config) {
        this.config = config;
    }

    @Override
    public NotifyChannel channel() {
        return NotifyChannel.DINGTALK;
    }

    @Override
    public NotifyResult send(NotifyRequest request) {
        if (StrUtil.isBlank(config.getUrl())) {
            return NotifyResult.fail("dingtalk webhook not configured");
        }
        String url = config.getUrl();
        if (StrUtil.isNotBlank(config.getSecret())) {
            long timestamp = System.currentTimeMillis();
            String sign = sign(timestamp, config.getSecret());
            url += (url.contains("?") ? "&" : "?") + "timestamp=" + timestamp + "&sign=" + sign;
        }
        JSONObject body = new JSONObject();
        body.put("msgtype", "markdown");
        JSONObject markdown = new JSONObject();
        markdown.put("title", request.getTitle());
        markdown.put("text", "### " + request.getTitle() + "\n\n" + request.getContent());
        body.put("markdown", markdown);
        JSONObject at = new JSONObject();
        at.put("atMobiles", request.getAtMobiles());
        at.put("isAtAll", request.isAtAll());
        body.put("at", at);
        try {
            String resp = HttpRequest.post(url)
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

    private String sign(long timestamp, String secret) {
        try {
            String stringToSign = timestamp + "\n" + secret;
            Mac mac = Mac.getInstance("HmacSHA256");
            mac.init(new SecretKeySpec(secret.getBytes(StandardCharsets.UTF_8), "HmacSHA256"));
            byte[] hash = mac.doFinal(stringToSign.getBytes(StandardCharsets.UTF_8));
            return URLUtil.encode(Base64.encode(hash));
        } catch (Exception e) {
            throw new IllegalStateException(e);
        }
    }
}

