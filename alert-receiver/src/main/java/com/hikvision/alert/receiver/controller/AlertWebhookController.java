package com.hikvision.alert.receiver.controller;

import com.hikvision.alert.core.Alert;
import com.hikvision.alert.receiver.service.AlertDispatchService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AlertWebhookController {

    private final AlertDispatchService dispatchService;

    public AlertWebhookController(AlertDispatchService dispatchService) {
        this.dispatchService = dispatchService;
    }

    @PostMapping("/alerts")
    public String receive(@RequestBody Alert alert) {
        return dispatchService.receive(alert);
    }
}
