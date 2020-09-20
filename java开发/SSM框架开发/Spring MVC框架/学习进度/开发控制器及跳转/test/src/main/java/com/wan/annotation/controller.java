package com.wan.annotation;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller//控制器注解
@RequestMapping(value="/all")//该控制器所有请求都会被映射到单个路径中
public class controller {

    @RequestMapping(value="/index")//只映射单个目标
    public ModelAndView handleRequest(javax.servlet.http.HttpServletRequest httpServletRequest, javax.servlet.http.HttpServletResponse httpServletResponse) throws Exception {
        //创建ModelAndView对象
        ModelAndView mav=new ModelAndView();
        //向模型对象中添加数据
        mav.addObject("msg","ModelAndView类型未能实现数据与视图之间的解耦");
        //设计逻辑视图
        mav.setViewName("/index.jsp");
        return mav;
    }
}
