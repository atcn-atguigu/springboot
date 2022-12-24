package com.atguigu.pojo;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class User_LocalDateTime {

    private Long id;
    private String name;
    private LocalDateTime registerDate;  // String输入：2022-12-23T10:15:30 --> 转换成日期类型格式如：2022-12-23T10:15:30
}
