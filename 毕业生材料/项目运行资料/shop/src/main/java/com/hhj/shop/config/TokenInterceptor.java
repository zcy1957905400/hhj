package com.hhj.shop.config;

import com.alibaba.fastjson.JSONObject;
import com.hhj.shop.service.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

public class TokenInterceptor implements HandlerInterceptor{
    @Autowired
    private TokenService tokenService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception
    {
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=utf-8");
        String token = request.getHeader("token");
        Object tk=tokenService.gettoken(token);
        ///System.out.println(tk);
        if (tk==null) {
            PrintWriter out = null;
            JSONObject res = new JSONObject();
            res.put("code", "1000");
            res.put("msg", "用户未登录或登录状态已过期，请登录！");
            res.put("data","null");
            out = response.getWriter();
            out.append(res.toString());
            return false;
        }
//        if(!jedis.exists(token)){
//            response.getWriter().print("登录状态已过期，请重新登录");
//            return false;
//        }
//        int identity = Integer.parseInt(jedis.hget(token,"auth"));
//        String url = request.getRequestURI();
//        if(identity==2){
//            if(url.startsWith("/course")||url.startsWith("/elective")||url.startsWith("/group")||url.startsWith("/module")
//                    ||url.startsWith("/project")||url.startsWith("/school")||url.startsWith("/class")||url.startsWith("/students")
//                    ||url.startsWith("/teachers")||url.startsWith("/test")){
//                response.getWriter().print("你没有权限，无法操作");
//                return false;
//            }
//        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
