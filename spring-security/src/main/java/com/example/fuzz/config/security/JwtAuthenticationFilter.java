package com.example.fuzz.config.security;

import cn.hutool.core.util.StrUtil;
import com.example.fuzz.config.Audience;
import com.example.fuzz.exception.GlobalException;
import com.example.fuzz.service.SelfUserDetailService;
import com.example.fuzz.util.JwtTokenUtil;
import com.example.fuzz.util.ResponseUtil;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * <p>
 *
 * </p>
 *
 * @author fuzz
 * @since 2020/11/27 15:44
 */
@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {
    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private SelfUserDetailService selfUserDetailService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

//        if (request.getRequestURL().equals(""))
        System.out.println(request.getRequestURL());
        System.out.println(request.getRequestURI());
        if ("/index".equals(request.getRequestURI())) {
            filterChain.doFilter(request, response);
            return;
        }

        final String authHeader = request.getHeader(JwtTokenUtil.AUTH_HEADER_KEY);
        System.out.println("## authHeader= " + authHeader);
        if (StrUtil.isBlank(authHeader) || !authHeader.startsWith(JwtTokenUtil.TOKEN_PREFIX)) {
//            System.out.println("##  用户未登录 ##");
//            ResponseUtil.renderJson(response, "token验证失败");
//            return;
        }
//        final String token = authHeader.substring(7);
//        jwtTokenUtil.parseJwt(token);
//        String username = jwtTokenUtil.getUsername(token);
//
//        UserDetails userDetails = selfUserDetailService.loadUserByUsername(username);
//        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
//        authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
//
//        SecurityContextHolder.getContext()
//                .setAuthentication(authentication);

        filterChain.doFilter(request, response);

    }
}
