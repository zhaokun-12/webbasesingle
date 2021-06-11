package com.example.webbasesingle;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.example.webbasesingle.dao")
public class WebbasesingleApplication {

    public static void main(String[] args) {
        SpringApplication.run(WebbasesingleApplication.class, args);
    }

}
