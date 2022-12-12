package com.atguigu.mvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class RequestMappingController_04_MethodTest {

    @RequestMapping(
            value = {"/testRequestMethod"},
            method = {RequestMethod.GET, RequestMethod.POST} // method值也可以同时存在多个匹配，默认不设置则支持所有METHOD
    )
    public String testRequestMethod() {
        // 设置视图名称
        return "success";
    }

    /**
     * 注：
     * 1、对于处理指定请求方式的控制器方法，SpringMVC中提供了@RequestMapping的派生注解
     * 处理get请求的映射–>@GetMapping
     * 处理post请求的映射–>@PostMapping
     * 处理put请求的映射–>@PutMapping
     * 处理delete请求的映射–>@DeleteMapping
     *
     * 2、常用的请求方式有get，post，put，delete
     * 但是目前浏览器只支持get和post，若在form表单提交时，为method设置了其他请求方式的字符串（put或delete），则按照默认的请求方式get处理
     * 若要发送put和delete请求，则需要通过spring提供的过滤器HiddenHttpMethodFilter，在RESTful部分会讲到
     */
    @GetMapping("/testGetMapping")
    public String testGetMapping() {
        return "success";
    }

    @PostMapping("/testPostMapping")
    public String testPostMapping() {
        return "success";
    }

    @PutMapping("/testPutMapping")
    public String testPutMapping() {
        return "success";
    }

    @DeleteMapping("/testDeleteMapping")
    public String testDeleteMapping() {
        return "success";
    }

    @PatchMapping("/testPatchMapping")
    public String testPatchMapping() {
        return "success";
    }
}
