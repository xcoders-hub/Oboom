package com.example.interceptor;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
/**
 * @author liangkanglin
 * @date 2018/8/7 9:27
 * @param
 * @return
 * code:用户登录对Session的拦截
 */

public class SessionInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object o) throws Exception {
        System.out.println(request.getRequestURI());
        //登录不做拦截
        if(request.getRequestURI().equals("/city/login") || request.getRequestURI().equals("/user/login_view"))
        {
            return true;
        }
        //验证session是否存在
        Object obj = request.getSession().getAttribute("_session_user");
        if(obj == null)
        {
            System.out.println("-------------------->"+request.getLocalAddr());
            System.out.println("-------------------->"+request.getRemoteAddr());
            System.out.println("-------------------->"+request.getRemoteUser());
            System.out.println("-------------------->"+request.getServletPath());
            System.out.println("-------------------->"+request.getRemoteHost());
            System.out.println("-------------------->"+request.getContextPath());
            response.sendRedirect("/city/3");
            return true;
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {
        System.out.println("-------------------->postHandle");
    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {
        System.out.println("-------------------->afterCompletion");
    }
}

