package com.atguigu.mvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class RequestMappingController_07_ValueRegex {

    // ？：表示任意的单个字符
    @RequestMapping("/a?c/testQuestionMark")
    public String testQuestionMark() {
        return "success";
    }

    // *：表示任意的0个或多个字符
    @RequestMapping("/a*c/testSingleStar")
    public String testSingleStar() {
        return "success";
    }

    // **：表示任意的一层或多层目录，注意：在使用**时，只能使用/**/xxx的方式
    @RequestMapping("/**/testDoubleStar")
    public String testDoubleStar() {
        return "success";
    }
}
