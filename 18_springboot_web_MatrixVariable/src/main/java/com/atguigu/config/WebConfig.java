package com.atguigu.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.PathMatchConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.util.UrlPathHelper;

@Configuration(proxyBeanMethods = false)
/**
 * 开启矩阵变量的配置方式一：继承WebMvcConfigurer，重写configurePathMatch()方法 - setRemoveSemicolonContent(false)
 */
/*
public class WebConfig implements WebMvcConfigurer {
    @Override
    public void configurePathMatch(PathMatchConfigurer configurer) {
        UrlPathHelper urlPathHelper = new UrlPathHelper();
        // SpringBoot默认配置是移除URL中的分号";"后面的内容，这里我们因为测试使用矩阵变量@MatrixVariable，所以要把配置改为false
        urlPathHelper.setRemoveSemicolonContent(false);
        configurer.setUrlPathHelper(urlPathHelper);
    }
}
*/
/**
 * 开启矩阵变量的配置方式二：Spring容器中修改容器对象里的配置方法
 */
public class WebConfig {
    // 容器中放"webMvcConfigurer"容器对象
    @Bean
    public WebMvcConfigurer webMvcConfigurer() {
        // new WebMvcConfigurer() 接口带默认实现方法，只重写我们需要重写的方法
        return new WebMvcConfigurer() {
            @Override
            public void configurePathMatch(PathMatchConfigurer configurer) {
                UrlPathHelper urlPathHelper = new UrlPathHelper();
                // SpringBoot默认配置是移除URL中的分号";"后面的内容，这里我们因为测试使用矩阵变量@MatrixVariable，所以要把配置改为false
                urlPathHelper.setRemoveSemicolonContent(false);
                configurer.setUrlPathHelper(urlPathHelper);
            }
        };
    }
}
