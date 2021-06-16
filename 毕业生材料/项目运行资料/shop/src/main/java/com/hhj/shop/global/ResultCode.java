package com.hhj.shop.global;

import lombok.Getter;

@Getter
public enum ResultCode {
    SUCCESS(0, "操作成功"),
    FAILED(1001, "响应失败"),
    VALIDATE_FAILED(1002, "参数校验失败"),
    AUTH_FAIL(1003,"无访问权限"),
    AUTHENTICATION(1004,"用户名或密码错误"),
    CODE_FAIL(1006,"验证码错误"),
    CLASSIFY_FAIL(2001,"商品分类已存在"),
    MEMBER_FAIL(1005,"手机号已注册"),
    ERROR(5000, "未知错误");
    private int code;
    private String msg;

    ResultCode(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
