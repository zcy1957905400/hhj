package com.hhj.shop.service;

import com.hhj.shop.dto.*;
import com.hhj.shop.entity.Member;
import com.hhj.shop.entity.User;
import com.hhj.shop.global.ResultVO;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public interface MemberService {
    //    ResultVO<User> delete(Integer id);
//
    ResultVO<String> insert(UserDTO userDTO);
//
    ResultVO<String> update(Member member);
//
    ResultVO<Member> findById(Integer id);

    LoginMDTO login(Member member,HttpServletRequest request);

    ResultDTO findPageWithResult(PageDTO pageDTO);
    //
    ResultVO<String> batchUpdate(BatchDTO batchDTO);
}
