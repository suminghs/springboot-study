package com.example.fuzz.exception;

/**
 * <p>
 *
 * </p>
 *
 * @author fuzz
 * @since 2020/11/27 16:00
 */
public class GlobalException extends RuntimeException {
    /** 错误码，和http状态码一致 **/
    private Integer code;

    /** 错误消息 **/
    private String message;

    public GlobalException(String message, Integer code) {
        this.code = code;
        this.message = message;
    }

    public GlobalException(String message) {
        this.code = 500;
        this.message = message;
    }

    public GlobalException(Integer code) {
        this.code = code;
    }


    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
