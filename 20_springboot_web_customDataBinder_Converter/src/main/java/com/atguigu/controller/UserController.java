package com.atguigu.controller;

import com.atguigu.pojo.User_LocalDateTime;
import com.atguigu.pojo.User2_Date;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
public class UserController {
    @PostMapping("/testConverter/user/LocalDateTime")
    public String converterTest1(@RequestBody User_LocalDateTime user, Model model) {
        // 设置到共享域后再在视图中渲染
        model.addAttribute("userInputted", user);
        return "success";
    }

    @PostMapping("/testConverter/user/Date")
    public String converterTest2(@RequestBody User2_Date user, Model model) {
        // 设置到共享域后再在视图中渲染
        model.addAttribute("userInputted", user);
        return "success";
    }
}
