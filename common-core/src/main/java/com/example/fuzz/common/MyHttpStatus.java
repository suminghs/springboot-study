package com.example.fuzz.common;

public enum MyHttpStatus {
    HS_OK(200, "成功"),
    HS_UNAUTHORIZED(401, "无访问权限"),
    HS_FORBIDDEN(403, "拒绝访问"),
    HS_SYSTEM_ERROR(500, "未知异常，请联系管理员！"),
    HS_TOKEN_EXPIRED(600, "token过期"),
    HS_TOKEN_INSUFFICIENT(610, "token身份验证异常"),
    HS_USER_EXISTS(620, "数据已存在"),
    HS_NOT_NULL(630, "提交数据不得为空");
    private Integer code;
    private String desc;

    MyHttpStatus(Integer code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}