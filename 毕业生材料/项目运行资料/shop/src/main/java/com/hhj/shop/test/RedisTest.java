package com.hhj.shop.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

//单元测试类
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class RedisTest {
    //定义日志
    private static final Logger log= LoggerFactory.getLogger(RedisTest.class);
    //由于之前已经自定义注入RedisTemplate组件，因而在此可以直接自动装配
    @Autowired
    private RedisTemplate redisTemplate;
    //采用RedisTemplate将字符串信息写入缓存中并读取出来
    @Test
    public void one(){
        log.info("------开始RedisTemplate操作组件实战----");
        //定义字符串内容及存入缓存的key
        final String content="token:PC-c4ca4238a0b923820dcc509a6f75849b-20210407215443251-222517";
        final String key="token:PC-c4ca4238a0b923820dcc509a6f75849b-20210407215443251-222517";
        //Redis通用的操作组件
        ValueOperations valueOperations=redisTemplate.opsForValue();
        //将字符串信息写入缓存中
        log.info("写入缓存中的内容：{} ", content);
        valueOperations.set(key, content);
        //从缓存中读取内容
        Object result=valueOperations.get(key);
        log.info("读取出来的内容：{} ", result);
    }
}

