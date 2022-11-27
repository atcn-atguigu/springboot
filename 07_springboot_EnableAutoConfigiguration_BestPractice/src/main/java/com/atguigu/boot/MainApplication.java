package com.atguigu.boot;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
@Slf4j
public class MainApplication {

    public static void main(String[] args) {

        //1、返回我们IOC容器
        ConfigurableApplicationContext context = SpringApplication.run(MainApplication.class, args);

        //2、查看并打印容器里面的所有组件bean名
        String[] names = context.getBeanDefinitionNames();
        log.info("=========== start: 查看并打印容器里面的所有组件bean名 ============");
        for (String name : names) {
            System.out.println(name);
        }
        log.info("=========== end: 查看并打印容器里面的所有组件bean名 ============");
    }
}