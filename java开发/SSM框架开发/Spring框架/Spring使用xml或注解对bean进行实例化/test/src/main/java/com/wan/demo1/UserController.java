package com.wan.demo1;

import org.springframework.stereotype.Controller;

import javax.annotation.Resource;

@Controller("userControlle")//作用在控制层,用于将控制层的类标识为Spring中的Bean
public class UserController {
    //@Resource(name = "userService")//用于对Bean的属性变量，属性的set(),get()方法及构造方法进行标注,
    private UserServiceImpl UserServiceImpl;

    public void writer(){
        this.UserServiceImpl.writer();
        System.out.println("userController.........");
    }

    public void setUserService(UserServiceImpl userService) {
        this.UserServiceImpl = userService;
    }
}
