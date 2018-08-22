package com.michaelcgood.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.michaelcgood.interceptor.LoginCheckInterceptor;

//@Configuration
public class WebMvcConfig extends WebMvcConfigurerAdapter {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new LoginCheckInterceptor())
                .addPathPatterns("/*")
                .excludePathPatterns("/login"); //로그인 쪽은 예외처리를 한다.
    }
}