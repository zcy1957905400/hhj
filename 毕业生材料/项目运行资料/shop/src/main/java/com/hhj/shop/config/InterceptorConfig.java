package com.hhj.shop.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.ArrayList;
import java.util.List;

@Configuration
class InterceptorConfig implements WebMvcConfigurer
{
    @Bean
    public TokenInterceptor initAuthInterceptor()
    {
        return new TokenInterceptor();
    }

//    @Autowired
//    private TokenInterceptor  tokenInterceptor;
//    @Autowired
//    private AdminAuthorityInterceptor adminAuthorityInterceptor;


    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        List<String> excludePathlist=new ArrayList<>();
        excludePathlist.add("/alipay/pay");
        excludePathlist.add("/alipay/aliPayCallBack");
        excludePathlist.add("/classify/ByPage1");
        excludePathlist.add("/goods/findGoodsWithClassify");
        excludePathlist.add("/goods/findCarousel");
        excludePathlist.add("/user/login");
        excludePathlist.add("/user/update");
        excludePathlist.add("/user/add");
        excludePathlist.add("/goods/findById");
        excludePathlist.add("/Region/findAllRegion");
        excludePathlist.add("/member/login");
        excludePathlist.add("/member/add");
        excludePathlist.add("/member/update");
        excludePathlist.add("/index");
        excludePathlist.add("/validateCode");
        excludePathlist.add("/welcome");
        excludePathlist.add("/classify_list");
        excludePathlist.add("/classify_batch");
        excludePathlist.add("/classify_add");
        excludePathlist.add("/index/**");
        excludePathlist.add("/admin/**");
        excludePathlist.add("/goods/findGoods");
        excludePathlist.add("/goods/imageUpload");
        excludePathlist.add("/static/**");
        excludePathlist.add("/templates/**");
        excludePathlist.add("/ajaxUpload");
        excludePathlist.add("/classify/ByPage");
        excludePathlist.add("/swagger-resources/**");
        excludePathlist.add("/webjars/**");
        excludePathlist.add("/v2/**");
        excludePathlist.add("/swagger-ui.html/**");
        excludePathlist.add("/doc.html/**");
        excludePathlist.add("/api-docs-ext/**");
        registry.addInterceptor(initAuthInterceptor()).addPathPatterns("/**")
                .excludePathPatterns(excludePathlist);///放行url地址
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/**").addResourceLocations("classpath:/META-INF/resources/")
                .addResourceLocations("classpath:/static/");
    }

}

