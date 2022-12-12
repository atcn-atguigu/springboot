package com.atguigu.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ObjDataShareScope_05_ModelMap {

    @RequestMapping("/testScopeOfModelMap")
    public String testScopeOfModelMap(ModelMap modelMap) { // 形参传入Model类
        modelMap.addAttribute("modelMapAttributeData", "Hello, Model Map! -- addAttribute()");
        modelMap.put("modelMapPutData", "Hello, Model Map! -- put()");
        System.out.println(modelMap);    // Model、Map、ModelMap：输出文本格式一样
        System.out.println(modelMap.getClass().getName());   // Model、Map、ModelMap：获取反射类类名一样是：BindingAwareModelMap
        return "success";
    }
}
