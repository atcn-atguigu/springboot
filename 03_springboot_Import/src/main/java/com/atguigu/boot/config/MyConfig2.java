package com.atguigu.boot.config;

import com.atguigu.boot.bean.Pet;
import com.atguigu.boot.bean.User;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MyConfig2 {

    // 返回bean的name为"user2"
    @Bean
    public User user2() {
        User user = new User("Ben2", 20);
        user.setPet(getTomcatPet2());   // setter配置依赖的Pet类，这里使用@Bean注解对应的方法获取beanId
        return user;
    }

    // 返回bean的name为"tomcat2"
    @Bean("tomcat2") // 这里的"tomcat"为显式定义的bean名称，否则方法名为bean名称
    public Pet getTomcatPet2() {
        return new Pet("tomcat2");
    }
}
