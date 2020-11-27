package com.example.fuzz.exception.handler;

import com.example.fuzz.common.ApiResponse;
import com.example.fuzz.exception.GlobalException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.servlet.http.HttpServletRequest;

/**
 * <p>
 *
 * </p>
 *
 * @author fuzz
 * @since 2020/11/27 16:29
 */
@ControllerAdvice
public class ExceptionHandler {

    /**
     * 拦截 GlobalException 异常
     */
    @org.springframework.web.bind.annotation.ExceptionHandler(value = GlobalException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    private ApiResponse globalExceptionHandler(GlobalException e) {
        System.out.println("111");
        return ApiResponse.ofError("内部错误");
    }

}
