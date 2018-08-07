package com.example.interceptor;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
public class SessionConfiguration extends WebMvcConfigurerAdapter
{
    /*
    * 接下来我们需要将SessionInterceptor拦截器添加到SpringBoot的配置中，
    * 让SpringBoot项目有这么一个拦截器存在，我们新创建一个SessionConfiguration，将拦截器的配置以及拦截路径配置好*/
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new SessionInterceptor()).addPathPatterns("/user/**");
        super.addInterceptors(registry);
    }





}
