package com.example.fuzz.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.exmple.fuzz.service.ITestService;
import org.springframework.stereotype.Component;

/**
 * <p>
 *
 * </p>
 *
 * @author fuzz
 * @since 2020/10/16 14:29
 */
@Component
@Service
public class TestService implements ITestService {
    public String hello() {
        return "hello";
    }
}
