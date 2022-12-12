package com.atguigu.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Arrays;

@Controller
public class FetchReqArgs_02_MethodParameter {

    // 通过控制器方法的形参来获取参数
    @RequestMapping("/testParameter")
    public String testParameter(String username, String password) {
        System.out.println("username：" + username + "，password：" + password);
        return "success";
    }

    /**
     * ?username=root&password=123456&hobby=running&hobby=swimming
     * 若请求参数中出现多个同名的请求参数，则可以使用字符串接收（hobby：running,swimming），或使用字符串数组接收（hobby：[running, swimming]）
     *
     * 拓展知识点： 如果用Servlet原生方式写法，获取请求参数里的字符串数组值：httpServletRequest.getParameterValues("hobby");
     */
    @RequestMapping("/testParameterMultiValue")
    public String testParameterMultiValue(String username, String password, String hobby) { // String接收字符串数组
        System.out.println("username：" + username + "，password：" + password + "，hobby：" + hobby);
        return "success";
    }

    @RequestMapping("/testParameterStringArray")
    public String testParameterStringArray(String username, String password, String[] hobby) { // String[]接收字符串数组
        System.out.println("username：" + username + "，password：" + password + "，hobby：" + Arrays.toString(hobby));
        return "success";
    }
}
