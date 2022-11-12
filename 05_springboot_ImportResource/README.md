## 11、底层注解-@ImportResource导入Spring配置文件

比如，公司使用beans.xml文件生成配置bean，然而你为了省事，想继续复用bean.xml，@ImportResource粉墨登场。

beans.xml：
```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd">

    <bean id="user1" class="com.atguigu.boot.bean.User">
        <property name="name" value="zhangsan"></property>
        <property name="age" value="18"></property>
        <property name="pet" ref="pet1"/>
    </bean>

    <bean id="pet1" class="com.atguigu.boot.bean.Pet">
        <property name="name" value="tomcat"></property>
    </bean>
</beans>
```

使用方法：
```java
@Configuration
@ImportResource("classpath:beans.xml")
public class MyConfig {
}
```

测试类：
```java
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
```

Console log:
```plain/text
15:56:56.769 [main] DEBUG org.springframework.context.annotation.AnnotationConfigApplicationContext - Refreshing org.springframework.context.annotation.AnnotationConfigApplicationContext@1ed4ae0f
15:56:56.814 [main] DEBUG org.springframework.beans.factory.support.DefaultListableBeanFactory - Creating shared instance of singleton bean 'org.springframework.context.annotation.internalConfigurationAnnotationProcessor'
15:56:57.266 [main] DEBUG org.springframework.beans.factory.xml.XmlBeanDefinitionReader - Loaded 2 bean definitions from class path resource [beans.xml]
15:56:57.542 [main] DEBUG org.springframework.beans.factory.support.DefaultListableBeanFactory - Creating shared instance of singleton bean 'org.springframework.context.event.internalEventListenerProcessor'
15:56:57.549 [main] DEBUG org.springframework.beans.factory.support.DefaultListableBeanFactory - Creating shared instance of singleton bean 'org.springframework.context.event.internalEventListenerFactory'
15:56:57.551 [main] DEBUG org.springframework.beans.factory.support.DefaultListableBeanFactory - Creating shared instance of singleton bean 'org.springframework.context.annotation.internalAutowiredAnnotationProcessor'
15:56:57.554 [main] DEBUG org.springframework.beans.factory.support.DefaultListableBeanFactory - Creating shared instance of singleton bean 'org.springframework.context.annotation.internalCommonAnnotationProcessor'
15:56:57.568 [main] DEBUG org.springframework.beans.factory.support.DefaultListableBeanFactory - Creating shared instance of singleton bean 'myConfig'
15:56:57.577 [main] DEBUG org.springframework.beans.factory.support.DefaultListableBeanFactory - Creating shared instance of singleton bean 'user1'
15:56:57.704 [main] DEBUG org.springframework.beans.factory.support.DefaultListableBeanFactory - Creating shared instance of singleton bean 'pet1'
=========测试context.getBean的内容为实际bean返回的字符串内容：User(name=zhangsan, age=18, pet=Pet(name=tomcat))
=========测试context.getBean的内容为实际bean返回的字符串内容：Pet(name=tomcat)
15:56:57.892 [main] DEBUG org.springframework.context.annotation.AnnotationConfigApplicationContext - Closing org.springframework.context.annotation.AnnotationConfigApplicationContext@1ed4ae0f, started on Sat Nov 12 15:56:56 CST 2022

Process finished with exit code 0
```
