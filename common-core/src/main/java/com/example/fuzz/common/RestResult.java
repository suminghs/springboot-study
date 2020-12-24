package com.example.fuzz.common;


import lombok.Data;

@Data
public class RestResult {

    private Integer code;//成功失败在RestConstant类中定义
    private String message;//描述信息
    private Object data;//接口返回值

    public RestResult(){
    }

    public RestResult(Integer code, String message){
        super();
        this.code=code;
        this.message=message;
    }
    public RestResult(Integer code, String message, Object data){
        super();
        this.code=code;
        this.message=message;
        this.data=data;
    }

    /**
     * 成功
     * @return
     */
    public static RestResult success(){
       return new RestResult(MyHttpStatus.HS_OK.getCode(), MyHttpStatus.HS_OK.getDesc());
    }

    public static RestResult success(String msg){
        return new RestResult(MyHttpStatus.HS_OK.getCode(), msg);
    }


    /**
     * 失败
     * @return
     */
    public static RestResult fail(){
        return new RestResult(MyHttpStatus.HS_SYSTEM_ERROR.getCode(),MyHttpStatus.HS_SYSTEM_ERROR.getDesc());
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    @Override
    public String toString(){
        return "code=" +this.getCode()
                + ",message=" + this.getMessage();

    }
}
