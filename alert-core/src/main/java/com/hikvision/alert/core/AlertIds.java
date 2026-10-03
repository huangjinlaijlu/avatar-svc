package com.hikvision.alert.core;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;

public final class AlertIds {

    private AlertIds() {
    }

    public static String fingerprint(String source, String title, java.util.Map<String, String> labels) {
        StringBuilder sb = new StringBuilder();
        sb.append(source == null ? "" : source).append('|').append(title == null ? "" : title);
        if (labels != null) {
            labels.entrySet().stream().sorted(java.util.Map.Entry.comparingByKey())
                    .forEach(e -> sb.append('|').append(e.getKey()).append('=').append(e.getValue()));
        }
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] digest = md.digest(sb.toString().getBytes(StandardCharsets.UTF_8));
            StringBuilder hex = new StringBuilder();
            for (byte b : digest) {
                hex.append(String.format("%02x", b));
            }
            return hex.toString();
        } catch (Exception e) {
            throw new IllegalStateException(e);
        }
    }
}
