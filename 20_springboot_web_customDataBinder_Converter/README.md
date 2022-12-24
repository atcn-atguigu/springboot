## 类型转换器Converter - A Custom Data Binder in Spring MVC
参考文档： https://www.baeldung.com/spring-mvc-custom-data-binder

### 1. Overview
This article will show how we can use Spring's Data Binding mechanism in order to make our code more clear and readable by applying automatic primitives to objects conversions.

By default, Spring only knows how to convert simple types. In other words, once we submit data to controller Int, String or Boolean type of data, it will be bound to appropriate Java types automatically.

But in real-world projects, that won't be enough, as we might need to bind more complex types of objects.

### 2. Binding Individual Objects to Request Parameters
第一步，定义实体User：
```java
// 案例1
@Data
public class User2_Date {

    private Long id;
    private String name;
    private Date registerDate;  // String输入：2022-12-23T10:15:30 或 2022-12-23 --> 转换成日期类型格式如：Fri Dec 23 18:15:30 CST 2022
}

// 案例2
@Data
public class User_LocalDateTime {

    private Long id;
    private String name;
    private LocalDateTime registerDate;  // String输入：2022-12-23T10:15:30 --> 转换成日期类型格式如：2022-12-23T10:15:30
}
```
第二步，实现Converter接口：
```java
// 案例1
@Component
public class String2DateConverter implements Converter<String, Date> {

    private SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    @Override
    public Date convert(String source) {
        if (source != null && !"".equals(source)) {
            try {
                simpleDateFormat.parse(source);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        return null;
    }
}

// 案例2
@Component
public class String2LocalDateTimeConverter implements Converter<String, LocalDateTime> {
    @Override
    public LocalDateTime convert(String source) {
        return LocalDateTime.parse(
                source, DateTimeFormatter.ISO_LOCAL_DATE_TIME);
    }
}
```
第三步，将新定义的类型转换器注入到spring容器中（重写addFormatters()方法）：
```java
/**
 * 开启配置方式一：实现接口WebMvcConfigurer对应方法
 */
/*
@Configuration(proxyBeanMethods = false)
public class WebConfig implements WebMvcConfigurer {
    @Override
    public void addFormatters(FormatterRegistry registry) {
        registry.addConverter(new String2DateConverter());
        registry.addConverter(new String2LocalDateTimeConverter());
    }
}
*/


/**
 * 开启配置方式二：容器中放"webMvcConfigurer"容器对象并重写对应方法
 */
@Configuration(proxyBeanMethods = false)
public class WebConfig {
    @Bean
    public WebMvcConfigurer webMvcConfigurer() {
        // new WebMvcConfigurer() 接口带默认实现方法，只重写我们需要重写的方法
        return new WebMvcConfigurer() {

            // 将新定义的类型转换器注入到spring容器中
            @Override
            public void addFormatters(FormatterRegistry registry) {
                registry.addConverter(new String2DateConverter());
                registry.addConverter(new String2LocalDateTimeConverter());
            }
        };
    }
}
```
第四步，调用接口
```java
@Controller
public class UserController {
    @PostMapping("/testConverter/user/LocalDateTime")
    public String converterTest1(@RequestBody User_LocalDateTime user, Model model) {
        // 设置到共享域后再在视图中渲染
        model.addAttribute("userInputted", user);
        return "success";
    }

    @PostMapping("/testConverter/user/Date")
    public String converterTest2(@RequestBody User2_Date user, Model model) {
        // 设置到共享域后再在视图中渲染
        model.addAttribute("userInputted", user);
        return "success";
    }
}
```