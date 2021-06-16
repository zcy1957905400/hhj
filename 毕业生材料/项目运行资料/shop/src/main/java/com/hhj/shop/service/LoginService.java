package com.hhj.shop.service;

import com.hhj.shop.dto.LoginUDTO;
import com.hhj.shop.entity.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;


public interface LoginService {
    public LoginUDTO login(User user, HttpServletRequest request, HttpSession session);
}