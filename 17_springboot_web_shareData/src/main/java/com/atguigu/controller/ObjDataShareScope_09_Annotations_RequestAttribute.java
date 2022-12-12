package com.atguigu.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestAttribute;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@Controller
public class ObjDataShareScope_09_Annotations_RequestAttribute {

    // ServletContext应用范围是整个应用范围
    @GetMapping("/testAnnotationRequestAttribute")
    public String testAnnotationRequestAttribute(HttpServletRequest httpServletRequest) {
        httpServletRequest.setAttribute("msg", "成功");
        httpServletRequest.setAttribute("code", "200");
        // 为了传递请求域中的属性值，这里使用forward转发，在另一个方法里使用@RequestAttribute获取请求域中的属性值
        return "forward:/readRequestAttribute";
    }

    @GetMapping("/readRequestAttribute")
    public String readAttribute(@RequestAttribute("msg") String msg,
                                @RequestAttribute("code") Integer code,
                                HttpServletRequest httpServletRequest) {
        // 使用Map返回给浏览器，更方便打印更多数据
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("context_msg", msg);
        map.put("context_code", code);
        System.out.println("@RequestAttribute注解读取的值为：" + map);
        // request域共享数据，渲染视图success页展示数据
        httpServletRequest.setAttribute("readRequestAttributeScope", map);
        return "success";
    }
}
