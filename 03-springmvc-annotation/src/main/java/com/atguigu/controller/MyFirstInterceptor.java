package com.atguigu.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

public class MyFirstInterceptor implements HandlerInterceptor {

    //Ŀ�귽������֮ǰִ��
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
                             Object handler) {
        System.out.println("MyFirstInterceptor preHandle..." + request.getRequestURI());
        return true;
    }

    //Ŀ�귽��ִ����ȷ�Ժ�ִ��
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
                           ModelAndView modelAndView) {
        System.out.println("MyFirstInterceptor postHandle...");
    }

    //ҳ����Ӧ�Ժ�ִ��
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response,
                                Object handler, Exception ex) {
        System.out.println("MyFirstInterceptor afterCompletion...");
    }

}
