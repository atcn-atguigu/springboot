package com.atguigu.boot.conditionalOnProperties2;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class NotificationConfig {

    // 参考：https://www.baeldung.com/spring-conditionalonproperty

    // 根据properties文件里的内容（默认），接口NotificationSender的实现类为"emailNotification"
    @Bean(name = "emailNotification")
//    @ConditionalOnProperty(prefix = "notification", name = "service") // 属性值存在，则创建bean
    @ConditionalOnProperty(prefix = "notification", name = "service", havingValue = "email") // 属性值存在且值为"email"，则创建bean
    public NotificationSender notificationSender() {
        return new EmailNotification(); // default email
    }

    // 根据properties文件里的内容为"sms"，接口NotificationSender的实现类为"smsNotification"
    @Bean(name = "smsNotification")
    @ConditionalOnProperty(prefix = "notification", name = "service", havingValue = "sms") // Use case 2
    public NotificationSender notificationSender2() { // when value as "sms", use sms sender
        return new SmsNotification();
    }
}
