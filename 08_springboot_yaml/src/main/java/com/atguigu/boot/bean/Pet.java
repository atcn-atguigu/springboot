package com.atguigu.boot.bean;

import lombok.Data;
import org.springframework.stereotype.Component;

@Component
@Data
public class Pet {
    private String name;
    private Double weight;
}
