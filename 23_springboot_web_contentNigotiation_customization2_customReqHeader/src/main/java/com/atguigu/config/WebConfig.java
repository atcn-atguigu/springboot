package com.atguigu.config;

import com.atguigu.converter.GuiguMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;


/**
 * 开启配置方式一：实现接口WebMvcConfigurer对应方法
 */
/*
@Configuration(proxyBeanMethods = false)
public class WebConfig implements WebMvcConfigurer {
    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        // 【请求头方式的内容协商拓展】添加自定义的Converter到列表中
        converters.add(new GuiguMessageConverter());
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

            // 将新定义的内容协商消息转换器注入到spring容器中
            @Override
            public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
                // 【请求头方式的内容协商拓展】添加自定义的Converter到列表中
                converters.add(new GuiguMessageConverter());
            }
        };
    }
}
