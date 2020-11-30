package com.example.fuzz.controller;

import com.example.fuzz.common.ApiResponse;
import com.example.fuzz.dto.LoginRequest;
import com.example.fuzz.exception.GlobalException;
import com.example.fuzz.util.JwtTokenUtil;
import com.example.fuzz.vo.SelfUserDetails;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
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
@Api("控制器模块")
public class WebController {
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;


    @ApiOperation("首页")
    @GetMapping("/index")
    public String index() {
        throw new GlobalException("测试异常");
//        return "index";
    }

    @ApiOperation("登录")
    @PostMapping("/login")
    public ApiResponse login(@Validated @RequestBody LoginRequest loginRequest) {
        Authentication authenticate;
        try {
            authenticate = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));
        } catch (Exception e) {
            throw new GlobalException("账号或密码错误");
        }
        SelfUserDetails userDetails = (SelfUserDetails) authenticate.getPrincipal();
        String token = jwtTokenUtil.createJwt(userDetails.getId(), userDetails.getUsername());
        return ApiResponse.ofSuccess(jwtTokenUtil.formatTokenInfo(token, userDetails));
    }

}
