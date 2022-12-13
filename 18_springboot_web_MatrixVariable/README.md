## 29、请求处理-常用参数注解使用
SpringBoot写法 - [参考了spring-mvc的案例，详细说明文档请看这里](https://github.com/atcn-atguigu/spring-mvc/tree/master/03_springmvc_fetchReqArgs)
注解：
- `@PathVariable` 路径变量
- `@RequestHeader` 获取请求头
- `@RequestParam` 获取请求参数（指问号后的参数，url?a=1&b=2）
- `@CookieValue` 获取Cookie值
- `@RequestAttribute` 获取request域属性
- `@RequestBody` 获取请求体[POST]
- `@MatrixVariable` 矩阵变量


## 30、请求处理-@RequestAttribute
SpringBoot写法 - [参考了spring-mvc的案例，详细说明文档请看这里](https://github.com/atcn-atguigu/spring-mvc/tree/master/04_springmvc_shareData)


## 31、请求处理-@MatrixVariable与UrlPathHelper
```java
@Controller
public class MatrixVariableAnnotation {

    @RequestMapping("/")
    public String index() {
        return "index";
    }

    /**
     * 1、SpringBoot默认是禁用矩阵变量的，两种开启方式写法查看WebConfig.java
     * <p>
     * 2、矩阵变量语法：
     * 1）写法一需要URL路径变量才能被解析（如下），写法二则不需要
     * /testMatrixVariable/manager;username=Ben;interests=basketball,badminton;age=22
     * /testMatrixVariable/developer;username=Tom;interests=baseball,football;age=28
     * 
     * 2）写法二不需要URL路径变量
     * /testMatrixVariable/1;age=20/2;age=10
     */
    @RequestMapping("/testMatrixVariable/{role}")
    public String testMatrixVariable1(@PathVariable("role") String role, // ⚠️有@PathVariable, @MatrixVariable才能被解析使用
                                      @MatrixVariable("username") String username,
                                      @MatrixVariable("interests") List<String> interests,
                                      @MatrixVariable("age") Integer age) {
        HashMap<String, Object> map = new HashMap<>();
        map.put("role", role);
        map.put("username", username);
        map.put("interests", interests);
        map.put("age", age);
        System.out.println("========= Testing @MatrixVariable =========\n" + map);
        return "success";
    }

    // /testMatrixVariable/1;age=20/2;age=10
    @GetMapping("/testMatrixVariable/{bossId}/{empId}") // 写法二不需要@PathVariable路径变量
    public String testMatrixVariable2(@MatrixVariable(value = "age", pathVar = "bossId") Integer bossAge,
                                      @MatrixVariable(value = "age", pathVar = "empId") Integer empAge) {
        Map<String, Object> map = new HashMap<>();
        map.put("bossAge", bossAge);
        map.put("empAge", empAge);
        System.out.println("========= Testing @MatrixVariable =========\n" + map);
        return "success";
    }
}
```

**手动开启矩阵变量**：

- 实现`WebMvcConfigurer`接口：

```java
@Configuration(proxyBeanMethods = false)
/**
 * 开启矩阵变量的配置方式一：继承WebMvcConfigurer，重写configurePathMatch()方法 - setRemoveSemicolonContent(false)
 */
public class WebConfig implements WebMvcConfigurer {
    @Override
    public void configurePathMatch(PathMatchConfigurer configurer) {
        UrlPathHelper urlPathHelper = new UrlPathHelper();
        // SpringBoot默认配置是移除URL中的分号";"后面的内容，这里我们因为测试使用矩阵变量@MatrixVariable，所以要把配置改为false
        urlPathHelper.setRemoveSemicolonContent(false);
        configurer.setUrlPathHelper(urlPathHelper);
    }
}
```

- 创建返回`WebMvcConfigurer`Bean：

```java
@Configuration(proxyBeanMethods = false)
/**
 * 开启矩阵变量的配置方式二：Spring容器中修改容器对象里的配置方法
 */
public class WebConfig {
    // 容器中放"webMvcConfigurer"容器对象
    @Bean
    public WebMvcConfigurer webMvcConfigurer() {
        // new WebMvcConfigurer() 接口带默认实现方法，只重写我们需要重写的方法
        return new WebMvcConfigurer() {
            @Override
            public void configurePathMatch(PathMatchConfigurer configurer) {
                UrlPathHelper urlPathHelper = new UrlPathHelper();
                // SpringBoot默认配置是移除URL中的分号";"后面的内容，这里我们因为测试使用矩阵变量@MatrixVariable，所以要把配置改为false
                urlPathHelper.setRemoveSemicolonContent(false);
                configurer.setUrlPathHelper(urlPathHelper);
            }
        };
    }
}
```