package com.iot;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@MapperScan("com.iot.mapper")
@ComponentScan({"com.iot.Controller", "com.iot.service","com.iot"})
@SpringBootApplication
public class Application {
   public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}