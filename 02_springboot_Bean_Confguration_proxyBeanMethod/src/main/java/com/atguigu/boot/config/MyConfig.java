package com.atguigu.boot.config;


import com.atguigu.boot.bean.Pet;
import com.atguigu.boot.bean.User;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


/**
 * @Configuration - 等同于一个Spring.xml配置文件，默认参数： proxyBeanMethods = true，表示使用bean代理模式，目的解决bean之间依赖问题，此时都是bean为单例。
 *  @Configuration(proxyBeanMethods = true) // true（默认）: 保证每个@Bean方法被调用多少次返回的组件都是单实例的
 *  @Configuration(proxyBeanMethods = false) // false: 每个@Bean方法被调用多少次返回的组件都是新创建的
 *  - 最佳实战
 * 	- 配置 类组件之间**无依赖关系**用Lite模式加速容器启动过程，减少判断
 * 	- 配置 类组件之间**有依赖关系**，方法会被调用得到之前单实例组件，用Full模式（默认）
 *
 * @Bean - 等同于xml文件下配置的bean，对应的方法名，为bean的名称，也可以显示定义名称如：@Bean("myBeanId")
 */
@Configuration(proxyBeanMethods = true) // true（默认）: 保证每个@Bean方法被调用多少次返回的组件都是单实例的
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
