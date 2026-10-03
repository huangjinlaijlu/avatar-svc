package com.hikvision.alert.notify.notifier;

import cn.hutool.core.codec.Base64;
import cn.hutool.core.util.StrUtil;
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

public class FeishuWebhookNotifier implements Notifier {

    private final NotifyProperties.Webhook config;

    public FeishuWebhookNotifier(NotifyProperties.Webhook config) {
        this.config = config;
    }

    @Override
    public NotifyChannel channel() {
        return NotifyChannel.FEISHU;
    }

    @Override
    public NotifyResult send(NotifyRequest request) {
        if (StrUtil.isBlank(config.getUrl())) {
            return NotifyResult.fail("feishu webhook not configured");
        }
        JSONObject body = new JSONObject();
        if (StrUtil.isNotBlank(config.getSecret())) {
            long timestamp = System.currentTimeMillis() / 1000;
            body.put("timestamp", String.valueOf(timestamp));
            body.put("sign", sign(timestamp, config.getSecret()));
        }
        body.put("msg_type", "text");
        JSONObject content = new JSONObject();
        content.put("text", request.getTitle() + "\n" + request.getContent());
        body.put("content", content);
        try {
            String resp = HttpRequest.post(config.getUrl())
                    .body(body.toString(), StandardCharsets.UTF_8.name())
                    .timeout(5000).execute().body();
            JSONObject json = JSONUtil.parseObj(resp);
            return codeOf(json.get("code")) == 0 ? NotifyResult.ok() : NotifyResult.fail(resp);
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
            return Base64.encode(mac.doFinal(stringToSign.getBytes(StandardCharsets.UTF_8)));
        } catch (Exception e) {
            throw new IllegalStateException(e);
        }
    }
}

