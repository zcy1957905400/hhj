package com.hhj.shop.controller;

import com.hhj.shop.entity.SmsParams;
import com.hhj.shop.global.ResultCode;
import com.hhj.shop.global.ResultVO;
import com.hhj.shop.service.SendSmsService;
import com.hhj.shop.service.StatisticsService;
import com.hhj.shop.service.TokenService;
import com.hhj.shop.service.impl.AddressServiceImpl;
import com.hhj.shop.util.NotResponseBody;
import com.hhj.shop.util.RandomCode;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(tags = "短信验证码")
@RestController
public class SmsCodeController {

    @Autowired
    private TokenService tokenService;
    @Autowired
    private SendSmsService sendSmsService;
    protected static Logger log = LoggerFactory.getLogger(SmsCodeController.class);


    @PostMapping("/SmsCode")
    @NotResponseBody  //是否绕过数据统一响应开关
    public ResultVO<String> SmsCode(@RequestBody SmsParams smsParams) {
        // List<List<String>> LineChart = statisticsService.findByOrderSales();
        String code = RandomCode.RandomCode();
        log.info("尊敬的用户：您的验证码为：" + code + "，15分钟有效，为保障帐户安全，请勿向任何人提供此验证码。");
        boolean sendFlag = sendSmsService.sendSms(smsParams.getPhone(), code);
        log.info(smsParams.getPhone());
        //boolean sendFlag=true;
        if (sendFlag) {
            this.tokenService.saveCode(smsParams.getPhone(),code);
            return new ResultVO<>(ResultCode.SUCCESS, code);
        } else {
            return new ResultVO<>(ResultCode.ERROR, null);
        }

    }

}
