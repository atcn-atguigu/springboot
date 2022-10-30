package com.atguigu.boot.conditionalOnProperties2;

public class SmsNotification implements NotificationSender {
    @Override
    public String send(String message) {
        return "SMS Notification: " + message;
    }
}
