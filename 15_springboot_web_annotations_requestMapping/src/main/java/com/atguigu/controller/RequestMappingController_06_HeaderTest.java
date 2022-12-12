package com.atguigu.mvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/testRequestHeader")
public class RequestMappingController_06_HeaderTest {

    /**
     * “header”：要求请求映射所匹配的请求必须携带header请求头信息
     *
     * “!header”：要求请求映射所匹配的请求必须不能携带header请求头信息
     *
     * “header=value”：要求请求映射所匹配的请求必须携带header请求头信息且header=value
     *
     * “header!=value”：要求请求映射所匹配的请求必须携带header请求头信息且header!=value
     */
    @RequestMapping(
            value = {"/mustHaveHeader"},
            method = {RequestMethod.GET},
            headers = {"Accept"}  // 必须携带Accept请求头
    )
    public String mustHaveHeader() {
        return "success";
    }

    @RequestMapping(
            value = {"/canNotHaveHeader"},
            method = {RequestMethod.GET},
            headers = {"!Upgrade-Insecure-Requests"}  // 不能携带Upgrade-Insecure-Requests请求头，这个浏览器会发
    )
    public String canNotHaveHeader() {
        return "success";
    }

    @RequestMapping(
            value = {"/headerKeyValueMatch"},
            method = {RequestMethod.GET},
            headers = {"Host=localhost:8080"}  // 请求头Host=localhost:8080
    )
    public String headerKeyValueMatch() {
        return "success";
    }

    @RequestMapping(
            value = {"/headerKeyValueNotMatch"},
            method = {RequestMethod.GET},
            headers = {"Host!=localhost:8080"}  // 请求头Host!=localhost:8080
    )
    public String headerKeyValueNotMatch() {
        return "success";
    }
}
