package com.iot;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(basePackages = "com.iot.mapper")
public class Application {
    static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}