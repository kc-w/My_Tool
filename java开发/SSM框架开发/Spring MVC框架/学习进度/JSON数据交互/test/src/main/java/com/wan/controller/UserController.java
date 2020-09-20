package com.wan.controller;

import com.wan.POJO.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class UserController {
    @RequestMapping("/testJson")
    @ResponseBody//用于返回对象,该注解用在方法上.
    public User testJson(@RequestBody User user){//用于将请求体中的数据绑定到方法的形参中
        System.out.println(user.getUsername());
        return user;
    }
}
