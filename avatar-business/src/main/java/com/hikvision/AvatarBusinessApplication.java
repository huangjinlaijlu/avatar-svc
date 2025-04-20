package com.hikvision;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jackson.JacksonAutoConfiguration;

/**
 * @Description 主启动类
 * @Author Jinlai
 * @Date 2025/4/19 23:04
 * @Version 1.0
 **/
@SpringBootApplication(exclude = {JacksonAutoConfiguration.class})
public class AvatarBusinessApplication {
    public static void main(String[] args) {
        SpringApplication.run(AvatarBusinessApplication.class, args);
    }
}