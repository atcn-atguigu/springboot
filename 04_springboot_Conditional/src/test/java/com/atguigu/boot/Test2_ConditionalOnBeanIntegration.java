package com.atguigu.boot;

import org.junit.jupiter.api.Test;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.test.context.runner.ApplicationContextRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import static org.assertj.core.api.Assertions.assertThat;

public class Test2_ConditionalOnBeanIntegration {

    private final ApplicationContextRunner contextRunner = new ApplicationContextRunner();

    @Configuration
    protected static class BasicConfiguration {
        @Bean
        public String created() {
            return "This is always created";
        }
    }

    @Configuration
    @ConditionalOnBean(name = "created")    // 如果名为'created' bean对象存在，则创建名为'createOnBean' bean对象
    protected static class ConditionalOnBeanConfiguration {
        @Bean
        public String createOnBean() {
            return "This is created when bean (name=created) is present";
        }
    }

    @Configuration
    @ConditionalOnMissingBean(name = "created") // 如果名为'created' bean对象不存在，则创建名为'createOnMissingBean' bean对象
    protected static class ConditionalOnMissingBeanConfiguration {
        @Bean
        public String createOnMissingBean() {
            return "This is created when bean (name=created) is missing";
        }
    }

    @Test
    public void whenDependentBeanIsPresent_thenConditionalBeanCreated() {
        this.contextRunner.withUserConfiguration(BasicConfiguration.class, ConditionalOnBeanConfiguration.class)
            .run((context) -> {
                assertThat(context).hasBean("created");
                assertThat(context).getBean("created")
                    .isEqualTo("This is always created");
                assertThat(context).hasBean("createOnBean");
                assertThat(context).getBean("createOnBean")
                    .isEqualTo("This is created when bean (name=created) is present");
            });
    }

    @Test
    public void whenDependentBeanIsPresent_thenConditionalMissingBeanIgnored() {
        this.contextRunner.withUserConfiguration(BasicConfiguration.class, ConditionalOnMissingBeanConfiguration.class)
            .run((context) -> {
                assertThat(context).hasBean("created");
                assertThat(context).getBean("created")
                    .isEqualTo("This is always created");
                assertThat(context).doesNotHaveBean("createOnMissingBean");
            });
    }

    @Test
    public void whenDependentBeanIsNotPresent_thenConditionalMissingBeanCreated() {
        this.contextRunner.withUserConfiguration(ConditionalOnMissingBeanConfiguration.class)
            .run((context) -> {
                assertThat(context).hasBean("createOnMissingBean");
                assertThat(context).getBean("createOnMissingBean")
                    .isEqualTo("This is created when bean (name=created) is missing");
                assertThat(context).doesNotHaveBean("created");
            });
    }
}
