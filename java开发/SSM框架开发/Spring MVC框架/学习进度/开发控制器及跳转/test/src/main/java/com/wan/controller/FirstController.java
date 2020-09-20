package com.wan.controller;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;


public class FirstController implements Controller {

    public ModelAndView handleRequest(javax.servlet.http.HttpServletRequest httpServletRequest, javax.servlet.http.HttpServletResponse httpServletResponse) throws Exception {
        //创建ModelAndView对象
        ModelAndView mav=new ModelAndView();
        //向模型对象中添加数据
        mav.addObject("msg","Spring MVC");
        //设计逻辑视图
        mav.setViewName("/WEB-INF/jsp/first.jsp");
        return mav;
    }
}
