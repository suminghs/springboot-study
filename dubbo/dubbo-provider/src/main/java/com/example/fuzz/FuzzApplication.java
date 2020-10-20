package com.example.fuzz;

import com.alibaba.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@EnableDubbo
@SpringBootApplication
public class FuzzApplication {

    public static void main(String[] args) {
        SpringApplication.run(FuzzApplication.class, args);
    }

}
