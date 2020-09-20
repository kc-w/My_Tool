package com.wan.Controller;

import com.wan.pojo.admin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import com.wan.service.adminService;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller//控制器注解
public class AdminControllerAjax {

    @Autowired//根据类型来查找和装配元素
    private adminService adminService;


    //用于登陆跳转
    @RequestMapping(value = "adminLogin")
    public String login(String number,String password, Model model){
        admin admin=adminService.login(number,password);
        if(admin!=null){
            model.addAttribute("admin",admin);
            return "WEB-INF/jsp/admin/console";
        }
        return "WEB-INF/jsp/admin/adminLogin";
    }



}
