package com.atguigu.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.HashMap;
import java.util.Map;

@Controller
public class FetchReqArgs_03_RequestParamAnnotation {

    // 前端传过来的请求参数为"user-name"，但形参变量名不允许，引出注解@RequestParam
    @RequestMapping("/testRequestParamTag")
    public String testRequestParamTag(@RequestParam("user-name") String username) {
        System.out.println("username：" + username); // 调用传参数值(user-name=admin)，输出：username：admin
        return "success";
    }

    @RequestMapping("/testRequestParamTagRequired")
    public String testRequestParamTagRequired(@RequestParam(value = "user-name", required = false) String username) {
        System.out.println("username：" + username); // 调用不传参数的输出：username：null
        return "success";
    }

    @RequestMapping("/testRequestParamTagDefaultValue")
    public String testRequestParamTagDefaultValue(@RequestParam(value = "user-name", required = false, defaultValue = "hello") String username) {
        System.out.println("username：" + username); // 调用不传参数的输出：username：hello
        return "success";
    }

    @RequestMapping("/testRequestParamTagDefaultValue2")
    public String testRequestParamTagDefaultValue2(@RequestParam(value = "user-name", required = false, defaultValue = "hello") String username) {
        System.out.println("username：" + username); // 调用传参数(user-name=）的输出：username：hello 【注意：参数值为空，被认为没有传】
        return "success";
    }

    /**
     * 拓展，使用 @RequestParam Map<String, String>获取所有请求参数
     *
     * 注解说明：
     * If the method parameter is
     * {@link java.util.Map Map<String, String>;}
     * or
     * {@link org.springframework.util.MultiValueMap MultiValueMap<String, String>;}
     * and a parameter name is not specified, then the map parameter is populated
     * with all request parameter names and values.
     */
    @RequestMapping("/testRequestParamTagMap")
    public String testRequestParamTagMap(@RequestParam Map<String, String> allRequestParams) {
        System.out.println("@RequestParam Map<String,String> allRequestParams 接收的所有参数为：\n" + allRequestParams);
        System.out.println("用户名为：" + allRequestParams.get("username"));
        System.out.println("密码为：" + allRequestParams.get("password"));
        // 使用Map封装入参Map object
        Map<String, Object> map = new HashMap<>();
        map.put("params", allRequestParams);    // 套娃开始了
        System.out.println("套娃测试 - params：\n" + map.get("params")); // 从Map中取出Map
        return "success";
    }
}
