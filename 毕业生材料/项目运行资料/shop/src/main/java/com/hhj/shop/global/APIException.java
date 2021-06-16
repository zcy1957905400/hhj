package com.hhj.shop.global;

import lombok.Getter;

@Getter //只要getter方法，无需setter
public class APIException extends RuntimeException {
    private int code;
    private String msg;
    private ResultCode resultCode;

    public ResultCode getResultCode() {
        return resultCode;
    }

    public APIException() {
        this(1001, "接口错误");
    }

    public APIException(String msg) {
        this(1001, msg);
    }

    public APIException(int code, String msg) {
        super(msg);
        this.code = code;
        this.msg = msg;
    }
}
