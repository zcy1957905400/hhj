package com.hhj.shop.entity;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class Member {
    /**
     * 用户id，主键
     */
    @JsonProperty(value = "id")
    private Integer id;

    /**
     * 手机号
     */
    @JsonProperty(value = "phone")
    private String phone;

    /**
     * 姓名
     */
    @JsonProperty(value = "name")
    private String name;

    /**
     * 密码
     */
    @JsonProperty(value = "pwd")
    private String pwd;

    /**
     * 头像
     */
    @JsonProperty(value = "portrait")
    private String portrait;

    /**
     * 验证码
     */
    @JsonProperty(value = "code")
    private String code;

    /**
     * smscode
     */
    @JsonProperty(value = "SmsCode")
    private String SmsCode;

}
