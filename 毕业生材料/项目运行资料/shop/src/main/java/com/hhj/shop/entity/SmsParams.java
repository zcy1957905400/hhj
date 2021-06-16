package com.hhj.shop.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

@Data
@Accessors(chain = true)
public class SmsParams implements Serializable{

    /**
     * 验证码
     */
    @JsonProperty(value = "verifyCode")
    private String verifyCode;

    /**
     * 手机号码
     */
    @JsonProperty(value = "phone")
    private String phone;

    public SmsParams() {
    }

    public SmsParams(String phone, String verifyCode) {
        this.phone = phone;
        this.verifyCode = verifyCode;
    }
}

