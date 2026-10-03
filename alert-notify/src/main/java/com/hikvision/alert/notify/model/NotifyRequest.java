package com.hikvision.alert.notify.model;

import java.util.ArrayList;
import java.util.List;

public class NotifyRequest {
    private String title;
    private String content;
    private List<String> phones = new ArrayList<>();
    private List<String> emails = new ArrayList<>();
    private List<String> atMobiles = new ArrayList<>();
    private boolean atAll = false;

    public static Builder builder() {
        return new Builder();
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public List<String> getPhones() {
        return phones;
    }

    public void setPhones(List<String> phones) {
        this.phones = phones;
    }

    public List<String> getEmails() {
        return emails;
    }

    public void setEmails(List<String> emails) {
        this.emails = emails;
    }

    public List<String> getAtMobiles() {
        return atMobiles;
    }

    public void setAtMobiles(List<String> atMobiles) {
        this.atMobiles = atMobiles;
    }

    public boolean isAtAll() {
        return atAll;
    }

    public void setAtAll(boolean atAll) {
        this.atAll = atAll;
    }

    public static class Builder {
        private final NotifyRequest request = new NotifyRequest();

        public Builder title(String title) {
            request.title = title;
            return this;
        }

        public Builder content(String content) {
            request.content = content;
            return this;
        }

        public Builder phones(List<String> phones) {
            request.phones = phones == null ? new ArrayList<String>() : phones;
            return this;
        }

        public Builder emails(List<String> emails) {
            request.emails = emails == null ? new ArrayList<String>() : emails;
            return this;
        }

        public Builder atMobiles(List<String> atMobiles) {
            request.atMobiles = atMobiles == null ? new ArrayList<String>() : atMobiles;
            return this;
        }

        public Builder atAll(boolean atAll) {
            request.atAll = atAll;
            return this;
        }

        public NotifyRequest build() {
            return request;
        }
    }
}
