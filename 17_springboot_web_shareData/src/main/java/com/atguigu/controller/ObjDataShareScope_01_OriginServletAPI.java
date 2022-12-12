package com.atguigu.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
public class ObjDataShareScope_01_OriginServletAPI {

    @RequestMapping("/")
    public String index() {
        return "index";
    }

    // 使用servlet API向request域对象共享数据
    @RequestMapping("/testScopeOfServletAPI")
    public String testServletAPI(HttpServletRequest httpServletRequest) {    // 使用Servlet原生接口获取当前请求的请求参数
        // Servlet API 域共享数据主要有3个方法：
        // （1）setAttribute()；
        httpServletRequest.setAttribute("testServletAPIAttributeData", "Hello, HttpServletRequest!!");
        //（2）getAttribute()；
        System.out.println("使用Servlet原生API设置数据共享scope，属性'servletAPIAttributeData'：" + httpServletRequest.getAttribute("testServletAPIAttributeData"));
        //（3）removeAttribute()
//        httpServletRequest.removeAttribute("testServletAPIAttributeData");
        return "success";   // 请求转发给success视图页面，相应servlet request域对象数据也为之共享
    }
}
