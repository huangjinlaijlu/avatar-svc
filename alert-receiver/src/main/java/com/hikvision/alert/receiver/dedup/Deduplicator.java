package com.hikvision.alert.receiver.dedup;

import java.util.concurrent.ConcurrentHashMap;

public class Deduplicator {

    private final long windowMillis;
    private final ConcurrentHashMap<String, Long> lastSeen = new ConcurrentHashMap<>();

    public Deduplicator(long windowSeconds) {
        this.windowMillis = windowSeconds * 1000;
    }

    public boolean isDuplicate(String fingerprint) {
        if (fingerprint == null || fingerprint.isEmpty()) {
            return false;
        }
        long now = System.currentTimeMillis();
        Long last = lastSeen.get(fingerprint);
        if (last != null && now - last < windowMillis) {
            return true;
        }
        lastSeen.put(fingerprint, now);
        return false;
    }
}
