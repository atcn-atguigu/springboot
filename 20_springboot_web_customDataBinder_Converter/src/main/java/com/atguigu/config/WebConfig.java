package com.atguigu.config;

import com.atguigu.converter.String2DateConverter;
import com.atguigu.converter.String2LocalDateTimeConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


/**
 * 开启配置方式一：实现接口WebMvcConfigurer对应方法
 */
/*
@Configuration(proxyBeanMethods = false)
public class WebConfig implements WebMvcConfigurer {
    @Override
    public void addFormatters(FormatterRegistry registry) {
        registry.addConverter(new String2DateConverter());
        registry.addConverter(new String2LocalDateTimeConverter());
    }
}
*/


/**
 * 开启配置方式二：容器中放"webMvcConfigurer"容器对象并重写对应方法
 */
@Configuration(proxyBeanMethods = false)
public class WebConfig {
    @Bean
    public WebMvcConfigurer webMvcConfigurer() {
        // new WebMvcConfigurer() 接口带默认实现方法，只重写我们需要重写的方法
        return new WebMvcConfigurer() {

            // 将新定义的类型转换器注入到spring容器中
            @Override
            public void addFormatters(FormatterRegistry registry) {
                registry.addConverter(new String2DateConverter());
                registry.addConverter(new String2LocalDateTimeConverter());
            }
        };
    }
}
