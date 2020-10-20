package com.example.fuzz.service;

import com.alibaba.dubbo.config.annotation.Reference;
import org.springframework.stereotype.Service;

/**
 * <p>
 *
 * </p>
 *
 * @author fuzz
 * @since 2020/10/16 14:58
 */
@Service
public class UserService {
    @Reference
    private ITestService testService;

    public void hello() {
        System.out.println("rpc调用：》》" + testService.hello());
    }
}
