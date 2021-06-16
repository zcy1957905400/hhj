package com.hhj.shop.service.impl;

import com.hhj.shop.controller.MemberController;
import com.hhj.shop.dto.*;
import com.hhj.shop.entity.Member;
import com.hhj.shop.entity.User;
import com.hhj.shop.global.ResultCode;
import com.hhj.shop.global.ResultVO;
import com.hhj.shop.repository.LoginRepository;
import com.hhj.shop.repository.MemberRepository;
import com.hhj.shop.service.MemberService;
import com.hhj.shop.service.TokenService;
import com.hhj.shop.util.MD5Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@Service
public class MemberServiceImpl implements MemberService {

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private TokenService tokenService;

    @Autowired
    private LoginRepository loginRepository;

    protected static Logger log = LoggerFactory.getLogger(MemberServiceImpl.class);

    @Override
    public LoginMDTO login(Member member, HttpServletRequest request) {
        log.info(member.toString());
        LoginMDTO loginMDTO = new LoginMDTO();
        member.setPwd(MD5Util.string2MD5(member.getPwd()));
        Member result = memberRepository.login(member);
//        String rand = (String) session.getAttribute("rand");
//        if (!rand.equalsIgnoreCase(member.getCode())) {
//            loginMDTO.setIsLogin("1006");
//        }
        if (result != null) {//成功
            String userAgentStr = request.getHeader("user-agent");
            //生成token
            String token = this.tokenService.generateToken(userAgentStr, result.getName());
            System.out.println(token);
            //保存token
            this.tokenService.savemember(token, member);
            //组织前台需要的信息
            loginMDTO.setToken(token);
            loginMDTO.setIsLogin("true");
            loginMDTO.setTokenCreatedDate(System.currentTimeMillis());
            loginMDTO.setTokenExpiryDate(System.currentTimeMillis() + 7200000);
            loginMDTO.setMember(result);

        } else {
            loginMDTO.setIsLogin("1004");
        }
        return loginMDTO;
}


    @Override
    public ResultVO<String> insert(UserDTO userDTO) {
        Member member = new Member();
        member.setPhone(userDTO.getPhone());
        member.setName(userDTO.getName());
        member.setPortrait(userDTO.getPortrait());
        member.setPwd(MD5Util.string2MD5(userDTO.getPassword()));
        int count = memberRepository.findByPhoneWithCount(member.getPhone());
        System.out.println(count);
        if (count == 0) {
            int result = memberRepository.insert(member);
            if (result > 0) {
                return new ResultVO<>(ResultCode.SUCCESS, null);

            } else
                return new ResultVO<>(ResultCode.ERROR, null);
        } else
            return new ResultVO<>(ResultCode.MEMBER_FAIL, null);
    }

    @Override
    public ResultVO<String> update(Member member) {
        member.setPwd(MD5Util.string2MD5(member.getPwd()));
        int result = memberRepository.updateDynamic(member);
        if (result > 0) {
            return new ResultVO<>(ResultCode.SUCCESS, null);
        }
        return new ResultVO<>(ResultCode.ERROR, null);
    }

    @Override
    public ResultVO<Member> findById(Integer id) {
        Member member = memberRepository.selectById(id);
        return new ResultVO<>(ResultCode.SUCCESS, member);
    }

    @Override
    public ResultDTO findPageWithResult(PageDTO pageDTO) {
        String keywords= pageDTO.getKeywords();
        if(keywords!=null&&keywords!=""){
            pageDTO.setKeywords('%' + keywords + '%');
        }
        System.out.println(keywords);
        //共多少个类型
        int totalCount =memberRepository.findPageWithCount(pageDTO);
        //计算共多少页
        int pageSize = pageDTO.getLimit();
        pageDTO.setPage((pageDTO.getPage()-1)*pageSize);
        //int totalPage = (int)Math.ceil(totalCount*1.0/pageSize);
        System.out.println(pageDTO);
        List<Member> memberList=memberRepository.findPageWithResult(pageDTO);
        ResultDTO resultDTO=new ResultDTO();
        //resultDTO.setData(userList);
        //ResultPageVO<List<resultDTO>> listResultPageVO=new ResultPageVO<>(resultDTO);
        System.out.println(resultDTO);
        if (memberList.size()!=0) {
            resultDTO.setCode(0);
            log.info("总数："+totalCount);
            resultDTO.setCount(totalCount);
            resultDTO.setMsg("请求成功");
            resultDTO.setData(memberList);
            return resultDTO;
        }else {
            resultDTO.setCode(5000);
            resultDTO.setCount(totalCount);
            resultDTO.setMsg("未知错误");
            return resultDTO;
        }
    }

    @Override
    public ResultVO<String> batchUpdate(BatchDTO batchDTO) {
        return null;
    }
}
