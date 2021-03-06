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
        System.out.println("全局异常处理============");
        return ApiResponse.ofError(e.getMessage());
    }
//    /**
//     * 拦截 GlobalException 异常
//     */
//    @org.springframework.web.bind.annotation.ExceptionHandler(value = Exception.class)
//    @ResponseBody
//    @ResponseStatus(HttpStatus.OK)
//    private ApiResponse exceptionHandler(Exception e) {
//        System.out.println("222");
//        return ApiResponse.ofError("系统异常");
//    }

}
