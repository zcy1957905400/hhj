package com.hhj.shop.entity;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigDecimal;

@Data
@Getter
@Setter
public class User {
    @JsonProperty(value = "id")
    private Integer id;
    @JsonProperty(value = "name")
    private String name;
    @Size(min = 6, max = 11, message = "密码长度必须是6-16个字符")
    @JsonProperty(value = "password")
    private String password;
    @JsonProperty(value = "phone")
    private String phone;
    @JsonProperty(value = "portrait")
    private String portrait;
    @JsonProperty(value = "type")
    private Integer type;
    @JsonProperty(value = "leaveAmount")
    private BigDecimal leaveAmount;
    @JsonProperty(value = "status")
    private Integer status;
    @JsonProperty(value = "code")
    private String code;
    @JsonProperty(value = "SmsCode")
    private String SmsCode;

    public User(Integer id, String name, String password, String phone, String portrait, Integer type,Integer status, BigDecimal leaveAmount,String code,String SmsCode) {
        this.id = id;
        this.name = name;
        this.password = password;
        this.phone = phone;
        this.portrait = portrait;
        this.type = type;
        this.leaveAmount = leaveAmount;
        this.status=status;
        this.code=code;
        this.SmsCode=SmsCode;
    }

    public User() {

    }
}
