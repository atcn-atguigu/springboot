## request域、session域、application域对象数据共享
SpringBoot写法 - [参考了spring-mvc的案例，详细说明文档请看这里](https://github.com/atcn-atguigu/spring-mvc/tree/master/04_springmvc_shareData)

引入依赖**spring-boot-starter-thymeleaf**
```xml
<!-- starter-thymeleaf 依赖-->
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-thymeleaf</artifactId>
</dependency>
```
### request域 - 控制器方法形参：HttpServletRequest - setAttribute()，getAttribute()，removeAttribute()
### request域 - 控制器方法体内: new ModelAndView() -  addObject()，setViewName()。方法体返回ModelAndView实例化对象
### request域 - 控制器方法形参：Model - addAttribute()，getAttribute()
### request域 - 控制器方法形参：Map<String, Object> - put()，get()，remove()
### request域 - 控制器方法形参：ModelMap - addAttribute()，put()，getAttribute()，remove()
### session域 - 控制器方法形参：HttpSession - setAttribute()，getAttribute()，removeAttribute()
### application域 - 控制器方法形参：HttpSession；方法提：ServletContext servletContext = session.getServletContext(); - setAttribute()，getAttribute()，removeAttribute()
### request域 - 控制器方法形参：@RequestAttribute注解参数读取request域的值