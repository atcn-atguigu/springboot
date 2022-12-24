package com.atguigu.pojo;

import lombok.Data;

import java.util.Date;

@Data
public class User2_Date {

    private Long id;
    private String name;
    private Date registerDate;  // String输入：2022-12-23T10:15:30 或 2022-12-23 --> 转换成日期类型格式如：Fri Dec 23 18:15:30 CST 2022
}
