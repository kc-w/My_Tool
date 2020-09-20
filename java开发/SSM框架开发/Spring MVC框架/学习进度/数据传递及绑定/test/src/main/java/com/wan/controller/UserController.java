package com.wan.controller;

import com.wan.POJO.Order;
import com.wan.POJO.User;
import com.wan.POJO.UserVO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;

//简单数据类型绑定
@Controller
public class UserController {
    @RequestMapping(value = "/select1")
    public String selectUser(HttpServletRequest request){
        String id=request.getParameter("id");
        System.out.println(id);
        return "success";
    }

    @RequestMapping(value = "/select2")
    public String selectUser(Integer id){
        System.out.println(id);
        return "success";
    }

    //前端参数名更改
    @RequestMapping(value = "/select3")
    public String selectUser1(@RequestParam(value="uid")Integer id){
        System.out.println(id);
        return "success";
    }

    //简单类型数据绑定
    @RequestMapping(value = "/toregister")
    public String toRegister(Model model){
        model.addAttribute("msg","注册");
        return "register";
    }

    @RequestMapping(value = "/register")
    public String Register(User user,Model model){
        model.addAttribute("user",user);
        return "success";
    }

    //pojo类型数据绑定
    @RequestMapping(value = "/toorder")
    public String order(){
        return "order";
    }

    @RequestMapping(value = "/order")
    public String orders(Order order,Model model){
        model.addAttribute("order",order);
        return "order";
    }

    //自定义类型数据绑定日期数据
    @RequestMapping("/mydate")
    public String mydate(Date date, Model model){
        model.addAttribute("date",date);
        return "success";
    }

    //复杂绑定:绑定数组
    @RequestMapping("/touser")
    public String seleteUser(){
        return "user";
    }
    @RequestMapping("/deleteuser")
    public String deleteUser(String[] id, Model model){
        String s="";
        if(id!=null){
            for (String i:id){
                s=s+i+" ";
            }
            model.addAttribute("msg","删除的了"+s);
        }else {
            model.addAttribute("msg","删除的数据为空");
        }
        return "user";
    }


    //复杂绑定:绑定集合
    @RequestMapping("/users")
    public String users(UserVO userVO,Model model){
        //将所有用户数据封装到集合中
        List<User> users=userVO.getUsers();
        String s="";
        for (User user:users){
            if(user.getId()!=null){
                s=s+"修改了id为"+user.getId()+"的姓名为"+user.getUsername()+"<br>";
            }
        }
        model.addAttribute("m",s);
        return "user";
    }


}
