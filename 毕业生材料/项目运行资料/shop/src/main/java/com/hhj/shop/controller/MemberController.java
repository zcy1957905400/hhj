package com.hhj.shop.controller;

import com.hhj.shop.dto.LoginMDTO;
import com.hhj.shop.dto.PageDTO;
import com.hhj.shop.dto.ResultDTO;
import com.hhj.shop.dto.UserDTO;
import com.hhj.shop.entity.Member;
import com.hhj.shop.global.ResultCode;
import com.hhj.shop.global.ResultVO;
import com.hhj.shop.service.MemberService;
import com.hhj.shop.service.TokenService;
import com.hhj.shop.util.NotResponseBody;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Api(tags = "会员管理模块")
@RestController
@RequestMapping("/member")
public class MemberController {

    @Autowired
    private MemberService memberService;

    @Autowired
    private TokenService tokenService;

    protected static Logger log = LoggerFactory.getLogger(MemberController.class);

    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "会员id", required = true)
    })
    @ApiOperation(value = "会员查询")
    @GetMapping("/findById")
    @NotResponseBody  //是否绕过数据统一响应开关
    public ResultVO<Member> findById(@Valid Member member, BindingResult bindingResult) {
        log.info("管理员信息分页" + member.toString());
        return memberService.findById(member.getId());
    }

    @ApiOperation(value = "添加用户")
    @PostMapping("/add")
    //@NotResponseBody  //是否绕过数据统一响应开关
    public ResultVO<String> add(@Valid @RequestBody UserDTO userDTO, BindingResult bindingResult) {
        log.info("管理员信息：" + userDTO.toString());
        return memberService.insert(userDTO);
    }

    @ApiOperation(value = "修改会员信息")
    @PostMapping("/update")
    //@NotResponseBody  //是否绕过数据统一响应开关
    public ResultVO<String> update(@Valid @RequestBody Member member, BindingResult bindingResult) {
        log.info("会员信息：" + member.toString());
        Object tk = tokenService.gettoken(member.getPhone());
        log.info(tk.toString());
        if (tk.equals( member.getSmsCode())) {
            return memberService.update(member);
        } else {
            return new ResultVO<>(ResultCode.CODE_FAIL, null);
        }
    }

    @ApiImplicitParams({
            @ApiImplicitParam(name = "phone", value = "姓名", required = true),
            @ApiImplicitParam(name = "pwd", value = "密码", required = true),
            @ApiImplicitParam(name = "code", value = "验证码", required = true)
    })
    @ApiOperation(value = "登录")
    @PostMapping("/login")
    //@NotResponseBody  //是否绕过数据统一响应开关
    public ResultVO<LoginMDTO> login(@Valid @RequestBody Member member, BindingResult bindingResult, HttpServletRequest request) {
        System.out.println(bindingResult.hasErrors());
        if (bindingResult.hasErrors()) {
            return new ResultVO<>(ResultCode.VALIDATE_FAILED, null);
        }
        LoginMDTO dto = memberService.login(member, request);

        if (dto.getIsLogin().equals("1004")) {
            return new ResultVO<>(ResultCode.AUTHENTICATION, null);
        } else if (dto.getIsLogin().equals("1003")) {
            return new ResultVO<>(ResultCode.AUTH_FAIL, null);
        } else if (dto.getIsLogin().equals("1006")) {
            return new ResultVO<>(ResultCode.CODE_FAIL, null);
        } else
            return new ResultVO<>(dto);
    }

    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", value = "页码", required = true),
            @ApiImplicitParam(name = "limit", value = "每页多少条", required = true),
    })
    @ApiOperation(value = "会员查询")
    @GetMapping("/ByPage")
    @NotResponseBody  //是否绕过数据统一响应开关
    public ResultDTO UserByPage(@Valid PageDTO pageDTO, BindingResult bindingResult) {
        log.info("会员信息分页" + pageDTO.toString());
        return memberService.findPageWithResult(pageDTO);
    }
}
