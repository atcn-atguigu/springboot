package com.atguigu.boot;

import com.atguigu.boot.conditionalOnProperties.CustomService;
import com.atguigu.boot.conditionalOnProperties.DefaultService;
import com.atguigu.boot.conditionalOnProperties.SimpleService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.test.context.runner.ApplicationContextRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.TestPropertySource;

import static org.assertj.core.api.Assertions.assertThat;



public class Test3_1_ConditionalOnPropertyIntegration_Service {

    private final ApplicationContextRunner contextRunner = new ApplicationContextRunner();

    @Configuration
    @TestPropertySource("classpath:ConditionalOnPropertyTest.properties")   // 类路径加载相关对应测试的properties文件（可不加）
    protected static class SimpleServiceConfiguration {

        @Bean
        @ConditionalOnProperty(name = "com.baeldung.service", havingValue = "default") // 基于properties文件里的属性值为"default"来创建接口SimpleService的实现类
        @ConditionalOnMissingBean // 如果context没有，且上面条件也满足，则创建
        public DefaultService defaultService() {
            return new DefaultService();
        }

        @Bean
        @ConditionalOnProperty(name = "com.baeldung.service", havingValue = "custom") // 基于properties文件里的属性值为"custom"来创建接口SimpleService的实现类
        @ConditionalOnMissingBean // 如果context没有，且上面条件也满足，则创建
        public CustomService customService() {
            return new CustomService();
        }
    }

    @Test
    public void whenGivenCustomPropertyValue_thenCustomServiceCreated() {
        // - withPropertyValues() - 添加context里properties文件的属性值
        this.contextRunner.withPropertyValues("com.baeldung.service=custom")
            .withUserConfiguration(SimpleServiceConfiguration.class)
            .run(context -> {
                assertThat(context).hasBean("customService");
                SimpleService simpleService = context.getBean(CustomService.class);
                assertThat(simpleService.serve()).isEqualTo("Custom Service");
                assertThat(context).doesNotHaveBean("defaultService");
            });
    }

    @Test
    public void whenGivenDefaultPropertyValue_thenDefaultServiceCreated() {
        this.contextRunner.withPropertyValues("com.baeldung.service=default")
            .withUserConfiguration(SimpleServiceConfiguration.class)
            .run(context -> {
                assertThat(context).hasBean("defaultService");
                SimpleService simpleService = context.getBean(DefaultService.class);
                assertThat(simpleService.serve()).isEqualTo("Default Service");
                assertThat(context).doesNotHaveBean("customService");
            });
    }
}
