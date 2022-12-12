package com.atguigu.mvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class RequestMappingController_01_SingleLayer {

    @RequestMapping("/")
    public String index() {
        // 设置视图名称
        return "index";
    }
}
