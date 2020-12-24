package com.example.fuzz.config;

import com.example.fuzz.common.GlobalException;
import com.example.fuzz.common.MyHttpStatus;
import com.example.fuzz.util.JwtTokenUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class JwtInterceptor extends HandlerInterceptorAdapter {

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        final String authHeader = request.getHeader(JwtTokenUtil.AUTH_HEADER_KEY);
        System.out.println("## authHeader= " + authHeader);
        if (StringUtils.isBlank(authHeader) || !authHeader.startsWith(JwtTokenUtil.TOKEN_PREFIX)) {
            System.out.println("##  用户未登录 ##");
            throw new GlobalException("token 验证失败", MyHttpStatus.HS_TOKEN_INSUFFICIENT.getCode());
        }
        final String token = authHeader.substring(7);
        jwtTokenUtil.parseJwt(token);
        request.setAttribute("username", jwtTokenUtil.getUsername(token));

        return true;
    }
}
