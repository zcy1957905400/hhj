package com.hhj.shop.util;


import com.hhj.shop.service.RedisTempleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

@Component
public class RedisUtil {

    @Autowired
    private RedisTemplate<String, String> redisTemplate;
    @Autowired
    private RedisTempleService redisTempleService;


    /**
     * 保存redis缓存
     * @param key
     * @param value
     */
    public void set(String key, String value) {
        ValueOperations<String, String> valueOperations = redisTemplate.opsForValue();
        valueOperations.set(key, value);
    }

    /**
     * 保存redis缓存
     * @param key
     * @param value
     * @param seconds 失效时间
     */
    public void setex(String key, String value, int seconds) {
        //ValueOperations<String, String> valueOperations = redisTemplate.opsForValue();
        redisTempleService.set(key, key, seconds, TimeUnit.SECONDS);
        //valueOperations.set(key, value, seconds, TimeUnit.SECONDS);
        //System.out.println(valueOperations.get("mykey"));
    }

    /**
     * 保存redis缓存
     * @param key
     * @param value
     * @param seconds 失效时间
     */
    public void seteCode(String key, String value, int seconds) {
        //ValueOperations<String, String> valueOperations = redisTemplate.opsForValue();
        redisTempleService.set(key, value, seconds, TimeUnit.SECONDS);
        //valueOperations.set(key, value, seconds, TimeUnit.SECONDS);
        //System.out.println(valueOperations.get("mykey"));
    }

    /**
  * 获取redis缓存
  * @param key
  * @return
  */
    public Object get(String key) {
//        System.out.println(key);
        ValueOperations valueOperations=redisTemplate.opsForValue();
        //从缓存中读取内容
        try{
        Object result=valueOperations.get(key);
//        System.out.println(result);
        return result;
        }catch (Exception e){
            return null;
        }
    }

}

