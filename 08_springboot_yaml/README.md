## 20、配置文件-yaml的用法

同以前的properties用法

YAML 是 "YAML Ain't Markup Language"（YAML 不是一种标记语言）的递归缩写。在开发的这种语言时，YAML 的意思其实是："Yet Another Markup Language"（仍是一种标记语言）。

**非常适合用来做以数据为中心的配置文件**。

### 基本语法

- key: value；kv之间有空格
- 大小写敏感
- 使用缩进表示层级关系
- 缩进不允许使用tab，只允许空格
- 缩进的空格数不重要，只要相同层级的元素左对齐即可
- '#'表示注释
- 字符串无需加引号，如果要加，单引号''、双引号""表示字符串内容会被 转义、不转义

### 数据类型

- 字面量：单个的、不可再分的值。date、boolean、string、number、null

```yaml
k: v
```

- 对象：键值对的集合。map、hash、set、object

```yaml
#行内写法：  

k: {k1:v1,k2:v2,k3:v3}

#或

k: 
  k1: v1
  k2: v2
  k3: v3
```

- 数组：一组按次序排列的值。array、list、queue

```yaml
#行内写法：  

k: [v1,v2,v3]

#或者

k:
 - v1
 - v2
 - v3
```

### 实例

```java
@Component
@ConfigurationProperties(prefix = "person")
@Data
public class Person {
    private String userName;
    private Boolean isMarried;
    private Date birth;
    private Integer age;
    private String[] interests;
    private List<String> favouriteColors;
    private Pet pet;
    private Map<String, Object> score;
    private Set<Double> salaries;
    private Map<String, List<Pet>> allPets;
}
```
```java
@Component
@Data
public class Pet {
    private String name;
    private Double weight;
}
```

用yaml表示以上对象

```yaml
person:
  # ⚠️ "userName" 等同于 "user-name"
  userName: Sam           # String - ⚠️ 默认可不加引号。加"双引号"则转义字符可生效，用'单引号'代表字面意思不转义
  isMarried: false        # Boolean
  birth: 2000/11/28       # Date
  age: 12                 # Integer
  #  interests: [篮球，足球]  # String[] - Option 1
  interests:              # String[] - Option 2
    - basketball
    - football
  #  favouriteColors: [blue, green]      # List<String> - Option 1
  favouriteColors:                     # List<String> - Option 2
    - blue
    - green
  #  score: { English: 90, Chinese: 95}   # Map<String, Object>
  score:                  # Map<String, Object>
    English: 90
    Chinese: 95
  salaries:               # Set<Double>
    - 9999.90
    - 9999.99
  pet:                    # Pet pet
    name: dogA
    weight: 50.22
  allPets:                # Map<String, List<Pet>>
    sick:                 # Map key1 - sick
      - { name: dogA, weight: 50}   # Pet object: dogA - Option 1
      - name: catA                  # Pet object: catA - Option 2
        weight: 30
      - name: insectA                  # Pet object: insectA
        weight: 10
    health:               # Map key2 - health
      - { name: dogB, weight: 45}   # Pet object: dogB
      - { name: catC, weight: 55}   # Pet object: catC
```
Visit output: http://localhost:9443/
```json
{"userName":"Sam","isMarried":false,"birth":"2000-11-27T16:00:00.000+00:00","age":12,"interests":["篮球，足球"],"favouriteColors":["blue","green"],"pet":{"name":"dogA","weight":50.22},"score":{"English":90,"Chinese":95},"salaries":[9999.9,9999.99],"allPets":{"sick":[{"name":"dogA","weight":50.0},{"name":"catA","weight":30.0},{"name":"insectA","weight":10.0}],"health":[{"name":"dogB","weight":45.0},{"name":"catC","weight":55.0}]}}
```





## 21、配置文件-自定义类绑定的配置提示

> You can easily generate your own configuration metadata file from items annotated with `@ConfigurationProperties` by using the `spring-boot-configuration-processor` jar. The jar includes a Java annotation processor which is invoked as your project is compiled.——[link](https://docs.spring.io/spring-boot/docs/2.4.2/reference/htmlsingle/#configuration-metadata-annotation-processor)

自定义的类和配置文件绑定一般没有提示。若要提示，添加如下依赖：

```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-configuration-processor</artifactId>
    <optional>true</optional>
</dependency>

<!-- 下面插件作用是工程打包时，不将spring-boot-configuration-processor打进包内，让其只在编码的时候有用 -->
<build>
    <plugins>
        <plugin>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-maven-plugin</artifactId>
            <configuration>
                <excludes>
                    <exclude>
                        <groupId>org.springframework.boot</groupId>
                        <artifactId>spring-boot-configuration-processor</artifactId>
                    </exclude>
                </excludes>
            </configuration>
        </plugin>
    </plugins>
</build>
```