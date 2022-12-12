package com.atguigu.mvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class RequestMappingController_03_ValueTest {

    @RequestMapping(
            value = {"/testRequestMappingValue1", "/testRequestMappingValue2"}  // value值也可以同时存在多个匹配
    )
    public String testRequestMappingValue() {
        // 设置视图名称
        return "success";
    }
}
