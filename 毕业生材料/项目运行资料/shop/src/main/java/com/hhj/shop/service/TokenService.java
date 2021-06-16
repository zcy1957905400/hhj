package com.hhj.shop.service;

import com.alibaba.fastjson.JSONObject;
import com.hhj.shop.entity.Member;
import com.hhj.shop.entity.User;
import com.hhj.shop.util.RedisUtil;
import nl.bitwalker.useragentutils.UserAgent;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

@Service("tokenService")
public class TokenService {

    @Resource
    private RedisUtil redisUtil;


    //生成token(格式为token:设备-加密的用户名-时间-六位随机数)
    public String generateToken(String userAgentStr, String username) {
        StringBuilder token = new StringBuilder("token:");
        //设备
        UserAgent userAgent = UserAgent.parseUserAgentString(userAgentStr);
        if (userAgent.getOperatingSystem().isMobileDevice()) {
            token.append("MOBILE-");
        } else {
            token.append("PC-");
        }
        //加密的用户名
        token.append(DigestUtils.md5Hex(username) + "-");
        //时间
        token.append(new SimpleDateFormat("yyyyMMddHHmmssSSS").format(new Date()) + "-");
        //六位随机字符串
        token.append(new Random().nextInt(999999 - 111111 + 1) + 111111 );
        System.out.println("token-->" + token.toString());
        return token.toString();
    }

    //把token存到redis中
    public void save(String token,User user) {
        if (token.startsWith("token:PC")) {
            redisUtil.setex(token, JSONObject.toJSONString(user.getName()), 2*60*60);
        } else {
            redisUtil.set(token, JSONObject.toJSONString(user));
        }
    }

    //把code存到redis中
    public void saveCode(String phone,String code) {
        redisUtil.seteCode(phone, code, 5*60*60);
    }

    //把token存到redis中
    public void savemember(String token,Member member) {
        if (token.startsWith("token:PC")) {
            redisUtil.setex(token, JSONObject.toJSONString(member.getName()), 2*60*60);
        } else {
            redisUtil.set(token, JSONObject.toJSONString(member));
        }
    }

    //取出token
    public Object gettoken(String token){
        return redisUtil.get(token);
    }

}
