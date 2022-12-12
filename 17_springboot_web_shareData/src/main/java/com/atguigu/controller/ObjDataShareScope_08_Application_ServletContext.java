package com.atguigu.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;

@Controller
public class ObjDataShareScope_08_Application_ServletContext {

    // ServletContext应用范围是整个应用范围
    @RequestMapping("/testApplication")
    public String testApplication(HttpSession session){ // 通过HttpSession获取ServletContext
        ServletContext servletContext = session.getServletContext();
        // ServletContext的几个常用方法：setAttribute()、getAttribute()、removeAttribute()
        servletContext.setAttribute("testApplicationScope", "Hello, ServletContext!");
//        servletContext.getAttribute("testApplicationScope");
//        servletContext.removeAttribute("testApplicationScope");
        return "success";
    }
}
