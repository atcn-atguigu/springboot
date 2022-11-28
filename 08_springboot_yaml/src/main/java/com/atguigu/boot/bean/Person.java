package com.atguigu.boot.bean;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;

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
