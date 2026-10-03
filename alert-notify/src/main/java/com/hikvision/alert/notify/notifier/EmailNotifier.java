package com.hikvision.alert.notify.notifier;

import com.hikvision.alert.notify.enums.NotifyChannel;
import com.hikvision.alert.notify.model.NotifyRequest;
import com.hikvision.alert.notify.model.NotifyResult;
import com.hikvision.alert.notify.provider.EmailProvider;

import java.util.List;

public class EmailNotifier implements Notifier {

    private final EmailProvider provider;

    public EmailNotifier(EmailProvider provider) {
        this.provider = provider;
    }

    @Override
    public NotifyChannel channel() {
        return NotifyChannel.EMAIL;
    }

    @Override
    public NotifyResult send(NotifyRequest request) {
        List<String> emails = request.getEmails();
        if (emails == null || emails.isEmpty()) {
            return NotifyResult.fail("no email receivers");
        }
        try {
            provider.send(request.getTitle(), request.getContent(), emails);
            return NotifyResult.ok();
        } catch (Exception e) {
            return NotifyResult.fail(e.getMessage());
        }
    }
}
