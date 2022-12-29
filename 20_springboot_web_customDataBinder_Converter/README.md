## 类型转换器Converter - A Custom Data Binder in Spring MVC
参考文档： https://www.baeldung.com/spring-mvc-custom-data-binder

### Sample 1 - 日期转换
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


### Sample 2 - 字符串数组转对象
第一步，定义实体Goods：
```java
@Data
public class Goods {
    private String name;
    private double price;
    private int number;
}
```
第二步，实现Converter接口：
```java
@Component
public class GoodsConverter implements Converter<String, Goods> {
    @Override
    public Goods convert(String source) {
        // 创建一个Goods实例
        Goods goods = new Goods();
        // 以“，”分隔
        String stringvalues[] = source.split(",");
        if (stringvalues != null && stringvalues.length == 3) {
            // 为Goods实例赋值
            goods.setName(stringvalues[0].trim());
            goods.setPrice(Double.parseDouble(stringvalues[1].trim()));
            goods.setNumber(Integer.parseInt(stringvalues[2].trim()));
            return goods;
        } else {
            throw new IllegalArgumentException(String.format(
                    "类型转换失败， 需要格式'apple, 10.58,200 ',但格式是[% s ] ", source));
        }
    }
}
```
第三步，将新定义的类型转换器注入到spring容器中（重写addFormatters()方法）：
```java
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
                registry.addConverter(new GoodsConverter());
            }
        };
    }
}
```
第四步，调用接口
```java
@Controller
@RequestMapping("/my")
public class GoodsController {


    @RequestMapping("/converter")
    /*
     * 使用@RequestParam
     * ("goods")接收请求参数，然后调用自定义类型转换器GoodsConverter将字符串值转换为GoodsModel的对象gm
     */
    public String myConverter(@RequestParam("goods") Goods goods, Model model) {
        model.addAttribute("goods", goods);
        return "success2";
    }
}
```
```html
<h3>2、GoodsConverter(String -> Goods)</h3>
<form action="/my/converter" method= "post">
    请输入商品信息（格式为apple, 10.58, 200）:
    <!-- 以请求参数@RequestParam 形式传入商品信息，格式为字符串，逗号分隔，通过自定义的Converter映射转换成对象
        value: 商品的name, 商品的price, 商品的number
    -->
    <input type="text" name="goods" value="apple, 10.58, 200"/><br>
    <input type="submit" value="提交" />
</form>
<hr>
```