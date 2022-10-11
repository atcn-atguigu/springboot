## 09、底层注解-@Import导入组件

@Bean、@Component、@Controller、@Service、@Repository，它们是Spring的基本标签，在Spring Boot中并未改变它们原来的功能。

@ComponentScan 在[07、基础入门-SpringBoot-自动配置特性](#)有用例。


@Import({MyConfig2.class, User.class}) 给容器中**自动创建出这两个类型的组件**、默认组件的名字就是全类名

```java
@Import({MyConfig2.class, User.class})  // Import使用无参构造创建bean对象，其bean名称为全类名，也可以用于引入另外一个配置类
@Configuration
public class MyConfig {
}
```

测试类：

```java
public class TestImport {

    @Test
    public void testImport() {
        /**
         * 这里只导入了 MyConfig.class;
         *  然而实际上同样也导入了 MyConfig2.class, User.class - @Import({MyConfig2.class, User.class})
         */
        ApplicationContext context = new AnnotationConfigApplicationContext(MyConfig.class);
        String[] beanNamesForTypeMyConfig2 = context.getBeanNamesForType(MyConfig2.class);
        String[] beanNamesForTypeUser = context.getBeanNamesForType(User.class);
        Assert.assertTrue("@Import注解导入的bean对象名为，全类名", Arrays.stream(beanNamesForTypeMyConfig2).anyMatch(Predicate.isEqual("com.atguigu.boot.config.MyConfig2")));
        Assert.assertTrue("@Import注解导入的bean对象名为，全类名", Arrays.stream(beanNamesForTypeUser).anyMatch(Predicate.isEqual("com.atguigu.boot.bean.User")));
        Assert.assertTrue("@Import注解导入的bean对象名为，全类名", Arrays.stream(beanNamesForTypeUser).anyMatch(Predicate.isEqual("user")));
    }
}
```