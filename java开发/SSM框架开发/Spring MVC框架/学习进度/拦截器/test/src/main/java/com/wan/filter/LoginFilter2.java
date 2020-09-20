package com.wan.filter;

import com.wan.pojo.User;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

//通过实现HandlerInterceptor接口来实现拦截器
public class LoginFilter2 implements HandlerInterceptor{
    //该方法会在控制器方法前执行，其返回值代表是否中断后续操作,当返回true时表示向下执行，当返回false时，会中断所有操作
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)throws Exception{

        System.out.println("拦截器2的一方法执行");
        return true;
    }

    //该方法会在控制器方法调用之后，且解析视图之前执行，可以通过此方法对请求域中的模型和视图做进一步的修改
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView)throws Exception{

        System.out.println("拦截器2的二方法执行");
    }

    //该分发会在整个请求完成，视图解析完成之后执行，可以通过此方法实现资源清理、记录日志信息
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception e)throws Exception{

        System.out.println("拦截器2的三方法执行");
    }
}
