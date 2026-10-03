package com.hikvision.alert.notify.notifier;

import com.hikvision.alert.notify.enums.NotifyChannel;
import com.hikvision.alert.notify.model.NotifyRequest;
import com.hikvision.alert.notify.model.NotifyResult;
import com.hikvision.alert.notify.provider.VoiceCallProvider;

import java.util.List;

public class VoiceCallNotifier implements Notifier {

    private final VoiceCallProvider provider;

    public VoiceCallNotifier(VoiceCallProvider provider) {
        this.provider = provider;
    }

    @Override
    public NotifyChannel channel() {
        return NotifyChannel.VOICE;
    }

    @Override
    public NotifyResult send(NotifyRequest request) {
        List<String> phones = request.getPhones();
        if (phones == null || phones.isEmpty()) {
            return NotifyResult.fail("no voice receivers");
        }
        try {
            for (String phone : phones) {
                provider.call(phone, request.getContent());
            }
            return NotifyResult.ok();
        } catch (Exception e) {
            return NotifyResult.fail(e.getMessage());
        }
    }
}
