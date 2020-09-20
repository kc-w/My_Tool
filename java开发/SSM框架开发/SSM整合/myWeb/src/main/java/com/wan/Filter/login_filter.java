package com.wan.Filter;

import com.wan.pojo.user;
import com.wan.service.userService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

//登陆拦截器
public class login_filter implements HandlerInterceptor{



    //该方法会在控制器方法前执行，其返回值代表是否中断后续操作,当返回true时表示向下执行，当返回false时，会中断所有操作
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {
        //获取请求的url
        String url=httpServletRequest.getRequestURI();
        //判断是否以index.jsp结尾
        if(url.indexOf("/index.jsp")>=0){
            return true;
        }

        //获取Session
        HttpSession session=httpServletRequest.getSession();
        user user=(user)session.getAttribute("user");
        //判断session中是否有数据
        if(user!=null){
            return true;
        }

        httpServletRequest.setAttribute("msg","您还没有登陆,请先登陆");
        httpServletRequest.getRequestDispatcher("index.jsp").forward(httpServletRequest,httpServletResponse);

        return false;
    }

    //该方法会在控制器方法调用之后，且解析视图之前执行，可以通过此方法对请求域中的模型和视图做进一步的修改
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    //该分发会在整个请求完成，视图解析完成之后执行，可以通过此方法实现资源清理、记录日志信息
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }
}
