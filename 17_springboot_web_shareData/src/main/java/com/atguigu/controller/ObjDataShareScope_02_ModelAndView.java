package com.atguigu.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ObjDataShareScope_02_ModelAndView {

    @RequestMapping("/testScopeOfModelAndView")
    public ModelAndView testScopeOfModelAndView() {
        ModelAndView modelAndView = new ModelAndView();
        // 处理模型数据，即是向request域共享数据。
        modelAndView.addObject("modelAndViewScopeData", "Hello, Model and View!");
        // 设置视图名称
        modelAndView.setViewName("success");
        return modelAndView;    // modelAndView对象必须作为方法返回值
    }
}
