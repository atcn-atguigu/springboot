package com.atguigu.controller;


import com.atguigu.pojo.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class FetchReqArgs_06_Pojo {

    @RequestMapping("/testPojo")
    public String testPojo(User user){
        System.out.println(user); //最终结果-->User{id=null, username='root', password='123456', age=23, sex='男', email='123@qq.com'}
        return "success";
    }
}
