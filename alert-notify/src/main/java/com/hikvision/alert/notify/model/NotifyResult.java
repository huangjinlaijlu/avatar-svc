package com.hikvision.alert.notify.model;

public class NotifyResult {
    private boolean success;
    private String message;

    public NotifyResult() {
    }

    public NotifyResult(boolean success, String message) {
        this.success = success;
        this.message = message;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public static NotifyResult ok() {
        return new NotifyResult(true, "ok");
    }

    public static NotifyResult fail(String msg) {
        return new NotifyResult(false, msg);
    }
}
