package com.hhj.shop.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Size;

@Data
@Getter
@Setter
public class UserDTO {

    private Integer id;
    private String name;
    @Size(min = 6, max = 11, message = "密码长度必须是6-16个字符")
    private String password;
    private String phone;
    private String portrait;
    private Integer regId;
    private String add_name;
    private String detailed;
    private Integer type;
}
