package com.atguigu.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ObjDataShareScope_03_Model {

    @RequestMapping("/testScopeOfModel")
    public String testScopeOfModel(Model model) { // 形参传入Model类
        model.addAttribute("modelAttributeData", "Hello, Model!");
        System.out.println(model);    // Model、Map、ModelMap：输出文本格式一样
        System.out.println(model.getClass().getName());   // Model、Map、ModelMap：获取反射类类名一样是：BindingAwareModelMap
        return "success";
    }
}
