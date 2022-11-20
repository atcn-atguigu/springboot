package com.atguigu.boot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class MainApplication {

    public static void main(String[] args) {

        //1、返回我们IOC容器
        ConfigurableApplicationContext context = SpringApplication.run(MainApplication.class, args);

        //2、查看并打印容器里面的所有组件bean名
        String[] names = context.getBeanDefinitionNames();
        for (String name : names) {
            System.out.println(name);
        }
    }
}