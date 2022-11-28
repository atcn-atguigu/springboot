package com.atguigu.boot.controller;

import com.atguigu.boot.bean.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestYamlDataController {

    @Autowired
    private Person person;

    @GetMapping("/")
    public Person getPerson() {
        return person;
    }

}
