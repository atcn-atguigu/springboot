package com.atguigu.boot;

import org.junit.jupiter.api.Test;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingClass;
import org.springframework.boot.test.context.FilteredClassLoader;
import org.springframework.boot.test.context.runner.ApplicationContextRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import static org.assertj.core.api.Assertions.assertThat;

// 参考文档：https://blog.csdn.net/Developlee/article/details/100691983
public class Test1_ConditionalOnClassIntegration {

    /**
     * ApplicationContextRunner：简化自动配置测试
     * ApplicationContextRunner是一个实用程序类，它运行ApplicationContext并提供AssertJ样式断言。最好用作测试类中的字段以便共享配置，然后我们在每个测试中进行自定义：
     *
     * 知识点：
     * - AssertJ样式断言 - ApplicationContextAssert
     *  - hasBean()
     *  - doesNotHaveBean()
     * - withUserConfiguration() - ApplicationContextRunner为我们提供了withUserConfiguration方法，我们可以根据需要提供自动配置，以便为每个测试自定义ApplicationContext。
     * - run() - run 方法将 ContextConsumer 作为将断言应用于上下文的参数。测试退出时，ApplicationContext将自动关闭。
     * - FilteredClassLoader() - 当类不在classpath上时，我们如何测试相反的情况呢；这就是FilteredClassLoader发挥作用的地方。它用于在运行时过滤classpath上指定的类。
     * - withPropertyValues() - 强行更改当前context里properties文件的属性值
     */
    private final ApplicationContextRunner contextRunner = new ApplicationContextRunner();

    /**
     * 配置类：测试一些使用@ConditionalOnClass和@ConditionalOnMissingClass 注解的自动配置类
     * 这里使用静态类，也可以单独在src/main/java文件夹下创建
     */
    @Configuration
    @ConditionalOnClass(Test1_ConditionalOnClassIntegration.class)  // 当前测试类存在，则创建bean；所以这里'created' bean会被创建
    protected static class ConditionalOnClassConfiguration {
        @Bean
        public String created() {
            return "This is created when ConditionalOnClassIntegrationTest is present on the classpath";
        }
    }

    @Configuration
    @ConditionalOnMissingClass("com.atguigu.boot.Test1_ConditionalOnClassIntegration") // 当前测试类不存在，则创建bean；所以这里'missed' bean不会被创建
    protected static class ConditionalOnMissingClassConfiguration {
        @Bean
        public String missed() {
            return "This is missed when ConditionalOnClassIntegrationTest is present on the classpath";
        }
    }

    /**
     * 测试类
     */
    @Test
    public void whenDependentClassIsPresent_thenBeanCreated() {
        this.contextRunner.withUserConfiguration(ConditionalOnClassConfiguration.class)
            .run(context -> {
                assertThat(context).hasBean("created");
                System.out.println("=========测试context.getBean的内容为实际bean返回的字符串内容：" + context.getBean("created"));
                assertThat(context.getBean("created")).isEqualTo("This is created when ConditionalOnClassIntegrationTest is present on the classpath");
            });
    }

    @Test
    public void whenDependentClassIsPresent_thenBeanMissing() {
        this.contextRunner.withUserConfiguration(ConditionalOnMissingClassConfiguration.class)
            .run(context -> {
                assertThat(context).doesNotHaveBean("missed");
            });
    }

    @Test
    public void whenDependentClassIsNotPresent_thenBeanMissing() {
        this.contextRunner.withUserConfiguration(ConditionalOnClassConfiguration.class)
            .withClassLoader(new FilteredClassLoader(Test1_ConditionalOnClassIntegration.class))
            .run((context) -> {
                assertThat(context).doesNotHaveBean("created");
                assertThat(context).doesNotHaveBean(Test1_ConditionalOnClassIntegration.class);

            });
    }

    @Test
    public void whenDependentClassIsNotPresent_thenBeanCreated() {
        this.contextRunner.withUserConfiguration(ConditionalOnMissingClassConfiguration.class)
    // 这里过滤掉上一行类配置（ConditionalOnMissingClassConfiguration.class）要求类（Test1_ConditionalOnClassIntegration.class）不存在条件，满足条件，所以创建'missed' bean对象
            .withClassLoader(new FilteredClassLoader(Test1_ConditionalOnClassIntegration.class))
            .run((context) -> {
                assertThat(context).hasBean("missed");
                assertThat(context).getBean("missed")
                    .isEqualTo("This is missed when ConditionalOnClassIntegrationTest is present on the classpath");
                assertThat(context).doesNotHaveBean(Test1_ConditionalOnClassIntegration.class);

            });
    }
}
