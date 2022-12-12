package com.atguigu.mvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class RequestMappingController_08_ValuePlaceHolder {

    @RequestMapping("/testPathVariable/{username}/{id}")
    public String testPathVariable(@PathVariable("username") String username, @PathVariable("id") String id) {
        System.out.println("username is: " + username + ", id is: " + id);
        return "success";
    }
}
