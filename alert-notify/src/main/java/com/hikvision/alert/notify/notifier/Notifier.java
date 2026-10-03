package com.hikvision.alert.notify.notifier;

import com.hikvision.alert.notify.enums.NotifyChannel;
import com.hikvision.alert.notify.model.NotifyRequest;
import com.hikvision.alert.notify.model.NotifyResult;

public interface Notifier {
    NotifyChannel channel();

    NotifyResult send(NotifyRequest request);
}
