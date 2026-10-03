package com.hikvision.alert.receiver.adapter;

import com.hikvision.alert.core.Alert;
import com.hikvision.alert.core.Severity;
import com.hikvision.alert.receiver.service.AlertDispatchService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
public class PrometheusWebhookController {

    private final AlertDispatchService dispatchService;

    public PrometheusWebhookController(AlertDispatchService dispatchService) {
        this.dispatchService = dispatchService;
    }

    @PostMapping("/alerts/prometheus")
    public String receive(@RequestBody Map<String, Object> payload) {
        Object rawAlerts = payload.get("alerts");
        if (!(rawAlerts instanceof List)) {
            return "no alerts";
        }
        int accepted = 0;
        for (Object obj : (List<?>) rawAlerts) {
            if (!(obj instanceof Map)) {
                continue;
            }
            Map<?, ?> item = (Map<?, ?>) obj;
            Map<?, ?> labels = item.get("labels") instanceof Map ? (Map<?, ?>) item.get("labels") : null;
            Map<?, ?> annotations = item.get("annotations") instanceof Map ? (Map<?, ?>) item.get("annotations") : null;

            Alert alert = new Alert();
            alert.setSource("prometheus");
            String name = labels != null && labels.get("alertname") != null ? labels.get("alertname").toString() : "unknown";
            alert.setTitle(name);
            alert.setContent(annotations != null && annotations.get("description") != null
                    ? annotations.get("description").toString()
                    : (annotations != null && annotations.get("summary") != null ? annotations.get("summary").toString() : ""));
            alert.setSeverity(parseSeverity(labels != null ? labels.get("severity") : null));
            if (labels != null) {
                for (Map.Entry<?, ?> e : labels.entrySet()) {
                    alert.getLabels().put(String.valueOf(e.getKey()), String.valueOf(e.getValue()));
                }
            }
            dispatchService.receive(alert);
            accepted++;
        }
        return "accepted: " + accepted;
    }

    private Severity parseSeverity(Object value) {
        if (value == null) {
            return Severity.P2;
        }
        try {
            return Severity.valueOf(value.toString().toUpperCase());
        } catch (Exception e) {
            return Severity.P2;
        }
    }
}
