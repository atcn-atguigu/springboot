## 24、web场景-welcome与favicon功能

[官方文档 - spring-boot-features - Developing web applications - Welcome Page](https://docs.spring.io/spring-boot/docs/2.3.8.RELEASE/reference/htmlsingle/#boot-features-spring-mvc-welcome-page)

### 欢迎页支持

- index.html - 放在静态资源路径下，且没有修改访问前缀是访问"/"可自动映射index.html页面

    - 可以配置静态资源路径：classpath:/META-INF/resources/, classpath:/resources/, classpath:/static/, classpath:/public/
    - 自定义静态资源路径不生效，如：classpath:/myCustomizedStaticFolder/，"index.html"以及"favicon.ico"不能生效。
    - 但是不可以配置静态资源的访问前缀。否则导致 index.html不能被默认访问，只能加前缀形式以静态资源方式全路径访问，如：http://localhost:8080/resources/index.html
    - controller能处理/index。
  
### 自定义favicon.ico （指网页标签上的小图标）

- favicon.ico 放在静态资源目录下即可。

```yaml
spring:
  resources:
    # 静态资源默认路径
    #spring.web.resources.static-locations=classpath:/META-INF/resources/, classpath:/resources/, classpath:/static/, classpath:/public/
    # 手动修改后，⚠️注意，若开启访问前缀（/resources/**），则自定义目录"myCustomizedStaticFolder"下不建议放"index.html"以及"favicon.ico"文件，会不生效。除自定义，其他默认目录"/META-INF/resources/"、"/resources/"、"/static/"、"/public/"可生效
    static-locations: [classpath:/myCustomizedStaticFolder/, classpath:/META-INF/resources/, classpath:/resources/, classpath:/static/, classpath:/public/]

  mvc:
  # 默认静态资源访问前缀 - spring.mvc.static-path-pattern=/**
  # 手动修改后 - static-path-pattern: /resources/**，便于拦截器排除静态资源拦截
  # 若修改静态资源访问前缀，则访问欢迎页只能是手动加前缀，spring不会帮你映射到根路径：http://localhost:8080/resources/index.html
#    static-path-pattern: /resources/**
```


