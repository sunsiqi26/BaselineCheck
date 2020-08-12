package com.gxa.child.exception;

/**
 * 数据验证异常
 */
public class DataException  extends RuntimeException{

    private Integer code;//状态码
    private String msg;//提示信息

    public DataException( Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
