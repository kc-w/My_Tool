package com.wan.annotation;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller//控制器注解
@RequestMapping(value="/web")//该控制器所有请求都会被映射到单个路径中
public class controller1 {

    @RequestMapping(value="/a",method = RequestMethod.GET )//只映射单个目标
    public String handle(HttpServletRequest request, HttpServletResponse response, Model model){
        //向模型中添加数据
        System.out.println("1111");
        model.addAttribute("msg","企业开发的主流用法");
        System.out.println("1111");
        return "first";
    }

    @RequestMapping(value="/b")
    //重定向
    public String redirect(HttpServletRequest request, HttpServletResponse response, Model model){
        model.addAttribute("msg","重定向完成");
        return "redirect:test";
    }

    @RequestMapping(value="/c")
    //请求转发
    public String forward(HttpServletRequest request, HttpServletResponse response, Model model){
        model.addAttribute("msg","请求转发完成");
        return "forward:test";
    }

    @RequestMapping(value="/test")
    public String test(){
        return "first";
    }
}
