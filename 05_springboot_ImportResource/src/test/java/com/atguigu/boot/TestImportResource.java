package com.atguigu.boot;

import com.atguigu.boot.config.MyConfig;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.runner.ApplicationContextRunner;

import static org.assertj.core.api.Assertions.assertThat;

public class TestImportResource {

    private final ApplicationContextRunner contextRunner = new ApplicationContextRunner();

    @Test
    public void whenDependentClassIsPresent_thenBeanCreated() {
        this.contextRunner.withUserConfiguration(MyConfig.class)
            .run(context -> {
                assertThat(context).hasBean("user1");
                assertThat(context).hasBean("pet1");
                System.out.println("=========测试context.getBean的内容为实际bean返回的字符串内容：" + context.getBean("user1"));
                System.out.println("=========测试context.getBean的内容为实际bean返回的字符串内容：" + context.getBean("pet1"));
            });
    }
}
