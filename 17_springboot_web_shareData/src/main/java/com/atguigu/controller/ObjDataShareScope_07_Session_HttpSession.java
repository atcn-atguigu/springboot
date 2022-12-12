package com.atguigu.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

@Controller
public class ObjDataShareScope_07_Session_HttpSession {

    /**
     * Spring MVC为我们提供了注解:
     * @SessionAttribute - 当数据共享在request域的时候，在session域复制一份共享
     * ⚠️ 注意，由于不太好用，所以一般直接使用Servlet API -> HttpSession
     */
    @RequestMapping("/testSession")
    public String testSession(HttpSession session){
        session.setAttribute("testSessionScope", "Hello, HttpSession!");
        return "success";
    }
}
