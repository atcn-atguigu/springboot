package com.atguigu.boot;

import com.atguigu.boot.bean.Pet;
import com.atguigu.boot.config.MyConfig;
import com.atguigu.boot.config.MyConfig2;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class TestConfigurationProxyBeanMethod {

    /**
     * 1. 使用MyConfig配置类对象，多次调用同一个bean方法获取bean
     *  - @Configuration(proxyBeanMethods = true)  - 则每次调用@Bean注解对应的方法，都返回不同的bean对象
     */
    @Test
    public void testConfigurationProxyBeanMethodTrue() {
        ApplicationContext context1 = new AnnotationConfigApplicationContext(MyConfig.class);
        MyConfig myConfig = context1.getBean(MyConfig.class);
        Pet tom01 = myConfig.getTomcatPet();
        Pet tom02 = myConfig.getTomcatPet();
        Assert.assertTrue("MyConfig - getTomcatPet() - @Configuration(proxyBeanMethods = true)：", (tom01 == tom02));
    }

    /**
     * 2. 使用MyConfig2配置类对象，多次调用同一个bean方法获取bean
     *  - @Configuration(proxyBeanMethods = false)   - 则每次调用@Bean注解对应的方法，都返回相同的bean对象
     */
    @Test
    public void testConfigurationProxyBeanMethodFalse() {
        ApplicationContext context2 = new AnnotationConfigApplicationContext(MyConfig2.class);
        MyConfig2 myConfig2 = context2.getBean(MyConfig2.class);
        Pet tom03 = myConfig2.getTomcatPet2();
        Pet tom04 = myConfig2.getTomcatPet2();
        Assert.assertFalse("MyConfig2 - getTomcatPet2() - @Configuration(proxyBeanMethods = false)：", (tom03 == tom04));
    }

}
