package com.atguigu.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Map;

@Controller
public class ObjDataShareScope_04_Map {

    @RequestMapping("/testScopeOfMap")
    public String testScopeOfMap(Map<String, Object> map) {
        map.put("mapData", "Hello Map!");
        System.out.println(map);    // Model、Map、ModelMap：输出文本格式一样
        System.out.println(map.getClass().getName());   // Model、Map、ModelMap：获取反射类类名一样是：BindingAwareModelMap
        return "success";
    }
}
