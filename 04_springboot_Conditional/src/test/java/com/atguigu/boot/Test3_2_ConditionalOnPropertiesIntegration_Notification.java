package com.atguigu.boot;

import com.atguigu.boot.conditionalOnProperties2.EmailNotification;
import com.atguigu.boot.conditionalOnProperties2.NotificationConfig;
import com.atguigu.boot.conditionalOnProperties2.NotificationSender;
import com.atguigu.boot.conditionalOnProperties2.SmsNotification;
import org.junit.Test;
import org.springframework.boot.test.context.runner.ApplicationContextRunner;

import static org.assertj.core.api.Assertions.assertThat;

public class Test3_2_ConditionalOnPropertiesIntegration_Notification {

    private final ApplicationContextRunner contextRunner = new ApplicationContextRunner();

    @Test
    public void whenValueSetToEmail_thenCreateEmailNotification() {
        this.contextRunner.withPropertyValues("notification.service=email")
                .withUserConfiguration(NotificationConfig.class)
                .run(context -> {
                    assertThat(context).hasBean("emailNotification");
                    NotificationSender notificationSender = context.getBean(EmailNotification.class);
                    assertThat(notificationSender.send("Hello From Baeldung!")).isEqualTo("Email Notification: Hello From Baeldung!");
                    assertThat(context).doesNotHaveBean("smsNotification");
                });
    }

    @Test
    public void whenValueSetToSMS_thenCreateSMSNotification() {
        this.contextRunner.withPropertyValues("notification.service=sms")
                .withUserConfiguration(NotificationConfig.class)
                .run(context -> {
                    assertThat(context).hasBean("smsNotification");
                    NotificationSender notificationSender = context.getBean(SmsNotification.class);
                    assertThat(notificationSender.send("Hello From Baeldung!")).isEqualTo("SMS Notification: Hello From Baeldung!");
                    assertThat(context).doesNotHaveBean("emailNotification");
                });
    }
}
