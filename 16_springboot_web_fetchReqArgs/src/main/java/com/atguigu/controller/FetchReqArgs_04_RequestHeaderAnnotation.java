package com.atguigu.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class FetchReqArgs_04_RequestHeaderAnnotation {

    /**
     * @RequestHeader 用法同 @RequestParam
     */
    @RequestMapping("/testRequestHeaderTag")
    public String testRequestParamTag(@RequestHeader("Host") String host) {
        System.out.println("host：" + host); // 调用请求header数值，输出：host：localhost:8080
        return "success";
    }

    @RequestMapping("/testRequestHeaderTagRequired")
    public String testRequestParamTagRequired(@RequestHeader(value = "NotExistHeader", required = false) String notExistHeader) {
        System.out.println("NotExistHeader：" + notExistHeader); // 请求中不存在的header，输出：NotExistHeader：null
        return "success";
    }

    @RequestMapping("/testRequestHeaderTagDefaultValue")
    public String testRequestParamTagDefaultValue(@RequestHeader(value = "NotExistHeader", required = false, defaultValue = "hello") String notExistHeader) {
        System.out.println("NotExistHeader：" + notExistHeader); // 请求中不存在的header，输出默认值：NotExistHeader：hello
        return "success";
    }

    @RequestMapping("/testRequestHeaderTagDefaultValue2")
    public String testRequestParamTagDefaultValue2(@RequestHeader(value = "NotExistHeader", required = false, defaultValue = "hello") String notExistHeader) {
        System.out.println("NotExistHeader：" + notExistHeader); // 调用传参数(NotExistHeader=）的输出：NotExistHeader：hello 【注意：header值为空，被认为没有传】
        return "success";
    }
}
