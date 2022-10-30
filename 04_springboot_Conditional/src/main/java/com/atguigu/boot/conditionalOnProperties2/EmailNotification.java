package com.atguigu.boot.conditionalOnProperties2;

public class EmailNotification implements NotificationSender {
    @Override
    public String send(String message) {
        return "Email Notification: " + message;
    }
}
