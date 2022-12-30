package com.atguigu.config;

import com.atguigu.converter.GuiguMessageConverter;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.accept.ParameterContentNegotiationStrategy;
import org.springframework.web.servlet.config.annotation.ContentNegotiationConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Configuration(proxyBeanMethods = false)
public class WebConfig implements WebMvcConfigurer {
    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        // 【请求头方式的内容协商拓展】添加自定义的Converter到列表中
        converters.add(new GuiguMessageConverter());
    }

    @Override
    public void configureContentNegotiation(ContentNegotiationConfigurer configurer) {
        // 【请求参数方式的内容协商拓展】
        // 配置format请求参数支持的媒体类（format key, format mapped MediaType请求头）
        Map<String, MediaType> mediaTypes = new HashMap<>();
        // SpringBoot默认支持的配置，因为我们重写，所以需要加进去
        mediaTypes.put("json", MediaType.APPLICATION_JSON);
        mediaTypes.put("xml", MediaType.APPLICATION_XML);
        // 新增的自定义请求参数支持的类型： ?format=guigu, 对应映射的请求头为 "Accept： application/x-guigu"
        mediaTypes.put("guigu", MediaType.parseMediaType("application/x-guigu"));
        // 指定支持解析哪些参数对应哪些媒体类型
        ParameterContentNegotiationStrategy strategy = new ParameterContentNegotiationStrategy(mediaTypes);
        configurer.strategies(Arrays.asList(strategy));
    }
}
