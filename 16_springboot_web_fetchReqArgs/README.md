## 获取请求参数
SpringBoot写法 - [参考了spring-mvc的案例，详细说明文档请看这里](https://github.com/atcn-atguigu/spring-mvc/tree/master/03_springmvc_fetchReqArgs)

引入依赖**spring-boot-starter-thymeleaf**
```xml
<!-- starter-thymeleaf 依赖-->
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-thymeleaf</artifactId>
</dependency>
```
### 控制器方法形参获取参数：HttpServletRequest ｜ String ｜ String[]
### @RequestParam注解（属性：value、required、defaultValue）
### @RequestHeader注解（属性：value、required、defaultValue）
### 控制器方法HttpServletRequest形参的getSession()方法获取session，通过HttpSession来给浏览器Set-Cookie
### @CookieValue注解（属性：value、required、defaultValue）