package com.hikvision.alert.notify.notifier;

import com.hikvision.alert.notify.enums.NotifyChannel;
import com.hikvision.alert.notify.model.NotifyRequest;
import com.hikvision.alert.notify.model.NotifyResult;
import com.hikvision.alert.notify.provider.SmsProvider;

import java.util.List;

public class SmsNotifier implements Notifier {

    private final SmsProvider provider;

    public SmsNotifier(SmsProvider provider) {
        this.provider = provider;
    }

    @Override
    public NotifyChannel channel() {
        return NotifyChannel.SMS;
    }

    @Override
    public NotifyResult send(NotifyRequest request) {
        List<String> phones = request.getPhones();
        if (phones == null || phones.isEmpty()) {
            return NotifyResult.fail("no sms receivers");
        }
        try {
            for (String phone : phones) {
                provider.send(phone, request.getContent());
            }
            return NotifyResult.ok();
        } catch (Exception e) {
            return NotifyResult.fail(e.getMessage());
        }
    }
}
