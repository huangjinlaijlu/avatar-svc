package com.hikvision.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description 测试类
 * @Author Jinlai
 * @Date 2025/4/20 11:15
 * @Version 1.0
 **/
@RestController
@RequestMapping("/api/")
public class TestController {

    @Value("${user.age}")
    private String username;

    @GetMapping("/inFile")
    public String inFile() {
        System.out.println(username);
        return username;
    }
}
