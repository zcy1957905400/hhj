package com.hhj.shop.dto;

import com.hhj.shop.entity.Member;
import com.hhj.shop.entity.User;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class LoginMDTO {

    private String token;

    private Long tokenCreatedDate;

    private Long tokenExpiryDate;

    private String isLogin;

    private Member member;

}
