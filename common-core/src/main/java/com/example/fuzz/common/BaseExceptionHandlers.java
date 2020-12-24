package com.example.fuzz.common;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
public class BaseExceptionHandlers extends ResponseEntityExceptionHandler {


    /**
     * 拦截 GlobalException 异常
     *
     * @param request
     * @param e
     * @return
     */
    @ExceptionHandler(value = GlobalException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    private RestResult globalExceptionHandler(HttpServletRequest request, GlobalException e) {
        logger.error("RUL: " + request.getRequestURL() + " 发生自定义异常被统一异常处理",e);
        return ResultUtils.error(e.getMessage());
    }


    /**
     * 拦截系统异常
     *
     * @param request
     * @param e
     * @return
     */
//    @ExceptionHandler(Throwable.class)
//    @ResponseBody
//    @ResponseStatus(HttpStatus.OK)
//    private RestResult exceptionHandler(HttpServletRequest request, Exception e, HttpServletResponse response) {
//        logger.error("RUL: " + request.getRequestURL() + " 发生以下系统异常被统一异常处理",e);
//        e.printStackTrace();
//        if(e.getClass().getName().equals("org.springframework.security.access.AccessDeniedException")){
//            return ResultUtils.error(HttpStatus.FORBIDDEN.value(),"您无此操作权限，请联系管理员");
//        }
//        return ResultUtils.error();
//    }

}