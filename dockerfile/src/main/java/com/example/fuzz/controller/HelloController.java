package com.example.fuzz.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *
 * </p>
 *
 * @author fuzz
 * @since 2020/11/9 11:13
 */
@RestController
public class HelloController {

    @GetMapping("/hello")
    public String hello() {
        return "hello, docker";
    }

}
