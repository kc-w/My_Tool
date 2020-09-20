package com.wan.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpSession;

@Controller//控制器注解,控制退出登陆
public class login_out {

    @RequestMapping(value = "/loginout.action")
    public String loginout(HttpSession httpSession){
        httpSession.invalidate();
        return "redirect:login.action";
    }

    @RequestMapping(value = "/login.action",method = RequestMethod.GET)
    public String toLogin(){
        return "WEB-INF/jsp/login";
    }
}
