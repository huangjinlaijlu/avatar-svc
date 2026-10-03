package com.hikvision.alert.notify;

import com.hikvision.alert.notify.enums.NotifyChannel;
import com.hikvision.alert.notify.model.NotifyRequest;
import com.hikvision.alert.notify.model.NotifyResult;
import com.hikvision.alert.notify.notifier.Notifier;

import java.util.EnumMap;
import java.util.List;
import java.util.Map;

public class NotificationService {

    private final Map<NotifyChannel, Notifier> registry = new EnumMap<>(NotifyChannel.class);

    public NotificationService(List<Notifier> notifiers) {
        if (notifiers != null) {
            for (Notifier n : notifiers) {
                registry.put(n.channel(), n);
            }
        }
    }

    public NotifyResult send(NotifyChannel channel, String title, String content) {
        return send(channel, NotifyRequest.builder().title(title).content(content).build());
    }

    public NotifyResult send(NotifyChannel channel, NotifyRequest request) {
        Notifier notifier = registry.get(channel);
        if (notifier == null) {
            return NotifyResult.fail("channel not supported: " + channel);
        }
        return notifier.send(request);
    }
}
