package com.hhj.shop.util;

import java.util.Random;

/**
 * @author: hhj
 * @Description: java生成一个6位的随机数（验证码）
 */
public class RandomCode {

//    public static void main(String[] args) {
//        System.out.println(RandomCode());
//    }

    public static String RandomCode(){
        String str="0123456789";
        StringBuilder sb=new StringBuilder(6);
        for(int i=0;i<6;i++)
        {
            char ch=str.charAt(new Random().nextInt(str.length()));
            sb.append(ch);
        }
        return sb.toString();
    }
}

