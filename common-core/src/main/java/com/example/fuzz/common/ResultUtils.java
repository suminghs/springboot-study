package com.example.fuzz.common;


import java.util.List;

public class ResultUtils {

    public static RestResult result(Integer code, String message, Object data) {
        RestResult response = new RestResult();
        response.setCode(code);
        response.setMessage(message);
        response.setData(data);
        return response;
    }

    public static RestResult successPage(long totalRow, List result) {
        RestResult response = new RestResult();
        response.setCode(MyHttpStatus.HS_OK.getCode());
        response.setMessage(MyHttpStatus.HS_OK.getDesc());
        response.setData(new MyPage(totalRow, result));
        return response;
    }

    /**
     * 自定义message,带数据对象返回成功
     *
     * @return
     */
    public static RestResult success(String message, Object data) {
        return result(MyHttpStatus.HS_OK.getCode(), message, data);
    }

    /**
     * 自定义code,message返回成功
     *
     * @return
     */
    public static RestResult success(Integer code, String message) {
        return result(MyHttpStatus.HS_OK.getCode(), message, null);
    }

    /**
     * 带数据对象返回成功
     *
     * @param data
     * @return
     */
    public static RestResult success(Object data) {
        return success(MyHttpStatus.HS_OK.getDesc(), data);
    }

    /**
     * 自定义message,带数据对象返回成功
     *
     * @param message
     * @return
     */
    public static RestResult success(String message) {
        return success(message, null);
    }

    /**
     * 默认的返回成功
     *
     * @return
     */
    public static RestResult success() {
        return success(MyHttpStatus.HS_OK.getDesc(), null);
    }

    /**
     * 自定义code，message 返回失败
     */
    public static RestResult error(Integer code, String message) {
        return result(code, message, null);
    }

    /**
     * 自定义message 返回失败
     */
    public static RestResult error(String message) {
        return error(MyHttpStatus.HS_SYSTEM_ERROR.getCode(), message);
    }

    /**
     * 带数据对象返回失败
     */
    public static RestResult error(Object data) {
        return result(MyHttpStatus.HS_SYSTEM_ERROR.getCode(), MyHttpStatus.HS_SYSTEM_ERROR.getDesc(), data);
    }

    /**
     * 带数据对象返回失败
     */
    public static RestResult error(GlobalException exception) {
        return result(exception.getCode(), exception.getMessage(), null);
    }

    /**
     * 默认返回失败
     */
    public static RestResult error() {
        return error(MyHttpStatus.HS_SYSTEM_ERROR.getCode(), MyHttpStatus.HS_SYSTEM_ERROR.getDesc());
    }

}
