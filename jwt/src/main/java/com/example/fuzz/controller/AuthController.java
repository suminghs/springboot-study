package com.example.fuzz.controller;

import cn.hutool.core.util.RandomUtil;
import com.example.fuzz.common.RestResult;
import com.example.fuzz.common.ResultUtils;
import com.example.fuzz.pojo.User;
import com.example.fuzz.util.JwtTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * <p>
 *
 * </p>
 *
 * @author fuzz
 * @since 2020/12/24 11:18
 */
@RestController
public class AuthController {

    @Autowired
    JwtTokenUtil jwtTokenUtil;

    @GetMapping("/login")
    public RestResult login() {
        User user = new User(RandomUtil.randomLong(), RandomUtil.randomString(5), RandomUtil.randomString(10), RandomUtil.randomString(5));
        String jwt = jwtTokenUtil.createJwt(user.getId(), user.getUsername());
        return ResultUtils.success(jwtTokenUtil.formatTokenInfo(jwt, user));
    }

    @GetMapping("/testToken")
    public RestResult testToken(HttpServletRequest request) {
        return ResultUtils.success("当前用户" + request.getAttribute("username"));
    }

}
