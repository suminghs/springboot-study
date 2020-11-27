package com.example.fuzz.controller;

import com.example.fuzz.exception.GlobalException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *
 * </p>
 *
 * @author fuzz
 * @since 2020/11/27 14:34
 */
@RestController
public class WebController {

    @GetMapping("/index")
    public String index() {
        throw new GlobalException("测试异常");
//        return "index";
    }

}
