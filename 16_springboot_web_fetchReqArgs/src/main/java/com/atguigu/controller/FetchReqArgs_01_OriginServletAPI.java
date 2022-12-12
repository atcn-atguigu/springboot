package com.atguigu.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
public class FetchReqArgs_01_OriginServletAPI {

    @RequestMapping("/")
    public String index() {
        return "index";
    }

    // 不推荐用法，用了就是侮辱SpringMVC
    @RequestMapping("/testServletAPI")  // 原生API缺陷无法使用URL占位符方式写
    public String testServletAPI(HttpServletRequest httpServletRequest) {    // 使用Servlet原生接口获取当前请求的请求参数
        String username = httpServletRequest.getParameter("username");
        String password = httpServletRequest.getParameter("password");
        System.out.println("username：" + username + "，password：" + password);
        return "success";
    }
}
