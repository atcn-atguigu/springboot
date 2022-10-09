## 08、底层注解-@Configuration详解

#### 基本使用
##### @Configuration(proxyBeanMethods = true)
```java
/**
 * @Configuration - 等同于一个Spring.xml配置文件，默认参数： proxyBeanMethods = true，表示使用bean代理模式，目的解决bean之间依赖问题，此时都是bean为单例。
 *  @Configuration(proxyBeanMethods = true) // true（默认）: 保证每个@Bean方法被调用多少次返回的组件都是单实例的
 *  @Configuration(proxyBeanMethods = false) // false: 每个@Bean方法被调用多少次返回的组件都是新创建的
 *  - 最佳实战
 * 	- 配置 类组件之间**无依赖关系**用Lite模式加速容器启动过程，减少判断
 * 	- 配置 类组件之间**有依赖关系**，方法会被调用得到之前单实例组件，用Full模式（默认）
 *
 * @Bean - 等同于xml文件下配置的bean，对应的方法名，为bean的名称，也可以显示定义名称如：@Bean("myBeanId")
 */
@Configuration(proxyBeanMethods = true) // true（默认）: 保证每个@Bean方法被调用多少次返回的组件都是单实例的
public class MyConfig {

  // 返回bean的name为"user"
  @Bean
  public User user() {
    User user = new User("Ben", 20);
    user.setPet(getTomcatPet());   // setter配置依赖的Pet类，这里使用@Bean注解对应的方法获取beanId
    return user;
  }

  // 返回bean的name为"tomcat"
  @Bean("tomcat") // 这里的"tomcat"为显式定义的bean名称，否则方法名为bean名称
  public Pet getTomcatPet() {
    return new Pet("tomcat");
  }
}
```

##### @Configuration(proxyBeanMethods = false)
```java
@Configuration(proxyBeanMethods = false) // false: 每个@Bean方法被调用多少次返回的组件都是新创建的
public class MyConfig2 {

    // 返回bean的name为"user2"
    @Bean
    public User user2() {
        User user = new User("Ben2", 20);
        user.setPet(getTomcatPet2());   // setter配置依赖的Pet类，这里使用@Bean注解对应的方法获取beanId
        return user;
    }

    // 返回bean的name为"tomcat2"
    @Bean("tomcat2") // 这里的"tomcat"为显式定义的bean名称，否则方法名为bean名称
    public Pet getTomcatPet2() {
        return new Pet("tomcat2");
    }
}
```

##### Unit tests
```java
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
```


- 最佳实战
    - 配置 @Configuration(proxyBeanMethods = false) - 类组件之间**无依赖关系**用Lite模式加速容器启动过程，减少判断
    - 配置 @Configuration(proxyBeanMethods = true) - 类组件之间**有依赖关系**，方法会被调用得到之前单实例组件，用Full模式（默认）