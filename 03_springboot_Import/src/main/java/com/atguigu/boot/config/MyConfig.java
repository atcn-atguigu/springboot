package com.atguigu.boot.config;

import com.atguigu.boot.bean.Pet;
import com.atguigu.boot.bean.User;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * @Import({MyConfig2.class, User.class}) - 可用于任意一个组件上或配置类上
 *  给容器中创建出对应类型的组件对象，默认组建对象名字就是全类名
 */
@Import({MyConfig2.class, User.class})  // Import使用无参构造创建bean对象，其bean名称为全类名，也可以用于引入另外一个配置类
@Configuration
public class MyConfig {

    // 返回bean的name为"user"
    @Bean
    public User user() {
        User user = new User("Ben", 20);
        user.setPet(getTomcatPet());   // setter配置依赖的Pet类，这里使用@Bean注解对应的方法获取beanId
        return user;
    }

    // 返回bean的name为"tomcat"
    @Bean("tomcat") // 这里的"tomcat"为显式定义的bean名称，否则方法名为bean名称
    public Pet getTomcatPet() {
        return new Pet("tomcat");
    }
}
