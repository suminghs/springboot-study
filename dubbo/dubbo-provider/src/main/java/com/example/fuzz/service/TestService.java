package com.example.fuzz.service;

import com.alibaba.dubbo.config.annotation.Service;
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
    @Override
    public String hello() {
        return "hello";
    }
}
