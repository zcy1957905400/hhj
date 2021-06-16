package com.hhj.shop.service.impl;

import com.hhj.shop.dto.LoginUDTO;
import com.hhj.shop.entity.User;
import com.hhj.shop.repository.LoginRepository;
import com.hhj.shop.service.LoginService;
import com.hhj.shop.service.TokenService;
import com.hhj.shop.util.MD5Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Service
public class LoginServiceImpl implements LoginService {

    @Autowired
    private TokenService tokenService;

    @Autowired
    private LoginRepository loginRepository;

    protected static Logger log = LoggerFactory.getLogger(LoginServiceImpl.class);

    @Override
    public LoginUDTO login(User user,HttpServletRequest request,HttpSession session) {
        LoginUDTO LoginUDTO = new LoginUDTO();
        user.setPassword(MD5Util.string2MD5(user.getPassword()));
        User result=new User();
        if (user.getName()==null){
            String rand = (String) session.getAttribute("rand");
            log.info(rand+"-----"+user.getCode());
            if (!rand.equalsIgnoreCase(user.getCode())) {
                LoginUDTO.setIsLogin("1006");
            }
            result= loginRepository.login(user);
        }else {
           result= loginRepository.loginByname(user);
        }

        if(result!=null) {//成功
            if (result.getType()==1){
                String userAgentStr = request.getHeader("user-agent");
                //生成token
                String token = this.tokenService.generateToken(userAgentStr, result.getName());
                System.out.println(token);
                //保存token
                this.tokenService.save(token, user);
                //组织前台需要的信息

                LoginUDTO.setToken(token);
                LoginUDTO.setIsLogin("true");
                LoginUDTO.setTokenCreatedDate(System.currentTimeMillis());
                LoginUDTO.setTokenExpiryDate(System.currentTimeMillis() + 7200000);
                LoginUDTO.setUser(result);

            } else { //失败
                LoginUDTO.setIsLogin("1003");
            }

        }else {
            LoginUDTO.setIsLogin("1004");
        }
        return LoginUDTO;
    }
}
