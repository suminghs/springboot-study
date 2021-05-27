package com.example.fuzz.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *
 * </p>
 *
 * @author fuzz
 * @since 2021/5/11 17:07
 */
@Slf4j
@RestController
public class IndexController {


    @GetMapping({"/", "/index"})
    public String index() {
        log.info("1111");
        log.error("2222");
        return "hello, world";
    }

    @GetMapping({"/error"})
    public String error() throws Exception {
        throw new Exception("error");
    }
}
