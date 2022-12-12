package com.atguigu.mvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/a")   // [@RequestMapping注解的位置] 第一层URI
public class RequestMappingController_02_MultiLayer {

    @RequestMapping("/b")  // [@RequestMapping注解的位置] 第二层URI
    public String multiLayerTest() {
        // 设置视图名称
        return "success";
    }
}
