package com.hhj.shop.controller;

import com.hhj.shop.dto.LoginUDTO;
import com.hhj.shop.entity.User;
import com.hhj.shop.global.ResultCode;
import com.hhj.shop.service.LoginService;
import com.hhj.shop.global.ResultVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Api(tags = "登录模块")
@RestController
@RequestMapping("/user")
public class LoginController {
    @Autowired
    private LoginService loginService;

    @ApiImplicitParam(name = "name",value = "姓名",required = true)
    @ApiOperation(value = "登录")
    @PostMapping("/login")
    //@NotResponseBody  //是否绕过数据统一响应开关
    public ResultVO<LoginUDTO> login(@RequestBody @Valid User user, BindingResult bindingResult, HttpServletRequest request, HttpSession session){
        System.out.println(bindingResult.hasErrors());
        if(bindingResult.hasErrors()){
            return new ResultVO<>(ResultCode.VALIDATE_FAILED,null);
        }
        LoginUDTO LoginUDTO=loginService.login(user,request,session);

        if (LoginUDTO.getIsLogin().equals("1004")){
            return new ResultVO<>(ResultCode.AUTHENTICATION,null);
        }else if  (LoginUDTO.getIsLogin().equals("1003")){
            return new ResultVO<>(ResultCode.AUTH_FAIL,null);
        }else
            return new ResultVO<>(LoginUDTO);
    }

}

