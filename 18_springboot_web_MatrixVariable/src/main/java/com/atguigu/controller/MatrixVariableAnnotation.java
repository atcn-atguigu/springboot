package com.atguigu.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.MatrixVariable;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
