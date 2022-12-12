package com.atguigu.mvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/testRequestParam")
public class RequestMappingController_05_ParamsTest {

    /**
     * “param”：要求请求映射所匹配的请求必须携带param请求参数
     *
     * “!param”：要求请求映射所匹配的请求必须不能携带param请求参数
     *
     * “param=value”：要求请求映射所匹配的请求必须携带param请求参数且param=value
     *
     * “param!=value”：要求请求映射所匹配的请求必须携带param请求参数但是param!=value
     */
    @RequestMapping(
            value = {"/mustHaveParam"},
            method = {RequestMethod.GET},
            params = {"username"}  // 必须携带username参数
    )
    public String mustHaveParam() {
        return "success";
    }

    @RequestMapping(
            value = {"/canNotHaveParam"},
            method = {RequestMethod.GET},
            params = {"!skipLogon"}  // 不能携带skipLogon参数，除skipLogon以外所有其他参数都再匹配规则
    )
    public String canNotHaveParam() {
        return "success";
    }

    @RequestMapping(
            value = {"/paramKeyValueMatch"},
            method = {RequestMethod.GET},
            params = {"user=admin"}  // 参数user=admin
    )
    public String paramKeyValueMatch() {
        return "success";
    }

    @RequestMapping(
            value = {"/paramKeyValueNotMatch"},
            method = {RequestMethod.GET},
            params = {"user!=anonymous"}  // 参数user!=anonymous
    )
    public String paramKeyValueNotMatch() {
        return "success";
    }
}
